package hu.ace.geaapp.ui.view;

public enum Feature {

    KIVALO(0),
    MEGFELELO(1),
    ROSSZ(2);


   /* private final int featureType;

    Feature(int featureType){
        this.featureType = featureType;
    }*/

    private int featureType;

    Feature(int value){
        this.featureType = value;
    }

    public int getFeatureType(){
        return this.featureType;
    }

//    public static int getFeatureType(){
//        return featureType;
//    }
}
