package Exams.Invaders;

public interface Invader extends Comparable<Invader> {

    int getDamage();

    int getDistance();

    void setDistance(int distance);
}
