package hu.ace.geaapp.data.remote;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.StructFeatures;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.singleton.SettingsSingleton;
import io.reactivex.ObservableEmitter;

public class AddFeaturesSOAP <T extends String> extends  AbstractSOAP<T>{

    private String assetnumber;
    private String assetID;
    private String assetUID;
    private String assetStatus;
    private StructFeatures structFeature;

    public AddFeaturesSOAP(Asset asset, StructFeatures feature){
        this.assetnumber = asset.getAssetnum();
        this.assetID = asset.getAssetID();
        this.assetUID = asset.getAssetuID();
        this.assetStatus = asset.getStatus();

        this.structFeature = feature;

       // System.out.println(" ASSET = "+assetnumber);
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
                "                  <max:ACE_GJK_SZERK action=\"Add\">\n" +
                                        "<max:ABLAKMO>"+structFeature.getAblakmoso()+"</max:ABLAKMO>\n" +
                                        "<max:ABLAKT>"+structFeature.getAblaktorlo()+"</max:ABLAKT>\n" +
                                        "<max:AKKUMULATOR>"+structFeature.getAkkumulator()+"</max:AKKUMULATOR>\n" +
                                        "<max:BIZTONSAGIOV>"+structFeature.getBiztonsagiov()+"</max:BIZTONSAGIOV>\n" +
                                        " <max:FEKLAMPA>"+structFeature.getFeklampa()+"</max:FEKLAMPA>\n" +
                                        " <max:FEKRENDSZER>"+structFeature.getFekrendszer()+"</max:FEKRENDSZER>\n" +
                                        "<max:FUTOMU>"+structFeature.getFutomuall()+"</max:FUTOMU>\n" +
                                        " <max:GUMIK>"+structFeature.getGumikall()+"</max:GUMIK>\n" +
                                        " <max:HELYZETJ>"+structFeature.getHelyzetjelzo()+"</max:HELYZETJ>\n" +
                                        " <max:HUTOR>"+structFeature.getHutorendszer()+"</max:HUTOR>\n" +
                                        " <max:INDITOM>"+structFeature.getInditomotor()+"</max:INDITOM>\n" +
                                        "  <max:IRANYJ>"+structFeature.getIranyjelzo()+"</max:IRANYJ>\n" +
                                        "   <max:KIPUFOGO>"+structFeature.getKipufogorendszer()+"</max:KIPUFOGO>\n" +
                                        "  <max:KLAMPAK>"+structFeature.getKontrolllampak()+"</max:KLAMPAK>\n" +
                                        " <max:KORMANYMU>"+structFeature.getKormanymu()+"</max:KORMANYMU>\n" +
                                        " <max:KURT>"+structFeature.getKurt()+"</max:KURT>\n" +
                                        "<max:NOTES></max:NOTES>\n" +
                                        "<max:OLAJF>"+structFeature.getOlajfolyas()+"</max:OLAJF>\n" +
                                        " <max:OLAJNY>"+structFeature.getOlajnyomas()+"</max:OLAJNY>\n" +
                                        " <max:PERSONID>"+ SettingsSingleton.getInstance().getUserName() +"</max:PERSONID>\n" +
                                        "<max:ROGZITOF>"+structFeature.getRogzitofek()+"</max:ROGZITOF>\n" +
                                        "<max:SZELVEDO>"+structFeature.getSzelvedoall()+"</max:SZELVEDO>\n" +
                                        "<max:TAVFENYSZORO>"+structFeature.getTavolsagifenyszoro()+"</max:TAVFENYSZORO>\n" +
                                        " <max:TENGELYK>"+structFeature.getTengelykapcsolo()+"</max:TENGELYK>\n" +
                                        "<max:TFENYSZORO>"+structFeature.getTompitottfenyszoro()+"</max:TFENYSZORO>\n" +
                                        "<max:TOLATOL>"+structFeature.getTolatolampa()+"</max:TOLATOL>\n" +
                                        " <max:TOLTES>"+structFeature.getToltes()+"</max:TOLTES>\n" +
                                        "<max:UTASTER>"+structFeature.getUtasterall()+"</max:UTASTER>\n" +
                                        " <max:VISSZAPILL>"+structFeature.getVisszapillanto()+"</max:VISSZAPILL>\n"+
                "      </max:ACE_GJK_SZERK>\n"+
                "        </max:ASSET>\n" +
                "         </max:ACE_GEA_ASSETSet>\n" +
                "      </max:UpdateACE_GEA_ASSET>\n"+
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

    }
}
