package main;

import java.io.*;
import java.nio.file.Path;

public class Config {

    GamePanel gamePanel;

            public Config(GamePanel gamePanel) {
                this.gamePanel = gamePanel;
            }

            public void saveConfig()  {
                Path saveData = Path.of("config.txt");

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveData.toFile()));

                    // FULL SCREEN
                    if (gamePanel.isFullScreenStatus()) {
                        bufferedWriter.write("On");
                    }
                    if (!gamePanel.isFullScreenStatus()) {
                        bufferedWriter.write("Off");
                    }
                    bufferedWriter.newLine();

                    // MUSIC VOLUME
                    bufferedWriter.write(String.valueOf(gamePanel.getMusic().getVolumeScale()));
                    bufferedWriter.newLine();

                    // SE VOLUME
                    bufferedWriter.write(String.valueOf(gamePanel.getSe().getVolumeScale()));
                    bufferedWriter.newLine();

                    bufferedWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            public void loadConfig() {

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));

                    String s = bufferedReader.readLine();

                    // FULL SCREEN
                    if (s.equals("On")) {
                        gamePanel.setFullScreenStatus(true);
                    }
                    if (s.equals("Off")) {
                        gamePanel.setFullScreenStatus(false);
                    }

                    // MUSIC VOLUME
                    s = bufferedReader.readLine();
                    gamePanel.getMusic().setVolumeScale(Integer.parseInt(s));

                    // SE VOLUME
                    s = bufferedReader.readLine();
                    gamePanel.getSe().setVolumeScale(Integer.parseInt(s));

                    bufferedReader.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

}
