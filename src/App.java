import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Dimension;

public class App extends JFrame{

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    public static JFrame frame = new App();
    public Screen screen = new Screen();


    public App(){
        add(screen);
        setUndecorated(true);
        setSize(screenSize);
        setVisible(true);
        
    }
    public static void main(String[] args) {
        //JFrame frame = new JFrame();

    }
}
