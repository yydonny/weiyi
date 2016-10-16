package ui.impl;

import org.junit.Test;
import ui.Proxy;

import static org.junit.Assert.*;

/**
 * <p>This is a
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public class ConsoleIT {

    private Proxy console = new Console();
    @Test
    public void testReadNumberOfPieces() throws Exception {
        System.out.println(console.readNumberOfPieces());
    }

    @Test
    public void testReadPieceInfo() throws Exception {
        System.out.println(console.readPieceInfo(1));
    }
}