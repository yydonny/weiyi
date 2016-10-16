package pojo.impl.piece;

import exception.InitialPositionOccupiedException;
import jdk.Exported;
import org.easymock.EasyMock;
import org.junit.Test;
import pojo.IChessBoard;
import util.TestHelper;

import java.awt.*;
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

    private AbstractPiece abstractPieceAnywhereInitSameCaptureAsMovement = new AbstractPiece(null,null) {
        @Override
        protected boolean isInitialPositionLegal() {
            return true;
        }

        @Override
        protected boolean capturedSameAsMovement() {
            return true;
        }

        @Override
        protected List<Point> findAllPossibleMoves(IChessBoard iChessBoard) {
            return null;
        }

        public Point getPosition() {
            return super.getPosition();
        }

        @Override
        public String getType() {
            return null;
        }
    };

    private IChessBoard iChessBoard = EasyMock.createMock(IChessBoard.class);


    @Test(expected = IllegalArgumentException.class)
    public void testPlaceWithWrongColor() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        assertTrue(abstractPieceAnywhereInitSameCaptureAsMovement.place(iChessBoard));
}

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceWithNullColor() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        assertTrue(abstractPieceAnywhereInitSameCaptureAsMovement.place(iChessBoard));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceWithNullPosition() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        assertTrue(abstractPieceAnywhereInitSameCaptureAsMovement.place(iChessBoard));
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testPlace() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);
        abstractPieceAnywhereInitSameCaptureAsMovement.setColor("B");
        abstractPieceAnywhereInitSameCaptureAsMovement.setPosition(new Point(0, 0));
        assertTrue("in board test", abstractPieceAnywhereInitSameCaptureAsMovement.place(iChessBoard));

        abstractPieceAnywhereInitSameCaptureAsMovement.setColor("B");
        abstractPieceAnywhereInitSameCaptureAsMovement.setPosition(new Point(11, 1));
        abstractPieceAnywhereInitSameCaptureAsMovement.place(iChessBoard);
    }
}