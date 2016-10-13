package id.ac.unikom.codelabs.mvpweatherapp.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Bayu WPP on 10/13/2016.
 */

public class MathUtility {
    public static String getNoDecimal(float temp) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(temp));
        bigDecimal = bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("#,##0");
        return decimalFormat.format(bigDecimal);
    }
}
