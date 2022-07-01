package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D g2;
    private Font webfontRegular;
    private final BufferedImage heart_full;
    private final BufferedImage heart_half;
    private final BufferedImage heart_blank;
    private final BufferedImage crystal_full;
    private final BufferedImage crystal_half;
    private final BufferedImage crystal_blank;
    private final ArrayList<String> message = new ArrayList<>();
    private final ArrayList<Integer> messageCounter = new ArrayList<>();
    private String currentDialogue = "";
    private int commandNum = 0;
    private int slotCol = 0;
    private int slotRow = 0;
    private int subState = 0;


    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        InputStream inputStream = getClass().getResourceAsStream("/font/000webfont Regular.ttf");
        try {
            assert inputStream != null;
            webfontRegular = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gamePanel);
        heart_full = heart.getEntityImage();
        heart_half = heart.getEntityImage2();
        heart_blank = heart.getEntityImage3();

        Entity crystal = new OBJ_ManaCrystal(gamePanel);
        crystal_full = crystal.getEntityImage();
        crystal_half = crystal.getEntityImage2();
        crystal_blank = crystal.getEntityImage3();
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(webfontRegular);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // TITLE STATE
        if (gamePanel.getGameState() == gamePanel.getTitleState()) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife();
            drawMessage();
        }

        // PAUSE STATE
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if (gamePanel.getGameState() == gamePanel.getCharacterState()) {
            drawCharacterScreen();
            drawInventory();
        }

        if (gamePanel.getGameState() == gamePanel.getOptionState()) {
            drawOptionScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gamePanel.getTileSize() / 2;
        int y = gamePanel.getTileSize() / 2;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gamePanel.getPlayer().getMaxLife() / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gamePanel.getTileSize();
        }

        // RESET
        x = gamePanel.getTileSize() / 2;
        y = gamePanel.getTileSize() / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gamePanel.getPlayer().getLife()) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gamePanel.getPlayer().getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gamePanel.getTileSize();
        }

        // DRAW MAX MANA
        x = (gamePanel.getTileSize() / 2) ;
        y = (int) (gamePanel.getTileSize() * 1.5);
        i = 0;
        while (i < gamePanel.getPlayer().getMaxMana() / 2) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += gamePanel.getTileSize();
        }

        x = (gamePanel.getTileSize() / 2) ;
        y = (int) (gamePanel.getTileSize() * 1.5);
        i = 0;

        // DRAW CURRENT MANA
        while (i < gamePanel.getPlayer().getMana()) {
            g2.drawImage(crystal_half, x, y, null);
            i++;
            if (i < gamePanel.getPlayer().getMana()) {
                g2.drawImage(crystal_full, x, y, null);
            }
            i++;
            x += gamePanel.getTileSize();
        }

    }

    public void drawMessage() {
        int messageX = gamePanel.getTileSize() - 35;
        int messageY = gamePanel.getTileSize() * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {

                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D Game";
        int x = getXCenterText(text);
        int y = gamePanel.getTileSize() * 3;

        // SHADOW
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // MAIN CHARACTER IMAGE
        x = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() * 2) / 2;
        y += gamePanel.getTileSize() * 2;
        g2.drawImage(gamePanel.getPlayer().getStandDown(), x, y, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXCenterText(text);
        y += gamePanel.getTileSize() * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "LOAD GAME";
        x = getXCenterText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "QUIT";
        x = getXCenterText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gamePanel.getTileSize(), y);
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXCenterText(text);
        int y = gamePanel.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // WINDOW
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() / 2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize() * 4);
        int height = gamePanel.getTileSize() * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gamePanel.getTileSize() * 2;
        final int frameY = gamePanel.getTileSize();
        final int frameWidth = gamePanel.getTileSize() * 5;
        final int frameHeight = gamePanel.getTileSize() * 9;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.getTileSize();
        final int lineHeight = 30;

        // NAMES
        g2.drawString("Level", textX, textY);
        g2.drawString("Life", textX, textY += lineHeight);
        g2.drawString("Mana", textX, textY += lineHeight);
        g2.drawString("Strength", textX, textY += lineHeight);
        g2.drawString("Toughness", textX, textY += lineHeight);
        g2.drawString("Attack", textX, textY += lineHeight);
        g2.drawString("Defense", textX, textY += lineHeight);
        g2.drawString("Exp", textX, textY += lineHeight);
