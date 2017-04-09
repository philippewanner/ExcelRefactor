package io.hotkey.excelrefactor.model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

public class FileAcessHelper {
	
	private static final Logger LOGGER = Logger.getLogger(FileAcessHelper.class);
	
	public static void writeWorkbookInFile(final Workbook workbook, final File file) {

		try (FileOutputStream fos = new FileOutputStream(file)) {

			workbook.write(fos);

		} catch (IOException e) {
			LOGGER.error("Error at writing workbook into file: " + file.getAbsolutePath(), e);
		}
	}
}
