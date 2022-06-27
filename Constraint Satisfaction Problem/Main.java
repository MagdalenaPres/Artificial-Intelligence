package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws FileNotFoundException
    {

    }

    private static void backtracking() throws FileNotFoundException
    {
        Backtracking backtracking = new Backtracking();

        //BinaryBoard board = new BinaryBoard("src/com/company/Data/binary_10x10");
        FutoshikiBoard board = new FutoshikiBoard("src/com/company/Data/futoshiki_6x6");

        //BinaryConstraint constraint = new BinaryConstraint();
        FutoshikiConstraint constraint = new FutoshikiConstraint("src/com/company/Data/futoshiki_6x6");

        ArrayList<Integer> iterations = new ArrayList<>();
        ArrayList<Integer> returnNumber = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        backtracking.run(board, constraint, iterations, 2, System.currentTimeMillis(), returnNumber);
        long endTime = System.currentTimeMillis();

        //System.out.println("Całkowity czas: "+ (endTime - startTime));
        //System.out.println("Wszystkie węzły: "+ iterations.size());
        //System.out.println("Wszystkie nawroty: "+ returnNumber.size());
    }

    private static void forward() throws FileNotFoundException
    {
        ForwardChecking forwardChecking = new ForwardChecking();

        //BinaryBoard board = new BinaryBoard("src/com/company/Data/binary_10x10");
        FutoshikiBoard board = new FutoshikiBoard("src/com/company/Data/futoshiki_6x6");

        //BinaryConstraint constraint = new BinaryConstraint();
        FutoshikiConstraint constraint = new FutoshikiConstraint("src/com/company/Data/futoshiki_6x6");

        ArrayList<Integer> iterations = new ArrayList<>();
        ArrayList<Integer> returnNumber = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        forwardChecking.run(board, constraint, iterations, 2, System.currentTimeMillis(), returnNumber);
        long endTime = System.currentTimeMillis();

        //System.out.println("Całkowity czas: "+ (endTime - startTime));
        //System.out.println("Wszystkie węzły: "+ iterations.size());
        //System.out.println("Wszystkie nawroty: "+ returnNumber.size());
    }
}
