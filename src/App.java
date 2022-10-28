import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Dimension;

public class App extends JFrame{

    public static JFrame frame = new App();
    public Screen screen = new Screen();
    //public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public App(){
        add(screen);
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
        
    }
    public static void main(String[] args) {
        //JFrame frame = new JFrame();

    }
}
