package _17_QuadKDIntervalTreesHashTables_Exercise._1_SweepAndPrune;

public class GameObject {
    private Rectangle objectForm;
    private final String name;

    public GameObject(int x1, int y1, String name) {
        this.objectForm = new Rectangle(x1, y1, 10, 10);
        this.name = name;
    }

    public Rectangle getObjectForm() {
        return this.objectForm;
    }

    public String getName() {
        return this.name;
    }

    public void setObjectForm(Rectangle objectForm) {
        this.objectForm = objectForm;
    }
}
