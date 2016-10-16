package pojo;

import java.awt.*;
import java.util.List;

/**
 * <p>This is a interface for all chess pieces
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public interface IChessPiece {
    /**
     * @param iChessBoard chessboard it is placed
     * @return possible position for next move
     */
    public List<Point> calculateNextMove(IChessBoard iChessBoard);


    /**
     * @param iChessBoard chessboard it is placed
     * @return true when position is legal
     */
    public boolean place(IChessBoard iChessBoard);


    /**
     * @return position of this piece
     */
    public Point getPosition();

    /**
     * @return the color of the piece, i.e. W or B
     */
    public String getColor();
}
