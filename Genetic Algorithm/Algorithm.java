import java.util.ArrayList;
import java.util.Collections;

public class Algorithm
{
    public Individual geneticAlgorithm(int iterations, ArrayList<Individual> population, ArrayList<Connection> connections, float Px, float Pm){
        SelectionOperator selectionOperator = new SelectionOperator();
        CrossoverOperator crossoverOperator = new CrossoverOperator();
        Mutation mutation = new Mutation();
        Individual bestIndividual = null;
        int bestResult = Integer.MAX_VALUE;

        ArrayList<Individual> newPopulation = new ArrayList<>();

        for(int i=0; i < iterations; i++){
            while(newPopulation.size() != population.size())
            {
                Individual father = selectionOperator.tournament(5, population);
                Individual mother = selectionOperator.tournament(5, population);
                Individual child;

                if (Math.random() < Px){
                    child = crossoverOperator.crossover(father, mother);
                }
                else{
                    child = father;
                }
                if (Math.random() < Pm){
                    child = mutation.mutation(child);
                }

                child.setFitnessValue(calculateFitnessFunction(child.getMachines(), connections));
                newPopulation.add(child);
            }
            int best = Integer.MAX_VALUE;
            int worst = 0;
            int avg = 0;

            population = new ArrayList<>(newPopulation);

            for (Individual individual : population)
            {
                if (bestResult > individual.getFitnessValue()) {
                    bestResult = (int)individual.getFitnessValue();
                    bestIndividual = individual;
                }
                if (best > individual.getFitnessValue()) {
                    best = (int)individual.getFitnessValue();
                }
                if (worst < individual.getFitnessValue()) {
                    worst = (int)individual.getFitnessValue();
                }
                avg += (int)individual.getFitnessValue();
            }

            System.out.println(bestResult);
            newPopulation.clear();
        }
        return bestIndividual;
    }

    public double calculateFitnessFunction(ArrayList<Machine> m, ArrayList<Connection> connections){
        double sigma = 0.0;

        for(int i=0; i<m.size(); i++)
        {
            int i2 = i;
            for(int j=0; j<m.size(); j++)
            {
                int j2 = j;
                if(i != j)
                {
                    int d = Math.abs(m.get(i).getX() - m.get(j).getX()) + Math.abs(m.get(i).getY() - m.get(j).getY());

                    Connection con = connections.stream()
                            .filter(elem -> elem.getId_m1()==i2 && elem.getId_m2()==j2)
                            .findFirst()
                            .orElse(null);
                    if(con != null){
                        sigma += d*con.getCost()*con.getAmount();
                    }
                }
            }
        }
        return sigma;
    }
}
