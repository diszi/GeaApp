package hu.ace.geaapp.data.model;

public class StructFeaturesTemp {

    private String id;
    private String description;
    private int ablakmoso;
    private int ablaktorlo;
    private int akkumulator;
    private int biztonsagiov;
    private int futomuall;
    private int feklampa;
    private int fekrendszer;
    private int gumikall;
    private int helyzetjelzo;
    private int hutorendszer;
    private int inditomotor;
    private int iranyjelzo;
    private int kipufogorendszer;
    private int kontrolllampak;
    private int kormanymu;
    private int kurt;
    private int olajfolyas;
    private int olajnyomas;
    private int rogzitofek;
    private int tengelykapcsolo;
    private int tolatolampa;
    private int tompitottfenyszoro;
    private int tavolsagifenyszoro;
    private int toltes;
    private int utasterall;
    private int visszapillanto;
    private int szelvedoall;

    private String note;


    public StructFeaturesTemp(String id, String description,int ablakmoso, int ablaktorlo, int akkumulator, int biztonsagiov, int futomuall, int feklampa, int fekrendszer,
                          int gumikall, int helyzetjelzo, int hutorendszer, int inditomotor, int iranyjelzo, int kipufogorendszer,
                          int kontrolllampak, int kormanymu, int kurt, int olajfolyas, int olajnyomas, int rogzitofek,
                          int tengelykapcsolo, int tolatolampa, int tompitottfenyszoro, int tavolsagifenyszoro, int toltes,
                          int utasterall, int visszapillanto,int szelvedoall,String note){
        this.ablakmoso = ablakmoso;
        this.ablaktorlo = ablaktorlo;
        this.akkumulator = akkumulator;
        this.biztonsagiov = biztonsagiov;
        this.futomuall = futomuall;
        this.feklampa = feklampa;
        this.fekrendszer = fekrendszer;
        this.gumikall = gumikall;
        this.helyzetjelzo = helyzetjelzo;
        this.hutorendszer = hutorendszer;
        this.inditomotor = inditomotor;
        this.iranyjelzo = iranyjelzo;
        this.kipufogorendszer = kipufogorendszer;
        this.kontrolllampak = kontrolllampak;
        this.kormanymu = kormanymu;
        this.kurt = kurt;
        this.olajfolyas = olajfolyas;
        this.olajnyomas = olajnyomas;
        this.rogzitofek = rogzitofek;
        this.tengelykapcsolo = tengelykapcsolo;
        this.tolatolampa = tolatolampa;
        this.tompitottfenyszoro = tompitottfenyszoro;
        this.tavolsagifenyszoro = tavolsagifenyszoro;
        this.toltes = toltes;
        this.utasterall = utasterall;
        this.visszapillanto = visszapillanto;
        this.szelvedoall = szelvedoall;
        this.note = note;
        this.id = id;
        this.description = description;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSzelvedoall() {
        return szelvedoall;
    }

    public void setSzelvedoall(int szelvedoall) {
        this.szelvedoall = szelvedoall;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAblakmoso() {
        return ablakmoso;
    }

    public void setAblakmoso(int ablakmoso) {
        this.ablakmoso = ablakmoso;
    }

    public int getAblaktorlo() {
        return ablaktorlo;
    }

    public void setAblaktorlo(int ablaktorlo) {
        this.ablaktorlo = ablaktorlo;
    }

    public int getAkkumulator() {
        return akkumulator;
    }

    public void setAkkumulator(int akkumulator) {
        this.akkumulator = akkumulator;
    }

    public int getBiztonsagiov() {
        return biztonsagiov;
    }

    public void setBiztonsagiov(int biztonsagiov) {
        this.biztonsagiov = biztonsagiov;
    }

    public int getFutomuall() {
        return futomuall;
    }

    public void setFutomuall(int futomuall) {
        this.futomuall = futomuall;
    }

    public int getFeklampa() {
        return feklampa;
    }

    public void setFeklampa(int feklampa) {
        this.feklampa = feklampa;
    }

    public int getFekrendszer() {
        return fekrendszer;
    }

    public void setFekrendszer(int fekrendszer) {
        this.fekrendszer = fekrendszer;
    }

    public int getGumikall() {
        return gumikall;
    }

    public void setGumikall(int gumikall) {
        this.gumikall = gumikall;
    }

    public int getHelyzetjelzo() {
        return helyzetjelzo;
    }

    public void setHelyzetjelzo(int helyzetjelzo) {
        this.helyzetjelzo = helyzetjelzo;
    }

    public int getHutorendszer() {
        return hutorendszer;
    }

    public void setHutorendszer(int hutorendszer) {
        this.hutorendszer = hutorendszer;
    }

    public int getInditomotor() {
        return inditomotor;
    }

    public void setInditomotor(int inditomotor) {
        this.inditomotor = inditomotor;
    }

    public int getIranyjelzo() {
        return iranyjelzo;
    }

    public void setIranyjelzo(int iranyjelzo) {
        this.iranyjelzo = iranyjelzo;
    }

    public int getKipufogorendszer() {
        return kipufogorendszer;
    }

    public void setKipufogorendszer(int kipufogorendszer) {
        this.kipufogorendszer = kipufogorendszer;
    }

    public int getKontrolllampak() {
        return kontrolllampak;
    }

    public void setKontrolllampak(int kontrolllampak) {
        this.kontrolllampak = kontrolllampak;
    }

    public int getKormanymu() {
        return kormanymu;
    }

    public void setKormanymu(int kormanymu) {
        this.kormanymu = kormanymu;
    }

    public int getKurt() {
        return kurt;
    }

    public void setKurt(int kurt) {
        this.kurt = kurt;
    }

    public int getOlajfolyas() {
        return olajfolyas;
    }

    public void setOlajfolyas(int olajfolyas) {
        this.olajfolyas = olajfolyas;
    }

    public int getOlajnyomas() {
        return olajnyomas;
    }

    public void setOlajnyomas(int olajnyomas) {
        this.olajnyomas = olajnyomas;
    }

    public int getRogzitofek() {
        return rogzitofek;
    }

    public void setRogzitofek(int rogzitofek) {
        this.rogzitofek = rogzitofek;
    }

    public int getTengelykapcsolo() {
        return tengelykapcsolo;
    }

    public void setTengelykapcsolo(int tengelykapcsolo) {
        this.tengelykapcsolo = tengelykapcsolo;
    }

    public int getTolatolampa() {
        return tolatolampa;
    }

    public void setTolatolampa(int tolatolampa) {
        this.tolatolampa = tolatolampa;
    }

    public int getTompitottfenyszoro() {
        return tompitottfenyszoro;
    }

    public void setTompitottfenyszoro(int tompitottfenyszoro) {
        this.tompitottfenyszoro = tompitottfenyszoro;
    }

    public int getTavolsagifenyszoro() {
        return tavolsagifenyszoro;
    }

    public void setTavolsagifenyszoro(int tavolsagifenyszoro) {
        this.tavolsagifenyszoro = tavolsagifenyszoro;
    }

    public int getToltes() {
        return toltes;
    }

    public void setToltes(int tolto) {
        this.toltes = tolto;
    }

    public int getUtasterall() {
        return utasterall;
    }

    public void setUtasterall(int utasterall) {
        this.utasterall = utasterall;
    }

    public int getVisszapillanto() {
        return visszapillanto;
    }

    public void setVisszapillanto(int visszapillanto) {
        this.visszapillanto = visszapillanto;
    }

}
