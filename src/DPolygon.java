import java.awt.Polygon;
import java.awt.Color;


public class DPolygon {
    Color color;
    double[] x, y, z;
    int poly = 0;

    /**
     * Creates Drawable Polygon object on screen (Polygon Face)
     * @param x Set of X coordinates
     * @param y Set of Y coordinates
     * @param z Set of Z coordinates
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

    /**
     * Method for creating a polygon
     */
    public void createPolygon()
    {
        /*
         * Everytime we create a polygon, we create a drawable polygon for the screen
         */
        poly = Screen.numOfPolygons;
        Screen.drawablePolygons[Screen.numOfPolygons] = new PolygonObject(new double[]{}, new double[]{}, color);
        updatePolygon();
    }

    public void updatePolygon(){
        double dx = - 50 * Render.calculatePositionX(Screen.viewFrom, Screen.viewTo, Screen.viewTo[0], Screen.viewTo[1], Screen.viewTo[2]);
        double dy = - 50 * Render.calculatePositionY(Screen.viewFrom, Screen.viewTo, Screen.viewTo[0], Screen.viewTo[1], Screen.viewTo[2]);
        double[] newX = new double[x.length];
        double[] newY = new double[y.length];
        for(int i = 0; i<x.length; i++){
            newX[i] = App.screenSize.getWidth()/2 + dx + 50 * Render.calculatePositionX(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
            newY[i] = App.screenSize.getHeight()/2 + dy + 50 * Render.calculatePositionY(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
        }
        /*
         * Everytime we create a polygon, we create a drawable polygon for the screen
         */
        Screen.drawablePolygons[poly] = new PolygonObject(newX, newY, color);
        Screen.drawablePolygons[poly].averageDist = getDistance();
        Screen.numOfPolygons--;
    }

    public double getDistance(){
        double total = 0;
        for(int i = 0; i < x.length; i++)
            total += getDistanceToPoint(i);
        return total / x.length;
    }

    public double getDistanceToPoint(int i){
		return Math.sqrt(
				(Screen.viewFrom[0] - x[i])*(Screen.viewFrom[0] - x[i]) +
				(Screen.viewFrom[1] - y[i])*(Screen.viewFrom[1] - y[i]) +
				(Screen.viewFrom[2] - z[i])*(Screen.viewFrom[2] - z[i]));
	}

}
