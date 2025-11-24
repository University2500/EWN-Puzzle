import java.util.Scanner;

public class GameMain {

    public static void main(String[] args) {
        // ============================================================
        // TODO: Implement the main() function
        // ------------------------------------------------------------
        // This is the main entry point of the program.
        //
        // The function should perform the following tasks:
        Scanner input = new Scanner(System.in);
        System.out.println("Choose a game mode: (1 for Human Player, 2 for Random Player, 3 for AI player) ");
        int gameMode = input.nextInt();
        Player player = null;
        // 1. Prompt the user to choose a game mode:
        //      - Human Player
        //      - Random Player
        //      - AI Player
            switch(gameMode){
                case 1:
                    System.out.print("Enter player name: ");
                    String playerName = input.next();
                    player = new HumanPlayer(playerName);
                    break;
                case 2:
                    player = new RandomPlayer("Random Player");
                    break;
                case 3:
                    player = new AIPlayer("AI Player");
            }
        // 2. Create a player object based on the selected mode:
        //      - For Human Player => prompt for player name.
        //      - For Random Player => use default name "Random Player".
        //      - For AI Player => use default name "AI Player".
        //
        System.out.println("Select a level: (1-4)");
        int level = input.nextInt();
        // 3. Prompt the user to select a level.

        HumanPlayer.chooseMove();
        // 4. Call the chooseMove() function of the player
        //    to perform their move based on the current game state.
        GameState.isWinning();
        // 5. Display the result of the game
        //
        // ============================================================

        // You may also add any other helper functions, variables,
        // and constructors needed for your implementation.
    }
}

