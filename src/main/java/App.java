import exception.InitialPositionOccupiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import pojo.IChessBoard;
import pojo.IChessPiece;
import pojo.impl.ChessBoard;
import ui.Proxy;
import ui.impl.Console;


import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Will on 2016/9/13.
 */
public class App {
    public static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        Proxy proxy = new Console();

        IChessBoard chessBoard = new ChessBoard();

        do {
            int n = Integer.valueOf(proxy.readNumberOfPieces());

            chessBoard.reset();
            for (int i = 1; i < n + 1; i++) {
                try {
                    IChessPiece chessPiece = proxy.readPieceInfo(i);
                    if(chessPiece!=null)
                        chessBoard.place(chessPiece);
                    else throw new IllegalArgumentException("Unknown type.");
                } catch (InitialPositionOccupiedException e) {
                    System.out.println(e.getMessage() + " Please re-enter.");
                    if (proxy instanceof Console) {
                        --i;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + " Please re-enter.");
                    if (proxy instanceof Console) {
                        --i;
                    }
                }
            }
            proxy.outputResult( chessBoard.calculateMoves());
        }while(proxy.next());


        //GenericXmlApplicationContext context = initSpring();
        //logger.debug("started");
        //decoupled IOC
        //Proxy proxy = (Proxy)context.getBean("Console");

    }
    //initilize spring IOC context
    private static GenericXmlApplicationContext initSpring(){
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath*:spring.xml");
        context.refresh();
        return context;
      }
}
