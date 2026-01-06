import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameLoader {
    private int targetPiece;
    private int[] piecePositions = new int[6];
    private List<Integer> diceSequence = new ArrayList<>();

    public GameLoader(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            targetPiece = scanner.nextInt(); // Line 1 in input file

            for (int i = 0; i < 6; i++) {
                piecePositions[i] = scanner.nextInt(); // Line 2 in input file
            }

            while (scanner.hasNextInt()) {
                diceSequence.add(scanner.nextInt()); // Line 3 in input file
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Level file '" + filename + "' not found.");
            System.exit(1);
        }
    }

    // UPDATED: Now accepts playerName and overwrites the file correctly
    public void printGameDetails(String playerName) {

        // 1. Print to Console (Optional, for debugging)
        System.out.println("--- Loading Game Data ---");
        System.out.println("Target Piece: " + targetPiece);
        System.out.println("Positions: " + Arrays.toString(piecePositions));
        System.out.println("Dice Sequence: " + diceSequence);

        // 2. Write to File (CORRECTED)
        // 'false' here is CRITICAL. It clears the file to start a new game log.
        try (PrintWriter writer = new PrintWriter(new FileWriter("moves.txt", false))) {

            // Line 1: Player Name (Required by assignment)
            writer.println(playerName);

            // Line 2: Dice Sequence
            for (int i = 0; i < diceSequence.size(); i++) {
                writer.print(diceSequence.get(i) + (i < diceSequence.size() - 1 ? " " : ""));
            }
            writer.println();

            // Line 3: Target Piece
            writer.println(targetPiece);

            // Line 4: Initial Positions
            for (int i = 0; i < 6; i++) {
                writer.print(piecePositions[i] + (i < 5 ? " " : ""));
            }
            writer.println();

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public int getTargetPiece() {
        return targetPiece;
    }

    public int[] getPiecePositions() {
        int[] copy = new int[6];
        System.arraycopy(piecePositions, 0, copy, 0, 6);
        return copy;
    }

    public List<Integer> getDiceSequence() {
        return new ArrayList<>(diceSequence);
    }
}