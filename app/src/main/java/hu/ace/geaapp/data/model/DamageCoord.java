package hu.ace.geaapp.data.model;

public class DamageCoord {

    private float coordinateX;
    private float coordinateY;

    public DamageCoord(float x, float y){
        //System.out.println(" Damage COORD = [x="+x+"; y="+y+"]");
        this.coordinateX = x;
        this.coordinateY = y;
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
}
