/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package i.got.ninetynine.problems.but.java.aint.one.Models;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * PuzzleItem does what the thing says
 *
 * @author Brayden
 */
public class PuzzleItem {

    // ===========================================================
    // Constants
    // ===========================================================
    public static final PuzzleItem EMPTY_PUZZLE = new PuzzleItem(new Point(2, 2), (ImageIcon)null);
    // ===========================================================
    // Fields
    // ===========================================================
    private Point location;
    private ImageIcon image;

    // ===========================================================
    // Constructors
    // ===========================================================
    public PuzzleItem() {
        this(null, (ImageIcon) null);
    }

    public PuzzleItem(Point location, ImageIcon image) {
        this.location = location;
        this.image = image;
    }

    public PuzzleItem(Point location, BufferedImage image) {
        this.location = location;
        this.image = new ImageIcon(image);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = new ImageIcon(image);
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public boolean equals(Object itm) {
        if (!(itm instanceof PuzzleItem)) {
            return false;
        }
        PuzzleItem pzl = (PuzzleItem)itm;
        
        
        return pzl.location.x == location.x && pzl.location.y == location.y;
    }
    // ===========================================================
    // Methods
    // ===========================================================
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.location);
        hash = 89 * hash + Objects.hashCode(this.image);
        return hash;
    }
}
