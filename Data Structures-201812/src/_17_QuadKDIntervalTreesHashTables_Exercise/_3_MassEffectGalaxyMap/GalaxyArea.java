package _17_QuadKDIntervalTreesHashTables_Exercise._3_MassEffectGalaxyMap;

public class GalaxyArea {

    private double x;
    private double y;
    private double x2;
    private double y2;

    public GalaxyArea(double x, double y, double weight, double height) {
        this.x = x;
        this.y = y;
        this.x2 = x + weight;
        this.y2 = y + height;
    }

    public boolean intersects(GalaxyArea area) {
        return this.x <= area.getX2() &&
                this.x2 >= area.getX() &&
                this.y <= area.getY2() &&
                this.y2 >= area.getY();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getX2() {
        return this.x2;
    }

    public double getY2() {
        return this.y2;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }
}
