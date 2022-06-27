package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Board board = new Board();

        BotPlayer whitePlayer = new BotPlayer("minmax", true, true);
        BotPlayer blackPlayer = new BotPlayer("alfabeta", false, false);
        //Player blackPlayer = new HumanPlayer(secondName, false);

        whitePlayer.setSearchDepth(8);
        blackPlayer.setSearchDepth(8);

        Game game = new Game();
        game.run(whitePlayer,blackPlayer,  board, 2);
        System.out.println("Average time per move for "+ whitePlayer.getName()+ ": " +
                String.format("%.4f", game.getAverageTimeForMoveOfWhitePlayer()) + " milli seconds");
        System.out.println("Average number of visited nodes per move for "+
                whitePlayer.getName()+ ": " + game.getAverageNodeVisitedForWhitePlayer());

        System.out.println("Average time per move for "+ blackPlayer.getName()+ ": " +
                String.format("%.4f", game.getAverageTimeForMoveOfBlackPlayer()) + " milli seconds");
        System.out.println("Average number of visited nodes per move for "+
                blackPlayer.getName()+ ": " + game.getAverageNodeVisitedForBlackPlayer());

    }
}
