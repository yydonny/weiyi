package pojo.impl;

import org.apache.commons.lang3.StringUtils;
import pojo.IChessBoard;
import pojo.IChessPiece;

import java.awt.*;
import java.util.ArrayList;
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
    public List<String> calculateMoves() {
        List<String> ret = new ArrayList<String>();
        for(Map.Entry chessPieceEntry:getPieces().entrySet()){
            IChessPiece chessPiece = (IChessPiece) chessPieceEntry.getValue();
            //White P on f7: [e8, f8]
            StringBuilder sb = new StringBuilder();
            if(chessPiece.getColor().equals("W")) sb.append("White ");
            if(chessPiece.getColor().equals("B")) sb.append("Black ");
            sb.append(chessPiece.getType());
            sb.append(" on ").append(mappingFromInternal(chessPiece.getPosition().x,
                    chessPiece.getPosition().y)).append(": ");

            List<Point> list = ((IChessPiece) chessPieceEntry.getValue()).calculateNextMove(this);
            List<String> mappedList = new ArrayList<String>();
            for(Point p:list){
                mappedList.add(mappingFromInternal(p.x, p.y));
            }
            sb.append("[").append(StringUtils.join(mappedList.toArray(), ',')).append("]");
            ret.add(sb.toString());
        }
        return ret;
    }

    /**
     * reset remove all pieces from the chess board
     */
    @Override
    public void reset() {
        getPieces().clear();
    }


    /**
     * Gets pieces
     *
     * @return value of pieces
     */
    private Map<Point, IChessPiece> getPieces() {
        return pieces;
    }


    /**
     * e.g. (x,y) = (1,1) is mapped to "b2"
     * @param x internal x-axis coordinate
     * @param y internal y axis coordinate
     * @return String of the position representation
     */
    public final static String mappingFromInternal(int x, int y){
        StringBuilder sb = new StringBuilder();
        sb.append((char)(x+97));
        sb.append(String.valueOf(y + 1));
        return sb.toString();
    }

    /**
     * e.g. "b2" is mapped to  (x,y) = (1,1)
     * @param xy the String representation of the position
     * @return internal Point
     */
    public final static Point mappingToInternal(String xy){
        char[] chars = xy.toCharArray();
        int x,y;
        if(chars.length<2) throw new IllegalArgumentException("Failed to parse the position");
        return new Point(chars[0]-97,Integer.valueOf(String.valueOf(chars[1]))-1);
    }

    final private Point boundary = new Point(7,7);

    //LinkedHashMap to retain the input order
    private Map<Point, IChessPiece> pieces = new LinkedHashMap<Point, IChessPiece>();


}
