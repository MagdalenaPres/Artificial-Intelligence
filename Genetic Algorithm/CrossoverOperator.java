import java.util.ArrayList;

public class CrossoverOperator
{
    public Individual crossover(Individual mother, Individual father){
        Individual child = new Individual();
        int partition = mother.getMachines().size() / 2;
        ArrayList<Machine> machines = new ArrayList<>();

        for(int i = 0; i<partition; i++){
            machines.add(mother.getMachines().get(i));
        }
        for(int i = 0; i<father.getMachines().size(); i++){
            if(!machines.contains(father.getMachines().get(i))){
                machines.add(father.getMachines().get(i));
            }
        }
        child.setMachines(machines);

        return child;
    }

}
