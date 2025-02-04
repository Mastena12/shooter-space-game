
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {

    int counter = 0;
    double max = 1;
    double VelY = 0;
    double VelX = 0;
    int scWidth;
    int scHeigth;
    double GravityX = 0;
    double GravityY = 0;
    

    Player(int x, int y, int width, int height, int scWidth, int scHeigth) {
        super(x, y, width, height);
        this.scHeigth = scHeigth;
        this.scWidth = scWidth;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        counter++;
        if (counter>=0) {
            VelX += GravityX;
            VelY += GravityY;
            counter =0;
        }

        if (VelX >= max) {
            VelX = max;
        }
        if (VelY >= max) {
            VelY = max;
        }
        if (VelX <= -max) {
            VelX = -max;
        }
        if (VelY <= -max) {
            VelY = -max;
        }
        this.x += VelX;
        this.y += VelY;
        Collicion();
    }

    public void Collicion() {
        if (y <= 0) {
            y = 0;
        }
        if (x <= 0) {
            x = 0;
        }
        if (y >= scHeigth - height) {
            y = scHeigth - height;
        }
        if (x >= scWidth / 2) {
            x = scWidth / 2;
        }
    }
}
