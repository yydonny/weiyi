package pojo.impl;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.IChessBoard;
import pojo.IChessPiece;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * <p>This is a
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class ChessBoardTest {

    private IChessBoard chessBoard = new ChessBoard();
    private IChessPiece iChessPiece = EasyMock.createMock(IChessPiece.class);

    private final Point BOUND = new Point(7,7);
    private final Point RANDOM_POSITION = new Point(1,2);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPlaceWhenAllowed() throws Exception {
        EasyMock.reset(iChessPiece);
        EasyMock.expect(iChessPiece.place(EasyMock.anyObject())).andReturn(true).times(1);
        EasyMock.expect(iChessPiece.getPosition()).andReturn(new Point(RANDOM_POSITION)).times(1);
        EasyMock.replay(iChessPiece);

        assertEquals("chessBoard.place = true", true, chessBoard.place(iChessPiece));
        assertEquals("chessBoard.getPieceAt = iChessPiece",iChessPiece, chessBoard.getPieceAt(new Point(RANDOM_POSITION)));
    }

    @Test
    public void testPlaceWhenNotAllowed() throws Exception {
        EasyMock.reset(iChessPiece);
        EasyMock.expect(iChessPiece.place(EasyMock.anyObject())).andReturn(false).times(1);
        EasyMock.replay(iChessPiece);

        assertEquals("chessBoard.place = false",false,chessBoard.place(iChessPiece));
        assertEquals("chessBoard.getPieceAt=null",null, chessBoard.getPieceAt(new Point(RANDOM_POSITION)));
    }


    @Test
    public void testGetBoundary() throws Exception {
        assertEquals(BOUND,chessBoard.getBoundary());
    }

    @Test
    public void testPlay() throws Exception {

    }
}