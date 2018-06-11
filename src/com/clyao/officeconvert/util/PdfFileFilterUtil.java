package com.clyao.officeconvert.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**   
* @Title: Pdf文件选择过滤工具类
* @Description: Pdf文件选择过滤工具类
* @author clyao   
* @date 2018-06-07 15:00 
* @version V1.0   
*/
public class PdfFileFilterUtil extends FileFilter {

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		return file.isDirectory() || name.toLowerCase().endsWith(".pdf");
	}

	@Override
	public String getDescription() {
		return "*.pdf";
	}
	
	

}
