package main;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Board board = new Board();

        MainVisual visual = new MainVisual();

        Scanner scanner = new Scanner(System.in);

        board.display();

        while(!board.gameOver()) {



            /*
            System.out.println("Make your move");
            String inputCommand = scanner.nextLine();

            if(inputCommand.equals("D")){
                board.flipThroughDiscard();
                board.display();
                continue;
            }

            if(inputCommand.isEmpty())
                continue;

            char[] charArray = inputCommand.toCharArray();
            char secondLast = charArray[charArray.length - 2];
            char last = charArray[charArray.length - 1];

            if (last >= 1 && last <= 7) {
                if (charArray[1] == '-' && Character.isDigit(last)) {
                    if (charArray[0] == 'D') {
                        if (secondLast == 'F') {
                            board.move(board.discard, board.foundationPiles[Character.getNumericValue(last) - 1]);
                            board.display();
                        } else {
                            board.move(board.discard, board.tablePiles[Character.getNumericValue(last) - 1]);
                            board.display();
                        }
                    } else if (secondLast == 'F' && Character.isDigit(charArray[0])) {
                        board.move(board.tablePiles[Character.getNumericValue(charArray[0]) - 1], board.foundationPiles[Character.getNumericValue(last) - 1]);
                        board.display();

                    } else if (Character.isDigit(charArray[0])) {
                        board.move(board.tablePiles[Character.getNumericValue(charArray[0]) - 1], board.tablePiles[Character.getNumericValue(last) - 1]);
                        board.display();

                    }
                } else {
                    System.out.println("Invalid Entry Please use the format:\nPileFrom-PileTo\nIf one of the piles" +
                            " is discard type D, and if one is a foundation add F directly before the number");

                }
            } else {
                System.out.println("Entry out of range, please enter a pile betwteen 1 and 7");

            }
            */
        }
    }
}
