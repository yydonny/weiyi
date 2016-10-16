package ui.impl;

import org.springframework.stereotype.Component;
import pojo.IChessPiece;
import pojo.impl.ChessBoard;
import pojo.impl.piece.Bishop;
import ui.Proxy;

import java.util.List;

/**
 * <p>This is a Console UI implementation for input/output
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
@Component
public class Console implements Proxy {

    @Override
    public String readNumberOfPieces() {
        if (console != null) {
            System.out.println("FindValidMoves");
            String ret = console.readLine("Enter number of pieces: ");
            return ret;
        } else {
            System.out.println("Console is unavailable!");
        }
        return null;
    }

    @Override
    public IChessPiece readPieceInfo(int noOfPiece) {
        IChessPiece ret = null;

        if (console != null) {
            System.out.println("\nPiece "+noOfPiece);
            String color = console.readLine("Enter colour (W/B): ");
            String type = console.readLine("Enter type (B/N/P): ");
            String position = console.readLine("Enter position: ");
            switch(type){
                case "B":
                    ret = new Bishop(color, ChessBoard.mappingToInternal(position));
                    break;
                case "K":
                    break;
                default: ret = null;
            }
        } else {
            System.out.println("Console is unavailable!");
        }
        return ret;
    }

    @Override
    public boolean outputResult(List<String> results) {
        System.out.println("\nValid moves");
        for(String result:results) System.out.println(result);
        return true;
    }

    @Override
    public boolean next() {
        boolean ret;
        if (console != null) {
            String cont = console.readLine("\nContinue (Y/N)?: ");
            if (cont.equals("Y")) ret =true;
            else ret = false;
        } else {
            System.out.println("Console is unavailable!");
            ret = false;
        }
        return ret;
    }
    private java.io.Console console = System.console();
}
