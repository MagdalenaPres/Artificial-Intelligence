import java.util.ArrayList;


public class Main {
    private static final String easy_cost = "D:/Studia/Sem6/Sztuczna Inteligencja/Laby/Zadanie1/src/easy_cost.json";
    private static final String easy_flow = "D:/Studia/Sem6/Sztuczna Inteligencja/Laby/Zadanie1/src/easy_flow.json";

    private static final String flat_cost = "D:/Studia/Sem6/Sztuczna Inteligencja/Laby/Zadanie1/src/flat_cost.json";
    private static final String flat_flow = "D:/Studia/Sem6/Sztuczna Inteligencja/Laby/Zadanie1/src/flat_flow.json";

    private static final String hard_cost = "D:/Studia/Sem6/Sztuczna Inteligencja/Laby/Zadanie1/src/hard_cost.json";
    private static final String hard_flow = "D:/Studia/Sem6/Sztuczna Inteligencja/Laby/Zadanie1/src/hard_flow.json";

    public static void main(String[] args)
    {
        Data d = new Data();
        Algorithm a = new Algorithm();
        DataGenerator dg = new DataGenerator();
        RandomAlgorithm randomAlgorithm = new RandomAlgorithm();

        ArrayList<Connection> connections = d.createConnectionsList(hard_cost,hard_flow);
        //liczba osobnikow w populacji
        ArrayList<Individual> population = dg.createPopulation(100, 24, 5, 6,
                hard_cost,hard_flow);

        a.geneticAlgorithm(1000, population, connections, 0.4f, 0.2f);
        //System.out.println(randomAlgorithm.randomPositionMethod(100000, connections));

    }
}
