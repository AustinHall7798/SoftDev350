import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionAndAnswer extends JFrame{
    private JTextField QuestionField;
    private JButton submitButton;
    public JTextField answerField;
    private JPanel MainScreen;

    public QuestionAndAnswer(String question, GameScreenGUI gameScreenGUI) {
        super("Trivia Maze Game");


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainScreen);
        this.pack();
        this.setVisible(true);
        QuestionField.setText(question);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
                gameScreenGUI.setVisible(true);
            }
        });
    }

    private void closeWindow() {
        this.setVisible(false);
    }
}
