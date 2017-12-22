/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class Prize extends Entity
{
    private static String[] prizeImages = new String[] {"Images/Red_Flag.png","Images/Blue_Present.png","Images/Yellow_Star.png"};
    public int prizeValue = 1;//Creating array of images.

    public Prize(int X, int Y, int Height, int Width)
    {
        super(X, Y, Height, Width);
        this.Type = "prize";
        randomizePrize();
    }

    public void randomizePrize()
    {
        int index = makeRandom(0,2);//Chooses random prize.
        this.imgPath = prizeImages[index];
        prizeValue = index + 1;//Index assigns value to array based from left to right and adding one. i.e. [0]+1=1, etc.
        //Setting prize value for Red Flag to 1, Blue Present to 2, and Yellow Start to 3.
    }
    public int makeRandom(int low, int high)
    {
        int range = high - low;
        return (int) Math.round(Math.random() * range) + low;
    }
}
