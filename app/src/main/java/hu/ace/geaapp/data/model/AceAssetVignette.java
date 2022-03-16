package hu.ace.geaapp.data.model;

import java.io.Serializable;


//ACE_ASSET_GJK_AUTOP
public class AceAssetVignette implements Serializable {


    public static String SERIALIZABLE_NAME = "ASSET_VEHICLE_VIGNETTE";

    private String year;
    private String type;
    private String expirationDate;
    private String price;
    private boolean status; //true = matrica lejart, false=matrica ervenyes


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
