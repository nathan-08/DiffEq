import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.*; // Point2D, AffineTransform

public class Panel extends JPanel {
  private ArrayList<Double> xvals = new ArrayList<>();
  private ArrayList<Double> yvals = new ArrayList<>();
  private AffineTransform transform;
  public void addPoint(double x, double y) {
    Point2D.Double point = new Point2D.Double(x,y);
    transform.transform(point, point);
    xvals.add((x-getWidth()/2.0)/f);
    yvals.add((y-getHeight()/2.0-40.0)*(-1.0)/f);
    repaint();
  }
  public Dimension getPreferredSize() {
    return new Dimension(800, 800);
  }
  private double get_slope(double x, double y) { // y' = f(x,y)
    return x*x/(y*y);
  }
  private double f = 40.0; // zoom factor
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    int w = getWidth();
    int h = getHeight();

    transform = new AffineTransform();
    transform.translate(getWidth()/2, getHeight()/2);
    transform.scale(1,-1);
    g2d.setTransform(transform);

    g2d.setColor(new Color(0xa0, 0xa0, 0xf0));
    g2d.drawLine(-w/2,0,w/2,0);
    g2d.drawLine(0,-h/2,0,h/2);

    g2d.setColor(new Color(0x80, 0x80, 0x80));
    for (int i = 0; i < xvals.size(); ++i) {
      draw_curve(xvals.get(i), yvals.get(i), g2d);
    }
  }
  private void draw_curve(double inix, double iniy, Graphics2D g2d) {
    for (int i = 0; i < 2; ++i) {
      double x = inix;
      double y = iniy;
      while (Math.abs(x*f) < getWidth()/2 && Math.abs(y*f) < getHeight()/2) {
        double slope = 0.0;
        try {
          slope = get_slope(x,y);
        } catch (Exception e) { System.err.print("ERR: get_slope"); break; }
        double x2 = x + (Math.pow(-1, i)) * (Math.cos(Math.atan(slope))) / 100.0;
        double y2 = y + (Math.pow(-1, i)) * (Math.sin(Math.atan(slope))) / 100.0;
        g2d.drawLine((int)(x*f),(int)(y*f),(int)(x2*f),(int)(y2*f));
        x = x2;
        y = y2;
      }
    }
  }
}
