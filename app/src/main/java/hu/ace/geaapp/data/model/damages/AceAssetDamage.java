package hu.ace.geaapp.data.model.damages;

import java.io.Serializable;

import hu.ace.geaapp.data.model.DamageCoord;
import hu.ace.geaapp.ui.view.Damage;
import hu.ace.geaapp.ui.view.inspection.CustomView;

//ASSET_GJK_KAR
public class AceAssetDamage implements Serializable {

    public static String SERIALIZABLE_NAME = "ASSET_VEHICLE_DAMAGE";

    private String driver;
    private String damageNr;
    private String damagePlace;
    private String damageTime;

    private String coordinateX;
    private String coordinateY;
    private String status; //category
    private String description; //damage note

    private String damageID;

    private CustomView customView;
    private DamageCoord damageCoordinate;
    private Damage damageEnum;


    public Damage getDamageEnum() {
        return damageEnum;
    }

    public void setDamageEnum(Damage damageEnum) {
        this.damageEnum = damageEnum;
    }

    public String getDamageID() {
        return damageID;
    }

    public void setDamageID(String damageID) {
        this.damageID = damageID;
    }

    public CustomView getCustomView() {
        return customView;
    }

    public void setCustomView(CustomView customView) {
        this.customView = customView;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDamageNr() {
        return damageNr;
    }

    public void setDamageNr(String damageNr) {
        this.damageNr = damageNr;
    }

    public String getDamagePlace() {
        return damagePlace;
    }

    public void setDamagePlace(String damagePlace) {
        this.damagePlace = damagePlace;
    }

    public String getDamageTime() {
        return damageTime;
    }

    public void setDamageTime(String damageTime) {
        this.damageTime = damageTime;
    }


    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DamageCoord getDamageCoordinate() {
        return damageCoordinate;
    }

    public void setDamageCoordinate(DamageCoord damageCoordinate) {
        this.damageCoordinate = damageCoordinate;
    }
}
