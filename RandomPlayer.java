import java.util.Random;
public class RandomPlayer extends Player{
    // ============================================================
    public RandomPlayer(String playerName) {
        super(playerName);
    }
    public int chooseMove(){
            int[] chooseMoves = GameState.generatePossibleMoves();
            Random rand = new Random();
            int i =  rand.nextInt(1,(chooseMoves.length)-1);
            return chooseMoves[i];
    }
    // TODO: Implement chooseMove()

    // ------------------------------------------------------------
    // This method randomly choose the moves to solve the puzzle
    //
    // You may decide on the return type, parameters, and logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.
}
