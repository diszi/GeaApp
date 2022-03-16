package hu.ace.geaapp.data.model;


import java.io.Serializable;

//tartozekok
public class Accessories implements Serializable {

    public static String SERIALIZABLE_NAME = "ASSET_VEHICLE_ACCESSORIES"; //tartozekok

    private String accessoriesID;
    private String description;
    private String notes;
    private String personID;

    private String assetnum;


    private boolean antenna;
    private boolean assistanceInfo;
    private boolean elakadasjelzo;
    private boolean emelo;
    private boolean flotta;

    private boolean forgalmiEngedely;
    private boolean izzokeszlet;
    private boolean igazolasKGFB;
    private boolean keresztlecek;
    private boolean kerekkulcs;

    private boolean kerekorkulccsal;
    private boolean kezelesiUtmutato;
    private boolean mentodoboz;
    private boolean mobilparkolas;
    private boolean potkerek;

    private boolean radiokod;
    private boolean szervizkonyv;
    private boolean vonohorog;
    private boolean vonoszem;
    private boolean uzemeanyagkartya;

    private boolean autopalyamatrica;


    private String altalanos;
    private String gumiszonyegNR;
    private String lathatosagiMellenyNR;
    private String gumiNyariAcelFelninNR;
    private String gumiNyariFelniNelkulNR;
    private String gumiNyariKonnyufelFelninNR;

    private String gumiTeliAcelFelninNR;
    private String gumiTeliFelniNelkulNR;
    private String gumiTeliKonnyufelFelninNR;

    private String riasztoTaviranyitoNR;


    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAccessoriesID() {
        return accessoriesID;
    }

