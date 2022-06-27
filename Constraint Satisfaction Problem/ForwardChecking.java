package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ForwardChecking {
    private ArrayList<Integer[][]> results = new ArrayList<>();
    Heuristics h = new Heuristics();

    public void run(Board board, Constraint constraint, ArrayList<Integer> iterations, int option, long startTime, ArrayList<Integer> returnNumber)
    {
        if(!setCurrentDomains(board,constraint)){
            returnNumber.add(1);
            return;
        }
        Variable currentVariable = switch (option) {
            case 0 -> h.inSequence(board);
            case 1 -> h.random(board);
            case 2 -> h.leastNumerousDomain(board);
            case 3 -> h.mostNumerousDomain(board);
            default -> h.inSequence(board);
        };

        if(currentVariable != null)
        {
            //Map<Integer, Integer> values = new HashMap<>();
            //for(int i = 0; i < board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getCurrentDomain().size(); i++){
            //    values.put(board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getCurrentDomain().get(i), 0);
            //}

            ArrayList<Integer> usedNumbers = new ArrayList<>();
            for(int i = 0; i < board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getCurrentDomain().size(); i++)
            {
                //int value = h.leastFrequentlyAppearingValue(board, values, usedNumbers);
                //usedNumbers.add(value);

                //board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].setValue(value);
                //iterations.add(1);
                //run(board, constraint, iterations, option, startTime, returnNumber);
                //board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].setValue(-1);

                board.getBoard()[currentVariable.getRow()][currentVariable.getCol()]
                        .setValue(board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].getCurrentDomain().get(i));
                iterations.add(1);
                run(board, constraint, iterations, option, startTime, returnNumber); //idę w głąb
                board.getBoard()[currentVariable.getRow()][currentVariable.getCol()].setValue(-1);
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
            System.out.println(System.currentTimeMillis() - startTime);
            //if(results.size() == 1){
            //    System.out.println("Węzły do pierwszego: "+ iterations.size());
            //    System.out.println("Nawroty do pierwszego: "+ returnNumber.size());
            //    System.out.println("Czas do pierwszego: " + (System.currentTimeMillis() - startTime));
            //}
        }
    }
    private boolean setCurrentDomains(Board board, Constraint constraint)
    {
        for(int i = 0; i < board.getSize(); i++)
        {
            for(int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    ArrayList<Integer> newDomain = new ArrayList<>();
                    for(int k = 0; k < board.getBoard()[i][j].getDomain().size(); k++)
                    {
                        if(constraint.isOk(board, i, j, board.getBoard()[i][j].getDomain().get(k)))
                        {
                            newDomain.add(board.getBoard()[i][j].getDomain().get(k));
                        }
                    }
                    if(newDomain.size() == 0)
                    {
                        return false;
                    }
                    else
                    {
                        board.getBoard()[i][j].setCurrentDomain(newDomain);
                    }
                }
            }
        }
        return true;
    }

    private void showDomains(Board board) {
        for (int row = 0; row < board.getSize(); row++)
        {
            for (int col = 0; col < board.getSize(); col++)
            {
                if (board.getBoard()[row][col].getValue() == -1)
                {
                    System.out.print(board.getBoard()[row][col].getCurrentDomain() + " ");
                }
                else
                {
                    System.out.print("*" +board.getBoard()[row][col].getValue() + "*" );
                }
            }
            System.out.println();
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
            System.out.println(i + 1);
            System.out.println();
        }
    }
}

