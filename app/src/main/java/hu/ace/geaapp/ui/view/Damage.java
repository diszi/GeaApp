package hu.ace.geaapp.ui.view;

public enum Damage {

    BREAK("Törés"),
    SCRATCH("Karcolás"),
    BASH("Horpadás"),
    LACK("Hiány");

    private final String damageType;

    Damage(String damageType){
        this.damageType = damageType;
    }

    public String getDamageType(){
        return this.damageType;
    }

}
