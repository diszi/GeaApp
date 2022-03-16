package hu.ace.geaapp.data.model;

import java.io.Serializable;

//ACE_ASSET_GJK
public class AceAssetGJK implements Serializable {

    public static String SERIALIZABLE_NAME = "ASSET_VEHICLE_DETAILS";

    private String licensePlate; //RDSZ
    private String type; //TYPE
    private String fuel; //UZEMANYAG
    private String year; //EV
    private String financing; //FINANSZIROZAS
    private String owner; //TULAJDONOS
    private String username; //FELHASZN
    private String licenser; //HASZNENG
    private String cm3; //CM3
    private String trafficLicenseNumber; //FORGENG


    private String vignette; //AUTOP
    private String tireSize; //GUMIM
    private String nextOilChange; //KSZERVIZ
    private String kmHour; //KMORA
    private String inventoryNumber; //BARCODEID
    private String entryValidity; //BELEPENG
    private String trafficLicenseValidity; //FORGERV
    private String status; //STATUS
    private String motorNumber; //MOTORSZAM


    //aktualis km ora - ACE_KMORA
    private String vehicleIDnumber; //ALVSZAM
    //autopalya matrica - AUTOP
    private String currentKMdate;//CURRENT_KMDATE
    //GUMIM
    private String costCenter; //KTG



    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLicenser() {
        return licenser;
    }

    public void setLicenser(String licenser) {
        this.licenser = licenser;
    }

    public String getCm3() {
        return cm3;
    }

    public void setCm3(String cm3) {
        this.cm3 = cm3;
    }

    public String getTrafficLicenseNumber() {
        return trafficLicenseNumber;
    }

    public void setTrafficLicenseNumber(String trafficLicenseNumber) {
        this.trafficLicenseNumber = trafficLicenseNumber;
    }

    public String getVehicleIDnumber() {
        return vehicleIDnumber;
    }

    public void setVehicleIDnumber(String vehicleIDnumber) {
        this.vehicleIDnumber = vehicleIDnumber;
    }

    public String getVignette() {
        return vignette;
    }

    public void setVignette(String vignette) {
        this.vignette = vignette;
    }

    public String getTireSize() {
        return tireSize;
    }

    public void setTireSize(String tireSize) {
        this.tireSize = tireSize;
    }

    public String getNextOilChange() {
        return nextOilChange;
    }

    public void setNextOilChange(String nextOilChange) {
        this.nextOilChange = nextOilChange;
    }

    public String getKmHour() {
        return kmHour;
    }

    public void setKmHour(String kmHour) {
        this.kmHour = kmHour;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getEntryValidity() {
        return entryValidity;
    }

    public void setEntryValidity(String entryValidity) {
        this.entryValidity = entryValidity;
    }

    public String getTrafficLicenseValidity() {
        return trafficLicenseValidity;
    }

    public void setTrafficLicenseValidity(String trafficLicenseValidity) {
        this.trafficLicenseValidity = trafficLicenseValidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        this.motorNumber = motorNumber;
    }

    public String getCurrentKMdate() {
        return currentKMdate;
    }

    public void setCurrentKMdate(String currentKMdate) {
        this.currentKMdate = currentKMdate;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }
}
