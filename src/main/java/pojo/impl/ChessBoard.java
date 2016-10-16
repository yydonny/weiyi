package pojo.impl;

import pojo.IChessBoard;
import pojo.IChessPiece;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>This is a IChessBoard implementation
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class ChessBoard implements IChessBoard{
    /**
     * @param iChessPiece chessPiece to be placed
     * @return
     */
    @Override
    public boolean place(IChessPiece iChessPiece) {
        if(iChessPiece.place(this))
        {
            getPieces().put(iChessPiece.getPosition(),iChessPiece);
            return true;
        };
        return false;
    }


    /**
     * @return the maximum coordinate of the chess board
     * it is assumed (0,0) is the lower-left boundary point of
     * the chess board, and can not be changed
     */
    @Override
    public Point getBoundary() {
        return boundary;
    }

    /**
     * @param position the position of the piece in the chess board
     * @return IChessPiece instance for the existing piece, or else null
     */
    @Override
    public IChessPiece getPieceAt(Point position) {
        return getPieces().get(position);
    }

    @Override
    public List<String> play() {
        return null;
    }


    /**
     * Gets pieces
     *
     * @return value of pieces
     */
    private Map<Point, IChessPiece> getPieces() {
        return pieces;
    }

    final private Point boundary = new Point(7,7);

    private Map<Point, IChessPiece> pieces = new LinkedHashMap<Point, IChessPiece>();


}
