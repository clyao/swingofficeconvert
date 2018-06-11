package com.clyao.officeconvert.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;
import com.clyao.officeconvert.license.OfficeConvertLicense;

/**   
* @Title: Word转图片工具类
* @Description: Word转图片工具类
* @author clyao   
* @date 2018-06-07 15:00 
* @version V1.0   
*/
public class WordConvertToImage {
	
	/**
	 * Word转图片方法
	 * @param selectFilePath 选择文件的路径
	 * @param convertFile 转换文件的路径
	 * @param saveFilePath 保存转换文件路径
	 * @param selectFileName 选择文件的文件名
	 * @return 转换结果
	 */
	public static boolean convertWord(String selectFilePath, File convertFile, String saveFilePath, String selectFileName){
		// 验证License
		if(OfficeConvertLicense.getLicense()){
			try {
				long old = System.currentTimeMillis();
	            Document document = new Document(new FileInputStream(selectFilePath));
	            ImageSaveOptions imageSaveOptions = new ImageSaveOptions(SaveFormat.JPEG);
	            imageSaveOptions.setResolution(300);
	            FileOutputStream fileOutputStream = null;
	            for(int i = 0; i < document.getPageCount(); i++){
	            	convertFile = new File(saveFilePath + "\\" + selectFileName.split("\\.")[0] + i + ".jpg");
		            fileOutputStream = new FileOutputStream(convertFile);
		            imageSaveOptions.setPageIndex(i);
		            document.save(fileOutputStream, imageSaveOptions);
	            }
	            fileOutputStream.close();
	            int selection = JOptionPane.showConfirmDialog(null, "是否打开文件所在目录", "转换后操作提示", JOptionPane.YES_NO_OPTION);
				if(selection == JOptionPane.OK_OPTION){
					try {
						//打开转换后的文件夹并选择该文件
						Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL Explorer.exe /select," + convertFile.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	            long now = System.currentTimeMillis();
	            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n");
	            return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}else{
			JOptionPane.showMessageDialog(null, "请选择文件和保存目录后\n再执行开始转换按钮进行转换", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