//        g2.drawString("Next level", textX, textY += lineHeight);
        g2.drawString("Coin", textX, textY+= lineHeight);
        g2.drawString("Weapon", textX, textY += lineHeight + 10);
        g2.drawString("Shield", textX, textY + (lineHeight + 15));

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gamePanel.getTileSize();
        String value;

        value = String.valueOf(gamePanel.getPlayer().getLevel());
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.getPlayer().getLife() + "/" + gamePanel.getPlayer().getMaxLife();
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.getPlayer().getMana() + "/" + gamePanel.getPlayer().getMaxMana();
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().getStrength());
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().getToughness());
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().getAttack());
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().getDefense());
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gamePanel.getPlayer().getExp() + "/" + gamePanel.getPlayer().getNextLevelExp();
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().getCoin());
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gamePanel.getPlayer().getCurrentWeapon().getDown1(), tailX - gamePanel.getTileSize(), textY - 24, null);
        textY += gamePanel.getTileSize();
        g2.drawImage(gamePanel.getPlayer().getCurrentShield().getDown1(), tailX - gamePanel.getTileSize(), textY - 24, null);
    }

    public void drawInventory() {

        // FRAME
        int frameX = gamePanel.getTileSize() * 12;
        int frameY = gamePanel.getTileSize();
        int frameWidth = gamePanel.getTileSize() * 6;
        int frameHeight = gamePanel.getTileSize() * 5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.getTileSize() + 3;

        // DRAW PLAYER ITEMS
        for (int i = 0; i < gamePanel.getPlayer().getInventory().size(); i++) {

            // EQUIP CURSOR
            if (gamePanel.getPlayer().getInventory().get(i) == gamePanel.getPlayer().getCurrentWeapon() ||
                gamePanel.getPlayer().getInventory().get(i) == gamePanel.getPlayer().getCurrentShield()) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gamePanel.getTileSize(), gamePanel.getTileSize(), 10, 10);
            }

            g2.drawImage(gamePanel.getPlayer().getInventory().get(i).getDown1(), slotX, slotY, null);
            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        // CURSOR
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gamePanel.getTileSize();
        int cursorHeight = gamePanel.getTileSize();

        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESCRIPTION FRAME
        int dFrameY = frameY + frameHeight;
        int dFrameHeight = gamePanel.getTileSize() * 3;


        // DRAW DESCRIPTION TEXT
        int textX = frameX + 20;
        int textY = dFrameY + gamePanel.getTileSize();
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if (itemIndex < gamePanel.getPlayer().getInventory().size()) {

            drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);

            for (String line : gamePanel.getPlayer().getInventory().get(itemIndex).getDescription().split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public void drawOptionScreen() {

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW;
        int frameX = gamePanel.getTileSize() * 6;
        int frameY = gamePanel.getTileSize();
        int frameWidth = gamePanel.getTileSize() * 8;
        int frameHeight = gamePanel.getTileSize() * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                options_top(frameX, frameY);
                break;
            case 1:
                options_fullScreenNotification(frameX, frameY);
                break;
            case 2:
                break;
        }

        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXCenterText(text);
        textY = frameY + gamePanel.getTileSize();
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gamePanel.getTileSize();
        textY += gamePanel.getTileSize() * 2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setFullScreenStatus(!gamePanel.isFullScreenStatus());
                subState = 1;
            }
        }

        // MUSIC
        textY += gamePanel.getTileSize();
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        // SE
        textY += gamePanel.getTileSize();
        g2.drawString("SE", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        // CONTROLS
        textY += gamePanel.getTileSize();
        g2.drawString("Controls", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
        }

        // QUIT GAME
        textY += gamePanel.getTileSize();
        g2.drawString("Quit Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
        }

        // BACK
        textY += gamePanel.getTileSize() * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + (int) (gamePanel.getTileSize() * 4.5);
        textY = frameY + gamePanel.getTileSize() * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gamePanel.isFullScreenStatus()) {
            g2.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME
        textY += gamePanel.getTileSize();
        g2.drawRect(textX, textY, 120, 24);

        // SE VOLUME
        textY += gamePanel.getTileSize();
        g2.drawRect(textX, textY, 120, 24);

    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gamePanel.getTileSize();
        int textY = frameY + gamePanel.getTileSize() * 3;

        currentDialogue = "The change will take\neffect after restarting\nthe game";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gamePanel.getTileSize() * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
            }
        }
    }

    public int getItemIndexOnSlot() {
        return slotCol + (slotRow * 5);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.getScreenWidth() / 2 - length / 2;
    }

    public int getXAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }

    public void decreaseCommandNum() {
        this.commandNum--;
    }

    public void increaseCommandNum() {
        this.commandNum++;
    }

    public void decreaseSlotRow() {
        this.slotRow--;
    }

    public void increaseSlotRow() {
        this.slotRow++;
    }

    public void decreaseSlotCol() {
        this.slotCol--;
    }

    public void increaseSlotCol() {
        this.slotCol++;
    }

    public int getSlotCol() {
        return slotCol;
    }

    public int getSlotRow() {
        return slotRow;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public int getSubState() {
        return subState;
    }



    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }
}
