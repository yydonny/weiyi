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
 * <p>This is a UT for Knight
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class KnightTest {

    private IChessPiece knight;
    private IChessBoard iChessBoard = EasyMock.createMock(IChessBoard.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculateNextMoveInEmptyChessBoard() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard,new Point(7,7), 3);
        knight = new Knight("W",new Point(2,2));
        knight.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(0, 1));
                    add(new Point(1, 0));
                    add(new Point(4, 1));
                    add(new Point(1, 4));
                    add(new Point(4, 3));add(new Point(3, 4));
                    add(new Point(0, 3));add(new Point(3, 0));

                }}, knight.calculateNextMove(iChessBoard));
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testSettingPiecePositionAfterPlace() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);

        knight = new Knight("W", new Point(0, 0));
        assertTrue("in board test", knight.place(iChessBoard));

        knight = new Knight("W", new Point(19,1));
        knight.place(iChessBoard);
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testCalculateNextMoveWithInitialOccupiedPieceInChessBoard() throws Exception {
        Knight __existingPiece = new Knight("W",new Point(3,3));
        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3, __existingPiece);

        knight = new Knight("W",new Point(3,3));
        knight.place(iChessBoard);

    }

    @Test
    public void testCalculateNextMoveWithBlockingPieceInChessBoard() throws Exception {
        Knight __existingPiece = new Knight("W",new Point(3,4));

        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(7, 7), 3, __existingPiece);

        knight = new Knight("W",new Point(2,2));
        knight.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(0, 1));
                    add(new Point(1, 0));
                    add(new Point(4, 1));
                    add(new Point(1, 4));
                    add(new Point(4, 3));
                    add(new Point(0, 3));add(new Point(3, 0));
                }}, knight.calculateNextMove(iChessBoard));
    }

    @Test
    public void testCalculateNextMoveWithCaptureablePieceInChessBoard() throws Exception {
        Knight __existingPiece = new Knight("B",new Point(3,4));


        Knight __existingPiece1 = new Knight("B",new Point(0,3));


        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(7, 7), 3,
                __existingPiece,__existingPiece1);

        knight = new Knight("W",new Point(2,2));
        knight.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>() {{
                    add(new Point(0, 1));
                    add(new Point(1, 0));
                    add(new Point(4, 1));
                    add(new Point(1, 4));
                    add(new Point(4, 3));add(new Point(3, 4));
                    add(new Point(0, 3));add(new Point(3, 0));
                }}, knight.calculateNextMove(iChessBoard));
    }
}