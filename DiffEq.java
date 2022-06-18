import javax.swing.*;
import java.awt.event.*;

public class DiffEq {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        runGUI();
      }
    });
  }
  private static void runGUI() {
    JFrame frame = new JFrame("DiffEq");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel panel = new Panel();
    frame.addKeyListener(new KeyListener(){
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
      }
      public void keyReleased(KeyEvent e) {}
      public void keyTyped(KeyEvent e) {}
    });

    frame.addMouseListener(new MouseListener(){
      public void mouseClicked(MouseEvent e) {
        panel.addPoint(e.getX(), e.getY());
      }
      public void mousePressed(MouseEvent e) {}
      public void mouseReleased(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
    });
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
