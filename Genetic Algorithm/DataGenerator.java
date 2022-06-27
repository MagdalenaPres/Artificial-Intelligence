import java.util.ArrayList;
import java.util.Collections;

public class DataGenerator {
    Algorithm a = new Algorithm();
    Data d = new Data();

    public ArrayList<Pair> randomPositionMethod(int max_x, int max_y){
        ArrayList<Pair> positions = new ArrayList<>();
        for(int x = 0; x < max_x; x++)
        {
            for(int y = 0; y < max_y; y++)
            {
                Pair p1 = new Pair(x, y);
                Pair p2 = new Pair(y, x);
                if(!positions.contains(p1) && !positions.contains(p2))
                {
                    positions.add(p1);
                }
            }
        }
        Collections.shuffle(positions);
        return positions;
    }
    public ArrayList<Machine> createMachines(int numberOfMachines, int x, int y){
        ArrayList<Machine> machines = new ArrayList<>();
        ArrayList<Pair> positions = randomPositionMethod(x, y);
        for(int i=0; i<numberOfMachines; i++){
            Machine m = new Machine(i, positions.get(i).getX(), positions.get(i).getY());
            machines.add(m);
        }
        return machines;
    }

    public ArrayList<Individual> createPopulation(int numberOfMachinesLists, int numberOfMachines,
                                                  int x, int y, String cost, String amount){
        ArrayList<Individual> population = new ArrayList<>();

        for(int j=0; j<numberOfMachinesLists; j++){
            ArrayList<Machine> machines;
            machines = createMachines(numberOfMachines, x, y);
            double sigma = a.calculateFitnessFunction(machines, d.createConnectionsList(cost, amount));

            Individual i = new Individual(machines, sigma, 0);
            population.add(i);
        }
        return population;
    }

}
