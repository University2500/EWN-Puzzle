import java.util.List;
import java.util.Random;
public class RandomPlayer extends Player{
    private Random random;
    // ============================================================
    public RandomPlayer(String playerName) {
        super(playerName);
        this.random = new Random();
    }

    // TODO: Implement chooseMove()
    @Override
    public GameState chooseMove(GameState currentState) {
        // 1. Get all possible legal moves
        List<GameState> possibleMoves = currentState.generatePossibleMoves();

        // 2. Handle the case where no moves exist (Game Over / Stalemate)
        if (possibleMoves.isEmpty()) {
            System.out.println("Random Player has no moves possible.");
            return currentState;
        }

        // 3. Pick a random index from the list
        int randomIndex = random.nextInt(possibleMoves.size());
        GameState selectedMove = possibleMoves.get(randomIndex);

        System.out.println("Random Player chose move #" + (randomIndex + 1) + " out of " + possibleMoves.size());

        return selectedMove;
    }
    // ------------------------------------------------------------
    // This method randomly choose the moves to solve the puzzle
    //
    // You may decide on the return type, parameters, and logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.
}
