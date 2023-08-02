import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Screen extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
    
    //PolygonObject poly;
    double FPS = 240, sleepTime = 1000/FPS, lastRefresh = 0;

    static double[] viewFrom = new double[]{10,10,10};
    static double[] viewTo = new double[]{1,1,1.5};

    public static int numOfPolygons = 0, numOf3DPolygons = 0;

    public static PolygonObject[] drawablePolygons = new PolygonObject[200];
    static DPolygon[] polygons = new DPolygon[200];

    public static int[] render_order;

    public boolean[] Keys = new boolean[10];

    /**
     * Constructor for Screen Class
     */
    public Screen(){
        addKeyListener(this);
        setFocusable(true); 
        // Cube polygon
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{0,0,2,2}, new double[]{0,0,0,0}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{0,0,2,2}, new double[]{3,3,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{0,0,2,2}, new double[]{0,0,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{2,2,2,2}, new double[]{0,0,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,0,0,0}, new double[]{0,2,2,0}, new double[]{0,0,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{2,2,2,2}, new double[]{0,2,2,0}, new double[]{0,0,3,3}, Color.blue);

        for(int i = -4; i < 5; i++)
			for(int j = -4; j < 5; j++){
				polygons[numOf3DPolygons] = new DPolygon(new double[]{i, i, i + 1, i + 1}, new double[]{j, j + 1, j + 1, j},  new double[]{0, 0, 0, 0}, Color.red	);
                polygons[numOf3DPolygons] = new DPolygon(new double[]{i, i, i + 1, i + 1}, new double[]{j, j + 1, j + 1, j},  new double[]{-1, -1, -1, -1}, Color.black);
            }
    }
    
    public void paintComponent(Graphics g){
        control();
        g.clearRect(0, 0, 2560, 1440);
        for(int i = 0; i < numOf3DPolygons; i++){
            polygons[i].updatePolygon();
        }
        renderOrder();

        for(int i = 0; i < numOf3DPolygons; i++){
            drawablePolygons[render_order[i]].drawPolygon(g);
        }
        refresh();
    }

    public void refresh(){
        while(true){
            if((System.currentTimeMillis() - lastRefresh) > sleepTime){
                lastRefresh = System.currentTimeMillis();
                repaint();
                break;
            } else{
                try{
                    Thread.sleep((long)(sleepTime - (System.currentTimeMillis() - lastRefresh)));
                } catch(Exception e){

                }
            }
        }
    }

    /*
     * Sets render order based on average distance from viewFrom
     */
    private void renderOrder(){
        render_order = new int[numOfPolygons];
        double[] polygonDistances = new double[numOfPolygons];
        for(int polygon_index = 0; polygon_index < numOfPolygons; polygon_index++){
            polygonDistances[polygon_index] = drawablePolygons[polygon_index].averageDist;
            render_order[polygon_index] = polygon_index;
        }
        for(int i = 0; i < polygonDistances.length-1; i++){
            if(polygonDistances[i] < polygonDistances[i+1]){
                double temp = polygonDistances[i];
                int renderElement = render_order[i];

                render_order[i] = render_order[i+1];
                polygonDistances[i] = polygonDistances[i+1];

                render_order[i+1] = renderElement;
                polygonDistances[i+1] = temp;
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
            Keys[1] = true;
		if(e.getKeyCode() == KeyEvent.VK_A)
            Keys[2] = true;
		if(e.getKeyCode() == KeyEvent.VK_S)
            Keys[3] = true;
		if(e.getKeyCode() == KeyEvent.VK_D)
            Keys[4] = true;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W)
            Keys[1] = false;
		if(e.getKeyCode() == KeyEvent.VK_A)
            Keys[2] = false;
		if(e.getKeyCode() == KeyEvent.VK_S)
            Keys[3] = false;
		if(e.getKeyCode() == KeyEvent.VK_D)
            Keys[4] = false;
    }

    public void control(){
        Vector ViewVector = new Vector(viewTo[0] - viewFrom[0], viewTo[1] - viewFrom[1], viewTo[2] - viewFrom[2]);
		if(Keys[1])
		{
			viewFrom[0] += ViewVector.x;
			viewFrom[1] += ViewVector.y;
			viewFrom[2] += ViewVector.z;
			viewTo[0] += ViewVector.x;
			viewTo[1] += ViewVector.y;
			viewTo[2] += ViewVector.z;
		}
		
		if(Keys[3])
		{
			viewFrom[0] -= ViewVector.x;
			viewFrom[1] -= ViewVector.y;
			viewFrom[2] -= ViewVector.z;
			viewTo[0] -= ViewVector.x;
			viewTo[1] -= ViewVector.y;
			viewTo[2] -= ViewVector.z;
		}
		
		Vector VerticalVector = new Vector(0, 0, 1);
		Vector SideViewVector = ViewVector.crossProduct(VerticalVector);
		
		if(Keys[2])
		{
			viewFrom[0] += SideViewVector.x;
			viewFrom[1] += SideViewVector.y;
			viewFrom[2] += SideViewVector.z;
			viewTo[0] += SideViewVector.x;
			viewTo[1] += SideViewVector.y;
			viewTo[2] += SideViewVector.z;
		}
		
		if(Keys[4])
		{
			viewFrom[0] -= SideViewVector.x;
			viewFrom[1] -= SideViewVector.y;
			viewFrom[2] -= SideViewVector.z;
			viewTo[0] -= SideViewVector.x;
			viewTo[1] -= SideViewVector.y;
			viewTo[2] -= SideViewVector.z;
		}
    }
    
}
