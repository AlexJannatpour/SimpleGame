import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**Simple Game(Final Project)
 *@author Alex Jannatpour
 *@version Fall 2016
 *CSci 1130
 */
public class AlexJannatpour_SimpleGame extends JApplet implements ActionListener, ItemListener
{
    //Beginning of Game(JPanel class) and JPanels.
    Game panelGame;
    JPanel panelTitle, panelButtons, panelCharacterSelection;
    //End of all.

    //Beginning of JComponents.
    JLabel labelTitle;//JLabel we will add to panelTitle.
    ButtonGroup bgCharacterChoice;//JButtonGroup to contain JRadioButtons selection.
    JRadioButton jrbKirby, jrbMario, jrbPacMan, jrbBowser, jrbPikachu;//Registering JRadioButtons.
    JButton buttonStart;//Registering JButton.
    //End of JComponents.

    public void init()
    {
        //Start panelGame.
        panelGame = new Game();//Creating new Game/JPanel which will include what we wrote in that object.
        panelGame.setLayout(new FlowLayout());//Setting Layout to FlowLayout.
        add(panelGame, BorderLayout.CENTER);//Adding Game/JPanel to JApplet.
        //End panelGame.

        //Start panelTitle.
        panelTitle=new JPanel();
        labelTitle=new JLabel("<html><font size=+2 color=BLUE>Collect the Prizes to Win!</font></html>");//HTML formatting of text.
        panelTitle.setBackground(Color.white);//Setting JPanel background color.
        panelTitle.add(labelTitle);//Adding JLabel to panelTitle.
        add(panelTitle, BorderLayout.NORTH);//Assigning JPanel to "North" BorderLayout or at top of JApplet.
        //End panelTitle.

        //Start panelButtons.
        panelButtons = new JPanel();
        panelButtons.setBackground(Color.white);//Setting JPanel background color.

        buttonStart = new JButton("Start");//Giving visible text name for JButton.
        buttonStart.setBackground(Color.green);//Giving color to JButton.
        buttonStart.setFont(new Font("Times",Font.BOLD,16));//Setting new font for JButton.
        buttonStart.setFocusPainted(false);//Removing dotted outline from JButton when clicked.
        buttonStart.addActionListener(this);//Adding ActionListener to JButton so it will do something when clicked.

        //Start adding buttons panelButtons.
        panelButtons.add(buttonStart);//Adding JButton to panelButtons.
        //End adding buttons to panelButtons.
        add(panelButtons, BorderLayout.SOUTH);//Assigning JPanel to "South" BorderLayout or at bottom of JApplet.
        //End panelButtons.

        //Start panelCharacterSelection.
        panelCharacterSelection = new JPanel();//Creating new JPanel, panelCharacterSelection.
        panelCharacterSelection.setLayout(new GridLayout(5, 1));//Using GridLayout to create 5 rows in 1 column.
        add(panelCharacterSelection, BorderLayout.WEST);//Assigning JPanel to "West" BorderLayout or left-most in JApplet.

        bgCharacterChoice = new ButtonGroup();//Creating new JButtonGroup. Must do this so you cannot select more than one option at one time.
        jrbKirby = new JRadioButton("Kirby");//Creating JRadioButton, x1.
        jrbKirby.setBackground(Color.white);//Color to contrast between JRadioButtons.
        jrbMario = new JRadioButton("Mario");//Creating JRadioButton, x2.
        jrbPacMan = new JRadioButton("Pac-Man");//Creating JRadioButton, x3.
        jrbPacMan.setBackground(Color.white);//Color to contrast between JRadioButtons.
        jrbBowser = new JRadioButton("Bowser");//Creating JRadioButton, x4.
        jrbPikachu = new JRadioButton("Pikachu");//Creating JRadioButton, x5.


        Font jrbFont = new Font("Serif",Font.BOLD,18);//Custom font #1.
        Font jrbFont_2 = new Font("Times",Font.PLAIN,18);//Custom font #2.
        jrbBowser.setFont(jrbFont);//Applying font #1 to JRadioButton.
        jrbKirby.setFont(jrbFont_2);//Applying font #2 to JRadioButton.
        jrbMario.setFont(jrbFont);//Applying font to #1 JRadioButton.
        jrbPacMan.setFont(jrbFont_2);//Applying font to #2 JRadioButton.
        jrbPikachu.setFont(jrbFont);//Applying font to #1 JRadioButton.

        jrbMario.setSelected(true);//Pac-Man is set to default character selected in JRadio button group.

        jrbKirby.setFocusPainted(false);//Remove dotted outline when JRadioButton is selected.
        jrbMario.setFocusPainted(false);//Remove dotted outline when JRadioButton is selected.
        jrbPacMan.setFocusPainted(false);//Remove dotted outline when JRadioButton is selected.
        jrbPikachu.setFocusPainted(false);//Remove dotted outline when JRadioButton is selected.
        jrbBowser.setFocusPainted(false);//Remove dotted outline when JRadioButton is selected.

        bgCharacterChoice.add(jrbBowser);//Registering JButton to JButtonGroup, x1.
        bgCharacterChoice.add(jrbKirby);//Registering JButton to JButtonGroup, x2.
        bgCharacterChoice.add(jrbMario);//Registering JButton to JButtonGroup, x3.
        bgCharacterChoice.add(jrbPacMan);//Registering JButton to JButtonGroup, x4.
        bgCharacterChoice.add(jrbPikachu);//Registering JButton to JButtonGroup, x5.

        jrbBowser.addItemListener(this);//Adding ActionListener to JRadioButton to do something when selected.
        jrbKirby.addItemListener(this);//Adding ActionListener to JRadioButton to do something when selected.
        jrbMario.addItemListener(this);//Adding ActionListener to JRadioButton to do something when selected.
        jrbPacMan.addItemListener(this);//Adding ActionListener to JRadioButton to do something when selected.
        jrbPikachu.addItemListener(this);//Adding ActionListener to JRadioButton to do something when selected.

        panelCharacterSelection.add(jrbBowser);//Finally adding JRadioButton to panelCharacterSelection.
        panelCharacterSelection.add(jrbKirby);//Finally adding JRadioButton to panelCharacterSelection.
        panelCharacterSelection.add(jrbMario);//Finally adding JRadioButton to panelCharacterSelection.
        panelCharacterSelection.add(jrbPacMan);//Finally adding JRadioButton to panelCharacterSelection.
        panelCharacterSelection.add(jrbPikachu);//Finally adding JRadioButton to panelCharacterSelection.
        //End panelCharacterSelection.
    }
    public void start(){
        panelGame.setBoard();//Set up the walls and background without prize on map or timer starting.
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        panelGame.grabFocus();//Grabbing focus because KeyListener needs focus on Game.
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        panelGame.setNewGame();//This is where the prize and timer start.
        repaint();
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == jrbBowser)//Helping us to select character for our Player.
        {
            panelGame.entityPlayer.setImgPath("Images/Bowser.png");
        }
        else if (e.getSource() == jrbKirby)
        {
            panelGame.entityPlayer.setImgPath("Images/Kirby.png");
        }
        else if (e.getSource() == jrbPikachu)
        {
            panelGame.entityPlayer.setImgPath("Images/Pikachu.png");
        }
        else if (e.getSource() == jrbMario)
        {
            panelGame.entityPlayer.setImgPath("Images/Mario.png");
        }
        else if (e.getSource() == jrbPacMan)
        {
            panelGame.entityPlayer.setImgPath("Images/Pac-Man.png");
        }
        revalidate();//Both needed to show character switch.
        repaint();
    }
}
