package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject {

    private GamePanel gamePanel;

    public OBJ_Door(GamePanel gamePanel) {

        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

}
