package util;

import java.awt.*;

/**
 * <p>This is a Helper class having basic util mehtods
 * for calculation.
 * </p>
 *
 * @author Will Li
 *         Created by Will Li on 10/16/2016.
 */
public final class Helper {
    public static boolean inBound(Point position,Point bound){
        return (bound.getX() >= position.getX()
                && 0 <= position.getX()
                && bound.getY() >= position.getY()
                && 0 <= position.getY());
    }
}
