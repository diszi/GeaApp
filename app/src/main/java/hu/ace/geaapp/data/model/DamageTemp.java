package hu.ace.geaapp.data.model;

import java.io.Serializable;

import hu.ace.geaapp.ui.view.Damage;

public class DamageTemp implements Serializable {

    public static String SERIALIZABLE_NAME = "DAMAGE_TEMP";

    private float coordinateX;
    private float coordinateY;
    private String type; //status
    private String note; // description
    private int color;

    private Damage damageType;

    public DamageTemp(){}

    public DamageTemp(float coordinateX, float coordinateY,Damage damageType, String note,int color){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
//        this.type = type;
        this.note = note;
        this.damageType = damageType;
        this.color = color;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Damage getDamageType() {
        return damageType;
    }

    public void setDamageType(Damage damageType) {
        this.damageType = damageType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String notes) {
        this.note = notes;
    }
}
