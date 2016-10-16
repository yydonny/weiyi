package pojo.impl.piece;

import pojo.IChessBoard;
import pojo.IChessPiece;
import util.Helper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>This is a Bishop implementation
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class Bishop extends AbstractPiece {
    /**
     * this method will be implemented to realize movement and capture
     * path for different Piece
     * @param iChessBoard chessboard it is placed
     * @return the list of the possible positions for next movement
     */
    @Override
    public List<Point> calculateNextMove(IChessBoard iChessBoard) {
        //move while checking blocking, and capture
        List<Point> moves = findAllPossibleMoves(iChessBoard);

        List<Point> ret = new ArrayList<Point>();
        Point bound = iChessBoard.getBoundary();
        //1.move to one position
        for(Point possibleMove:moves)
        {
                //2.check if occupied, if occupied check if same color and if yes, back 1; no add to list
                //if not occupied, add to list
                IChessPiece existingPiece = iChessBoard.getPieceAt(possibleMove);
                if(existingPiece!=null)
                {
                    if(existingPiece.getColor()==this.getColor()){
                        //occupied by same color
                        continue;
                    }else{//is captured same as move
                        if(capturedSameAsMovement()){
                            ret.add(possibleMove.getLocation());
                        }else{
                            continue;
                        }
                    }
                }//existingPiece==null
                else{
                    ret.add(possibleMove.getLocation());
                }
            //3. if capture is different from movement, move to one capture
            if(!capturedSameAsMovement()){
                //TODO
                    }
            //4.continue 1 until no more position
        }

        return ret;
    }


    /**
     * Bishop does not have constraint apart from as long as in the chess board,
     * it will NOT add the piece into the chess board if no issue as adding a piece is
     * left to ChessBoard to do.
     * @param iChessBoard chessboard it is placed
     * @return true when position is legal
     */
    @Override
    public boolean place(IChessBoard iChessBoard) {
        if(super.place(iChessBoard))
        {
            return true;
        }
        else return false;
    }

    public Bishop(String color, Point position) {
        super(color, position);
    }

    private List<Point> findAllPossibleMoves(IChessBoard iChessBoard){
        List<Point> ret = new ArrayList<Point>();
        Point bound = iChessBoard.getBoundary();
        //this.getPosition()-->List<Point>
        for(int[] direction:Bishop.directions)
        {   boolean outOfBound = false;
            int steps = 1; // how many steps in this direction to be checked
            while(!outOfBound)
            {
                Point nextPosition =  new Point(
                        (int)getPosition().getX()+direction[0] * steps,
                        (int)getPosition().getX()+direction[1] * steps
                );
                //check if in boundary
                if(Helper.inBound(nextPosition,bound)) ret.add(nextPosition);
                    else outOfBound = true;//go for next direction of the move
                steps++;
            }
        }
        return ret;
    }

    private boolean capturedSameAsMovement(){return true;}

    //four moving directions of bishop
    private static final int[][] directions = {
            {-1,-1},
            {1,-1},
            {1,1},
            {-1,1}
    };
}
