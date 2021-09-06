package cn.royalcms.component.upload;

import mavenreposs.php.functions.strtotime.DateFormatter;

import static mavenreposs.php.functions.PHPFunctions.*;

public class RandomFilenameRule implements FilenameRule {

    @Override
    public String apply(String s) {
        int random = rand(1000, 10000);
        return date(DateFormatter.EightDigitYearMonthDayWithTime.getFormat(), time()) + random;
    }
}
