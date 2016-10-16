package util;

import org.easymock.EasyMock;
import pojo.IChessBoard;

import java.awt.*;

/**
 * <p>This is a
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class TestHelper {

    public static void initEmptyChessBoard(IChessBoard iChessBoard){
        EasyMock.reset(iChessBoard);
        EasyMock.expect(iChessBoard.getBoundary()).andReturn(new Point(10,10)).times(2);
        EasyMock.expect(iChessBoard.getPieceAt(EasyMock.anyObject())).andReturn(null).anyTimes();
        EasyMock.replay(iChessBoard);
    }

    public static void initEmptyChessBoard(IChessBoard iChessBoard, Point bound, int getBoundaryCallTimes){
        EasyMock.reset(iChessBoard);
        EasyMock.expect(iChessBoard.getBoundary()).andReturn(bound).times(getBoundaryCallTimes);
        EasyMock.expect(iChessBoard.getPieceAt(EasyMock.anyObject())).andReturn(null).anyTimes();
        EasyMock.replay(iChessBoard);
    }
}
