import javax.swing.*;
import java.awt.*;
/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class Entity
{
    private int X, Y, Height, Width, LastX, LastY;
    protected String Type;
    protected Color customColor;

    protected String imgPath;

    public Entity(int X, int Y, int Height, int Width)
    {
        this.X = X;
        this.Y = Y;
        this.Height = Height;
        this.Width = Width;
    }
    //Getters and setters.
    public int getX(){ return X;}
    public void setX(int value){X = value;}
    public int getY(){ return Y;}
    public void setY(int value){Y = value;}
    public int getHeight(){ return Height;}
    public int getWidth(){ return Width;}
    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }
    public void Move(int x, int y)
    {
        LastX = this.X;//Storing last position in case we need to revert if boundaries are crossed.
        LastY = this.Y;
        X += x;
        Y += y;
    }
    public void Revert()
    {
        X = LastX;//Set to previous location when boundaries are crossed.
        Y = LastY;
    }
    //Entity will paint itself.
    public void paint(Graphics g, JPanel p)
    {
        //If I have an image, I want to use that image, otherwise use default color or Mario(image) now.
        if(imgPath!=null && imgPath!="")
        {
            java.net.URL imgURL = getClass().getResource(imgPath);
            Image img = new ImageIcon(imgURL).getImage();
            g.drawImage(img, X, Y, getWidth(), getHeight(), p);
        }
        else{
            g.setColor(customColor);
            g.fillRect(X,Y,Width,Height);
        }
    }
}
