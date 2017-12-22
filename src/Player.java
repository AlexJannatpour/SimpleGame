/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class Player extends Entity
//Player is an Entity and similar to copying all of the code out of Entity and pasting it into Player.
{
    public final int speed = 15;//Distance(speed) at which Player movers per key pressed.
    public Player(int X, int Y, int Height, int Width)
    {
        super(X, Y, Height, Width);
        this.Type = "player";
        this.imgPath = "Images/Mario.png";//Default character selected.
    }
    public void MoveLeft()
    {
        this.Move(-speed, 0);
    }
    public void MoveRight()
    {
        this.Move(speed, 0);
    }
    public void MoveDown()
    {
        this.Move(0,speed);
    }
    public void MoveUp()
    {
        this.Move(0,-speed);
    }
}
