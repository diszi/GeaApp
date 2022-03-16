package hu.ace.geaapp.singleton;

import android.content.Context;
import android.content.SharedPreferences;

import hu.ace.geaapp.utils.UIConstans;

public class SettingsSingleton {

    private static final SettingsSingleton ourInstance = new SettingsSingleton();

    private Context context;
    private String fileConstant;
    private SharedPreferences sharedPreferences;

    private SettingsSingleton() {}

    public static SettingsSingleton getInstance() {
        return ourInstance;
    }

    public String getUserName(){
        return sharedPreferences.getString(UIConstans.LOGGED_IN_USER,null);
    }

    public void init(Context context, String userName) {

        this.context = context;
        this.fileConstant = UIConstans.APP_NAME + userName;
        sharedPreferences = context.getSharedPreferences(fileConstant, Context.MODE_PRIVATE);
        setSharedPreferences(sharedPreferences);
        System.out.println(" ---> logged username=" + userName);
        sharedPreferences.edit().putString(UIConstans.LOGGED_IN_USER, userName.toUpperCase()).commit();

    }


    public void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }


}
