package SpectraSystems.Nexus.models;

public class Rating {
    private double average;
    private int count;

    public Rating() {
    }

    public Rating(double average, int count) {
        this.average = average;
        this.count = count;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

