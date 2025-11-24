import java.util.HashMap;
import java.util.Map;
public class GameState {

    // ============================================================
    // TODO: Implement generatePossibleMoves()
    // ------------------------------------------------------------
    public static void generatePossibleMoves(){
        int AvailableMoves[][] = new int[10][10];

        int counter=0;
        int position1 = 0,position2 = 0,position3 = 0,position4 = 0,position5 = 0,position6 = 0;
        //determinne which piece moving now
        int currentPiece=0;
        //Create a hashmap(dictionary for the piece info( number, position of piece)
        Map<Integer, Integer> pieceInfo = new HashMap<>();
        pieceInfo.put(1,position1);
        pieceInfo.put(2,position2);
        pieceInfo.put(3,position3);
        pieceInfo.put(4,position4);
        pieceInfo.put(5,position5);
        pieceInfo.put(6,position6);
        //Printing the 10x10 board
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                AvailableMoves[i][j]=counter;

                counter++;
            }

        }

        //Creates an array for the possible moves
        int[] possibleMoves = new int[8];
        //Gets the piece informatioon from hash map
        int InitialPosition = pieceInfo.get(currentPiece);
        AvailableMoves[InitialPosition/10][(InitialPosition%10)]= -1;

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(AvailableMoves[i][j]+" ");
            }
            System.out.println();
        }

        /*determining whether the piece is on an edge
        first if statement determines if it is on the right edge (69,79,89 etc)
        secoond if statement determines if it is on the left edge (50,30,20 etc)
        third if statement determines if it is on the top edge (0,1,2 etc)
        fourth if statement determine if it is on the bottom edge (90,,91,92 etc)
         */
        if(InitialPosition%10==9){
            possibleMoves[6]=-1;
            possibleMoves[2] =-1;
            possibleMoves[3] =-1;
        }
        if(InitialPosition%10==0){
            possibleMoves[7] = -1;
            possibleMoves[4] =-1;
            possibleMoves[5] =-1;
        }
        if(InitialPosition/10==0){
            possibleMoves[0]=-1;
            possibleMoves[2] =-1;
            possibleMoves[5] =-1;
        }
        if(InitialPosition/10==9){
            possibleMoves[1]=-1;
            possibleMoves[3] =-1;
            possibleMoves[4] =-1;
        }


        if (possibleMoves[0]!=-1){
            possibleMoves[0] = AvailableMoves[(InitialPosition/10)-1][(InitialPosition%10)]; //move up
        }
        if (possibleMoves[1]!=-1){
            possibleMoves[1] = AvailableMoves[(InitialPosition/10)+1][(InitialPosition%10)]; //move down
        }
        if (possibleMoves[2]!=-1){
            possibleMoves[2] = AvailableMoves[(InitialPosition/10)-1][(InitialPosition%10)+1]; //diagonal up right
        }
        if (possibleMoves[3]!=-1){
            possibleMoves[3] = AvailableMoves[(InitialPosition/10)+1][(InitialPosition%10)+1]; //diagonal down right
        }
        if (possibleMoves[4]!=-1){
            possibleMoves[4] = AvailableMoves[(InitialPosition/10)+1][(InitialPosition%10)-1]; //diagonal down left
        }
        if (possibleMoves[5]!=-1){
            possibleMoves[5] = AvailableMoves[(InitialPosition/10)-1][(InitialPosition%10)-1]; //diagonal up left
        }
        if (possibleMoves[6]!=-1){
            possibleMoves[6] = AvailableMoves[(InitialPosition/10)][(InitialPosition%10)+1]; //move right
        }
        if (possibleMoves[7]!=-1){
            possibleMoves[7] = AvailableMoves[(InitialPosition/10)][(InitialPosition%10)-1];  //move left
        }
        System.out.println("");
        for(int i=0;i<possibleMoves.length;i++){
            if(possibleMoves[i]!=-1){
                System.out.print(possibleMoves[i]+" ");
            }
        }
    }

    // This method should generate all possible moves based on:
    //  - The current piece positions
    //  - The current dice roll
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================


    // ============================================================
    // TODO: Implement isWinning()
    public static void isWinning(){

    }
    // ------------------------------------------------------------
    // This method should check whether the current piece positions
    // fulfill the winning condition
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================


    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.
}
