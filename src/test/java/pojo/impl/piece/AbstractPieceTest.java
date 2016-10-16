package pojo.impl.piece;

import jdk.Exported;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.IChessBoard;
import pojo.impl.ChessBoard;
import util.Helper;
import util.TestHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>This is a UT for AbstractPiece with only implmenting the place method
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class AbstractPieceTest {

    private AbstractPiece abstractPiece = new AbstractPiece(null,null) {
        @Override
        public List<Point> calculateNextMove(IChessBoard iChessBoard) {
            return null;
        }
    };

    private IChessBoard iChessBoard = EasyMock.createMock(IChessBoard.class);


    @Test(expected = IllegalArgumentException.class)
    public void testPlaceWithWrongColor() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        assertTrue(abstractPiece.place(iChessBoard));
}

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceWithNullColor() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        assertTrue(abstractPiece.place(iChessBoard));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceWithNullPosition() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        assertTrue(abstractPiece.place(iChessBoard));
    }

    @Test
    public void testPlace() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        abstractPiece.setColor( "B");
        abstractPiece.setPosition(new Point(0, 0));
        assertTrue("in board test", abstractPiece.place(iChessBoard));

        abstractPiece.setColor( "B");
        abstractPiece.setPosition(new Point(11, 1));
        assertFalse("Out of board test",abstractPiece.place(iChessBoard));

    }
}