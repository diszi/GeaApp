package hu.ace.geaapp.data.remote;

import java.io.InputStream;
import java.util.Date;

import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.DamageTemp;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.singleton.SettingsSingleton;
import hu.ace.geaapp.utils.EnvironmentTool;
import hu.ace.geaapp.utils.UIConstans;
import io.reactivex.ObservableEmitter;

public class AddDamageSOAP <T extends String> extends  AbstractSOAP<T>{

    private String damageID;

    private String assetID;
    private String assetUID;
    private String assetNum;
    private String assetDescription;
    private String assetStatus;

    private String damageType; // status
    private String damageX;
    private String damageY;
    private String damageNote; //description;
    private String damageDriver;
    private String damagePlace;

    private String damageDate;

    public AddDamageSOAP(AceAssetDamage damageAdded, Asset asset){
        this.assetID = asset.getAssetID();
        this.assetUID = asset.getAssetuID();
        this.assetNum =asset.getAssetnum();
        this.assetStatus = asset.getStatus();

//        this.damageType = damage.getDamageType().getDamageType();
//        this.damageNote = damage.getNote();
//        this.damageX = String.valueOf(damage.getCoordinateX());
//        this.damageY = String.valueOf(damage.getCoordinateY());

        this.damageType = damageAdded.getStatus();
        this.damageNote = damageAdded.getDescription();
        this.damageX = damageAdded.getCoordinateX();
        this.damageY = damageAdded.getCoordinateY();

        this.damageDate = EnvironmentTool.convertDate(new Date(), UIConstans.DATETIME_PATTERN_STANDARD);
    }

    @Override
    protected void onSucces(InputStream inputStream, ObservableEmitter<T> emitter)  {
        emitter.onNext((T)assetID);
        emitter.onComplete();
    }

    @Override
    protected String getSOAPURL() {
        return Urls.SOAP_CREATE_DAMAGE;
    }

    @Override
    protected String getSOAPPayload() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:max=\"http://www.ibm.com/maximo\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <max:UpdateACE_GEA_ASSET creationDateTime=\"\" baseLanguage=\" EN\" transLanguage=\"EN \" messageID=\"\" maximoVersion=\"\">\n" +
                "         <max:ACE_GEA_ASSETSet>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <max:ASSET action=\"Change\"  transLanguage=\"EN\">\n" +
                "               <max:MAXINTERRORMSG> </max:MAXINTERRORMSG>\n" +
                "               <max:ASSETID>"+assetID+"</max:ASSETID>\n" +
                "               <max:ASSETNUM>"+assetNum+"</max:ASSETNUM>\n" +
                "               <max:ASSETUID>"+assetUID+"</max:ASSETUID>\n" +
                "               <max:CLASSSTRUCTUREID>1053</max:CLASSSTRUCTUREID>\n" +
                "               <max:DESCRIPTION></max:DESCRIPTION>\n" +
                "               <max:ORGID>ACE</max:ORGID>\n" +
                "               <max:SITEID>BUDAPEST</max:SITEID>\n" +
                "               <max:STATUS>"+assetStatus+"</max:STATUS>\n" +
                "               <max:ASSET_GJK_KAR action=\"Add\">\n" +
                "                  <max:DESCRIPTION>"+damageNote+"</max:DESCRIPTION>\n" +
                "                  <max:GJKVEZ>"+SettingsSingleton.getInstance().getUserName()+"</max:GJKVEZ>\n" +
                "                  <max:GJK_KARHELY></max:GJK_KARHELY>\n" +
                "                  <max:GJK_KARIDO>"+damageDate+"</max:GJK_KARIDO>\n" +
                "                  <max:GJK_KARSZAM></max:GJK_KARSZAM>\n" +
                "                  <max:PERSONID>"+ SettingsSingleton.getInstance().getUserName() +"</max:PERSONID>\n"+
                "                  <max:STATUS>"+damageType+"</max:STATUS>\n" +
                "                  <max:X>"+damageX+"</max:X>\n" +
                "                  <max:Y>"+damageY+"</max:Y>\n" +
                "               </max:ASSET_GJK_KAR>\n" +
                "            </max:ASSET>\n" +
                "         </max:ACE_GEA_ASSETSet>\n" +
                "      </max:UpdateACE_GEA_ASSET>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
