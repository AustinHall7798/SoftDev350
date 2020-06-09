import javafx.geometry.Point2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class StartScreenGUI extends JFrame {
    private JPanel StartGamePanel;
    private JButton StartNewGame;
    private JButton LoadGame;
    private JButton ReturnToTitleScreen;
    private JLabel PlayGameLabel;
    private static Maze Maze;
    private static GameScreenGUI gameScreen;
    private static StartScreenGUI startScreenGUI;

    public StartScreenGUI(String title) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(StartGamePanel);
        this.pack();
        this.setVisible(true);

        StartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InitializeNewGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        LoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoadGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void InitializeNewGame() throws IOException {
        gameScreen = new GameScreenGUI("Trivia Maze Game");
        this.setVisible(false);
        gameScreen.setVisible(true);
       // gameScreen.PlayGame();
    }

    public void LoadGame() throws IOException {
        new GameScreenGUI("Trivia Maze Game").LoadGame("Trivia Maze Game");
    }

}

