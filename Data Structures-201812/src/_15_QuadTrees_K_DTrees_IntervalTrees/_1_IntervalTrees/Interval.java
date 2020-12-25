package _15_QuadTrees_K_DTrees_IntervalTrees._1_IntervalTrees;

public class Interval {

    private double lo;
    private double hi;

    public Interval(double lo, double hi) {
        Interval.validateInterval(lo, hi);
        this.setLo(lo);
        this.setHi(hi);
    }

    public double getLo() {
        return this.lo;
    }

    public void setLo(double lo) {
        this.lo = lo;
    }

    public double getHi() {
        return this.hi;
    }

    public void setHi(double hi) {
        this.hi = hi;
    }

    public boolean intersects(double lo, double hi) {
        Interval.validateInterval(lo, hi);

        return this.lo < hi && this.hi > lo;
    }

    @Override
    public boolean equals(Object obj) {
        Interval other = (Interval) obj;
        return this.lo == other.lo && this.hi == other.hi;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", this.lo, this.hi);
    }

    private static void validateInterval(double lo, double hi) {
        if (hi < lo) {
            throw new IllegalArgumentException();
        }
    }
}
