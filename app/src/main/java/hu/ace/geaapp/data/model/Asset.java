package hu.ace.geaapp.data.model;

import java.io.Serializable;
import java.util.List;

import hu.ace.geaapp.data.model.damages.AceAssetDamage;

public class Asset implements Serializable {

    public static String SERIALIZABLE_NAME = "ASSET_VEHICLE";



    private String assetnum;
    private String assetuID;
    private String assetID;
    private String status;
    private String classStructureID;


    private AceAssetGJK aceAssetGJK;
    private List<AceAssetDamage> assetDamageList;
    private List<AceAssetVignette> assetVignetteList;
    private Accessories accessories; //tartozekok
    private StructFeatures structFeatures; //szerkezeti jellemzok

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AceAssetGJK getAceAssetGJK() {
        return aceAssetGJK;
    }

    public void setAceAssetGJK(AceAssetGJK aceAssetGJK) {
        this.aceAssetGJK = aceAssetGJK;
    }


    public String getAssetuID() {
        return assetuID;
    }

    public void setAssetuID(String assetuID) {
        this.assetuID = assetuID;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getClassStructureID() {
        return classStructureID;
    }

    public void setClassStructureID(String classStructureID) {
        this.classStructureID = classStructureID;
    }

    public List<AceAssetDamage> getAssetDamageList() {
        return assetDamageList;
    }

    public void setAssetDamageList(List<AceAssetDamage> assetDamageList) {
        this.assetDamageList = assetDamageList;
    }

    public List<AceAssetVignette> getAssetVignetteList() {
        return assetVignetteList;
    }

    public void setAssetVignetteList(List<AceAssetVignette> assetVignetteList) {
        this.assetVignetteList = assetVignetteList;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public StructFeatures getStructFeatures() {
        return structFeatures;
    }

    public void setStructFeatures(StructFeatures structFeatures) {
        this.structFeatures = structFeatures;
    }
}
