package io.hotkey.excelrefactor.model.searcher;

import io.hotkey.excelrefactor.model.util.StreamHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SearchFilter {

	public List<Sheet> filterWithSheetNames(final XSSFWorkbook workbook, final List<String> sheetNames){

    Stream<Sheet> sheetStream = StreamHelper.iteratorToFiniteStream(workbook.iterator());

		return sheetStream
				.filter(s -> isSheetNameEqualIgnoreCaseToAnyElementOf(s.getSheetName(), sheetNames))
				.collect(Collectors.toList());
	}

	private boolean isSheetNameEqualIgnoreCaseToAnyElementOf(final String sheetName, final List<String> sheetNamesToMatch){

		return sheetNamesToMatch.stream().anyMatch(s -> s.equalsIgnoreCase(sheetName));
	}

}
