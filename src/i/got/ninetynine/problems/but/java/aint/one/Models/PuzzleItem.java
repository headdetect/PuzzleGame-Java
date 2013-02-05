/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package i.got.ninetynine.problems.but.java.aint.one.Models;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * PuzzleItem does what the thing says
 *
 * @author Brayden
 */
public class PuzzleItem{

    // ===========================================================
    // Constants
    // ===========================================================
    public static final PuzzleItem OUT_OF_BOUNDS_ITEM = new PuzzleItem ();

    // ===========================================================
    // Fields
    // ===========================================================
    private Point location;

    private ImageIcon image;

    // ===========================================================
    // Constructors
    // ===========================================================
    public PuzzleItem (){
        this ( null, (ImageIcon) null );
    }

    public PuzzleItem ( Point location, ImageIcon image ){
        this.location = location;
        this.image = image;
    }

    public PuzzleItem ( Point location, BufferedImage image ){
        this.location = location;
        this.image = new ImageIcon ( image );
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public Point getLocation (){
        return location;
    }

    public void setLocation ( Point location ){
        this.location = location;
    }

    public ImageIcon getImage (){
        return image;
    }

    public void setImage ( ImageIcon image ){
        this.image = image;
    }
    
    public void setImage ( BufferedImage image ){
        this.image = new ImageIcon ( image );
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    // ===========================================================
    // Methods
    // ===========================================================
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
