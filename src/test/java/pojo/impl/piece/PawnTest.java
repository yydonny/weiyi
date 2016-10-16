package pojo.impl.piece;

import exception.InitialPositionOccupiedException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.IChessBoard;
import pojo.IChessPiece;
import util.TestHelper;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>This is a UT for Bishop
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class PawnTest {

    private IChessPiece pawn;
    private IChessBoard iChessBoard = EasyMock.createMock(IChessBoard.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculateNextMoveInEmptyChessBoard() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard,new Point(7,7));

        pawn = new Pawn("B",new Point(6,6));
        pawn.place(iChessBoard);
        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(6, 5));
                    add(new Point(6, 4));
                 }}, pawn.calculateNextMove(iChessBoard));

        pawn = new Pawn("B",new Point(6,5));
        pawn.place(iChessBoard);
        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(6, 4));
                }}, pawn.calculateNextMove(iChessBoard));

        pawn = new Pawn("B",new Point(6,1));
        pawn.place(iChessBoard);
        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(6, 0));
                }}, pawn.calculateNextMove(iChessBoard));

        pawn = new Pawn("W",new Point(6,1));
        pawn.place(iChessBoard);
        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(6, 2));
                    add(new Point(6, 3));
                }}, pawn.calculateNextMove(iChessBoard));
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testSettingPiecePositionAfterPlace() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);

        pawn = new Pawn("W", new Point(1, 1));
        assertTrue("in board test", pawn.place(iChessBoard));

        pawn = new Pawn("W", new Point(19,1));
        pawn.place(iChessBoard);
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testCalculateNextMoveWithInitialOccupiedPieceInChessBoard() throws Exception {
        Pawn __existingPiece = new Pawn("W",new Point(3,3));
        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3, __existingPiece);

        pawn = new Pawn("W",new Point(3,3));
        pawn.place(iChessBoard);

    }

    @Test
    public void testCalculateNextMoveWithBlockingPieceInChessBoard() throws Exception {
        Pawn __existingPiece = new Pawn("W",new Point(2,3));

        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(7, 7), 4, __existingPiece);

        pawn = new Pawn("W",new Point(2,2));
        pawn.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>() {{

                }}, pawn.calculateNextMove(iChessBoard));
    }

    @Test
    public void testCalculateNextMoveWithCaptureablePieceInChessBoard() throws Exception {
        Pawn __existingPiece = new Pawn("W",new Point(2,3));


        Pawn __existingPiece1 = new Pawn("B",new Point(2,2));


        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(7, 7), 4,
                __existingPiece,__existingPiece1);

        pawn = new Pawn("W",new Point(2,1));
        pawn.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(2, 2));
                    }}, pawn.calculateNextMove(iChessBoard));
    }
}