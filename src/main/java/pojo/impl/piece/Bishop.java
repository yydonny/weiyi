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

    @Override
    protected boolean isInitialPositionLegal() {
        return true;
    }

    public Bishop(String color, Point position) {
        super(color, position);
    }

    protected List<List<Point>> findAllPossibleMoves(IChessBoard iChessBoard){
        List<List<Point>> ret = new ArrayList<List<Point>>();
        Point bound = iChessBoard.getBoundary();
        //this.getPosition()-->List<Point>
        for(int[] direction:Bishop.directions)
        {   boolean outOfBound = false;
            int steps = 1; // how many steps in this direction to be checked
            List<Point> innerRet = new ArrayList<Point>();
            while(!outOfBound)
            {
                Point nextPosition =  new Point(
                        (int)getPosition().getX()+direction[0] * steps,
                        (int)getPosition().getY()+direction[1] * steps
                );
                //check if in boundary
                if(Helper.inBound(nextPosition,bound)) innerRet.add(nextPosition);
                    else outOfBound = true;//go for next direction of the move
                steps++;
            }
            if(innerRet.size()>0)ret.add(innerRet);
            else innerRet = null;
        }
        return ret;
    }

    @Override
    protected boolean capturedSameAsMovement(){return true;}

    //four moving directions of bishop
    private static final int[][] directions = {
            {-1,-1},
            {1,-1},
            {1,1},
            {-1,1}
    };

    /**
     * @return type of the piece
     */
    @Override
    public String getType() {
        return "B";
    }
}
