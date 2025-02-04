
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Bullet extends Rectangle{
    int scnWidt;
    Random rnd = new Random();
    double graviry = 0.25;
    double  VelocityX = 5.0;
    double VelocityY =0;
    Bullet(int x , int y , int Width , int Height , int scWidth){
        super(x, y, Width, Height); 
        this.scnWidt = scWidth;
    }
    Bullet(int x , int y , int Width , int Height , int scWidth , double VelocityX , double VelocityY){
        super(x, y, Width, Height); 
        this.scnWidt = scWidth;
        this.VelocityX = VelocityX;
    }
    Bullet(int x , int y , int Width , int Height , int scWidth , double VelocityY){
        super(x, y, Width, Height); 
        this.VelocityY = VelocityY;
        this.scnWidt = scWidth;
    }
    
    public void Draw(Graphics g){
        g.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        g.fill3DRect(x, y, width, height, true);
    }
    public void move(){
        VelocityX += graviry;
        y += VelocityY;
        x += VelocityX;
        if(y < 0 || y + this.height > GameFrame.ScreenHeight){
            VelocityY *=-1;
        }
    }
    
    
}
