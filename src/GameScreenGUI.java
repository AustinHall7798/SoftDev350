import javafx.geometry.Point2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameScreenGUI extends JFrame {
    private static boolean NeedsSaved;
    private JPanel GamePanel;
    private JButton UP;
    private JButton RIGHT;
    private JButton LEFT;
    private JButton DOWN;
    private JPanel MazeGrid;
    private JPanel ButtonPanel;
    private JTextArea QuestionArea;
    public JTextField textField1;
    private JPanel AnswerAreaPanel;
    private JButton submitButton;
    private JLabel RoomX0Y0;
    private JLabel RoomX1Y0;
    private JLabel RoomX2Y0;
    private JLabel RoomX3Y0;
    private JLabel RoomX0Y1;
    private JLabel RoomX1Y1;
    private JLabel RoomX2Y1;
    private JLabel RoomX3Y1;
    private JLabel RoomX0Y2;
    private JLabel RoomX1Y2;
    private JLabel RoomX2Y2;
    private JLabel RoomX3Y2;
    private JLabel RoomX0Y3;
    private JLabel RoomX1Y3;
    private JLabel RoomX2Y3;
    private JLabel RoomX3Y3;
    private JLabel X0Y0Right;
    private JLabel X1Y0Right;
    private JLabel X2Y0Right;
    private JLabel X0Y1Right;
    private JLabel X1Y1Right;
    private JLabel X2Y1Right;
    private JLabel X0Y2Right;
    private JLabel X1Y2Right;
    private JLabel X2Y2Right;
    private JLabel X0Y3Right;
    private JLabel X1Y3Right;
    private JLabel X2Y3Right;
    private JLabel X0Y0Bottom;
    private JLabel X1Y0Bottom;
    private JLabel X2Y0Bottom;
    private JLabel X3Y0Bottom;
    private JLabel X0Y1Bottom;
    private JLabel X1Y1Bottom;
    private JLabel X2Y1Bottom;
    private JLabel X3Y1Bottom;
    private JLabel X0Y2Bottom;
    private JLabel X1Y2Bottom;
    private JLabel X2Y2Bottom;
    private JLabel X3Y2Bottom;
    private JButton Save;
    private JButton Load;
    private JButton MainMenu;
    private JPanel ResourcePanel;
    private static Maze Maze;
    private QuestionAndAnswer answerField;
    private GameScreenGUI gameScreenGUI = this;
    private String answer;
    private boolean correctAnswer;
    private int direction;

    static Map<Point2D, JLabel> roomMap = new HashMap<Point2D, JLabel>();
    static Map<Point2D, JLabel[]> wallMap = new HashMap<Point2D, JLabel[]>();

    public GameScreenGUI(String title) throws IOException {
        super(title);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GamePanel);
        this.pack();
        ButtonPanel.setVisible(true);
        ResourcePanel.setVisible(true);

        Maze = new Maze();

        roomMap.put(new Point2D(0, 0), RoomX0Y0);
        roomMap.put(new Point2D(0, 1), RoomX0Y1);
        roomMap.put(new Point2D(0, 2), RoomX0Y2);
        roomMap.put(new Point2D(0, 3), RoomX0Y3);
        roomMap.put(new Point2D(1, 0), RoomX1Y0);
        roomMap.put(new Point2D(1, 1), RoomX1Y1);
        roomMap.put(new Point2D(1, 2), RoomX1Y2);
        roomMap.put(new Point2D(1, 3), RoomX1Y3);
        roomMap.put(new Point2D(2, 0), RoomX2Y0);
        roomMap.put(new Point2D(2, 1), RoomX2Y1);
        roomMap.put(new Point2D(2, 2), RoomX2Y2);
        roomMap.put(new Point2D(2, 3), RoomX2Y3);
        roomMap.put(new Point2D(3, 0), RoomX3Y0);
        roomMap.put(new Point2D(3, 1), RoomX3Y1);
        roomMap.put(new Point2D(3, 2), RoomX3Y2);
        roomMap.put(new Point2D(3, 3), RoomX3Y3);

        wallMap.put(new Point2D(0, 0), new JLabel[]{null, X0Y0Right, X0Y0Bottom, null});
        wallMap.put(new Point2D(1, 0), new JLabel[]{null, X1Y0Right, X1Y0Bottom, X0Y0Right});
        wallMap.put(new Point2D(2, 0), new JLabel[]{null, X2Y0Right, X2Y0Bottom, X1Y0Right});
        wallMap.put(new Point2D(3, 0), new JLabel[]{null, null, X3Y0Bottom, X2Y0Right});

        wallMap.put(new Point2D(0, 1), new JLabel[]{X0Y0Bottom, X0Y1Right, X0Y1Bottom, null});
        wallMap.put(new Point2D(1, 1), new JLabel[]{X1Y0Bottom, X1Y1Right, X1Y1Bottom, X0Y1Right});
        wallMap.put(new Point2D(2, 1), new JLabel[]{X2Y0Bottom, X2Y1Right, X2Y1Bottom, X1Y1Right});
        wallMap.put(new Point2D(3, 1), new JLabel[]{X3Y0Bottom, null, X3Y1Bottom, X2Y1Right});

        wallMap.put(new Point2D(0, 2), new JLabel[]{X0Y1Bottom, X0Y2Right, X0Y2Bottom, null});
        wallMap.put(new Point2D(1, 2), new JLabel[]{X1Y1Bottom, X1Y2Right, X1Y2Bottom, X0Y2Right});
        wallMap.put(new Point2D(2, 2), new JLabel[]{X2Y1Bottom, X2Y2Right, X2Y2Bottom, X1Y2Right});
        wallMap.put(new Point2D(3, 2), new JLabel[]{X3Y1Bottom, null, X3Y2Bottom, X2Y2Right});

        wallMap.put(new Point2D(0, 3), new JLabel[]{X0Y2Bottom, X0Y3Right, null, null});
        wallMap.put(new Point2D(1, 3), new JLabel[]{X1Y2Bottom, X1Y3Right, null, X0Y3Right});
        wallMap.put(new Point2D(2, 3), new JLabel[]{X2Y2Bottom, X2Y3Right, null, X1Y3Right});
        wallMap.put(new Point2D(3, 3), new JLabel[]{X3Y2Bottom, null, null, X2Y3Right});

        roomMap.get(Maze.GetRoom()).setIcon(new ImageIcon(ImageIO.read((this.getClass().getResource("MazeCharacter.png")))));
        boolean gameOver = false;
        boolean won = false;

        UP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Maze.Rooms[(int) Maze.GetRoom().getX()][(int) Maze.GetRoom().getY()].getTopLocked()) {
                    Maze.AskQuestion(0, gameScreenGUI);
                    displayQuestionWindow();
                    direction = 0;
                }
                if (Maze.CheckLoss(0, 0, new ArrayList<Point2D>()))
                    LostGame();
                NeedsSaved = true;
            }
        });
        RIGHT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Maze.Rooms[(int) Maze.GetRoom().getX()][(int) Maze.GetRoom().getY()].getRightLocked()) {
                    Maze.AskQuestion(1, gameScreenGUI);
                    displayQuestionWindow();
                    direction = 1;
                }
                if (Maze.CheckLoss(0, 0, new ArrayList<Point2D>()))
                    LostGame();
                NeedsSaved = true;
            }
        });
        DOWN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Maze.Rooms[(int) Maze.GetRoom().getX()][(int) Maze.GetRoom().getY()].getBottomLocked()) {
                    Maze.AskQuestion(2, gameScreenGUI);
                    displayQuestionWindow();
                    direction = 2;
                }
                if (Maze.CheckLoss(0, 0, new ArrayList<Point2D>()))
                    LostGame();
                NeedsSaved = true;
            }
        });
        LEFT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Maze.Rooms[(int) Maze.GetRoom().getX()][(int) Maze.GetRoom().getY()].getLeftLocked()) {
                    Maze.AskQuestion(3, gameScreenGUI);
                    displayQuestionWindow();
                    direction = 3;
                }
                if (Maze.CheckLoss(0, 0, new ArrayList<Point2D>()))
                    LostGame();
                NeedsSaved = true;
            }
        });

        won = Maze.CheckWin();
        if (won) {

            WonGame();

        }
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                correctAnswer = Maze.getAnswer(textField1.getText(), direction);
                try {
                    roomMap.get(Maze.GetRoom()).setIcon(new ImageIcon(ImageIO.read((this.getClass().getResource("BlackSquare.png")))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if (!correctAnswer) {
                    try {
                        setLockedWall();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                Maze.ChangeRooms(direction, correctAnswer);
                textField1.setText("");
                QuestionArea.setText("");
                try {
                    roomMap.get(Maze.GetRoom()).setIcon(new ImageIcon(ImageIO.read((this.getClass().getResource("MazeCharacter.png")))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                ButtonPanel.setVisible(true);
                ResourcePanel.setVisible(true);
                AnswerAreaPanel.setVisible(false);
            }
        });
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveGame();
            }
        });
        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadGame();
            }
        });
        MainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameScreenGUI.setVisible(false);
                TriviaMazeGameGui.main(null);
            }
        });
    }

    public void PlayGame() throws IOException {

        boolean gameOver = false;
        boolean won = false;

        while (!gameOver) {

            TakeTurn();
            won = Maze.CheckWin();
            if (won) {

                WonGame();

            }//end if games over

        }//end while game not over

    }//end PlayGame

    private void TakeTurn() throws IOException {

        roomMap.get(Maze.GetRoom()).setIcon(new ImageIcon(ImageIO.read((this.getClass().getResource("WallHorizontal.png")))));


    }//end Take Turn*/

    private static void LostGame() {

        // System.out.println("Game Over!");
        // System.out.println("Incorrect Answers: " + Maze.getLockedRoomsCount());
        // System.out.println("Correct Answers: " + Maze.getCorrectAnswersCount() + "\n");


    }//end LostGame

    private static void WonGame() {

        //System.out.println("Congratulations, you've won!");
        // System.out.println("Incorrect Answers: " + Maze.getLockedRoomsCount());
        // System.out.println("Correct Answers: " + Maze.getCorrectAnswersCount() + "\n");

        // MainGameMenu();

    }//end wongame

    public void displayQuestionWindow() {
        ButtonPanel.setVisible(false);
        ResourcePanel.setVisible(false);
        AnswerAreaPanel.setVisible(true);
        textField1.requestFocusInWindow();
    }

    public void setQuestionArea(String string) {
        QuestionArea.setText(string);
    }

    public String getAnswer() {
        return answer;
    }

    private void setLockedWall() throws IOException {
        if (direction % 2 != 0) {
            wallMap.get(Maze.GetRoom())[direction].setIcon((new ImageIcon(ImageIO.read((this.getClass().getResource("LockedHorizontal.png"))))));
        } else {
            wallMap.get(Maze.GetRoom())[direction].setIcon((new ImageIcon(ImageIO.read((this.getClass().getResource("LockedVertical.png"))))));
        }
    }

    private void SaveGame() {

        SaveData data = new SaveData();
        data.maze = Maze;
        data.gameScreenGUI = gameScreenGUI;

        try {

            ResourceManager.Save(data, "resources/" + "SavedGame" + ".triv");
            NeedsSaved = false;

        } catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }//end try

    }//end SaveGame

    private boolean LoadGame() {

        boolean loaded = false;
        try {
            //Will save the game with the chosen file name
            SaveData data = (SaveData) ResourceManager.Load("resources/SavedGame.triv");
            Maze = data.maze;
            gameScreenGUI = data.gameScreenGUI;
            loaded = true;
        } catch (Exception e) {
            System.out.println("Couldn't load saved data: " + e.getMessage());
        }
        return loaded;
    }//end LoadGame

    public boolean LoadGame(String string) {
        try {
            gameScreenGUI = new GameScreenGUI(string);
            LoadGame();
            return true;
        } catch(Exception e) {

        }
        return false;
    }
}

