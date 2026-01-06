import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(String playerName) {
        super(playerName);
    }
    // ============================================================
    // TODO: Implement chooseMove()
    public GameState chooseMove(GameState currentState) {
        // 1. Get all possible legal moves
        // Import this at the top of your file

// ... inside your method ...

        List<GameState> possibleMoves = currentState.generatePossibleMoves();

// 2. Handle the case where no moves exist
        if (possibleMoves.isEmpty()) {
            System.out.println("Human Player has no moves possible.");
            return currentState;
        }

// 3. Display available moves to the user
        System.out.println("--- Available Moves ---");
        for (int i = 0; i < possibleMoves.size(); i++) {
            // Note: Ensure your GameState class has a good .toString() method!
            System.out.println("[" + (i + 1) + "] " + possibleMoves.get(i).toString());
        }

// 4. Get User Input with Validation
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

// Loop until the user provides a valid index
        while (choice < 1 || choice > possibleMoves.size()) {
            System.out.print("Enter the number of your move (1-" + possibleMoves.size() + "): ");

            // Check if input is actually an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice < 1 || choice > possibleMoves.size()) {
                    System.out.println("Invalid choice. Please pick a number from the list.");
                }
            } else {
                System.out.println("That's not a number. Please try again.");
                scanner.next(); // Consume the invalid input to avoid an infinite loop
            }
        }

// 5. Convert user choice (1-based) to list index (0-based)
        GameState selectedMove = possibleMoves.get(choice - 1);

        System.out.println("You chose move #" + choice);

        return selectedMove;
    }
    // ------------------------------------------------------------
    // This method prompts the human player to choose the next move
    //
    // You may decide on the return type, parameters, and logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.
}
