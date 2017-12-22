/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class HighScore
{
    String name;
    int score;
    public HighScore(String name, int score)
    {
        this.name = name;
        this.score = score;
    }
    @Override
    public String toString()
    {
        return name + " => Score: " + score;
    }
}
