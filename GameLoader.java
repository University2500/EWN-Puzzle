import java.io.*;
import java.util.*;

public class GameLoader{
    private int level;
    private int targetPiece;   
    private int maxMoves;        
    private ArrayList<Integer> diceSequence; 
    private HashMap<Integer, Integer> piecePosition;
    public GameLoader(String filename){
        diceSequence = new ArrayList<>();
        piecePosition = new HashMap<>();
        try{
            Scanner scanner = new Scanner(new File(filename));
            level = Integer.parseInt(scanner.nextLine().trim());
            targetPiece = Integer.parseInt(scanner.nextLine().trim());
            maxMoves = Integer.parseInt(scanner.nextLine().trim());
            String diceLine = scanner.nextLine().trim();
            String[] numbers = diceLine.split("\\s+");
            for (String num : numbers) diceSequence.add(Integer.parseInt(num));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                int piece = Integer.parseInt(parts[0]);
                int position = Integer.parseInt(parts[1]);
                piecePosition.put(piece, position); 
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + filename);
        } catch (Exception e){
            System.out.println("Error reading file: " + filename);
        }
    }
    public void printGameDetails() {
        try (PrintWriter writer = new PrintWriter("moves.txt")){
            writer.println("=== EWN Variant - Level " + level + " ===");
            writer.println("Target Piece: " + targetPiece);
            writer.println("Maximum Moves: " + maxMoves);
            writer.println();
            writer.print("Dice Sequence: ");
            for (int i = 0; i < diceSequence.size(); i++){
                writer.print(diceSequence.get(i));
                writer.print(" ");
            }
            writer.println("\n");
            writer.println("Initial Piece Positions:");
            writer.println("Piece   Position");
            writer.println("-----   ---------");
            for (int p = 1; p <= 6; p++){
                Integer pos = piecePosition.get(p);
                String status = "";
                if (pos == null){
                    status = "not placed";
                } else if (pos == -1){
                    status = "captured";
                } else{
                    status = pos.toString();
                }
                writer.printf("%5d   %s%n", p, status);
            }
            writer.println("\n-----------------------------------");
            writer.println("Game simulation starts below.....");
            writer.println();
        } catch (Exception e){
            System.out.println("Error: Cannot write to moves.txt");
        }
    }
    public int getLevel(){ return level; }
    public int getTargetPiece(){ return targetPiece; }
    public int getMaxMoves(){ return maxMoves; }
    public ArrayList<Integer> getDiceSequence(){ return diceSequence; }
    public HashMap<Integer, Integer> getPiecePosition(){ return piecePosition; }
    public int getPositionOf(int piece) {
        Integer pos = piecePosition.get(piece);
        return (pos == null) ? -999 : pos;
    }
    public static void main(String[] args) {
        GameLoader loader = new GameLoader("gamedata.txt");
        loader.printGameDetails();
        System.out.println("GameLoader executed successfully! Check moves.txt");
    }
}
