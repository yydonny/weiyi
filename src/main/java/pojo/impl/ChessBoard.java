package pojo.impl;

import pojo.IChessBoard;
import pojo.IChessPiece;

import java.awt.*;
import java.util.List;

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

    @Override
    public List<String> play() {
        return null;
    }


    final private Point boundary = new Point(7,7);
}
