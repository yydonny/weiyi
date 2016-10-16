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
    public List<String> calculateNextMove(IChessBoard iChessBoard);


    /**
     * @param iChessBoard chessboard it is placed
     * @param position position to be placed
     * @return true when position is legal
     */
    public boolean place(IChessBoard iChessBoard, Point position);


    /**
     * @return position of this piece
     */
    public Point getPosition();
}
