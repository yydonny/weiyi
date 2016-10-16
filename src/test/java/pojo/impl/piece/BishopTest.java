package pojo.impl.piece;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.IChessBoard;
import pojo.IChessPiece;
import pojo.impl.ChessBoard;

import java.awt.*;

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
    public void testCalculateNextMove() throws Exception {

    }

    @Test
    public void testSettingPiecePositionAfterPlace() throws Exception {
        EasyMock.reset(iChessBoard);
        EasyMock.expect(iChessBoard.getBoundary()).andReturn(new Point(10,10)).times(2);
        EasyMock.replay(iChessBoard);

        bishop = new Bishop();
        assertTrue("in board test",bishop.place(iChessBoard, new Point(0, 0)));
        assertEquals("correctly set position",bishop.getPosition(),new Point(0,0));

        bishop = new Bishop();
        assertFalse("Out of board test",bishop.place(iChessBoard, new Point(19,1)));
        assertEquals("null position is set",bishop.getPosition(),null);

    }
}