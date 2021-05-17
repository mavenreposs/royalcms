package io.github.mavenreposs.royalcms.component.upload;

import mavenreposs.php.functions.strtotime.DateFormatter;

import java.io.File;

import static mavenreposs.php.functions.PHPFunctions.*;

public class UploadSubDirRule implements SubDirnameRule {
    @Override
    public String apply(String s) {
        // Generate the yearly and monthly dirs
        String time = date(DateFormatter.FourDigitYearMonthWithSlashes.getFormat(), time());

        return time.replace('/', File.separatorChar) + File.separator;
    }
}
