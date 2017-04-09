package io.hotkey.excelrefactor.model;

import io.hotkey.excelrefactor.model.searcher.SearchContext;
import io.hotkey.excelrefactor.model.util.FileAcessHelper;
import io.hotkey.excelrefactor.model.util.StreamHelper;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelRefactor {

  private static final Logger LOGGER = Logger.getLogger(ExcelRefactor.class);

  private final List<File> files;
  private final List<String> sheetNameConstraints;

  private static int replacementCounter = 0;

  public ExcelRefactor(final File excelFile) {
    this(Arrays.asList(excelFile), null);
  }

  public ExcelRefactor(final List<File> excelFiles, final List<String> sheetNameConstraints) {
    this.files = excelFiles;
    this.sheetNameConstraints = sheetNameConstraints;
  }

  public void renameStringCellContent(final Map<String, String> findReplaceElements) {

    LOGGER.info("Renaming started: searching " + findReplaceElements.size() + //
        " elements in " + files.size() + " Excel files.");

    for (File file : files) {

      try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

        SearchContext ctx = new SearchContext(workbook, sheetNameConstraints);
        if (ctx.getMatchingSheets().size() == 0) {
          LOGGER.warn("No matching sheet was found in " + file.getAbsolutePath());
        }
        else {

          Set<String> findElements = findReplaceElements.keySet();
          List<Cell> cellFounds = this.findStringTypeCell(findElements, ctx);
          if (cellFounds != null && cellFounds.size() > 0) {
            replaceStringTypeCell(cellFounds, findReplaceElements);
            replacementCounter += cellFounds.size();
            FileAcessHelper.writeWorkbookInFile(ctx.getWorkbook(), file);
            LOGGER.debug("Replaced " + cellFounds.size() + " elements in " + file.getName());
          }
        }

      }
      catch (FileNotFoundException fe) {
        LOGGER.error("File " + file.getName() + " not found.", fe);
      }
      catch (IOException ie) {
        LOGGER.error("File operation failure.", ie);
      }
    }

    LOGGER.info("Renaming done. [" + replacementCounter + " cell content(s) replaced]");
  }

  private List<Cell> findStringTypeCell(final Set<String> findElements, final SearchContext ctx) {

    List<Cell> result = null;

    for (Sheet sheet : ctx.getMatchingSheets()) {

      Iterator<Row> rowIterator = sheet.iterator();
      result = StreamHelper.iteratorToFiniteStream(rowIterator).flatMap(r -> StreamHelper.iteratorToFiniteStream(r.cellIterator()))
          .filter(c -> c.getCellType() == Cell.CELL_TYPE_STRING).filter(c -> findElements.contains(c.getStringCellValue()))
          .collect(Collectors.toList());
    }

    return result;
  }

  private void replaceStringTypeCell(final List<Cell> cells, final Map<String, String> findReplaceElements) {

    for (Cell cell : cells) {
      String oldContent = cell.getStringCellValue();
      String replacementContent = findReplaceElements.get(oldContent);

      cell.setCellValue(replacementContent);
    }
  }
}
