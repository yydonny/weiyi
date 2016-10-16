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
        bishop = new Bishop();
        bishop.place(iChessBoard,new Point(2,2),"W");

        assertEquals(
                new ArrayList<Point>(){{
                    add(new Point(1,1));
                    add(new Point(0,0));
                    add(new Point(3,1));
                    add(new Point(3,3));
                    add(new Point(1,3));
                    }},bishop.calculateNextMove(iChessBoard));
    }

    @Test
    public void testSettingPiecePositionAfterPlace() throws Exception {
        TestHelper.initEmptyChessBoard(iChessBoard);

        bishop = new Bishop();
        assertTrue("in board test",bishop.place(iChessBoard, new Point(0, 0),"W"));
        assertEquals("correctly set position",bishop.getPosition(),new Point(0,0));
        assertEquals("correctly set position",bishop.getColor(),"W");

        bishop = new Bishop();
        assertFalse("Out of board test",bishop.place(iChessBoard, new Point(19,1),"W"));
        assertEquals("null position is set",bishop.getPosition(),null);
        assertEquals("null color is set",bishop.getColor(),null);

    }

    @Test(expected = InitialPositionOccupiedException.class)
    public void testCalculateNextMoveWithInitialOccupiedPieceInChessBoard() throws Exception {
        Bishop __existingPiece = new Bishop();
        __existingPiece.setPosition(new Point(3,3));
        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3, __existingPiece);

        bishop = new Bishop();
        bishop.place(iChessBoard,new Point(3,3),"W");

    }

    @Test
    public void testCalculateNextMoveWithBlockingPieceInChessBoard() throws Exception {
        Bishop __existingPiece = new Bishop();
        __existingPiece.setPosition(new Point(3,3));
        __existingPiece.setColor("W");
        TestHelper.initChessBoardWithExistingPieces(iChessBoard, new Point(3, 3), 3, __existingPiece);

        bishop = new Bishop();
        bishop.place(iChessBoard,new Point(2,2),"W");

        assertEquals(
                new ArrayList<Point>(){{
                    add(new Point(1,1));
                    add(new Point(0,0));
                    add(new Point(3,1));
                    add(new Point(1,3));
                }},bishop.calculateNextMove(iChessBoard));
    }
}