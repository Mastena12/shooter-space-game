
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    int ScreenWidth = 1200;
    public static int  ScreenHeight = 650;
    GamePanel gp = new GamePanel(ScreenWidth, ScreenHeight);

    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(gp);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
