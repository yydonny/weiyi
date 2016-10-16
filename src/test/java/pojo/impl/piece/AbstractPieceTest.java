package pojo.impl.piece;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.IChessBoard;
import pojo.impl.ChessBoard;

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

    private AbstractPiece abstractPiece = new AbstractPiece() {
        @Override
        public List<String> calculateNextMove(IChessBoard iChessBoard) {
            return null;
        }
    };

    private IChessBoard iChessBoard = EasyMock.createMock(IChessBoard.class);


    @Test
    public void testPlace() throws Exception {
        EasyMock.reset(iChessBoard);
        EasyMock.expect(iChessBoard.getBoundary()).andReturn(new Point(10,10)).times(2);
        EasyMock.replay(iChessBoard);

        assertTrue("in board test",abstractPiece.place(iChessBoard, new Point(0, 0)));
        assertFalse("Out of board test",abstractPiece.place(iChessBoard, new Point(11,1)));

    }
}