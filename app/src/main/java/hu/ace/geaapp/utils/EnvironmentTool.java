package hu.ace.geaapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hu.ace.geaapp.BuildConfig;

public class EnvironmentTool {


    //get app version
    public static String getVersionApp(){
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        return versionName;
    }


    public static String convertDate(Date date, String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String convertDateTimeStringHU(String createdDate){
        SimpleDateFormat inFormat = new SimpleDateFormat(UIConstans.DATETIME_PATTERN_STANDARD); //datePattern
        SimpleDateFormat outFormat = new SimpleDateFormat(UIConstans.DATETIME_PATTERN_HU);
        Date destDate  = null;
        try{
            destDate = inFormat.parse(createdDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outFormat.format(destDate);
    }


    public static double calculateDistance(double x1, double y1, double x2, double y2){
        //x1,y1 = coord of damage, x2,y2 = coord of onclick
        //System.out.println(" X1 = "+x1+" y1="+y1);
        double distance = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        //System.out.println(" DISTANCE = "+distance);
        return distance;
    }

       /* public static int dp2px(Resources resource, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,   dp,resource.getDisplayMetrics());
    }

    public static float px2dp(Resources resource, float px)  {
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,resource.getDisplayMetrics());
    }*/
}
