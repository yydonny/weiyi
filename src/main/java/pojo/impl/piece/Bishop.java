package pojo.impl.piece;

import pojo.IChessBoard;
import pojo.IChessPiece;

import java.awt.*;
import java.util.List;

/**
 * <p>This is a Bishop implementation
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class Bishop extends AbstractPiece {
    @Override
    public List<String> calculateNextMove(IChessBoard iChessBoard) {
        return null;
    }


    /**
     * Bishop does not have constraint apart from as long as in the chess board
     * @param iChessBoard chessboard it is placed
     * @param position    position to be placed
     * @return true when position is legal
     */
    @Override
    public boolean place(IChessBoard iChessBoard, Point position) {
        if(super.place(iChessBoard,position))
        {
            setPosition(position);
            return true;
        }
        else return false;
    }


}
