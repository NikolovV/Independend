package Exams.Invaders;

public class InvaderImpl implements Invader, Comparable<Invader> {

    private final int damage;
    private int distance;

    public InvaderImpl(int damage, int distance) {
        this.damage = damage;
        this.distance = distance;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Invader o) {
        int comp = Integer.compare(this.distance, o.getDistance());
        if (comp == 0) {
            comp = Integer.compare(this.damage, o.getDamage());
        }
        return comp;
    }

}
