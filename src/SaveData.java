import javafx.geometry.Point2D;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 112358132134L;

    //Stores the object that will be serialized
    public Maze maze;
    public GameScreenGUI gameScreenGUI;
}
