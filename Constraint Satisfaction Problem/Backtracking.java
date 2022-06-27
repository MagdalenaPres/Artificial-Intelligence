package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Backtracking
{
    private ArrayList<Integer[][]> results = new ArrayList<>();
    Heuristics h = new Heuristics();

    public void run(Board board, Constraint constraint, ArrayList<Integer> iterations, int option, long startTime, ArrayList<Integer> returnNumber)
    {
        //Map<Integer, Integer> values = new HashMap<>();
        //for(int i = 0; i < board.getBoard()[0][1].getDomain().size(); i++){
        //    values.put(board.getBoard()[0][1].getDomain().get(i), 0);
        //}

        Variable currentVariable = switch (option) {
            case 0 -> h.inSequence(board);
            case 1 -> h.random(board);
            case 2 -> h.leastNumerousDomain(board);
            case 3 -> h.mostNumerousDomain(board);
            default -> h.inSequence(board);
        };

        if(currentVariable != null)
        {
            ArrayList<Integer> usedNumbers = new ArrayList<>();
            for (int index = 0 ; index < board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getDomain().size() ; index++)
            {
                //int value = h.leastFrequentlyAppearingValue(board, values, usedNumbers);
                //usedNumbers.add(value);

                //if (constraint.isOk(board, currentVariable.getRow(),currentVariable.getCol(), value))
                //{
                //    board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].setValue(value);
                //    iterations.add(1);
                //    run(board, constraint, iterations, option, startTime, returnNumber);
                //    board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].setValue(-1);
                //}
                if (constraint.isOk(board, currentVariable.getRow(),currentVariable.getCol(),
                        board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getDomain().get(index)))
                {
                    board.getBoard()[currentVariable.getRow()][currentVariable.getCol()]
                            .setValue(board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getDomain().get(index));
                    iterations.add(1);
                    run(board, constraint, iterations, option, startTime, returnNumber);
                    board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].setValue(-1);
                }
                else{
                    returnNumber.add(1);
                }
            }
        }
        else{
            Integer[][] newBoard = new Integer[board.getSize()][board.getSize()];
            for(int i = 0; i<board.getSize(); i++){
                for(int j = 0; j< board.getSize(); j++){
                    newBoard[i][j] = board.getBoard()[i][j].getValue();
                }
            }
            results.add(newBoard);

            //if(results.size() == 1){
            //    System.out.println("Węzły do pierwszego: "+ iterations.size());
            //    System.out.println("Nawroty do pierwszego: "+ returnNumber.size());
            //    System.out.println("Czas do pierwszego: " + (System.currentTimeMillis() - startTime));
            //}
        }
    }

    public void displayResults()
    {
        for(int i = 0; i < results.size(); i++)
        {
            for(int j = 0; j < results.get(i).length; j++)
            {
                for(int k = 0; k < results.get(i).length; k++)
                {
                    System.out.print(" " + results.get(i)[j][k]);
                }
                System.out.println();
            }
            System.out.println(i+1);
        }
    }
}
