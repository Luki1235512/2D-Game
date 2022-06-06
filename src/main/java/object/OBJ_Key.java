package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject {

    private GamePanel gamePanel;

    public OBJ_Key(GamePanel gamePanel) {

        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}