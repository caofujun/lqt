package com.nis.comm.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class g {
	public static String[][] a(File file, int ignoreRows, String sheetName) throws FileNotFoundException, IOException {
		ArrayList result = new ArrayList();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		HSSFSheet st = wb.getSheet(sheetName);

		for (int returnArray = ignoreRows; returnArray <= st.getLastRowNum(); ++returnArray) {
			HSSFRow i = st.getRow(returnArray);
			if (i != null) {
				int tempRowSize = i.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}

				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;

				for (short columnIndex = 0; columnIndex <= i.getLastCellNum(); ++columnIndex) {
					String value = "";
					cell = i.getCell(columnIndex);
					if (cell != null) {
						switch (cell.getCellType()) {
							case 0 :
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();
									if (date != null) {
										value = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
									} else {
										value = "";
									}
								} else {
									value = (new DecimalFormat("0")).format(cell.getNumericCellValue());
								}
								break;
							case 1 :
								value = cell.getStringCellValue();
								break;
							case 2 :
								if (!cell.getStringCellValue().equals("")) {
									value = cell.getStringCellValue();
								} else {
									value = String.valueOf(cell.getNumericCellValue());
								}
							case 3 :
								break;
							case 4 :
								value = cell.getBooleanCellValue() ? "Y" : "N";
								break;
							case 5 :
								value = "";
								break;
							default :
								value = "";
						}
					}

					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}

					values[columnIndex] = al(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}

		in.close();
		String[][] arg17 = new String[result.size()][rowSize];

		for (int arg18 = 0; arg18 < arg17.length; ++arg18) {
			arg17[arg18] = (String[]) result.get(arg18);
		}

		return arg17;
	}

	public static String[][] a(File file, int ignoreRows) throws FileNotFoundException, IOException {
		ArrayList result = new ArrayList();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;

		for (int returnArray = 0; returnArray < wb.getNumberOfSheets(); ++returnArray) {
			HSSFSheet i = wb.getSheetAt(returnArray);

			for (int rowIndex = ignoreRows; rowIndex <= i.getLastRowNum(); ++rowIndex) {
				HSSFRow row = i.getRow(rowIndex);
				if (row != null) {
					int tempRowSize = row.getLastCellNum() + 1;
					if (tempRowSize > rowSize) {
						rowSize = tempRowSize;
					}

					String[] values = new String[rowSize];
					Arrays.fill(values, "");
					boolean hasValue = false;

					for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); ++columnIndex) {
						String value = "";
						cell = row.getCell(columnIndex);
						if (cell != null) {
							switch (cell.getCellType()) {
								case 0 :
									if (HSSFDateUtil.isCellDateFormatted(cell)) {
										Date date = cell.getDateCellValue();
										if (date != null) {
											value = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
										} else {
											value = "";
										}
									} else {
										value = (new DecimalFormat("0")).format(cell.getNumericCellValue());
									}
									break;
								case 1 :
									value = cell.getStringCellValue();
									break;
								case 2 :
									if (!cell.getStringCellValue().equals("")) {
										value = cell.getStringCellValue();
									} else {
										value = String.valueOf(cell.getNumericCellValue());
									}
								case 3 :
									break;
								case 4 :
									value = cell.getBooleanCellValue() ? "Y" : "N";
									break;
								case 5 :
									value = "";
									break;
								default :
									value = "";
							}
						}

						if (columnIndex == 0 && value.trim().equals("")) {
							break;
						}

						values[columnIndex] = al(value);
						hasValue = true;
					}

					if (hasValue) {
						result.add(values);
					}
				}
			}
		}

		in.close();
		String[][] arg17 = new String[result.size()][rowSize];

		for (int arg18 = 0; arg18 < arg17.length; ++arg18) {
			arg17[arg18] = (String[]) result.get(arg18);
		}

		return arg17;
	}

	public static String al(String str) {
		if (str == null) {
			return "";
		} else {
			int length = str.length();

			for (int i = length - 1; i >= 0 && str.charAt(i) == 32; --i) {
				--length;
			}

			return str.substring(0, length);
		}
	}

	public static HSSFCellStyle a(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontName("黑体");
		boldFont.setFontHeightInPoints((short)18);
		boldFont.setBoldweight((short)700);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short)2);
		style.setVerticalAlignment((short)1);
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	public static HSSFCellStyle b(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontName("黑体");
		boldFont.setFontHeightInPoints((short)14);
		boldFont.setBoldweight((short)300);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short)2);
		style.setVerticalAlignment((short)1);
		style.setFont(boldFont);
		style.setBorderTop((short)0);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	public static HSSFCellStyle c(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short)200);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short)2);
		style.setVerticalAlignment((short)1);
		style.setFont(boldFont);
		style.setWrapText(true);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	public static void a(HSSFRow row, int column, HSSFCellStyle style, int cellType, Object value) {
		HSSFCell cell = row.createCell(column);
		if (style != null) {
			cell.setCellStyle(style);
		}

		switch (cellType) {
			case 0 :
				cell.setCellType(0);
				if (value == null) {
					value = Character.valueOf('0');
				}

				cell.setCellValue(Double.parseDouble(value.toString()));
				break;
			case 1 :
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			case 2 :
			case 3 :
		}

	}

	public static HSSFWorkbook a(String fileName, String[][] tableTitle, String[][] tableData, int[][] marginColumns) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("数据");
		sheet.setDefaultColumnWidth(20);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment((short)2);
		cellStyle.setVerticalAlignment((short)1);
		sheet = a(sheet, fileName, tableTitle, marginColumns, cellStyle);
		a(tableTitle.length + 2, sheet, tableData, cellStyle);
		return wb;
	}

	public static HSSFSheet a(HSSFSheet sheet, String fileName, String[][] tableTitle, int[][] marginColumns,
			HSSFCellStyle cellStyle) {
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(fileName);
		titleCell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableTitle[0].length - 1));
		HSSFRow condRow = sheet.createRow(1);
		int o;
		if (tableTitle != null) {
			for (o = 0; o < tableTitle.length; ++o) {
				HSSFRow row = sheet.createRow(o + 2);

				for (int j = 0; j < tableTitle[o].length; ++j) {
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(tableTitle[o][j]);
					cell.setCellStyle(cellStyle);
				}
			}
		}

		if (marginColumns != null) {
			for (o = 0; o < marginColumns.length; ++o) {
				sheet.addMergedRegion(new CellRangeAddress(marginColumns[o][0] + 2, marginColumns[o][1] + 2,
						marginColumns[o][2], marginColumns[o][3]));
			}
		}

		return sheet;
	}

	public static HSSFSheet a(int startRow, HSSFSheet sheet, String[][] tableData, HSSFCellStyle cellStyle) {
		for (int i = 0; i < tableData.length; ++i) {
			HSSFRow row = sheet.createRow(i + startRow);

			for (int j = 0; j < tableData[i].length; ++j) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(tableData[i][j]);
				cell.setCellStyle(cellStyle);
			}
		}

		return sheet;
	}
}