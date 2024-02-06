/* 
Project 2
CS 1181
Kalli Koppin
19 October 2023
This project is to enhance understanding of gui elemnts, java swing, and
action listeners using standard coding practice

LINKS USED FOR HELP
Timer link: https://stackoverflow.com/questions/1006611/java-swing-timer
JOptionPane link: https://www.javatpoint.com/java-joptionpane
Repaint, revalidate: https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
Audio Clip Link: https://www.geeksforgeeks.org/play-audio-file-using-java/ and https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4679187
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Random;

public class ColorSwitch extends JFrame implements ActionListener {
    // variable declaration
    private JPanel content;
    private JButton redButton;
    private JButton greenButton;
    private JButton yellowButton;
    private JButton blueButton;
    private JLabel colorLabel;
    private JLabel wordLabel;
    private JLabel missed;
    private JLabel score;
    private String[] colorNames = { "Red", "Green", "Yellow", "Blue" };
    private Color[] colors = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE };
    private Random random = new Random();
    private int points = 0;
    private int missedPoints = 0;
    private Timer timer;
    private AudioInputStream correct;
    private AudioInputStream incorrect;
    private AudioInputStream done;
    private Clip gameOverClip;
    private Clip yep;
    private Clip nope;

    public ColorSwitch() {
        // set up content panel
        content = new JPanel();

        setContentPane(content);
        setTitle("ColorSwitch");
        setSize(600, 350);
        content.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // opening file clips try catch block
        try {
            correct = AudioSystem.getAudioInputStream(new File("mixkit-correct-answer-notification-947.wav"));
            yep = AudioSystem.getClip();
            yep.open(correct);

            incorrect = AudioSystem.getAudioInputStream(new File("mixkit-tech-break-fail-2947.wav"));
            nope = AudioSystem.getClip();
            nope.open(incorrect);

            done = AudioSystem.getAudioInputStream(new File("mixkit-arcade-game-complete-or-approved-mission-205.wav"));
            gameOverClip = AudioSystem.getClip();
            gameOverClip.open(done);

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error playing sound...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error playing sound...");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Error playing sound...");
            e.printStackTrace();
        }

        // red button set up
        redButton = new JButton("Red");
        redButton.setBackground(Color.RED);
        redButton.setOpaque(true);
        redButton.setBorderPainted(false);
        redButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        content.add(redButton);

        // blue button set up
        blueButton = new JButton("Blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.setOpaque(true);
        blueButton.setBorderPainted(false);
        blueButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        content.add(blueButton);

        // yellow button set up
        yellowButton = new JButton("Yellow");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.setOpaque(true);
        yellowButton.setBorderPainted(false);
        yellowButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        content.add(yellowButton);

        // green button set up
        greenButton = new JButton("Green");
        greenButton.setBackground(Color.GREEN);
        greenButton.setOpaque(true);
        greenButton.setBorderPainted(false);
        greenButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        content.add(greenButton);

        // score setup
        score = new JLabel(" Score: " + points);
        score.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        // missed score counter
        missed = new JLabel("Missed: " + missedPoints);
        missed.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        colorLabel = new JLabel();
        wordLabel = new JLabel();

        // add action listenerrrr
        redButton.addActionListener(this);
        greenButton.addActionListener(this);
        yellowButton.addActionListener(this);
        blueButton.addActionListener(this);

        // layout
        setLayout(new GridLayout(2, 2));
        content.add(redButton);
        content.add(greenButton);
        content.add(yellowButton);
        content.add(blueButton);

        content.add(score);
        content.add(missed);
        content.add(colorLabel);
        content.add(wordLabel);

        updateLabels();

        setVisible(true);

        // timer for timed mode
        timer = new Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                timesUp();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    // method to update the word and color for gameplay
    public void updateLabels() {
        int randomIndex = random.nextInt(4);
        int randomColorIndex = random.nextInt(4);

        colorLabel.setText("");
        colorLabel.setBackground(colors[randomColorIndex]);
        colorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

        wordLabel.setText(colorNames[randomIndex]);
        wordLabel.setForeground(colors[randomColorIndex]);
        wordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
    }

    // action listener for buttons
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonColor = clickedButton.getText();

        if (buttonColor.equalsIgnoreCase(colorNames[getIndexOfColor(colors)])) {
            points++;
            score.setText(" Score: " + points);
            missed.setText("Missed: " + missedPoints);
            yep.setMicrosecondPosition(0);
            yep.start();

        } else {
            missedPoints++;
            score.setText("Score: " + points);
            missed.setText("Missed: " + missedPoints);
            nope.setMicrosecondPosition(0);
            nope.start();

        }

        updateLabels();
        shuffle();
    }

    // get the index of the Color array filled with the color options
    public int getIndexOfColor(Color[] colors) {
        for (int i = 0; i < colorNames.length; i++) {
            if (colors[i] == colorLabel.getBackground()) {
                return i;
            }
        }
        return -1;
    }

    // method to shuffle buttons
    public void shuffle() {

        JButton[] buttons = { redButton, greenButton, yellowButton, blueButton };
        Random random = new Random();

        for (int i = 0; i < buttons.length; i++) {
            int randomIndex = random.nextInt(buttons.length);
            JButton temp = buttons[i];
            buttons[i] = buttons[randomIndex];
            buttons[randomIndex] = temp;
        }

        content.removeAll();
        for (JButton button : buttons) {
            content.add(button);
        }

        content.add(score);
        content.add(missed);
        content.add(colorLabel);
        content.add(wordLabel);

        content.revalidate();
        content.repaint();

    }

    // method to show game over screen after the time is up
    public void timesUp() {
        int choice;
        String gameOverWithTimer = "Game Over!";
        String playAgain = "Game Over!\nYour score was " + points + "\nYou missed " + missedPoints
                + "\nPlay Again?";

        gameOverClip.setMicrosecondPosition(0);
        gameOverClip.start();

        choice = JOptionPane.showOptionDialog(null, playAgain, gameOverWithTimer, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        // if else statement if the user wants to play again
        if (choice == JOptionPane.YES_OPTION) {

            points = 0;
            missedPoints = 0;
            missed.setText("Missed: " + missedPoints);
            score.setText(" Score: " + points);
            updateLabels();
            timer.restart();

        } else {
            System.exit(0);

        }
    }
    public static void main(String[] args) {

        // asks the user what game mode and tells the
        // users the instructions and allows them to choose game mode they would like to
        // play in

        String message = "Welcome to ColorSwitch!\n If you choose the relaxed mode, you have no time limit and you can go on for however long you would like to. To exit the game, press the X in the corner.\n If you chose timed mode, you have 10 seconds to get as many as you can!\n The game will show you a color word, and it will sometimes be in a different color compared to what it says.\n The way to earn a point is by pressing the color of the word with the corresponding colored button! \n You want to make sure to pay attention to the positions of the color buttons as they change where they are located.\n THIS GAME HAS SOUND!\nHave Fun and Good Luck!";

        JOptionPane.showMessageDialog(null, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);
        int modeChoice = JOptionPane.showOptionDialog(null, "Choose a mode: ", "Mode Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[] { "Timed Mode", "Relaxed Mode" }, "Relaxed Mode");

        if (modeChoice == 0) {
            new ColorSwitch();
        } else if (modeChoice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);

        } else {
            ColorSwitch game = new ColorSwitch();
            game.shuffle();
            game.timer.stop();

        } // end of if else

    }// end of main
}// end of class
