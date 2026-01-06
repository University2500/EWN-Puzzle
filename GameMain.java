import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Choose Game Mode
        System.out.println("Choose a game mode: (1 for Human Player, 2 for Random Player, 3 for AI player) ");
        int gameMode = input.nextInt();
        Player player = null;

        switch(gameMode) {
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
                break;
        }

        // 2. Select Level and Load Data
        System.out.println("Select a level: (1-4)");
        int level = input.nextInt();
        String filename = "TestCases/level" + level + ".txt"; // Ensure folder path is correct

        GameLoader loader = new GameLoader(filename);
        loader.printGameDetails(player.getplayerName());
        // 3. Initialize the Game State
        GameState currentState = new GameState(
                loader.getPiecePositions(),
                loader.getTargetPiece(),
                0,
                loader.getDiceSequence()
        );




        System.out.println("\n--- GAME START ---");

        // Loop while the game is NOT won and we still have dice rolls left
        while (!currentState.isWinning()) {

            // 1. Ask player for the next move
            GameState nextState = player.chooseMove(currentState);

            // 2. Update the current state
            currentState = nextState;

            // 3. CRITICAL: Save the move to moves.txt
            // If this line is missing, the file will stop at line 4!
            player.printMove(currentState);

            // Safety check to stop infinite loops if needed
            if (currentState.getTurn() >= loader.getDiceSequence().size()) {
                System.out.println("No more dice rolls!");
                break;
            }
        }

        // ============================================================
        // 6. Check for Win Result
        // ============================================================
        if (currentState.isWinning()) {
            System.out.println("\n🎉 CONGRATULATIONS! " + player.getplayerName() + " won the game! 🎉");
        } else {
            System.out.println("\nGame Over.");
        }

    }
}