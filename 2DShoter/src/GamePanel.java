
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    boolean paused;
    int Level = 300;
    int counter = 0;
    int bulletPower = 1;
    int ReloadSpeed = 1;
    int Reload = 0;
    int Score = 109000;
    int bulletSize = 10;
    public static int Fps = 120;
    int rockSize = 40;
    int Width;
    int Height;
    int playerWidth;
    int playerHeight;
    int PlayerX;
    int PlayerY;
    ShopPanel shopPanel;
    Thread gameLoop;
    Player player;
    Dimension dimension;
    Random rand = new Random();
    ArrayList<Bullet> Bullets = new ArrayList<>();
    ArrayList<Rock> Rocks = new ArrayList<>();
    KeyA keyAd = new KeyA();
    public static boolean superPower = false;
    GamePanel(int width, int height) {

        this.Width = width;
        this.Height = height;
        this.setLayout(null);
        dimension = new Dimension(Width, Height);
        this.setPreferredSize(dimension);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyAd);
        setPlayer();
        shopPanel = new ShopPanel(player , this);
        this.add(shopPanel);
        setGameLoop();
        firstRock();

    }

    public void firstRock() {
        Rocks.add(new Rock(0, 0, 0, 0));
    }

    public void setGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void setRock() {
        counter++;
        if (counter >= Level) {
            Rocks.add(new Rock(Width + rand.nextInt(50), 50 + rand.nextInt(Height - 200), rockSize * rand.nextInt(1, 4), rockSize * rand.nextInt(1, 4)));
            counter = 0;
        }
    }

    public void setPlayer() {
        playerWidth = 80;
        playerHeight = 50;
        PlayerY = Height / 2 - playerHeight / 2;
        PlayerX = Width / 9;
        player = new Player(PlayerX, PlayerY, playerWidth, playerHeight, Width, Height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    public void Draw(Graphics g) {

        player.draw(g);

        for (int i = 0; i < Bullets.size(); i++) {
            Bullets.get(i).Draw(g);
        }

        for (int i = 0; i < Rocks.size(); i++) {
            Rocks.get(i).Draw(g);
        }
        DrawRelod(g);
        DrawScore(g);
    }

    public void DrawScore(Graphics g) {
        g.setColor(new Color(255, 215, 0));
        g.setFont(new Font(Font.SERIF, Font.ITALIC, 27));
        g.drawString("Gold : " + Score, 15, 35);
    }

    public void DrawRelod(Graphics g) {
        Reload += ReloadSpeed;
        Reload = Math.min(Reload, Fps * (3 / 2) - 5);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor((Color.cyan).darker().darker().darker());
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRect(20, Height - 35, Fps * (3 / 2), 15);
        g2D.setColor(new Color(184, 115, 51));
        g2D.fill3DRect(23, Height - 32, Reload, 10, true);
    }

    public void Update() {
        player.move();
        for (int i = 0; i < Bullets.size(); i++) {
            Bullets.get(i).move();
            if (Bullets.get(i).x >= Width - Bullets.get(i).width) {
                Bullets.remove(i);
            }
        }
        for (int i = 0; i < Rocks.size(); i++) {
            Rocks.get(i).move();
            if (i != 0 && Rocks.get(i).IsPassed()) {
                Rocks.remove(i);
            }
        }
        MovePlayer();
        setRock();
        Collision();
    }

    public void MovePlayer() {
        if (keyAd.upPressed) {
            player.GravityY = -0.5;
        }
        if (keyAd.downPressed) {
            player.GravityY = 0.5;
        }
        if (keyAd.RightPressed) {
            player.GravityX = 0.5;
        }
        if (keyAd.LeftPressed) {
            player.GravityX = -0.5;
        }
    }

    public void Collision() {
        A:
        for (int i = 0; i < Bullets.size(); i++) {
            for (int j = 0; j < Rocks.size(); j++) {
                if (Bullets.get(i).intersects(Rocks.get(j))) {
                    Bullets.remove(i);
                    Rocks.get(j).healt -= bulletPower;
                    if (Rocks.get(j).healt <= 0) {
                        Score += 5 * Rocks.get(j).height * Rocks.get(j).width / (rockSize * rockSize);
                        Rocks.remove(j);

                    }
                    break A;
                }
            }
        }
    }

    public void AddBullet() {
        Bullets.add(new Bullet(player.x + player.width, player.y + playerHeight / 2, bulletSize * 4, bulletSize, Width , 8 , 0));
        if(superPower){
            Bullets.add(new Bullet(player.x + player.width, player.y + playerHeight / 2, bulletSize * 4, bulletSize, Width , 4));
            Bullets.add(new Bullet(player.x + player.width, player.y + playerHeight / 2, bulletSize * 4, bulletSize, Width , -4));
        }
    }

    @Override
    public void run() {
        long now = System.nanoTime();
        long last = now;
        double Interwal = 1000000000 / Fps;
        double delta = 0;
        while (true) {
            now = System.nanoTime();
            delta += (now - last) / Interwal;
            last = now;
            if (delta >= 1) {
                delta--;
                if (!paused) {
                    Update();
                    repaint();
                }

            }
        }
    }

    public class KeyA extends KeyAdapter {

        public boolean upPressed, downPressed, RightPressed, LeftPressed;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    upPressed = true;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_A:
                    LeftPressed = true;
                    break;
                case KeyEvent.VK_D:
                    RightPressed = true;
                    break;
                default:
                    ;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    upPressed = false;
                    player.GravityY = 0;
                    player.VelY = 0;
                    break;
                case KeyEvent.VK_S:
                    downPressed = false;
                    player.GravityY = 0;
                    player.VelY = 0;
                    break;
                case KeyEvent.VK_A:
                    LeftPressed = false;
                    player.GravityX = 0;
                    player.VelX = 0;
                    break;
                case KeyEvent.VK_D:
                    RightPressed = false;
                    player.GravityX = 0;
                    player.VelX = 0;
                    break;
                case (KeyEvent.VK_ENTER):
                    if (Reload >= Fps - 5) {
                        AddBullet();
                        Reload = 0;
                    }
                    break;

                case (KeyEvent.VK_ESCAPE):
                    paused = !paused;
                    shopPanel.visible = !shopPanel.visible;
                    shopPanel.Display();
                    shopPanel.setGold(Score);
                    break;
                default:
                    ;
            }
        }
    }

}
