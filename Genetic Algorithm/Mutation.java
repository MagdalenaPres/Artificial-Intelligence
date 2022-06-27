import java.util.Collections;
import java.util.Random;

public class Mutation {

    public Individual mutation(Individual individual){
        final int[] ints = new Random().ints(1, individual.getMachines().size()).distinct().limit(2).toArray();

        Collections.swap(individual.getMachines(), ints[0], ints[1]);

        return individual;
    }
}
