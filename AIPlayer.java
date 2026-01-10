import java.util.*;

public class AIPlayer extends Player {

    private static final int TARGET_SQUARE = 0;

    public AIPlayer(String name) {
        super(name);
    }

    @Override
    public GameState chooseMove(GameState currentState) {
        // 1. Setup A* Search
        PriorityQueue<SearchNode> open = new PriorityQueue<>();
        Set<String> visited = new HashSet<>(); // To avoid checking the same state twice

        // Create the start node wrapping the current game state
        SearchNode start = new SearchNode(currentState, 0, null);
        open.offer(start);

        // 2. A* Loop
        while (!open.isEmpty()) {
            SearchNode node = open.poll();
            GameState state = node.state;

            // --- Skip if visited ---
            // We use toString() because it contains the unique state description
            // Or you can create a custom string key if toString isn't unique enough
            String stateKey = Arrays.toString(state.getPositions()) + "-" + state.getTurn();
            if (visited.contains(stateKey)) continue;
            visited.add(stateKey);

            // --- Goal Check ---
            if (state.isWinning()) {
                // Return the IMMEDIATE next move that leads to this win
                return node.firstMove;
            }

            // --- Depth Limit (Safety) ---
            if (node.depth >= 50) continue;

            // --- Expand Nodes ---
            // This uses the logic you ALREADY wrote in GameState
            List<GameState> possibleMoves = state.generatePossibleMoves();

            for (GameState nextState : possibleMoves) {
                // If we are at root (depth 0), the "firstMove" is the nextState itself.
                // Otherwise, keep passing down the existing firstMove.
                GameState firstMoveForChild = (node.depth == 0) ? nextState : node.firstMove;

                open.offer(new SearchNode(nextState, node.depth + 1, firstMoveForChild));
            }
        }

        // 3. Fallback: If no winning path is found, pick a random legal move
        List<GameState> fallbackMoves = currentState.generatePossibleMoves();
        if (!fallbackMoves.isEmpty()) {
            System.out.println("AI could not find a winning path, playing randomly.");
            return fallbackMoves.get(new Random().nextInt(fallbackMoves.size()));
        }

        return currentState; // No moves possible (Game Over)
    }

    // --- Heuristic: Chebyshev Distance ---
    private int getHeuristic(GameState state) {
        int targetPiece = state.getTargetPiece();
        // Adjust for 0-based index vs 1-based piece ID
        int pos = state.getPositions()[targetPiece - 1];

        if (pos == -1) return 1000; // High penalty if target is dead

        int row = pos / 10;
        int col = pos % 10;

        // Distance to (0,0) is simply max(row, col) in Chebyshev logic
        return Math.max(row, col);
    }

    // --- Search Node Wrapper ---
    private class SearchNode implements Comparable<SearchNode> {
        GameState state;
        int depth;      // g(n) cost so far
        int heuristic;  // h(n) estimated cost to goal
        GameState firstMove; // The move to return to GameMain

        SearchNode(GameState state, int depth, GameState firstMove) {
            this.state = state;
            this.depth = depth;
            this.firstMove = firstMove;
            this.heuristic = getHeuristic(state);
        }

        // f(n) = g(n) + h(n)
        public int getF() {
            return depth + heuristic;
        }

        @Override
        public int compareTo(SearchNode other) {
            return Integer.compare(this.getF(), other.getF());
        }
    }
}