package pojo.impl.piece;

import exception.InitialPositionOccupiedException;
import pojo.IChessBoard;
import pojo.IChessPiece;
import util.Helper;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * <p>This is an abstract class for all Pieces which contains the
 * point data structure. Subclass will need implement isInitialPositionLegal and
 * capturedSameAsMovement, findAllPossibleMoves
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public abstract class AbstractPiece implements IChessPiece{
    /**
     * this method will be implemented to realize movement and capture
     * path for different Piece
     * @param iChessBoard chessboard it is placed
     * @return the list of the possible positions for next movement
     */
    @Override
    public final List<Point> calculateNextMove(IChessBoard iChessBoard){
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
    };


    /**
     * This method should always be called to check the boundary constraint
     * Plus, this method will also check if the position has already been occupied so that
     * exception of illegal initial position will be thrown
     * Thus it is final and can not be overwritten in subclass
     * @param iChessBoard chessboard it is placed
     * @return true when position is legal
     */
    public final boolean place(IChessBoard iChessBoard) {
        if (iChessBoard == null || position == null || color == null)
            throw new IllegalArgumentException("iChessBoard pr position can not be null");
        if( (color!=null) && ! (color.equals("W") || color.equals("B") ))
            throw new IllegalArgumentException("color must be W or B");
        Point boundary = iChessBoard.getBoundary();
        if (Helper.inBound(position,boundary))
        {
            if(iChessBoard.getPieceAt(position)==null)return true && isInitialPositionLegal();
            else throw new InitialPositionOccupiedException("Initial position already occupied");
        }
        else {
            throw new InitialPositionOccupiedException("Initial position is out of boundary");
         }
    }

    protected abstract boolean isInitialPositionLegal();
    protected abstract boolean capturedSameAsMovement();
    protected abstract List<Point> findAllPossibleMoves(IChessBoard iChessBoard);

    public AbstractPiece(String color, Point position) {
        this.color = color;
        this.position = position;
    }


    private Point position;

    private String color;
    /**
     * Gets position
     *
     * @return value of position
     */
    public Point getPosition() {
        return position;
    }

    protected void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Gets color
     *
     * @return value of color
     */
    @Override
    public String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
    }
}
