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
public class PuzzleController {

    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================
    private PuzzleItem[] currentItems;
    private PuzzleItem[] solveItems;
    private UpdatePiecesListener mListener;

    // ===========================================================
    // Constructors
    // ===========================================================
    public PuzzleController() {
        currentItems = new PuzzleItem[9];
        solveItems = new PuzzleItem[9];
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public PuzzleItem[] getCurrentItems() {
        return currentItems;
    }
    
    public void setCurrentItems(PuzzleItem[] currentItems) {
        this.currentItems = currentItems;
    }
    
    public UpdatePiecesListener getUpdatePiecesListener() {
        return mListener;
    }
    
    public void setUpdatePiecesListener(UpdatePiecesListener mListener) {
        this.mListener = mListener;
        causeUpdate();
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    // ===========================================================
    // Methods
    // ===========================================================
    public void shuffle() {
        final Point[] points = new Point[]{
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(0, 1),
            new Point(1, 1),
            new Point(2, 1),
            new Point(0, 2),
            new Point(1, 2),
            new Point(2, 2),};
        
        
        Collections.shuffle(Arrays.asList(points));
        
        
        for (int i = 0; i < 9; i++) {
            currentItems[i].setLocation(points[i]);
        }
        
        causeUpdate();
    }
    
    public boolean move(PuzzleItem itm) {
        if (itm == null) {
            return false;
        }
        
        
        
        Point curr = itm.getLocation();
        
        PuzzleItem top = getItemAt(Utils.addPoint(curr, new Point(0, -1)));
        PuzzleItem left = getItemAt(Utils.addPoint(curr, new Point(-1, 0)));
        PuzzleItem bottom = getItemAt(Utils.addPoint(curr, new Point(0, 1)));
        PuzzleItem right = getItemAt(Utils.addPoint(curr, new Point(1, 0)));
        
        if (top == PuzzleItem.EMPTY_PUZZLE) {
            itm.setLocation(Utils.addPoint(curr, new Point(0, -1)));
            PuzzleItem.EMPTY_PUZZLE.setLocation(curr);
            currentItems[getIndex(itm)] = PuzzleItem.EMPTY_PUZZLE;
            currentItems[getIndex(top)] = itm;
        } else if (left == PuzzleItem.EMPTY_PUZZLE) {
            itm.setLocation(Utils.addPoint(curr, new Point(-1, 0)));
            PuzzleItem.EMPTY_PUZZLE.setLocation(curr);
            currentItems[getIndex(itm)] = PuzzleItem.EMPTY_PUZZLE;
            currentItems[getIndex(left)] = itm;
        } else if (bottom == PuzzleItem.EMPTY_PUZZLE) {
            itm.setLocation(Utils.addPoint(curr, new Point(0, 1)));
            PuzzleItem.EMPTY_PUZZLE.setLocation(curr);
            currentItems[getIndex(itm)] = PuzzleItem.EMPTY_PUZZLE;
            currentItems[getIndex(bottom)] = itm;
        } else if (right == PuzzleItem.EMPTY_PUZZLE) {
            itm.setLocation(Utils.addPoint(curr, new Point(1, 0)));
            PuzzleItem.EMPTY_PUZZLE.setLocation(curr);
            currentItems[getIndex(itm)] = PuzzleItem.EMPTY_PUZZLE;
            currentItems[getIndex(right)] = itm;
        } else {
            causeUpdate();
            checkFinish();
            return false;
        }
        
        causeUpdate();
        checkFinish();
        return true;
    }
    
    public PuzzleItem getItemAt(Point point) {
        if (point.getX() <= -1 || point.getY() <= -1 || point.getX() >= 3 || point.getY() >= 3) {
            return null;
        }
        
        for (int i = 0; i < 9; i++) {
            PuzzleItem itm = currentItems[i];
            
            if (itm.getLocation().equals(point)) {
                return itm;
            }
        }
        
        return null;
    }
    
    public void createPuzzles(String str) {
        try {
            
            BufferedImage full = ImageIO.read(new File(str));
            
            final int w = full.getWidth();
            final int h = full.getHeight();
            
            for (int i = 0; i < 9; i++) {
                
                final int x = i / 3;
                final int y = i % 3;
                
                final int xOverflow = w % 3;
                final int yOverflow = h % 3;
                
                final int xPos = x * (w - (xOverflow)) / 3;
                final int yPos = y * (h - (yOverflow)) / 3;
                
                final int wSize = (w - (xOverflow)) / 3;
                final int hSize = (h - (yOverflow)) / 3;
                
                if (i != 8) {
                    Point point = new Point(x, y);
                    BufferedImage img = full.getSubimage(xPos, yPos, wSize, hSize);
                    currentItems[i] = new PuzzleItem(point, img);
                    solveItems[i] = new PuzzleItem(point, img);
                } else {
                    currentItems[i] = PuzzleItem.EMPTY_PUZZLE;
                    solveItems[i] = PuzzleItem.EMPTY_PUZZLE;
                }
                
            }
            
            
            
            causeUpdate();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error creating image");
            Logger.getLogger(PuzzleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int getIndex(PuzzleItem itm) {
        for (int i = 0; i < 9; i++) {
            if (currentItems[i] == itm) {
                return i;
            }
        }
        
        throw new ArrayIndexOutOfBoundsException("PuzzleItem not inbounds");
    }
    
    void causeUpdate() {
        if (mListener != null) {
            mListener.update(this);
        }
    }
    
    void checkFinish() {
        
        for (int i = 0; i < 9; i++) {
            PuzzleItem check = currentItems[i];
            PuzzleItem solve = solveItems[i];
            
            if (!check.equals(solve)) {
                return;
            }
        }
        
        if (mListener != null) {
            mListener.finish(this);
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    public interface UpdatePiecesListener {
        
        void update(PuzzleController ctr);
        
        void finish(PuzzleController ctr);
    }
}
