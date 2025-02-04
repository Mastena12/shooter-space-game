
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Rock extends Rectangle{
    int rockSize =40;
    Random rand = new Random();
    int healt = (width*height)/(rockSize*rockSize);
    double speed = 1 + rand.nextInt(0,3)/0.5;
    Rock(int x , int y , int width , int height  ){
        super(x, y, width, height);
    }
    public void Draw(Graphics g){
        g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
        g.drawRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.fill3DRect(x, y, width, height, true);
    } 
    public void move(){
        x -= speed;
    }
    
    public boolean IsPassed(){
        if(x<=0 -width) return true;
        return false;
    }
}
