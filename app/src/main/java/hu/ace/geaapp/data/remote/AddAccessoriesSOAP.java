package hu.ace.geaapp.data.remote;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.Asset;
import io.reactivex.ObservableEmitter;

public class AddAccessoriesSOAP <T extends String> extends  AbstractSOAP<T>{

    private String assetnumber;
    private String assetID;
    private String assetUID;
    private String assetStatus;

    public AddAccessoriesSOAP(Asset asset){
        this.assetnumber = asset.getAssetnum();
        this.assetID = asset.getAssetID();
        this.assetUID = asset.getAssetuID();
        this.assetStatus = asset.getStatus();


      //  System.out.println(" ASSET = "+assetnumber);
    }


    @Override
    protected void onSucces(InputStream inputStream, ObservableEmitter<T> emitter) throws IOException, SAXException, ParserConfigurationException {
        emitter.onNext((T)assetID);
        emitter.onComplete();
    }

    @Override
    protected String getSOAPURL() {
        return Urls.SOAP_CREATE_ACCESSORIES;
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
                "               <max:ASSETNUM>"+assetnumber+"</max:ASSETNUM>\n" +
                "               <max:ASSETUID>"+assetUID+"</max:ASSETUID>\n" +
                "               <max:CLASSSTRUCTUREID>1053</max:CLASSSTRUCTUREID>\n" +
                "               <max:DESCRIPTION></max:DESCRIPTION>\n" +
                "               <max:ORGID>ACE</max:ORGID>\n" +
                "               <max:SITEID>BUDAPEST</max:SITEID>\n" +
                "               <max:STATUS>"+assetStatus+"</max:STATUS>\n" +
                "               <max:ACE_GJK_TART action=\"Add\">\n" +
             //   "        <max:ACE_GJK_TARTID>10</max:ACE_GJK_TARTID>\n" +
                "        <max:ALTALANOS>1</max:ALTALANOS>\n" +
                "        <max:ANTENNA>0</max:ANTENNA>\n" +
                "        <max:ASSISTANCE>1</max:ASSISTANCE>\n" +
                "        <max:AUTOPALYAM>0</max:AUTOPALYAM>\n" +
                //"        <max:CHDATE >2019-05-22T16:54:41</max:CHDATE>\n" +
               // "        <max:DESCRIPTION>string</max:DESCRIPTION>\n" +
                "        <max:ELAKADASJ>0</max:ELAKADASJ>\n" +
                "        <max:EMELO>0</max:EMELO>\n" +
                "        <max:FLOTTAK>0</max:FLOTTAK>\n" +
                "        <max:FORGALMIE>1</max:FORGALMIE>\n" +
                "        <max:GUMISZONYEG>0</max:GUMISZONYEG>\n" +
                "        <max:IZZOKESZLET>0</max:IZZOKESZLET>\n" +
                "        <max:KEREKKULCS>0</max:KEREKKULCS>\n" +
                "        <max:KEREKOR>1</max:KEREKOR>\n" +
                "        <max:KERESZTLECEK>1</max:KERESZTLECEK>\n" +
                "        <max:KEZELESIU>0</max:KEZELESIU>\n" +
                "        <max:KGFB>0</max:KGFB>\n" +
                "        <max:LATHATOSAGI>1</max:LATHATOSAGI>\n" +
                "        <max:MENTODOBOZ>0</max:MENTODOBOZ>\n" +
                "        <max:MOBILP>0</max:MOBILP>\n" +
                "        <max:NOTES>note</max:NOTES>\n" +
                "        <max:NY_ACELF>1</max:NY_ACELF>\n" +
                "        <max:NY_FELNIN>0</max:NY_FELNIN>\n" +
                "        <max:NY_KONNYUF>0</max:NY_KONNYUF>\n" +
                "        <max:PERSONID>maxadmin</max:PERSONID>\n" +
                "        <max:POTKEREK>0</max:POTKEREK>\n" +
                "        <max:RADIOK>1</max:RADIOK>\n" +
                "        <max:RIASZTO>0</max:RIASZTO>\n" +
                "        <max:SZERVIZKONYV>0</max:SZERVIZKONYV>\n" +
                "        <max:TORZSKONYV>1</max:TORZSKONYV>\n" +
                "        <max:T_ACELF>3</max:T_ACELF>\n" +
                "        <max:T_FELNIN>0</max:T_FELNIN>\n" +
                "        <max:T_KONNYUF>2</max:T_KONNYUF>\n" +
                "        <max:UZEMANYAGK>0</max:UZEMANYAGK>\n" +
                "        <max:VONOHOROG>1</max:VONOHOROG>\n" +
                "        <max:VONOSZEM>1</max:VONOSZEM>\n" +
                "      </max:ACE_GJK_TART>\n"+
                "            </max:ASSET>\n" +
                "         </max:ACE_GEA_ASSETSet>\n" +
                "      </max:UpdateACE_GEA_ASSET>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
