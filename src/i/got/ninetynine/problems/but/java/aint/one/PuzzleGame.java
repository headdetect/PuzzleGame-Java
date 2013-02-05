/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package i.got.ninetynine.problems.but.java.aint.one;

import i.got.ninetynine.problems.but.java.aint.one.Views.PuzzleFrame;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayden
 */
public class PuzzleGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            JOptionPane.showMessageDialog(null, "Well arn't you special");
        }
        
        
        /* Set the Nimbus look and feel, because y not? amirite? */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Well that failed.... falling back to default.");
        } 

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new PuzzleFrame().setVisible(true);
            }
        });
        
        
    }
}
