/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package i.got.ninetynine.problems.but.java.aint.one;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Utils does what the thing says
 *
 * @author Brayden
 */
public class Utils{

    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================
    // ===========================================================
    // Constructors
    // ===========================================================
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    // ===========================================================
    // Methods
    // ===========================================================
    public static Point addPoint ( Point one, Point two ){
        return new Point (
                (int) ( one.getX () + two.getX () ),
                (int) ( one.getY () + two.getY () ) );
    }
    
    public static Point subPoint ( Point one, Point two ){
        return new Point (
                (int) ( one.getX () - two.getX () ),
                (int) ( one.getY () - two.getY () ) );
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
