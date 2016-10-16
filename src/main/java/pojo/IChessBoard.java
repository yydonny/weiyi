package pojo;


import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * <p>This is a Interface for all ChessBoard
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public interface IChessBoard {

     /**
     * @param iChessPiece chessPiece to be placed
     * @return true when placement is allowed according to boundary and existing
     * pieces constraints
     */
    public boolean place(IChessPiece iChessPiece);

    /**
     * @return the maximum coordinate of the chess board
     * it is assumed (0,0) is the lower-left boundary point of
     * the chess board, and can not be changed
     */
    public Point getBoundary();

    /**
     * @param position the position of the piece in the chess board
     * @return IChessPiece instance for the existing piece, or else null
     */
    public IChessPiece getPieceAt(Point position);

    public List<String> play();
}
