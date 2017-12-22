import java.util.Timer;
import java.util.TimerTask;
/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class Countdown
{
    Timer timer = new Timer();
    private int timeLeft = 0;//How much time is left in the game.
    private boolean isTimerRunning = false;//Lets us know if the timer is running/on.
    private Game game;//Because we need to repaint and be able to end the game from the timer.

    public Countdown(Game game)
    {
        this.game = game;
    }
    public void subtractTime(int seconds) {
        timeLeft -= seconds;//Subtracts time from the timeLeft.
    }
    public void addTime(int seconds)
    {
        timeLeft += seconds;//Add time to timeLeft.
    }
    public void setTime(int seconds)
    {
        timeLeft = seconds;//Allows us to set a start time.
    }
    public String formatTime()
    {
        if(isTimerRunning == false) return "-:--";//Game has not begun and no time on timer.

        String minutes = "" + (int) Math.floor(timeLeft/60);
        String seconds = "" + timeLeft % 60;
        if(seconds.length() == 1)
        {
            seconds = "0" + seconds;//Formatting to show 1:05 as opposed to 1:5.
        }
        return minutes + ":" + seconds;
    }
    public void stopTimer()
    {
        if(isTimerRunning)
        {
            timer.cancel();
            isTimerRunning = false;
        }
    }
    public int getTimeLeft()
    {
        return timeLeft;
    }
    public void startTimer()
    {
        //Cannot start time that ticks negative.
        if(timeLeft <= 0 || isTimerRunning)
        {
            return;
        }
        isTimerRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()//This is the timer ticking.
        {
            @Override
            public void run() {//This is what happens when we tick.
                subtractTime(1);
                if (timeLeft <= 0)
                {
                    stopTimer();
                    game.endGame();
                }
                game.revalidate();
                game.repaint();
            }
        }, 0, 1000);
    }
}