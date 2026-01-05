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
            targetPiece = scanner.nextInt();

            for (int i = 0; i < 6; i++) {
                piecePositions[i] = scanner.nextInt();
            }

            while (scanner.hasNextInt()) {
                diceSequence.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Level file '" + filename + "' not found.");
            System.exit(1);
        }
    }
    public void printGameDetails() {
        // 1. Print to Console (for you to see)
        System.out.println("--- Loading Game Data ---");
        System.out.println("Target Piece: " + targetPiece);
        System.out.println("Positions: " + Arrays.toString(piecePositions));
        System.out.println("Dice Sequence: " + diceSequence);

        // 2. Write to File (your existing logic)
        try (PrintWriter writer = new PrintWriter(new FileWriter("moves.txt", true))) {
            // Print dice sequence
            for (int i = 0; i < diceSequence.size(); i++) {
                writer.print(diceSequence.get(i) + (i < diceSequence.size() - 1 ? " " : ""));
            }
            writer.println();
            writer.println(targetPiece);

            // Print positions
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