import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Player {
    protected String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    // Getter for the name (used in GameMain)
    public String getplayerName() {
        return this.playerName;
    }

    // ============================================================
    // 1. ABSTRACT FUNCTION: chooseMove()
    // ------------------------------------------------------------
    // We removed the body { ... } and added 'abstract'.
    // This forces HumanPlayer and RandomPlayer to implement their own.
    // ============================================================
    public abstract GameState chooseMove(GameState currentState);

    // ============================================================
    // 2. IMPLEMENTED: printMove()
    // ------------------------------------------------------------
    // Writes the new positions to "moves.txt" after a move is made.
    // ============================================================
    public void printMove(GameState state) {
        // "true" means APPEND to the file. We add to the existing list.
        try (PrintWriter out = new PrintWriter(new FileWriter("moves.txt", true))) {

            int[] positions = state.getPositions();

            // Loop through positions and print them with spaces
            for (int i = 0; i < positions.length; i++) {
                out.print(positions[i]);
                // Add a space only if it's not the last number
                if (i < positions.length - 1) {
                    out.print(" ");
                }
            }
            out.println(); // Move to next line for the next turn

        } catch (IOException e) {
            System.out.println("Error writing to moves.txt: " + e.getMessage());
        }
    }
}