package hu.ace.geaapp.data.remote;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.Accessories;
import hu.ace.geaapp.data.model.AccessoriesTemp;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.singleton.SettingsSingleton;
import hu.ace.geaapp.utils.EnvironmentTool;
import hu.ace.geaapp.utils.UIConstans;
import io.reactivex.ObservableEmitter;

public class UpdateAccessoriesSOAP <T extends String> extends  AbstractSOAP<T>{


    private String assetnum;
    private String assetID;
    private String assetStatus;
    private String assetuID;

    //private Accessories accessories;

    private AccessoriesTemp accessories;

   /* public UpdateAccessoriesSOAP(Asset asset, Accessories accessories){

        this.assetnum = asset.getAssetnum();
        this.assetID = asset.getAssetID();
        this.assetStatus = asset.getStatus();
        this.assetuID = asset.getAssetuID();

        this.accessories = accessories;
    }*/


    public UpdateAccessoriesSOAP(AccessoriesTemp accessories){
        Asset asset = HolderSingleton.getInstance().getVehicleAsset();
        this.assetnum = asset.getAssetnum();
        this.assetID = asset.getAssetID();
        this.assetStatus = asset.getStatus();
        this.assetuID = asset.getAssetuID();

        this.accessories = accessories;
    }

    @Override
    protected void onSucces(InputStream inputStream, ObservableEmitter<T> emitter) throws IOException, SAXException, ParserConfigurationException {
        emitter.onNext((T)assetID);
        emitter.onComplete();
    }

    @Override
    protected String getSOAPURL() {
        return Urls.SOAP_UPDATE_ACCESSORIES;
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
                "               <max:ASSETNUM>"+assetnum+"</max:ASSETNUM>\n" +
                "               <max:ASSETUID>"+assetuID+"</max:ASSETUID>\n" +
                "               <max:CLASSSTRUCTUREID>1053</max:CLASSSTRUCTUREID>\n" +
                "               <max:ORGID>ACE</max:ORGID>\n" +
                "               <max:SITEID>BUDAPEST</max:SITEID>\n" +
                "               <max:STATUS>"+assetStatus+"</max:STATUS>\n" +
                "                   <max:ACE_GJK_TART action=\"AddChange\">\n" +
                "                       <max:ACE_GJK_TARTID>"+accessories.getAccessoriesID()+"</max:ACE_GJK_TARTID>\n" +
                "                       <max:ALTALANOS>"+accessories.getAltalanos()+"</max:ALTALANOS>\n" +
                "                       <max:ANTENNA>"+accessories.isAntenna()+"</max:ANTENNA>\n" +
                "                       <max:ASSISTANCE>"+accessories.isAssistanceInfo()+"</max:ASSISTANCE>\n" +
                "                       <max:AUTOPALYAM>"+accessories.isAutopalyamatrica()+"</max:AUTOPALYAM>\n" +
                "                       <max:CHDATE>"+ EnvironmentTool.convertDate(new Date(), UIConstans.DATETIME_PATTERN_STANDARD) +"</max:CHDATE>\n" +
                "                       <max:DESCRIPTION>"+accessories.getDescription()+"</max:DESCRIPTION>\n" +
                "                       <max:ELAKADASJ>"+accessories.isElakadasjelzo()+"</max:ELAKADASJ>\n" +
                "                       <max:EMELO>"+accessories.isEmelo()+"</max:EMELO>\n" +
                "                       <max:FLOTTAK>"+accessories.isFlotta()+"</max:FLOTTAK>\n" +
                "                       <max:FORGALMIE>"+accessories.isForgalmiEngedely()+"</max:FORGALMIE>\n" +
                "                       <max:GUMISZONYEG>"+accessories.getGumiszonyegNR()+"</max:GUMISZONYEG>\n" +
                "                       <max:IZZOKESZLET>"+accessories.isIzzokeszlet()+"</max:IZZOKESZLET>\n" +
                "                   <max:KEREKKULCS>"+accessories.isKerekkulcs()+"</max:KEREKKULCS>\n" +
                "\t\t\t\t        <max:KEREKOR>"+accessories.isKerekorkulccsal()+"</max:KEREKOR>\n" +
                "\t\t\t\t        <max:KERESZTLECEK>"+accessories.isKeresztlecek()+"</max:KERESZTLECEK>\n" +
                "\t\t\t\t        <max:KEZELESIU>"+accessories.isKezelesiUtmutato()+"</max:KEZELESIU>\n" +
                "\t\t\t\t        <max:KGFB>"+accessories.isIgazolasKGFB()+"</max:KGFB>\n" +
                "\t\t\t\t        <max:LATHATOSAGI>"+accessories.getLathatosagiMellenyNR()+"</max:LATHATOSAGI>\n" +
                "\t\t\t\t        <max:MENTODOBOZ>"+accessories.isMentodoboz()+"</max:MENTODOBOZ>\n" +
                "\t\t\t\t        <max:MOBILP>"+accessories.isMobilparkolas()+"</max:MOBILP>\n" +
                "\t\t\t\t        <max:NOTES>"+accessories.getNotes()+"</max:NOTES>\n" +
                "\t\t\t\t        <max:NY_ACELF>"+accessories.getGumiNyariAcelFelninNR()+"</max:NY_ACELF>\n" +
                "\t\t\t\t        <max:NY_FELNIN>"+accessories.getGumiNyariFelniNelkulNR()+"</max:NY_FELNIN>\n" +
                "\t\t\t\t        <max:NY_KONNYUF>"+accessories.getGumiNyariKonnyufelFelninNR()+"</max:NY_KONNYUF>\n" +
                "\t\t\t\t        <max:PERSONID>"+ SettingsSingleton.getInstance().getUserName() +"</max:PERSONID>\n" +
                "\t\t\t\t        <max:POTKEREK>"+accessories.isPotkerek()+"</max:POTKEREK>\n" +
                "\t\t\t\t        <max:RADIOK>"+accessories.isRadiokod()+"</max:RADIOK>\n" +
                "\t\t\t\t        <max:RIASZTO>"+accessories.getRiasztoTaviranyitoNR()+"</max:RIASZTO>\n" +
                "\t\t\t\t        <max:SZERVIZKONYV>"+accessories.isSzervizkonyv()+"</max:SZERVIZKONYV>\n" +
//                "\t\t\t\t        <max:TORZSKONYV>"+accessories()+"</max:TORZSKONYV>\n" +
                "\t\t\t\t        <max:T_ACELF>"+accessories.getGumiTeliAcelFelninNR()+"</max:T_ACELF>\n" +
                "\t\t\t\t        <max:T_FELNIN>"+accessories.getGumiTeliFelniNelkulNR()+"</max:T_FELNIN>\n" +
                "\t\t\t\t        <max:T_KONNYUF>"+accessories.getGumiTeliKonnyufelFelninNR()+"</max:T_KONNYUF>\n" +
                "\t\t\t\t        <max:UZEMANYAGK>"+accessories.isUzemeanyagkartya()+"</max:UZEMANYAGK>\n" +
                "\t\t\t\t        <max:VONOHOROG>"+accessories.isVonohorog()+"</max:VONOHOROG>\n" +
                "\t\t\t\t        <max:VONOSZEM>"+accessories.isVonoszem()+"</max:VONOSZEM>\n" +
                "     \t\t </max:ACE_GJK_TART>\n"+
                "            </max:ASSET>\n" +
                "         </max:ACE_GEA_ASSETSet>\n" +
                "      </max:UpdateACE_GEA_ASSET>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
