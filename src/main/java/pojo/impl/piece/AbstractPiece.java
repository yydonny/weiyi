package pojo.impl.piece;

import pojo.IChessBoard;
import pojo.IChessPiece;
import util.Helper;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * <p>This is an abstract class for all Pieces which contains the
 * point data structure. Subclass will need implement IChessPiece interface
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public abstract class AbstractPiece implements IChessPiece{
     /**
     * @param iChessBoard chessboard it is placed
     * @return possible position for next move
     */
    @Override
    public abstract List<Point> calculateNextMove(IChessBoard iChessBoard);


    /**
     * This method should always be called in child class to check the boundary constraint
     * @param iChessBoard chessboard it is placed
     * @param position position to be placed
     * @return true when position is legal
     */
    public boolean place(IChessBoard iChessBoard, Point position, String color) {
        if (iChessBoard == null || position == null || color == null)
            throw new IllegalArgumentException("iChessBoard pr position can not be null");
        if( (color!=null) && ! (color.equals("W") || color.equals("B") ))
            throw new IllegalArgumentException("color must be W or B");
        Point boundary = iChessBoard.getBoundary();
        if (Helper.inBound(position,boundary))
            return true;
        else return false;
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
