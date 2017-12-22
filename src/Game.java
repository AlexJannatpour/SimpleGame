import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class Game extends JPanel implements KeyListener
{
    Player entityPlayer;
    int Score;
    Prize[] prizes = new Prize[8];//Number of prizes, array.
    Wall[] walls = new Wall[13];//Number of walls, array.
    Countdown countdown = new Countdown(this);
    boolean isGameOver = false;
    HighScore[] highscores = new HighScore[3];//Only allow up to 3 high scores.

    public Game()
    {
        setFocusable(true);//Help KeyListener focus on this.
        addKeyListener(this);
    }
    public int makeRandom(int low, int high)
    {
        int range = high - low;
        return (int) Math.round(Math.random() * range) + low;
    }
    public void endGame()
    {
        String name = JOptionPane.showInputDialog(this, "Thank you for playing! \nWhat is your name?");//First text in JOptionPane.
        if(name == null) name = "Unknown";//If no name entered, put "Unknown" by default.
        HighScore newScore = new HighScore(name, Score);
        addHighScore(newScore);

        String highScoresList = "";
        for(int i = 0; i < highscores.length; i++)
        {
            if(highscores[i] != null)
            {
                highScoresList += "\n" + highscores[i].toString();
            }
        }
        JOptionPane.showMessageDialog(this, "Top Score(s): \n =+=+=+=+=+=+=+=" + highScoresList);//Final text in JOptionPane.
    }

    public void addHighScore(HighScore newScore){
        //Looping backwards through the array, to see if we meet a previous high score, from the bottom up.
        for(int i = highscores.length - 1; i >= 0; i--)
        {
            if(highscores[i] == null)
            {
                //If the top position is blank, we take the top position.
                if(i == 0) highscores[i] = newScore;
                //If the position below an actual score is blank, and...
                //If the score above higher, than we place the new score in this blank spot.
                else if(highscores[i-1] != null && highscores[i-1].score > newScore.score) highscores[i] = newScore;
            }
            else if(highscores[i].score < newScore.score)
            {
                //If our score beats the previous score, we save the previous position to knock it down a peg.
                HighScore prev = highscores[i];
                //Move new score into previous holder's slot and...
                highscores[i] = newScore;

                //Push down previous high score.
                if(i < highscores.length - 1)
                {
                    highscores[i+1] = prev;
                }
                //If a spot doesn't exist, does not matter, it will go away.
            }
        }
    }
    public void setBoard()
    {
        entityPlayer = new Player(25,25,50,50);//Size and initial location of player.
        walls[0] = new Wall(getWidth()-250,570,10,150);//Segment #1 off bottom-right wall.
        walls[1] = new Wall(140,getHeight()-250,150,10);//Segment #1 off bottom-left wall.
        walls[2] = new Wall(100,0,150,10);//Top-left wall.
        walls[3] = new Wall(getWidth()-100,570,150,10);//Bottom-right wall.
        walls[4] = new Wall(0,getHeight()-100,10,150);//Bottom-left wall.
        walls[5] = new Wall(575,0,50,128);//Score and timer wall.
        walls[6] = new Wall(100,150,10,300);//Segment #1 off top-left wall.
        walls[7] = new Wall(250,150,150,10);//Segment #2 off top-left wall.
        walls[8] = new Wall(140,getHeight()-250,10,150);//Segment #2 off bottom-left wall.
        walls[9] =  new Wall(290,getHeight()-250,150,10);//Segment #3 bottom-left wall.
        walls[10] = new Wall(555,200,10,150);//Top-right wall.
        walls[11] = new Wall(555,200,150,10);//Segment #1 off top-right wall.
        walls[12] = new Wall(405,340,10,150);//Segment #2 off top-right wall.
    }
    public void setNewGame()
    {
        //set new locations for the prizes.
        for(int i=0;i< prizes.length;i++)
        {
            prizes[i] = new Prize(0,0,30,30);//Size of Prize in prize array.
            spawnPrize(prizes[i]);//Use spawnPrize method.
        }
        Score=0;//Set start score to 0.

        //Set the countdown starting time:
        countdown.setTime(15);
        countdown.stopTimer();
        entityPlayer.setX(25);
        entityPlayer.setY(25);
    }
    //spawnPrize picks a random location and type of prize.
    public void spawnPrize(Prize prize)
    {
        //Randomly choose a new location at X and Y... and set that location to the current prize.
        int x=makeRandom(30,getWidth()-30);
        int y=makeRandom(30,getHeight()-30);
        prize.setX(x);
        prize.setY(y);

        //Make certain that the prize does not collide with the player first. If so, recall method.
        if(doEntitiesCollide(prize,entityPlayer))
            spawnPrize(prize);

        //Making certain the new prize does not collide with walls second. If so, recall recall method.
        for(int j=0;j<walls.length;j++){
            //If a prize collides with the wall, it tries again.
            if(doEntitiesCollide(prize,walls[j]))
                spawnPrize(prize);
        }
        //Make sure it doesn't collide with other prizes third. If so, recall method.
        for(int i=0;i<prizes.length;i++)
        {
            Prize otherPrize = prizes[i];
            if (otherPrize != null && //Prize may not be initialized.
                    prize != otherPrize && //Do not check against itself.
                    doEntitiesCollide(prize, prizes[i])) //If it finally does collide, recall method.
                spawnPrize(prize);//Point of spawnPrize is to avoid prizes spawning on player, other prizes, and walls.
        }
        //If we get here, we have an approved location and now we choose a random prize.
        prize.randomizePrize();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Paint game background.
        java.net.URL imgURL = getClass().getResource("images/Game_Background.jpg");
        Image img = new ImageIcon(imgURL).getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        int wallix = 0;
        while(wallix<walls.length){
            walls[wallix].paint(g,this);
            wallix++;
        }
        for (int i = 0; i < prizes.length; i++)//For loop to paint prizes.
        {
            if(prizes[i] != null)
            prizes[i].paint(g, this);
        }
        entityPlayer.paint(g, this);
        g.setColor(Color.white);

        Font tr = new Font("Serif", Font.PLAIN, 18);
        g.setFont(tr);
        g.drawString("Score: " + Score, 580, 20);
        g.drawString("Time Left: " + countdown.formatTime(), 580, 45);
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(countdown.getTimeLeft() <= 0)
        {
            return;
        }
        countdown.startTimer();//startTimer will do the check if timer is running.
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_UP)
        {
            entityPlayer.MoveUp();
        }
        if(key==KeyEvent.VK_DOWN)
        {
            entityPlayer.MoveDown();
        }
        if(key==KeyEvent.VK_LEFT)
        {
            entityPlayer.MoveLeft();
        }
        if(key==KeyEvent.VK_RIGHT)
        {
            entityPlayer.MoveRight();
        }
        if(entityPlayer.getX() < 0 ||
                entityPlayer.getY() < 0 ||
                entityPlayer.getX() + entityPlayer.getWidth() > this.getWidth() ||
                entityPlayer.getY() + entityPlayer.getHeight() > this.getHeight())
        {
            entityPlayer.Revert();
        }

        for(int i=0;i< walls.length;i++)
        {
            if (doEntitiesCollide(walls[i], entityPlayer))
            {
                entityPlayer.Revert();
            }
        }
        for(int i=0;i<prizes.length;i++){
            if(doEntitiesCollide(entityPlayer,prizes[i])){
                Score += prizes[i].prizeValue;
                countdown.addTime(1);
                spawnPrize(prizes[i]);

            }
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean isEntityInBounds(Entity entity)
    {
        int newX = entity.getX();
        int newY = entity.getY();
        if(newX<0)
        {
            return false;
        }
        if(newY<0)
        {
            return false;
        }
        if(newX>getWidth()-entity.getWidth())
            return false;
        if(newY>getHeight()-entity.getHeight())
            return false;

        return true;
    }
    public boolean doEntitiesCollide(Entity entity_1,Entity entity_2)
    {
        //Lets get all the corners in easy-to-read variablea:
        int px1,px2,py1,py2,wx1,wx2,wy1,wy2;//Player versus wall.
        boolean xint, yint;
        px1=entity_1.getX();//Left X on first object.
        wx1=entity_2.getX();//Left X on second object.
        py1=entity_1.getY();//Top Y on first.
        wy1=entity_2.getY();//top Y on second.
        px2=px1+entity_1.getWidth();//Right X on first.
        wx2=wx1+entity_2.getWidth();//Right X on second.
        py2=py1+entity_1.getHeight();//Bottom Y on first.
        wy2=wy1+entity_2.getHeight();//Bottom Y on second.

        //we have two planes to check - x and y must both have intersections.
        //If both do, we intersect in 2d.
        xint=false;//Do the two objects intersect in the X axis?
        yint=false;//Do the two objects intersect in the Y axis?

        //Do we intersect in the x-axis?
        //We do if any line intersects with any corner in both directions...
        //i.e. px1 < wx1 < px2(and all other combinations in the X plane).
        if(     (px1 < wx1 && wx1 < px2) || (px1 < wx2 && wx2 < px2) ||
                (wx1 < px1 && px1 < wx2) || (wx1 < px2 && px2 < wx2))
        {
            xint = true;
        }
        //Do we intersect in the y-axis?
        //We do if any line intersects with any corner, in both directions.
        //i.e. py1 < wy1 < py2(and all other combinations in the Y plane).
        if(     (py1 < wy1 && wy1 < py2) || (py1 < wy2 && wy2 < py2) ||
                (wy1 < py1 && py1 < wy2) || (wy1 < py2 && py2 < wy2))
            yint=true;


        //If we intersect in both planes (X and Y), we intersect in 2d.
        if(xint && yint)
        {
            return true;//doEntitiesCollide? Yes!
        }
        return false;//doEntitiesCollide? No.
    }
}
