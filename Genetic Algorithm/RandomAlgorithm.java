import java.util.ArrayList;

public class RandomAlgorithm
{
    public int randomPositionMethod(int iterations, ArrayList<Connection> connections){
        Algorithm a = new Algorithm();
        DataGenerator dataGenerator = new DataGenerator();

        double sigma = Integer.MAX_VALUE;

        for(int i =0; i<iterations; i++){
            ArrayList<Machine> machines = dataGenerator.createMachines(24, 5, 6);
            double currentSigma = a.calculateFitnessFunction(machines, connections);
            if(currentSigma < sigma){
                sigma = currentSigma;
            }
        }
        return (int)sigma;
    }
}
