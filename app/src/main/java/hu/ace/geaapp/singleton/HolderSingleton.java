package hu.ace.geaapp.singleton;

import android.content.Context;

import hu.ace.geaapp.data.model.Asset;

public class HolderSingleton {

    private static final HolderSingleton ourInstance = new HolderSingleton();
    private Context context;
    private String authBase64=null;
    private String serverIPaddress=null;

    private String userFrom; //átadó
    private String userTo; //átvevő


    private Asset vehicleAsset;



    public static HolderSingleton getInstance() {
        return ourInstance;
    }

    private HolderSingleton() {}


    public void setContext(Context context) {
        this.context = context;
    }

    public void setAuthBase64(String base64Data){
        this.authBase64 = base64Data;
    }

    public String getAuthBase64(){
        return this.authBase64;
    }

    public String getServerIPaddress() {
        return serverIPaddress;
    }

    public void setServerIPaddress(String serverIPaddress) {
        this.serverIPaddress = serverIPaddress;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }


    public Asset getVehicleAsset() {
        return vehicleAsset;
    }

    public void setVehicleAsset(Asset vehicleAsset) {
        this.vehicleAsset = vehicleAsset;
    }
}