    public void setAccessoriesID(String accessoriesID) {
        this.accessoriesID = accessoriesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isAntenna() {
        return antenna;
    }

    public void setAntenna(boolean antenna) {
        this.antenna = antenna;
    }

    public boolean isAssistanceInfo() {
        return assistanceInfo;
    }

    public void setAssistanceInfo(boolean assistanceInfo) {
        this.assistanceInfo = assistanceInfo;
    }

    public boolean isElakadasjelzo() {
        return elakadasjelzo;
    }

    public void setElakadasjelzo(boolean elakadasjelzo) {
        this.elakadasjelzo = elakadasjelzo;
    }

    public boolean isEmelo() {
        return emelo;
    }

    public void setEmelo(boolean emelo) {
        this.emelo = emelo;
    }

    public boolean isFlotta() {
        return flotta;
    }

    public void setFlotta(boolean flotta) {
        this.flotta = flotta;
    }


    public boolean isForgalmiEngedely() {
        return forgalmiEngedely;
    }

    public void setForgalmiEngedely(boolean forgalmiEngedely) {
        this.forgalmiEngedely = forgalmiEngedely;
    }

    public boolean isIzzokeszlet() {
        return izzokeszlet;
    }

    public void setIzzokeszlet(boolean izzokeszlet) {
        this.izzokeszlet = izzokeszlet;
    }

    public boolean isIgazolasKGFB() {
        return igazolasKGFB;
    }

    public void setIgazolasKGFB(boolean igazolasKGFB) {
        this.igazolasKGFB = igazolasKGFB;
    }

    public boolean isKeresztlecek() {
        return keresztlecek;
    }

    public void setKeresztlecek(boolean keresztlecek) {
        this.keresztlecek = keresztlecek;
    }

    public boolean isKerekkulcs() {
        return kerekkulcs;
    }

    public void setKerekkulcs(boolean kerekkulcs) {
        this.kerekkulcs = kerekkulcs;
    }

    public boolean isKerekorkulccsal() {
        return kerekorkulccsal;
    }

    public void setKerekorkulccsal(boolean kerekorkulccsal) {
        this.kerekorkulccsal = kerekorkulccsal;
    }

    public boolean isKezelesiUtmutato() {
        return kezelesiUtmutato;
    }

    public void setKezelesiUtmutato(boolean kezelesiUtmutato) {
        this.kezelesiUtmutato = kezelesiUtmutato;
    }

    public boolean isMentodoboz() {
        return mentodoboz;
    }

    public void setMentodoboz(boolean mentodoboz) {
        this.mentodoboz = mentodoboz;
    }

    public boolean isMobilparkolas() {
        return mobilparkolas;
    }

    public void setMobilparkolas(boolean mobilparkolas) {
        this.mobilparkolas = mobilparkolas;
    }

    public boolean isPotkerek() {
        return potkerek;
    }

    public void setPotkerek(boolean potkerek) {
        this.potkerek = potkerek;
    }

    public boolean isRadiokod() {
        return radiokod;
    }

    public void setRadiokod(boolean radiokod) {
        this.radiokod = radiokod;
    }

    public boolean isSzervizkonyv() {
        return szervizkonyv;
    }

    public void setSzervizkonyv(boolean szervizkonyv) {
        this.szervizkonyv = szervizkonyv;
    }

    public boolean isVonohorog() {
        return vonohorog;
    }

    public void setVonohorog(boolean vonohorog) {
        this.vonohorog = vonohorog;
    }

    public boolean isVonoszem() {
        return vonoszem;
    }

    public void setVonoszem(boolean vonoszem) {
        this.vonoszem = vonoszem;
    }

    public boolean isUzemeanyagkartya() {
        return uzemeanyagkartya;
    }

    public void setUzemeanyagkartya(boolean uzemeanyagkartya) {
        this.uzemeanyagkartya = uzemeanyagkartya;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public boolean isAutopalyamatrica() {
        return autopalyamatrica;
    }

    public void setAutopalyamatrica(boolean autopalyamatrica) {
        this.autopalyamatrica = autopalyamatrica;
    }

    public String getAltalanos() {
        return altalanos;
    }

    public void setAltalanos(String altalanos) {
        this.altalanos = altalanos;
    }

    public String getGumiszonyegNR() {
        return gumiszonyegNR;
    }

    public void setGumiszonyegNR(String gumiszonyegNR) {
        this.gumiszonyegNR = gumiszonyegNR;
    }

    public String getLathatosagiMellenyNR() {
        return lathatosagiMellenyNR;
    }

    public void setLathatosagiMellenyNR(String lathatosagiMellenyNR) {
        this.lathatosagiMellenyNR = lathatosagiMellenyNR;
    }

    public String getGumiNyariAcelFelninNR() {
        return gumiNyariAcelFelninNR;
    }

    public void setGumiNyariAcelFelninNR(String gumiNyariAcelFelninNR) {
        this.gumiNyariAcelFelninNR = gumiNyariAcelFelninNR;
    }

    public String getGumiNyariFelniNelkulNR() {
        return gumiNyariFelniNelkulNR;
    }

    public void setGumiNyariFelniNelkulNR(String gumiNyariFelniNelkulNR) {
        this.gumiNyariFelniNelkulNR = gumiNyariFelniNelkulNR;
    }

    public String getGumiNyariKonnyufelFelninNR() {
        return gumiNyariKonnyufelFelninNR;
    }

    public void setGumiNyariKonnyufelFelninNR(String gumiNyariKonnyufelFelninNR) {
        this.gumiNyariKonnyufelFelninNR = gumiNyariKonnyufelFelninNR;
    }

    public String getGumiTeliAcelFelninNR() {
        return gumiTeliAcelFelninNR;
    }

    public void setGumiTeliAcelFelninNR(String gumiTeliAcelFelninNR) {
        this.gumiTeliAcelFelninNR = gumiTeliAcelFelninNR;
    }

    public String getGumiTeliFelniNelkulNR() {
        return gumiTeliFelniNelkulNR;
    }

    public void setGumiTeliFelniNelkulNR(String gumiTeliFelniNelkulNR) {
        this.gumiTeliFelniNelkulNR = gumiTeliFelniNelkulNR;
    }

    public String getGumiTeliKonnyufelFelninNR() {
        return gumiTeliKonnyufelFelninNR;
    }

    public void setGumiTeliKonnyufelFelninNR(String gumiTeliKonnyufelFelninNR) {
        this.gumiTeliKonnyufelFelninNR = gumiTeliKonnyufelFelninNR;
    }

    public String getRiasztoTaviranyitoNR() {
        return riasztoTaviranyitoNR;
    }

    public void setRiasztoTaviranyitoNR(String riasztoTaviranyitoNR) {
        this.riasztoTaviranyitoNR = riasztoTaviranyitoNR;
    }
}
