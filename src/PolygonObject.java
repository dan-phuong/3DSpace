import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class PolygonObject {
    Polygon polygon;
    Color color;
    // average distance 
    double dist = 0;

    /**
     * Creates Polygon object on screen
     * @param x Set of X coordinates
     * @param y Set of Y coordinates
     * @param c Color for polygon
     */
    public PolygonObject(double[] x, double[] y, Color c){
        Screen.numOfPolygons++;
        polygon = new Polygon();
        for(int i = 0; i<x.length; i++){
            polygon.addPoint( (int)x[i], (int)y[i]);
        }
        color = c;
    }

    public void drawPolygon(Graphics g){
        g.setColor(color);
        g.fillPolygon(polygon);
        g.setColor(Color.black);
        g.drawPolygon(polygon);
    }

}
