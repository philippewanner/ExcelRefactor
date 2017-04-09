package io.hotkey.excelrefactor.model.searcher;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SearchContext {

	private List<Sheet> matchingSheets;
	private XSSFWorkbook workbook;
	
	public SearchContext(final XSSFWorkbook book){
		this(book, null);
	}
	
	public SearchContext(final XSSFWorkbook book, final List<String> sheetNamesToRetain){
		workbook = book;
		if(sheetNamesToRetain != null){
			SearchFilter filter = new SearchFilter();
			matchingSheets = filter.filterWithSheetNames(book, sheetNamesToRetain);			
		}
	}
	
	public List<Sheet> getMatchingSheets() {
		return matchingSheets;
	}
	
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}	
}
