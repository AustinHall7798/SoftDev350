import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataAccessGUI extends JFrame{
    private JLabel Label;
    private JButton View;
    private JButton Add;
    private JButton Remove;
    private JButton button4;
    private JPanel DataPanel;
    private JPanel ViewPanel;

    public DataAccessGUI(String string) {
        super(string);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(DataPanel);
        this.pack();
        this.setVisible(true);
        View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
