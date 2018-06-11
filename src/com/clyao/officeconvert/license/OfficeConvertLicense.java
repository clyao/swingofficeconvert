package com.clyao.officeconvert.license;

import com.aspose.words.License;

public class OfficeConvertLicense {
	
	/**
     * 获取license
     * 
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            License aposeLic = new License();
            aposeLic.setLicense(OfficeConvertLicense.class.getResourceAsStream("/license.xml"));
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
