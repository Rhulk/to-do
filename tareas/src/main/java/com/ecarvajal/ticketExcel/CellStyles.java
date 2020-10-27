package com.ecarvajal.ticketExcel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/*
    Utility Class for Spreadsheet Cell Styling
 */

public class CellStyles {

public static CellStyle getTableHeaderStyle(Workbook workbook){
    CellStyle style = workbook.createCellStyle();

    Font font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    font.setFontName("Times New Roman");
    style.setWrapText(true);
    style.setFont(font);
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setLocked(true);

    return style;
}

public static CellStyle getShadedCellStyle(Workbook workbook, HSSFColor foreground, HSSFColor background ){
    CellStyle style = workbook.createCellStyle();

    Font font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    font.setFontName("Times New Roman");
    style.setFont(font);

    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setFillForegroundColor(foreground.getIndex());
    style.setFillBackgroundColor(background.getIndex());
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBorderTop(CellStyle.BORDER_THIN);
    style.setBorderLeft(CellStyle.BORDER_THIN);
    style.setBorderRight(CellStyle.BORDER_THIN);
    style.setWrapText(true);
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setLocked(true);

    return style;
}

public static CellStyle getColoredTextCellStyle(Workbook workbook, HSSFColor color){
    CellStyle style = workbook.createCellStyle();

    Font font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    font.setFontName("Times New Roman");
    style.setFont(font);
    style.setFillBackgroundColor(color.getIndex());
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setWrapText(true);
    font.setColor(color.getIndex());

    return style;
}
}
