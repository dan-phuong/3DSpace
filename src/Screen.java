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

    // Where we're viewing from
    static double[] viewFrom = new double[]{10,10,10};
    // Where we're viewing to
    static double[] viewTo = new double[]{5,0,0};

    public static int numOfPolygons = 0;
    public static int numOf3DPolygons = 0;
    public static PolygonObject[] drawablePolygons = new PolygonObject[100];

    // Sets number of 3D polygons
    static DPolygon[] polygons = new DPolygon[100];


    
    public Screen(){
        // Cube polygon
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{0,0,2,2}, new double[]{0,0,0,0}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{0,0,2,2}, new double[]{3,3,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{0,0,2,2}, new double[]{0,0,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,2,2,0}, new double[]{2,2,2,2}, new double[]{0,0,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{0,0,0,0}, new double[]{0,2,2,0}, new double[]{0,0,3,3}, Color.blue);
        polygons[numOf3DPolygons] = new DPolygon(new double[]{2,2,2,2}, new double[]{0,2,2,0}, new double[]{0,0,3,3}, Color.blue);
        //poly = new PolygonObject(new int[]{10,200,400}, new int[]{10,200,10}, Color.BLUE);
        addKeyListener(this);
        setFocusable(true); 
    }
    
    public void paintComponent(Graphics g){
        g.clearRect(0, 0, 2560, 1440);
        g.drawString(System.currentTimeMillis()+"", 20, 20);
        for(int i = 0; i < numOf3DPolygons; i++){
            polygons[i].updatePolygon();
            refresh();
        }

        for(int i = 0; i < numOfPolygons; i++){
            drawablePolygons[i].drawPolygon(g);
            refresh();
        }

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
			viewFrom[1] --;
		if(e.getKeyCode() == KeyEvent.VK_A)
            viewFrom[0] --;
		if(e.getKeyCode() == KeyEvent.VK_S)
            viewFrom[1] ++;
		if(e.getKeyCode() == KeyEvent.VK_D)
            viewFrom[0] ++;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
