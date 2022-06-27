import java.util.ArrayList;
import java.util.Collections;

public class SelectionOperator {

    public Individual tournament(int N, ArrayList<Individual> population){
        Collections.shuffle(population);

        if(N < 1 || N > population.size()){
            N = 5;
        }
        Individual ind = population.get(0);

        for(int i = 1; i < N; i++){
            if(population.get(i).getFitnessValue() < ind.getFitnessValue()){
                ind = population.get(i);
            }
        }
        return ind;
    }

    public Individual roulette(ArrayList<Individual> population)
    {
        Individual selectedValue = null;
        countProbability(population);
        float randomNumber = (float) Math.random();
        float roulette = 0;

        for(Individual i : population){
            roulette += i.getProbability();

            if(roulette >= randomNumber){
                selectedValue = i;
                break;
            }
        }
        return selectedValue;
    }

    private void countProbability(ArrayList<Individual> population){
        double sigmaSum = 0;

        for(Individual i : population){
            sigmaSum += i.getFitnessValue();
        }

        for(Individual i : population){
            i.setProbability((float) (1- (i.getFitnessValue() / sigmaSum))/(population.size()-1));
        }
    }
}
