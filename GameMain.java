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

        // 3. Initialize the Game State
        GameState currentState = new GameState(
                loader.getPiecePositions(),
                loader.getTargetPiece(),
                0,
                loader.getDiceSequence()
        );

        loader.printGameDetails();


        System.out.println("\n--- GAME START ---");

        // Loop while the game is NOT won and we still have dice rolls left
        while (!currentState.isWinning()) {

            // Check if we ran out of turns (dice rolls)
            if (currentState.getTurn() >= loader.getDiceSequence().size()) {
                System.out.println("Game Over: No more turns left!");
                break;
            }

            System.out.println("\nTurn " + (currentState.getTurn() + 1));

            // CRITICAL STEP:
            // 1. Player (Random or Human) chooses the move.
            // 2. We UPDATE 'currentState' with the result.
            currentState = player.chooseMove(currentState);

            // Optional: Print the board after the move
            System.out.println("Current Positions: " + Arrays.toString(currentState.getPositions()));
        }

        // ============================================================
        // 6. Check for Win Result
        // ============================================================
        if (currentState.isWinning()) {
            System.out.println("\n🎉 CONGRATULATIONS! " + player + " won the game! 🎉");
        } else {
            System.out.println("\nGame Over.");
        }
    }
}