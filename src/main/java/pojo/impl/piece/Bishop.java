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
            {
                //3.check if occupied, if occupied check if same color and if yes, back 1; no add to list
                //if not occupied, add to list
                IChessPiece existingPiece = iChessBoard.getPieceAt(possibleMove);
                if(existingPiece!=null)
                {
                    if(existingPiece.getColor()==this.getColor()){
                        //occupied by same color
                        break;
                    }else{//is captured same as move
                        if(capturedSameAsMovement()){
                            ret.add(possibleMove.getLocation());
                        }else{
                            break;
                        }
                    }
                }//existingPiece==null
                else{
                    ret.add(possibleMove.getLocation());
                }
            }
        }

        //4.continue 1 until no more position
        //5. if capture is different from movement, move to one capture
        //repeat 2.3.4


        return ret;
    }


    /**
     * Bishop does not have constraint apart from as long as in the chess board
     * @param iChessBoard chessboard it is placed
     * @param position    position to be placed
     * @param color       color of the piece
     * @return true when position is legal
     */
    @Override
    public boolean place(IChessBoard iChessBoard, Point position, String color) {
        if(super.place(iChessBoard,position,color))
        {
            setPosition(position);
            setColor(color);
            return true;
        }
        else return false;
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
