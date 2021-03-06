package hu.ace.geaapp.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import hu.ace.geaapp.data.model.Accessories;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.data.model.AceAssetGJK;
import hu.ace.geaapp.data.model.AceAssetVignette;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.StructFeatures;

public class EntityMapper {

    public static List<Asset> transformVehiclesList(String body){
        List<Asset> assetList = new LinkedList<>();

        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(body)));
            NodeList node = doc.getElementsByTagName("ASSET");
            for (int i = 0; i < node.getLength(); i++) {
                Asset inbound = transformAsset((Element) node.item(i));
                assetList.add(inbound);
            }

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }


        return assetList;
    }

    public static List<AceAssetDamage> transformVehicleDamageList(String body){
        List<AceAssetDamage> damageList = new ArrayList<>();

        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(body)));
            NodeList node = doc.getElementsByTagName("ASSET");
            if (node.getLength() == 1){
                Asset vehicleAsset = transformAsset((Element) node.item(0));
                if (vehicleAsset != null){
                    damageList.addAll(vehicleAsset.getAssetDamageList());
                }
            }
            for(int i=0;i<damageList.size();i++){
                System.out.println(" REST : DAMAGE ==> ID="+damageList.get(i).getDamageID()+
                        "; Status="+damageList.get(i).getStatus()+
                        "; coordX="+damageList.get(i).getCoordinateX()+"; coordY="+damageList.get(i).getCoordinateY()+
                        "; coordObj="+damageList.get(i).getDamageCoordinate()+"; desc="+damageList.get(i).getDescription()+
                        "; view="+damageList.get(i).getCustomView());
            }

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return damageList;
    }



    public static Asset transformVehicleItem(String body){

        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(body)));
            NodeList node = doc.getElementsByTagName("ASSET");
            if (node.getLength() == 1){
                return transformAsset((Element) node.item(0));
            }

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Asset transformAsset(Element element){
        Asset asset = new Asset();

        asset.setAssetnum(getNodeValue(element,"ASSETNUM"));
        asset.setStatus(getNodeValue(element,"STATUS"));
        asset.setAssetID(getNodeValue(element,"ASSETID"));
        asset.setAssetuID(getNodeValue(element,"ASSETUID"));
        asset.setClassStructureID(getNodeValue(element,"CLASSSTRUCTUREID"));

        //details info
        NodeList assetGJKNode = element.getElementsByTagName("ACE_ASSET_GJK");
        if (assetGJKNode != null){
            AceAssetGJK aceAssetGJK =transformAceAssetGJK((Element)assetGJKNode.item(0));
            asset.setAceAssetGJK(aceAssetGJK);
        }else{
            asset.setAceAssetGJK(null);
        }

        //vignette
        NodeList vignetteNode = element.getElementsByTagName("ACE_ASSET_GJK_AUTOP");
        List<AceAssetVignette> vignetteList = new ArrayList<>();
        for (int i=0;i<vignetteNode.getLength();i++){
            AceAssetVignette vignette = transformAceAssetVignette((Element) vignetteNode.item(i));
            vignetteList.add(vignette);
        }
        asset.setAssetVignetteList(vignetteList);

        //damage
        NodeList damageNode = element.getElementsByTagName("ASSET_GJK_KAR");
        List<AceAssetDamage> damageList = new ArrayList<>();
        for (int i=0;i<damageNode.getLength();i++){
            AceAssetDamage damage = transformAceAssetDamage((Element) damageNode.item(i));
            damageList.add(damage);
        }
        asset.setAssetDamageList(damageList);


        NodeList accessoriesNode = element.getElementsByTagName("ACE_GJK_TART");
        if (accessoriesNode.getLength() > 0){
            Accessories accessories = transformVehicleAccessories((Element) accessoriesNode.item(0));
            asset.setAccessories(accessories);
        }else{
            asset.setAccessories(null);
        }

        NodeList structFeatureNode = element.getElementsByTagName("ACE_GJK_SZERK");
        if (structFeatureNode.getLength() > 0){
            StructFeatures structFeatures = transformVehicleStructuralFeatures((Element) structFeatureNode.item(0));
           // System.out.println(" ASSET SZERK ID = "+structFeatures.getId());
            asset.setStructFeatures(structFeatures);
        }else{
            asset.setStructFeatures(null);
        }

        return asset;
    }

    //ACE_ASSET_GJK
    private static AceAssetGJK transformAceAssetGJK(Element element){
        AceAssetGJK aceAssetGJK = new AceAssetGJK();

        aceAssetGJK.setLicensePlate(getNodeValue(element,"RDSZ"));
        aceAssetGJK.setType(getNodeValue(element,"TYPE"));
        aceAssetGJK.setFuel(getNodeValue(element,"UZEMANYAG"));
        aceAssetGJK.setYear(getNodeValue(element,"EV"));
        aceAssetGJK.setFinancing(getNodeValue(element,"FINANSZIROZAS"));
        aceAssetGJK.setOwner(getNodeValue(element,"TULAJDONOS"));
        aceAssetGJK.setUsername(getNodeValue(element,"FELHASZN"));
        aceAssetGJK.setLicenser(getNodeValue(element,"HASZNENG"));
        aceAssetGJK.setCm3(getNodeValue(element,"CM3"));
        aceAssetGJK.setTrafficLicenseNumber(getNodeValue(element,"FORGENG"));
        aceAssetGJK.setVehicleIDnumber(getNodeValue(element,"ALVSZAM"));
        aceAssetGJK.setVignette(getNodeValue(element,"AUTOP"));
        aceAssetGJK.setTireSize(getNodeValue(element,"GUMIM"));
        aceAssetGJK.setNextOilChange(getNodeValue(element,"KSZERVIZ"));
        aceAssetGJK.setKmHour(getNodeValue(element,"KMORA"));
        aceAssetGJK.setInventoryNumber(getNodeValue(element,"BARCODEID"));
        aceAssetGJK.setEntryValidity(getNodeValue(element,"BELEPENG"));
        aceAssetGJK.setTrafficLicenseValidity(getNodeValue(element,"FORGERV"));
        aceAssetGJK.setStatus(getNodeValue(element,"STATUS"));
        aceAssetGJK.setMotorNumber(getNodeValue(element,"MOTORSZAM"));
        aceAssetGJK.setCurrentKMdate(getNodeValue(element,"CURRENT_KMDATE"));
        aceAssetGJK.setCostCenter(getNodeValue(element,"KTG"));
        return aceAssetGJK;
    }


    //ACE_ASSET_GJK_AUTOP
    private static AceAssetVignette transformAceAssetVignette(Element element){
        AceAssetVignette vignette = new AceAssetVignette();

        vignette.setType(getNodeValue(element,"DESCRIPTION"));
        vignette.setYear(getNodeValue(element,"EV"));
        vignette.setPrice(getNodeValue(element,"PRICE"));
        vignette.setExpirationDate(getNodeValue(element,"LEJARAT"));
        vignette.setStatus(getNodeValueBoolean(element,"STATUS"));
        return vignette;
    }

    //ASSET_GJK_KAR
    private static AceAssetDamage transformAceAssetDamage(Element element){
        AceAssetDamage damage = new AceAssetDamage();

        damage.setDamageID(getNodeValue(element,"ASSET_GJK_KARID"));
        damage.setDriver(getNodeValue(element,"GJKVEZ")); //aln
        damage.setDamageNr(getNodeValue(element,"GJK_KARSZAM")); //aln
        damage.setDamageTime(getNodeValue(element,"GJK_KARIDO")); //datetime
        damage.setDamagePlace(getNodeValue(element,"GJK_KARHELY")); //aln
        damage.setStatus(getNodeValue(element,"STATUS"));
        damage.setCoordinateX(getNodeValue(element,"X"));
        damage.setCoordinateY(getNodeValue(element,"Y"));
        damage.setDescription(getNodeValue(element,"DESCRIPTION"));
        return damage;
    }


    //ACE_GJK_TART
    private static Accessories transformVehicleAccessories(Element element){
        Accessories accessories = new Accessories();

        accessories.setAccessoriesID(getNodeValue(element,"ACE_GJK_TARTID"));
        accessories.setDescription(getNodeValue(element,"DESCRIPTION"));
        accessories.setNotes(getNodeValue(element,"NOTES"));
        accessories.setAltalanos(getNodeValue(element,"ALTALANOS"));
        accessories.setPersonID(getNodeValue(element,"PERSONID"));

        accessories.setAntenna(getNodeValueBoolean(element,"ANTENNA"));
        accessories.setAssistanceInfo(getNodeValueBoolean(element,"ASSISTANCE"));
        accessories.setElakadasjelzo(getNodeValueBoolean(element,"ELAKADASJ"));
        accessories.setEmelo(getNodeValueBoolean(element,"EMELO"));
        accessories.setFlotta(getNodeValueBoolean(element,"FLOTTAK"));

        accessories.setForgalmiEngedely(getNodeValueBoolean(element,"FORGALMIE"));
        accessories.setAutopalyamatrica(getNodeValueBoolean(element,"AUTOPALYAM"));
        accessories.setGumiszonyegNR(getNodeValue(element,"GUMISZONYEG"));
        accessories.setIzzokeszlet(getNodeValueBoolean(element,"IZZOKESZLET"));
        accessories.setKerekkulcs(getNodeValueBoolean(element,"KEREKKULCS"));
        accessories.setKerekorkulccsal(getNodeValueBoolean(element,"KEREKOR"));
        accessories.setKeresztlecek(getNodeValueBoolean(element,"KERESZTLECEK"));
        accessories.setKezelesiUtmutato(getNodeValueBoolean(element,"KEZELESIU"));
        accessories.setIgazolasKGFB(getNodeValueBoolean(element,"KGFB"));
        accessories.setLathatosagiMellenyNR(getNodeValue(element,"LATHATOSAGI"));
        accessories.setMentodoboz(getNodeValueBoolean(element,"MENTODOBOZ"));
        accessories.setMobilparkolas(getNodeValueBoolean(element,"MOBILP"));
        accessories.setGumiNyariAcelFelninNR(getNodeValue(element,"NY_ACELF"));
        accessories.setGumiNyariFelniNelkulNR(getNodeValue(element,"NY_FELNIN"));
        accessories.setGumiNyariKonnyufelFelninNR(getNodeValue(element,"NY_KONNYUF"));

        accessories.setPotkerek(getNodeValueBoolean(element,"POTKEREK"));
        accessories.setRadiokod(getNodeValueBoolean(element,"RADIOK"));
        accessories.setRiasztoTaviranyitoNR(getNodeValue(element,"RIASZTO"));
        accessories.setSzervizkonyv(getNodeValueBoolean(element,"SZERVIZKONYV"));
        accessories.setGumiTeliAcelFelninNR(getNodeValue(element,"T_ACELF"));
        accessories.setGumiTeliFelniNelkulNR(getNodeValue(element,"T_FELNIN"));
        accessories.setGumiTeliKonnyufelFelninNR(getNodeValue(element,"T_KONNYUF"));

        accessories.setUzemeanyagkartya(getNodeValueBoolean(element,"UZEMANYAGK"));
        accessories.setVonohorog(getNodeValueBoolean(element,"VONOHOROG"));
        accessories.setVonoszem(getNodeValueBoolean(element,"VONOSZEM"));


        return accessories;
    }

    //ACE_GJK_SZERK
    private static StructFeatures transformVehicleStructuralFeatures(Element element){
        StructFeatures structFeatures = new StructFeatures();

        structFeatures.setId(getNodeValue(element,"ACE_GJK_SZERKID"));
        structFeatures.setDescription(getNodeValue(element,"DESCRIPTION"));
        structFeatures.setAblakmoso(getNodeValueInt(element,"ABLAKMO"));
        structFeatures.setAblaktorlo(getNodeValueInt(element,"ABLAKT"));
        structFeatures.setAkkumulator(getNodeValueInt(element,"AKKUMULATOR"));
        structFeatures.setBiztonsagiov(getNodeValueInt(element,"BIZTONSAGIOV"));
        structFeatures.setFutomuall(getNodeValueInt(element,"FUTOMU"));
        structFeatures.setFeklampa(getNodeValueInt(element,"FEKLAMPA"));
        structFeatures.setFekrendszer(getNodeValueInt(element,"FEKRENDSZER"));
        structFeatures.setGumikall(getNodeValueInt(element,"GUMIK"));
        structFeatures.setHelyzetjelzo(getNodeValueInt(element,"HELYZETJ"));
        structFeatures.setHutorendszer(getNodeValueInt(element,"HUTOR"));
        structFeatures.setInditomotor(getNodeValueInt(element,"INDITOM"));
        structFeatures.setIranyjelzo(getNodeValueInt(element,"IRANYJ"));
        structFeatures.setKipufogorendszer(getNodeValueInt(element,"KIPUFOGO"));
        structFeatures.setSzelvedoall(getNodeValueInt(element,"SZELVEDO"));
        structFeatures.setKontrolllampak(getNodeValueInt(element,"KLAMPAK"));
        structFeatures.setKormanymu(getNodeValueInt(element,"KORMANYMU"));
        structFeatures.setKurt(getNodeValueInt(element,"KURT"));
        structFeatures.setOlajfolyas(getNodeValueInt(element,"OLAJF"));
        structFeatures.setOlajnyomas(getNodeValueInt(element,"OLAJNY"));
        structFeatures.setRogzitofek(getNodeValueInt(element,"ROGZITOF"));
        structFeatures.setTengelykapcsolo(getNodeValueInt(element,"TENGELYK"));
        structFeatures.setTolatolampa(getNodeValueInt(element,"TOLATOL"));
        structFeatures.setTompitottfenyszoro(getNodeValueInt(element,"TFENYSZORO"));
        structFeatures.setTavolsagifenyszoro(getNodeValueInt(element,"TAVFENYSZORO"));
        structFeatures.setToltes(getNodeValueInt(element,"TOLTES"));
        structFeatures.setUtasterall(getNodeValueInt(element,"UTASTER"));
        structFeatures.setVisszapillanto(getNodeValueInt(element,"VISSZAPILL"));
        structFeatures.setNote(getNodeValue(element,"NOTES"));

        return structFeatures;
    }


    private static String getNodeValue(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    private static int getNodeValueInt(Element element , String tag){
        return Integer.parseInt(element.getElementsByTagName(tag).item(0).getTextContent());
    }


    private  static boolean getNodeValueBoolean(Element element, String tag){
        return element.getElementsByTagName(tag).item(0).getTextContent().equals("1") ;
    }
}
