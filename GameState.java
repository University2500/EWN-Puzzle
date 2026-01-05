import java.util.ArrayList;
import java.util.List;

public class GameState {
    private int[] positions;
    private int target;
    private int turn;
    private List<Integer> diceSequence;

    public GameState(int[] initialPositions, int targetPiece, int startingTurn, List<Integer> diceSeq) {
        this.positions = new int[6];
        System.arraycopy(initialPositions, 0, this.positions, 0, 6);
        this.target = targetPiece;
        this.turn = startingTurn;
        this.diceSequence = diceSeq;
    }

    public GameState(GameState original) {
        this.positions = new int[6];
        System.arraycopy(original.positions, 0, this.positions, 0, 6);
        this.target = original.target;
        this.turn = original.turn;
        this.diceSequence = original.diceSequence;
    }

    public List<GameState> generatePossibleMoves() {
        List<GameState> moves = new ArrayList<>();

        if (turn >= diceSequence.size()) {
            return moves;
        }

        int diceRoll = diceSequence.get(turn);

        List<Integer> activePieces = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (positions[i] != -1) {
                activePieces.add(i + 1);
            }
        }

        List<Integer> movable = new ArrayList<>();
        if (activePieces.contains(diceRoll)) {
            movable.add(diceRoll);
        } else {

            Integer lower = null;
            Integer higher = null;
            for (int p : activePieces) {
                if (p < diceRoll && (lower == null || p > lower)) {
                    lower = p;
                }
                if (p > diceRoll && (higher == null || p < higher)) {
                    higher = p;
                }
            }
            if (lower != null) movable.add(lower);
            if (higher != null) movable.add(higher);
        }

        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int piece : movable) {
            int currentPos = positions[piece - 1];
            int row = currentPos / 10;
            int col = currentPos % 10;

            for (int d = 0; d < 8; d++) {
                int newRow = row + dRow[d];
                int newCol = col + dCol[d];

                if (newRow < 0 ||  newRow >= 10 || newCol < 0 || newCol >= 10) {
                    continue;
                }

                int newPos = newRow * 10 + newCol;

                if (newPos == 22) {
                    continue;
                }

                GameState next = new GameState(this);
                next.positions[piece - 1] = newPos;
                next.turn = this.turn + 1;

                for (int i = 0; i < 6; i++) {
                    if (positions[i] == newPos && positions[i] != -1) {
                        next.positions[i] = -1;
                        break;
                    }
                }

                moves.add(next);
            }
        }

        return moves;
    }

    public boolean isWinning() {
        return positions[target - 1] == 0;
    }

    public int getTurn() {
        return turn;
    }

    public int[] getPositions() {
        int[] copy = new int[6];
        System.arraycopy(positions, 0, copy, 0, 6);
        return copy;
    }
}
