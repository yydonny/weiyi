package pojo.impl.piece;

import pojo.IChessBoard;
import util.Helper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This is a Bishop implementation
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class Pawn extends AbstractPiece {

    @Override
    protected boolean isInitialPositionLegal() {
        if(getPosition().getY()<1||getPosition().getY()>6) throw new IllegalArgumentException("Pawn can only be placed in rank 2-7.");
        else return true;
    }

    public Pawn(String color, Point position) {
        super(color, position);
    }

    protected List<Point> findAllPossibleMoves(IChessBoard iChessBoard){
        List<Point> ret = new ArrayList<Point>();
        Point bound = iChessBoard.getBoundary();
        //this.getPosition()-->List<Point>
        for(int[] direction: Pawn.directions)
        {   boolean outOfBound = false;
            int steps = 1; // how many steps in this direction to be checked
            int multiplier = 1;//sign for different colored pawn
            if(getColor().equals("B"))  multiplier = -1;
            int maxSteps = 1;

            if(getColor().equals("B")&& getPosition().y>5) maxSteps = 2;
            if(getColor().equals("W")&& getPosition().y<2) maxSteps = 2;

            while(!outOfBound && steps<=maxSteps)
            {
                Point nextPosition =  new Point(
                        (int)getPosition().getX()+direction[0] * steps * multiplier,
                        (int)getPosition().getY()+direction[1] * steps * multiplier
                );
                //check if in boundary
                if(Helper.inBound(nextPosition,bound)) ret.add(nextPosition);
                    else outOfBound = true;//go for next direction of the move
                steps++;
            }
        }
        return ret;
    }

    @Override
    protected boolean capturedSameAsMovement(){return false;}

    //one moving directions of pawn
    private static final int[][] directions = {
            {0,1}
    };

    /**
     * @return type of the piece
     */
    @Override
    public String getType() {
        return "P";
    }
}
