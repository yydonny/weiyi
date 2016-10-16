package pojo.impl.piece;

import exception.InitialPositionOccupiedException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.context.Theme;
import pojo.IChessBoard;
import pojo.IChessPiece;
import pojo.impl.ChessBoard;
import util.TestHelper;

import javax.naming.ldap.InitialLdapContext;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * <p>This is a UT for Bishop
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class BishopTest {

    private IChessPiece bishop;
    private IChessBoard iChessBoard = EasyMock.createMock(IChessBoard.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculateNextMoveInEmptyChessBoard() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard,new Point(3,3), 3);
        bishop = new Bishop("W",new Point(2,2));
        bishop.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>(){{
                    add(new Point(1,1));
                    add(new Point(0,0));
                    add(new Point(3,1));
                    add(new Point(3,3));
                    add(new Point(1,3));
                    }},bishop.calculateNextMove(iChessBoard));
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testSettingPiecePositionAfterPlace() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);

        bishop = new Bishop("W", new Point(0, 0));
        assertTrue("in board test",bishop.place(iChessBoard));

        bishop = new Bishop("W", new Point(19,1));
        bishop.place(iChessBoard);
    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testCalculateNextMoveWithInitialOccupiedPieceInChessBoard() throws Exception {
        Bishop __existingPiece = new Bishop("W",new Point(3,3));
        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3, __existingPiece);

        bishop = new Bishop("W",new Point(3,3));
        bishop.place(iChessBoard);

    }

    @Test
    public void testCalculateNextMoveWithBlockingPieceInChessBoard() throws Exception {
        Bishop __existingPiece = new Bishop("W",new Point(3,3));

        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3, __existingPiece);

        bishop = new Bishop("W",new Point(2,2));
        bishop.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>(){{
                    add(new Point(1,1));
                    add(new Point(0,0));
                    add(new Point(3,1));
                    add(new Point(1,3));
                }},bishop.calculateNextMove(iChessBoard));
    }

    @Test
    public void testCalculateNextMoveWithCaptureablePieceInChessBoard() throws Exception {
        Bishop __existingPiece = new Bishop("B",new Point(3,3));


        Bishop __existingPiece1 = new Bishop("B",new Point(0,0));


        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3,
                __existingPiece,__existingPiece1);

        bishop = new Bishop("W",new Point(2,2));
        bishop.place(iChessBoard);

        assertEquals(
                new ArrayList<Point>(){{
                    add(new Point(1,1));
                    add(new Point(0,0));
                    add(new Point(3,1));
                    add(new Point(3,3));
                    add(new Point(1,3));
                }},bishop.calculateNextMove(iChessBoard));
    }
}