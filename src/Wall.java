import java.awt.*;
/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class Wall extends Entity
{
    public Wall(int X, int Y, int Height, int Width)
    {
        super(X, Y, Height, Width);
        this.customColor = Color.red;
        this.Type = "wall";

        this.imgPath="Images/Brick.jpg";//Default image for walls.
    }
}