import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;


public class DPolygon {
    Color color;
    double[] x, y, z;
    int poly = 0;

    /**
     * Creates Polygon object on screen
     * @param x Set of X coordinates
     * @param y Set of Y coordinates
     * @param c Color for polygon
     */
	public DPolygon(double[] x, double[] y, double[] z, Color c){
        Screen.numOf3DPolygons++;
        this.x = x;
        this.y = y;
        this.z = z;
        color = c;
        createPolygon();
    }

    public void createPolygon()
    {
        double total = 0;
        double[] newX = new double[x.length];
        double[] newY = new double[x.length];
        for(int i = 0; i<x.length; i++){
            newX[i] = 500+50 * Computation.calculatePositionX(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
            newY[i] = 500+50 * Computation.calculatePositionY(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
            total+= Computation.distance;
        }

                /*
         * Everytime we create a polygon, we create a drawable polygon for the screen
         */
        poly = Screen.numOfPolygons;
        Screen.drawablePolygons[Screen.numOfPolygons] = new PolygonObject(newX, newY, color);
        Screen.drawablePolygons[poly].dist = total/x.length;
    }

    void updatePolygon(){
        double[] newX = new double[x.length];
        double[] newY = new double[x.length];
        for(int i = 0; i<x.length; i++){
            newX[i] = 500+50 * Computation.calculatePositionX(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
            newY[i] = 500+50 * Computation.calculatePositionY(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
        }

                /*
         * Everytime we create a polygon, we create a drawable polygon for the screen
         */
        Screen.drawablePolygons[poly] = new PolygonObject(newX, newY, color);
        Screen.numOfPolygons--;
    }

    /*
    public void drawPolygon(Graphics g){
        g.setColor(color);
        g.drawPolygon(polygon);
    }
*/

}
