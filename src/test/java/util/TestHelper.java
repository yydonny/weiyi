package util;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import pojo.IChessBoard;
import pojo.IChessPiece;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    public static void initEmptyChessBoard(IChessBoard iChessBoard, Point bound){
        EasyMock.reset(iChessBoard);
        EasyMock.expect(iChessBoard.getBoundary()).andReturn(bound).anyTimes();
        EasyMock.expect(iChessBoard.getPieceAt(EasyMock.anyObject())).andReturn(null).anyTimes();
        EasyMock.replay(iChessBoard);
    }
    public static void initChessBoardWithExistingPieces(IChessBoard iChessBoard,
                                                        Point bound,
                                                        int getBoundaryCallTimes,
                                                        IChessPiece... existingPieces){
        Map<Point,IChessPiece> existingPieceList = new LinkedHashMap<Point,IChessPiece>(){{
        for(IChessPiece piece:existingPieces){
            put(piece.getPosition(), piece);
        }
        }};


        EasyMock.reset(iChessBoard);
        EasyMock.expect(iChessBoard.getBoundary()).andReturn(bound).times(getBoundaryCallTimes);
        EasyMock.expect(iChessBoard.getPieceAt(EasyMock.anyObject())).andAnswer(new IAnswer<IChessPiece>()
        {
            public IChessPiece answer() throws Throwable {
                return existingPieceList.get((EasyMock.getCurrentArguments()[0]));
            }
        }).anyTimes();
        EasyMock.replay(iChessBoard);

    }
}
