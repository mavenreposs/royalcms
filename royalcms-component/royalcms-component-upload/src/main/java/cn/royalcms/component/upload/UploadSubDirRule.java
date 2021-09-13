package cn.royalcms.component.upload;

import io.github.mavenreposs.php.functions.strtotime.DateFormatter;

import java.io.File;

import static io.github.mavenreposs.php.functions.PHPFunctions.date;
import static io.github.mavenreposs.php.functions.PHPFunctions.time;

public class UploadSubDirRule implements SubDirnameRule {
    @Override
    public String apply(String s) {
        // Generate the yearly and monthly dirs
        String time = date(DateFormatter.FourDigitYearMonthWithSlashes.getFormat(), time());

        return time.replace('/', File.separatorChar) + File.separator;
    }
}
