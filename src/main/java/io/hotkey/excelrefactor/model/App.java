package io.hotkey.excelrefactor.model;

import org.apache.log4j.Logger;

import java.util.*;

import java.io.File;

public class App {

  private static final String FOLDER_PATH = "/Users/philippewanner/temp";
  private static final List<String> SHEETNAME_CONSTRAINTS = Arrays.asList("sheet1");
  private static Map<String, String> findReplaceElements = new HashMap<>();
  private static final Logger LOGGER = Logger.getLogger(App.class);

  static {
    findReplaceElements.put("zugfahrtId", "a");
  };

  public static void main(final String[] args) {

    File folder = new File(FOLDER_PATH);
    List<File> excelFiles = new ArrayList<>();
    if(folder.isDirectory()) {
      File[] files = folder.listFiles(f -> f.isFile());
      if(0 != files.length) {
        excelFiles = Arrays.asList(files);
      } else {
          LOGGER.error("Empty files list");
      }
    } else {
        LOGGER.error("Not a directory");
    }

    ExcelRefactor refactor = new ExcelRefactor(excelFiles, SHEETNAME_CONSTRAINTS);
    refactor.renameStringCellContent(findReplaceElements);
  }
}
