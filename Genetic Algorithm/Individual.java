import java.util.ArrayList;

public class Individual {
    private ArrayList<Machine> machines;
    private double fitnessValue;
    private float probability;

    public Individual() { }

    public Individual(ArrayList<Machine> machines, double fitnessValue, float probability) {
        this.machines = machines;
        this.fitnessValue = fitnessValue;
        this.probability = probability;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }
}
