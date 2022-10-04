package main;

import entity.Entity;
import object.OBJ_Coin;
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
    private final BufferedImage coin;
    private final ArrayList<String> message = new ArrayList<>();
    private final ArrayList<Integer> messageCounter = new ArrayList<>();
    private String currentDialogue = "";
    private int commandNum = 0;
    private int playerSlotCol = 0;
    private int playerSlotRow = 0;
    private int npcSlotCol = 0;
    private int npcSlotRow = 0;
    private int subState = 0;
    private int counter = 0;
    private Entity npc;


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

        Entity goldCoin = new OBJ_Coin(gamePanel);
        coin = goldCoin.getDown1();
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
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if (gamePanel.getGameState() == gamePanel.getCharacterState()) {
            drawCharacterScreen();
            drawInventory(gamePanel.getPlayer(), true);
        }

        // OPTION STATE
        if (gamePanel.getGameState() == gamePanel.getOptionState()) {
            drawOptionScreen();
        }

        // GAME OVER STATE
        if (gamePanel.getGameState() == gamePanel.getGameOverState()) {
            drawGameOverScreen();
        }

        // TRANSITION STATE
        if (gamePanel.getGameState() == gamePanel.getTransitionState()) {
            drawTransition();
        }

        // TRADE STATE
        if (gamePanel.getGameState() == gamePanel.getTradeState()) {
            drawTradeScreen();
        }

        // SLEEP STATE
        if (gamePanel.getGameState() == gamePanel.getSleepState()) {
            drawSleepScreen();
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
        int x = gamePanel.getTileSize() * 3;
        int y = gamePanel.getTileSize() / 2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize() * 6);
        int height = gamePanel.getTileSize() * 4;

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

    public void drawInventory(Entity entity, boolean cursor) {

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gamePanel.getPlayer()) {
            frameX = gamePanel.getTileSize() * 12;
            frameY = gamePanel.getTileSize();
            frameWidth = gamePanel.getTileSize() * 6;
            frameHeight = gamePanel.getTileSize() * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gamePanel.getTileSize() * 2;
            frameY = gamePanel.getTileSize();
            frameWidth = gamePanel.getTileSize() * 6;
            frameHeight = gamePanel.getTileSize() * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.getTileSize() + 3;

        // DRAW PLAYER ITEMS
        for (int i = 0; i < entity.getInventory().size(); i++) {

            // EQUIP CURSOR
            if (entity.getInventory().get(i) == entity.getCurrentWeapon() ||
                entity.getInventory().get(i) == entity.getCurrentShield() ||
                entity.getInventory().get(i) == entity.getCurrentLight()) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gamePanel.getTileSize(), gamePanel.getTileSize(), 10, 10);
            }

            g2.drawImage(entity.getInventory().get(i).getDown1(), slotX, slotY, null);

            // DISPLAY AMOUNT
            if (entity == gamePanel.getPlayer() && entity.getInventory().get(i).getAmount() > 1) {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + entity.getInventory().get(i).getAmount();
                amountX = getXAlignToRightText(s, slotX + 44);
                amountY = slotY + gamePanel.getTileSize();

                // SHADOW
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);

                // NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX - 3, amountY - 3);
            }

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        // CURSOR
        if (cursor) {
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

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if (itemIndex < entity.getInventory().size()) {

                drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);

                for (String line : entity.getInventory().get(itemIndex).getDescription().split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }

    public void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        int x;
        int y;
        String text;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";

        // SHADOW
        g2.setColor(Color.BLACK);
        x = getXCenterText(text);
        y = gamePanel.getTileSize() * 4;
        g2.drawString(text, x, y);

        // MAIN
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        // RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXCenterText(text);
        y += gamePanel.getTileSize() * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        // BACK TO THE TITLE SCREEN
        text = "Quit";
        x = getXCenterText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
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
                options_control(frameX, frameY);
                break;
            case 3:
                options_endGameConfirmation(frameX, frameY);
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
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 2;
                commandNum = 0;
            }
        }

        // QUIT GAME
        textY += gamePanel.getTileSize();
        g2.drawString("Quit Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gamePanel.getTileSize() * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setGameState(gamePanel.getPlayState());
                commandNum = 0;
            }
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
        int volumeWidth = 24 * gamePanel.getMusic().getVolumeScale();
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE VOLUME
        textY += gamePanel.getTileSize();
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gamePanel.getSe().getVolumeScale();
        g2.fillRect(textX, textY, volumeWidth, 24);

        gamePanel.getConfig().saveConfig();

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

    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gamePanel.getTileSize();
        int textY = frameY + gamePanel.getTileSize() * 3;

        currentDialogue = "Quit the game and\nreturn to the\ntitle screen?";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }


            // YES
            String text = "Yes";
            textX = getXCenterText(text);
            textY += gamePanel.getTileSize() * 3;
            g2.drawString(text, textX, textY);
            if (commandNum == 0) {
                g2.drawString(">", textX - 25, textY);
                if (gamePanel.getKeyHandler().isEnterPressed()) {
                    subState = 0;
                    gamePanel.setGameState(gamePanel.getTitleState());
                }
            }

            // NO
            text = "No";
            textX = getXCenterText(text);
            textY += gamePanel.getTileSize();
            g2.drawString(text, textX, textY);
            if (commandNum == 1) {
                g2.drawString(">", textX - 25, textY);
                if (gamePanel.getKeyHandler().isEnterPressed()) {
                    subState = 0;
                    commandNum = 4;
                }
            }

        }


    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        // TILE
        String text = "Controls";
        textX = getXCenterText(text);
        textY = frameY + gamePanel.getTileSize();
        g2.drawString(text, textX, textY);

        textX = frameX + gamePanel.getTileSize();
        textY += gamePanel.getTileSize();

        g2.drawString("Move", textX, textY);
        g2.drawString("Confirm/Attack", textX, textY += gamePanel.getTileSize());
        g2.drawString("Shoot/Cast", textX, textY += gamePanel.getTileSize());
        g2.drawString("Character Screen", textX, textY += gamePanel.getTileSize());
        g2.drawString("Pause", textX, textY += gamePanel.getTileSize());
        g2.drawString("Options", textX, textY + gamePanel.getTileSize());

        textX = frameX + gamePanel.getTileSize() * 6;
        textY = frameY + gamePanel.getTileSize() * 2;

        g2.drawString("WASD", textX, textY);
        g2.drawString("ENTER", textX, textY += gamePanel.getTileSize());
        g2.drawString("F", textX, textY += gamePanel.getTileSize());
        g2.drawString("C", textX, textY += gamePanel.getTileSize());
        g2.drawString("P", textX, textY += gamePanel.getTileSize());
        g2.drawString("ESC", textX, textY + gamePanel.getTileSize());

        // BACK
        textX = frameX + gamePanel.getTileSize();
        textY = frameY + gamePanel.getTileSize() * 9;

        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void drawTransition() {

        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        if (counter == 50) {
            counter = 0;
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.setCurrentMap(gamePanel.getEventHandler().getTempMap());
            gamePanel.getPlayer().setWorldX(gamePanel.getTileSize() * gamePanel.getEventHandler().getTempCol());
            gamePanel.getPlayer().setWorldY(gamePanel.getTileSize() * gamePanel.getEventHandler().getTempRow());
            gamePanel.getEventHandler().setPreviousEventX(gamePanel.getPlayer().getWorldX());
            gamePanel.getEventHandler().setPreviousEventY(gamePanel.getPlayer().getWorldY());
        }
    }


    public void drawTradeScreen() {

        switch (subState) {
            case 0:
                trade_select();
                break;
            case 1:
                trade_buy();
                break;
            case 2:
                trade_sell();
                break;
        }
        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    public void trade_select() {
        drawDialogueScreen();

        // DRAW WINDOW
        int x = gamePanel.getTileSize() * 15;
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getTileSize() * 3;
        int height = (int) (gamePanel.getTileSize() * 3.5);
        drawSubWindow(x, y, width, height);

        // DRAW TEXTS
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        g2.drawString("Buy" , x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 1;
            }
        }
        y += gamePanel.getTileSize();

        g2.drawString("Sell" , x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 2;
            }
        }
        y += gamePanel.getTileSize();

        g2.drawString("Leave" , x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNum = 0;
                gamePanel.setGameState(gamePanel.getDialogueState());
                currentDialogue = "See you soon!";
            }
        }
        y += gamePanel.getTileSize();
    }

    public void trade_buy() {

        // DRAW PLAYER INVENTORY
        drawInventory(gamePanel.getPlayer(), false);

        // DRAW NPC INVENTORY
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() * 9;
        int width = gamePanel.getTileSize() * 6;
        int height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 55);

        // DRAW PLAYER COIN WINDOW
        x = gamePanel.getTileSize() * 12;
        y = gamePanel.getTileSize() * 9;
        width = gamePanel.getTileSize() * 6;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coins: " + gamePanel.getPlayer().getCoin(), x + 24, y + 55);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.getInventory().size()) {
            x = (int) (gamePanel.getTileSize() * 5.5);
            y = (int) (gamePanel.getTileSize() * 5.5);
            width = (int) (gamePanel.getTileSize() * 2.5);
            height = gamePanel.getTileSize();
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = npc.getInventory().get(itemIndex).getPrice();
            String text = "" + price;
            x = getXAlignToRightText(text, gamePanel.getTileSize() * 8 - 20);
            g2.drawString(text, x, y + 28);

            // BUY AN ITEM
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (npc.getInventory().get(itemIndex).getPrice() > gamePanel.getPlayer().getCoin()) {
                    subState = 0;
                    gamePanel.setGameState(gamePanel.getDialogueState());
                    currentDialogue = "You don't have enough money for that!";
                    drawDialogueScreen();
                }
                else {
                    if (gamePanel.getPlayer().canObtainItem(npc.getInventory().get(itemIndex))) {
                        gamePanel.getPlayer().decreaseCoin(npc.getInventory().get(itemIndex).getPrice());
                    }
                    else {
                        subState = 0;
                        gamePanel.setGameState(gamePanel.getDialogueState());
                        currentDialogue = "Your inventory is full";
                    }
                }
            }
        }
    }

    public void trade_sell() {

        // DRAW PLAYER INVENTORY
        drawInventory(gamePanel.getPlayer(), true);

        int x;
        int y;
        int width;
        int height;

        // DRAW HINT WINDOW
        x = gamePanel.getTileSize() * 2;
        y = gamePanel.getTileSize() * 9;
        width = gamePanel.getTileSize() * 6;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 55);

        // DRAW PLAYER COIN WINDOW
        x = gamePanel.getTileSize() * 12;
        y = gamePanel.getTileSize() * 9;
        width = gamePanel.getTileSize() * 6;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coins: " + gamePanel.getPlayer().getCoin(), x + 24, y + 55);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gamePanel.getPlayer().getInventory().size()) {
            x = (int) (gamePanel.getTileSize() * 15.5);
            y = (int) (gamePanel.getTileSize() * 5.5);
            width = (int) (gamePanel.getTileSize() * 2.5);
            height = gamePanel.getTileSize();
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = gamePanel.getPlayer().getInventory().get(itemIndex).getPrice() / 2;
            String text = "" + price;
            x = getXAlignToRightText(text, gamePanel.getTileSize() * 18 - 20);
            g2.drawString(text, x, y + 28);

            // SELL AN ITEM
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (gamePanel.getPlayer().getInventory().get(itemIndex) == gamePanel.getPlayer().getCurrentWeapon() ||
                    gamePanel.getPlayer().getInventory().get(itemIndex) == gamePanel.getPlayer().getCurrentShield()) {
                    commandNum = 0;
                    subState = 0;
                    gamePanel.setGameState(gamePanel.getDialogueState());
                    currentDialogue = "You cannot sell an equipped item!";
                }
                else {
                    if (gamePanel.getPlayer().getInventory().get(itemIndex).getAmount() > 1) {
                        gamePanel.getPlayer().getInventory().get(itemIndex).decreaseAmount();
                    }
                    else {
                        gamePanel.getPlayer().getInventory().remove(itemIndex);
                    }
                    gamePanel.getPlayer().increaseCoin(price);
                }
            }
        }

    }

    public void drawSleepScreen() {
        counter++;

        if (counter < 120) {
            gamePanel.getEnvironmentManager().getLighting().increaseFilterAlpha(0.01f);
            if (gamePanel.getEnvironmentManager().getLighting().getFilterAlpha() > 1f) {
                gamePanel.getEnvironmentManager().getLighting().setFilterAlpha(1f);
            }
        }
        if (counter >= 120) {
            gamePanel.getEnvironmentManager().getLighting().decreaseFilterAlpha(0.01f);
            if (gamePanel.getEnvironmentManager().getLighting().getFilterAlpha() <= 0f) {
                gamePanel.getEnvironmentManager().getLighting().setFilterAlpha(0f);
                counter = 0;
                gamePanel.getEnvironmentManager().getLighting().setDayState(gamePanel.getEnvironmentManager().getLighting().getDay());
                gamePanel.getEnvironmentManager().getLighting().setDayCounter(0);
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getPlayer().getImage();
            }
        }
    }


    public int getItemIndexOnSlot(int slotCol, int slotRow) {
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

    public void decreasePlayerSlotRow() {
        this.playerSlotRow--;
    }

    public void increasePlayerSlotRow() {
        this.playerSlotRow++;
    }

    public void decreasePlayerSlotCol() {
        this.playerSlotCol--;
    }

    public void increasePlayerSlotCol() {
        this.playerSlotCol++;
    }

    public int getPlayerSlotCol() {
        return playerSlotCol;
    }

    public int getPlayerSlotRow() {
        return playerSlotRow;
    }

    public int getNpcSlotCol() {
        return npcSlotCol;
    }

    public int getNpcSlotRow() {
        return npcSlotRow;
    }

    public void decreaseNpcSlotRow() {
        this.npcSlotRow--;
    }

    public void increaseNpcSlotRow() {
        this.npcSlotRow++;
    }

    public void decreaseNpcSlotCol() {
        this.npcSlotCol--;
    }

    public void increaseNpcSlotCol() {
        this.npcSlotCol++;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public int getSubState() {
        return subState;
    }

    public Font getWebfontRegular() {
        return webfontRegular;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    public void setNpc(Entity npc) {
        this.npc = npc;
    }

    public void setSubState(int subState) {
        this.subState = subState;
    }
}
