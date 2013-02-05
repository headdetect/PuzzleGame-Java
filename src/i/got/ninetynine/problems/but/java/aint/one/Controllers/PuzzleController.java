/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package i.got.ninetynine.problems.but.java.aint.one.Controllers;

import i.got.ninetynine.problems.but.java.aint.one.Models.PuzzleItem;
import i.got.ninetynine.problems.but.java.aint.one.Utils;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * PuzzleController does what the thing says
 *
 * @author Brayden
 */
public class PuzzleController{

    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================
    private PuzzleItem[] currentItems;

    private PuzzleItem[] solveItems;

    private static UpdatePiecesListener mListener;

    // ===========================================================
    // Constructors
    // ===========================================================
    public PuzzleController (){
        currentItems = new PuzzleItem[ 9 ];
        solveItems = new PuzzleItem[ 9 ];
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public PuzzleItem[] getCurrentItems (){
        return currentItems;
    }

    public void setCurrentItems ( PuzzleItem[] currentItems ){
        this.currentItems = currentItems;
    }

    public static UpdatePiecesListener getUpdatePiecesListener (){
        return mListener;
    }

    public static void setUpdatePiecesListener ( UpdatePiecesListener mListener ){
        PuzzleController.mListener = mListener;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    // ===========================================================
    // Methods
    // ===========================================================
    public void shuffle (){
        Collections.shuffle ( Arrays.asList ( currentItems ) );
    }

    public boolean move ( PuzzleItem itm ){
        Point curr = itm.getLocation ();

        PuzzleItem top = getItemAt ( Utils.addPoint ( curr, new Point ( -1, 0 ) ) );
        PuzzleItem left = getItemAt ( Utils.addPoint ( curr, new Point ( 0, -1 ) ) );
        PuzzleItem bottom = getItemAt ( Utils.addPoint ( curr, new Point ( 1, 0 ) ) );
        PuzzleItem right = getItemAt ( Utils.addPoint ( curr, new Point ( 0, -1 ) ) );

        if ( top == null ) {
            itm.setLocation ( Utils.addPoint ( curr, new Point ( -1, 0 ) ) );
        } else if ( left == null ) {
            itm.setLocation ( Utils.addPoint ( curr, new Point ( 0, -1 ) ) );
        } else if ( bottom == null ) {
            itm.setLocation ( Utils.addPoint ( curr, new Point ( -1, 0 ) ) );
        } else if ( right == null ) {
            itm.setLocation ( Utils.addPoint ( curr, new Point ( -1, 0 ) ) );
        } else {
            causeUpdate ();
            return false;
        }

        causeUpdate ();
        return true;
    }

    public PuzzleItem getItemAt ( Point point ){
        if ( point.getX () <= -1 || point.getY () <= -1 || point.getX () >= 3 || point.getY () >= 3 ) {
            return PuzzleItem.OUT_OF_BOUNDS_ITEM;
        }

        for ( int i = 0; i < 9; i++ ) {
            PuzzleItem itm = currentItems[i];

            if ( itm == null ) {
                return null;
            }

            if ( itm.getLocation ().equals ( point ) ) {
                return itm;
            }
        }

        return null;
    }

    public void createPuzzles ( String str ){
        try {

            BufferedImage full = ImageIO.read ( new File ( str ) );

            final int w = full.getWidth ();
            final int h = full.getHeight ();

            for ( int i = 0; i < 9; i++ ) {

                final int x = i / 3;
                final int y = i % 3;

                final int xOverflow = w % 3;
                final int yOverflow = h % 3;

                final int xPos = x * ( w - ( xOverflow ) ) / 3;
                final int yPos = y * ( h - ( yOverflow ) ) / 3;

                final int wSize = ( w - ( xOverflow ) ) / 3;
                final int hSize = ( h - ( yOverflow ) ) / 3;

                if ( i != 8 ) {
                    currentItems[i] = new PuzzleItem ( new Point ( x, y ), full.getSubimage ( xPos, yPos, wSize, hSize ) );
                }

            }



            causeUpdate ();

        } catch ( IOException ex ) {
            JOptionPane.showMessageDialog ( null, "Error creating image" );
            Logger.getLogger ( PuzzleController.class.getName () ).log ( Level.SEVERE, null, ex );
        }
    }

    void causeUpdate (){
        if ( mListener != null ) {
            mListener.update ( this );
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    public interface UpdatePiecesListener{

        void update ( PuzzleController ctr );

    }

}
