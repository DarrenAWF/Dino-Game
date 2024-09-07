package com.mycompany.finalproject_dinogame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import javax.imageio.ImageIO;

/* Name: Darren Drew
 * Date: June 8th, 2022
 * Desc: Flap Burd 2: Game Logic and Graphics (what you see when you play)
 */
public class game extends JPanel implements ActionListener, KeyListener, MouseListener {

    //Variables
    final int DELAY = 1;
    Timer update_timer;
    Rectangle dino; //Dino Hitbox
    Rectangle cactus11; //Cactus 1 Hitbox
    Rectangle cactus12; //Cactus 1 Hitbox
    Rectangle cactus21; //Cactus 2 Hitbox
    Rectangle cactus22; //Cactus 2 Hitbox
    Rectangle cactus3; //Cactus 3 Hitbox
    Rectangle cactus4; //Cactus 4 Hitbox
    Rectangle bird; //Cactus 4 Hitbox
    int height = 800;
    int width = 1400;
    int groundLevel = 600;
    int jumpCount = 0;
    int score = 0;
    int roundScore = 0;
    int highScore;
    String strHighScore;
    int timeStart = 0;
    int terrainLocation = 0;
    int scrollSpeed = 10;
    int randMax = 3;
    int collisionError = 10;
    int deadCount = 0;
    int displacementMax = 1000;
    int displacementMin = 0;
    int flightLevelMin = 0;
    int flightLevelMax = 9;
    int[] flightLevelArr = {300, 350, 375, 400, 425, 435, 450, 490, 500};
    int[] flightLevel = new int[2]; //Duck: 450
    int[] flightLevelRand = new int[2]; //Range: 300 -> 500
    int[] hitboxWidth = {0, 55, 35, 70, 110, 120, 180, 100, 100};
    int[] hitboxHeight = {0, 110, 70, 70, 110, 80, 110, 75, 75};
    int[] objectDisplacement = new int[2];
    int[] objectLocation = {2000, 1400}; //Displacement: 500-1200
    int[] objectRand = new int[4];
    boolean[] cycle = new boolean[2];
    boolean[] objectDead = new boolean[2];
    double timeScore = 8;
    double vSpeed = 0;
    double gravity = 1;
    double lowJumpHeight = 21;
    double highJumpHeight = 25;
    double jumpHeight = lowJumpHeight;
    double fallSpeed = 8;
    boolean jump = false;
    boolean down = false;
    boolean crouch = false;
    boolean start = false;
    boolean alternate = true;
    boolean dead = false;
    boolean restart = false;
    boolean day = true;
    boolean pauseScore = false;
    public Image idleSprite;
    public Image crouchSprite;
    public Image run1Sprite;
    public Image run2Sprite;
    public Image crouch1Sprite;
    public Image crouch2Sprite;
    public Image terrainSprite;
    public Image cactus1Sprite1;
    public Image cactus1Sprite2;
    public Image cactus2Sprite1;
    public Image cactus2Sprite2;
    public Image cactus3Sprite1;
    public Image cactus4Sprite1;
    public Image restartSprite;
    public Image deadSprite;
    public Image deadCrouchSprite;
    public Image birdSprite1;
    public Image birdSprite2;
    String filePath = "";
    //Darren
    int codeEnter = 0;
    int codeStep = 0;
    boolean darrenAwesome = false;
    public Image texture;

    //Main
    public game() {
        // Screen
        setPreferredSize(new Dimension(width, height));
        // Objects
        dino = new Rectangle(width / 14, groundLevel - 100, 100, 95);
        cactus11 = new Rectangle(1400, groundLevel - 110, 55, 110);
        cactus12 = new Rectangle(1400, groundLevel - 70, 35, 70);
        cactus21 = new Rectangle(1400, groundLevel - 70, 70, 70);
        cactus22 = new Rectangle(1400, groundLevel - 110, 110, 110);
        cactus3 = new Rectangle(1400, groundLevel - 80, 120, 80);
        cactus4 = new Rectangle(1400, groundLevel - 110, 180, 110);
        bird = new Rectangle(1400, flightLevelRand[0], 100, 75);
        // Random
        randMax = 3;
        Random random = new Random();
        objectRand[0] = random.nextInt(0, randMax);
        objectRand[1] = random.nextInt(1, randMax);
        objectDisplacement[0] = random.nextInt(displacementMin, displacementMax);
        cycle[0] = true;
        objectDead[0] = false;
        objectDead[1] = false;
        // Timer
        update_timer = new Timer(DELAY, this);
        update_timer.start();
        // High Score
        try {
            FileReader fr = new FileReader("highscore.txt");
            BufferedReader br = new BufferedReader(fr);
            strHighScore = br.readLine();
            highScore = Integer.valueOf(strHighScore);
            br.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void spritesDay() { // Sprites Day
        try {
            day = true;
            filePath = "src/main/resources/Sprite_Idle.png"; // IDLE
            idleSprite = ImageIO.read(new File(filePath)); // IDLE
            filePath = "src/main/resources/Sprite_Crouch.png"; // CROUCH
            crouchSprite = ImageIO.read(new File(filePath)); // CROUCH
            filePath = "src/main/resources/Sprite_Run1.png"; // RUN 1
            run1Sprite = ImageIO.read(new File(filePath)); // RUN 1
            filePath = "src/main/resources/Sprite_Run2.png"; // RUN 2
            run2Sprite = ImageIO.read(new File(filePath)); // RUN 2
            filePath = "src/main/resources/Sprite_Crouch1.png"; // CROUCH 1
            crouch1Sprite = ImageIO.read(new File(filePath)); // CROUCH 1
            filePath = "src/main/resources/Sprite_Crouch2.png"; // CROUCH 2
            crouch2Sprite = ImageIO.read(new File(filePath)); // CROUCH 2
            filePath = "src/main/resources/Sprite_Terrain.png"; // TERRAIN
            terrainSprite = ImageIO.read(new File(filePath)); // TERRAIN
            if (darrenAwesome == true) { // Darren
                spritesDarren();
            } else {
                filePath = "src/main/resources/Sprite_1Cactus1.png"; // CACTUS 1-1
                cactus1Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 1-1
                filePath = "src/main/resources/Sprite_1Cactus2.png"; // CACTUS 1-2
                cactus1Sprite2 = ImageIO.read(new File(filePath)); // CACTUS 1-2
                filePath = "src/main/resources/Sprite_2Cactus1.png"; // CACTUS 2-1
                cactus2Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 2-1
                filePath = "src/main/resources/Sprite_2Cactus2.png"; // CACTUS 2-2
                cactus2Sprite2 = ImageIO.read(new File(filePath)); // CACTUS 2-2
                filePath = "src/main/resources/Sprite_3Cactus1.png"; // CACTUS 3-1
                cactus3Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 3-1
                filePath = "src/main/resources/Sprite_4Cactus1.png"; // CACTUS 4-1
                cactus4Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 4-1
                filePath = "src/main/resources/Sprite_Bird1.png"; // BIRD 1
                birdSprite1 = ImageIO.read(new File(filePath)); // BIRD 1
                filePath = "src/main/resources/Sprite_Bird2.png"; // BIRD 2
                birdSprite2 = ImageIO.read(new File(filePath)); // BIRD 2
            }
            filePath = "src/main/resources/Sprite_Restart.png"; // RESTART
            restartSprite = ImageIO.read(new File(filePath)); // RESTART
            filePath = "src/main/resources/Sprite_Dead.png"; // DEAD
            deadSprite = ImageIO.read(new File(filePath)); // DEAD
            filePath = "src/main/resources/Sprite_CrouchDead.png"; // DEAD - CROUCH
            deadCrouchSprite = ImageIO.read(new File(filePath)); // DEAD - CROUCH
        } catch (Exception e) {
        }
    }

    public void spritesNight() { // Sprites Night
        try {
            day = false;
            filePath = "src/main/resources/Sprite_IdleNight.png"; // IDLE
            idleSprite = ImageIO.read(new File(filePath)); // IDLE
            filePath = "src/main/resources/Sprite_CrouchNight.png"; // CROUCH
            crouchSprite = ImageIO.read(new File(filePath)); // CROUCH
            filePath = "src/main/resources/Sprite_Run1Night.png"; // RUN 1
            run1Sprite = ImageIO.read(new File(filePath)); // RUN 1
            filePath = "src/main/resources/Sprite_Run2Night.png"; // RUN 2
            run2Sprite = ImageIO.read(new File(filePath)); // RUN 2
            filePath = "src/main/resources/Sprite_Crouch1Night.png"; // CROUCH 1
            crouch1Sprite = ImageIO.read(new File(filePath)); // CROUCH 1
            filePath = "src/main/resources/Sprite_Crouch2Night.png"; // CROUCH 2
            crouch2Sprite = ImageIO.read(new File(filePath)); // CROUCH 2
            filePath = "src/main/resources/Sprite_TerrainNight.png"; // TERRAIN
            terrainSprite = ImageIO.read(new File(filePath)); // TERRAIN
            if (darrenAwesome == true) { // Darren
                spritesDarren();
            } else {
                filePath = "src/main/resources/Sprite_1Cactus1Night.png"; // CACTUS 1-1
                cactus1Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 1-1
                filePath = "src/main/resources/Sprite_1Cactus2Night.png"; // CACTUS 1-2
                cactus1Sprite2 = ImageIO.read(new File(filePath)); // CACTUS 1-2
                filePath = "src/main/resources/Sprite_2Cactus1Night.png"; // CACTUS 2-1
                cactus2Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 2-1
                filePath = "src/main/resources/Sprite_2Cactus2Night.png"; // CACTUS 2-2
                cactus2Sprite2 = ImageIO.read(new File(filePath)); // CACTUS 2-2
                filePath = "src/main/resources/Sprite_3Cactus1Night.png"; // CACTUS 3-1
                cactus3Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 3-1
                filePath = "src/main/resources/Sprite_4Cactus1Night.png"; // CACTUS 4-1
                cactus4Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 4-1
                filePath = "src/main/resources/Sprite_Bird1Night.png"; // BIRD 1
                birdSprite1 = ImageIO.read(new File(filePath)); // BIRD 1
                filePath = "src/main/resources/Sprite_Bird2Night.png"; // BIRD 2
                birdSprite2 = ImageIO.read(new File(filePath)); // BIRD 2
            }
            filePath = "src/main/resources/Sprite_RestartNight.png"; // RESTART
            restartSprite = ImageIO.read(new File(filePath)); // RESTART
            filePath = "src/main/resources/Sprite_DeadNight.png"; // DEAD
            deadSprite = ImageIO.read(new File(filePath)); // DEAD
            filePath = "src/main/resources/Sprite_CrouchDeadNight.png"; // DEAD - CROUCH
            deadCrouchSprite = ImageIO.read(new File(filePath)); // DEAD - CROUCH
        } catch (Exception e) {
        }
    }

    public void spritesDarren() { // Sprites Darren
        try {
            filePath = "src/main/resources/Sprite_1Cactus1Darren.png"; // CACTUS 1-1
            cactus1Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 1-1
            filePath = "src/main/resources/Sprite_1Cactus2Darren.png"; // CACTUS 1-2
            cactus1Sprite2 = ImageIO.read(new File(filePath)); // CACTUS 1-2
            filePath = "src/main/resources/Sprite_2Cactus1Darren.png"; // CACTUS 2-1
            cactus2Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 2-1
            filePath = "src/main/resources/Sprite_2Cactus2Darren.png"; // CACTUS 2-2
            cactus2Sprite2 = ImageIO.read(new File(filePath)); // CACTUS 2-2
            filePath = "src/main/resources/Sprite_3Cactus1Darren.png"; // CACTUS 3-1
            cactus3Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 3-1
            filePath = "src/main/resources/Sprite_4Cactus1Darren.png"; // CACTUS 4-1
            cactus4Sprite1 = ImageIO.read(new File(filePath)); // CACTUS 4-1
            filePath = "src/main/resources/Sprite_Bird1Darren.png"; // BIRD 1
                birdSprite1 = ImageIO.read(new File(filePath)); // BIRD 1
                filePath = "src/main/resources/Sprite_Bird2Darren.png"; // BIRD 2
                birdSprite2 = ImageIO.read(new File(filePath)); // BIRD 2
        } catch (Exception e) {
        }
    }

    //===== GUI =========
    @Override
    public void paintComponent(Graphics g) { //Main Method
        Random random = new Random();
        //Day & Night
        if (day == true) {
            spritesDay();
        } else if (day == false) {
            spritesNight();
        }
        //Colours
        Color dayGrayColor = new Color(83, 83, 83);
        Color nightGrayColor = new Color(172, 172, 172);
        Color skyColor = new Color(245, 245, 245);
        Color skyColorNight = new Color(20, 20, 20);
        //Background
        if (day == true) {
            g.setColor(skyColor);
        } else {
            g.setColor(skyColorNight);
        }
        g.fillRect(0, 0, width, height);
        g.setColor(dayGrayColor);
        //Terrain
        g.drawImage(terrainSprite, terrainLocation, height - 199, null);
        if (start == true && dead == false) {
            terrainLocation -= scrollSpeed;
            if (terrainLocation <= -4200) {
                terrainLocation = 0;
            }
        }
        //Dino =================================================================
        if (crouch == false && dino.y <= groundLevel - dino.height) { // STAND
            g.drawImage(idleSprite, dino.x, dino.y, null);
            if (start == true && dino.y == groundLevel - dino.height) {
                if (alternate == true) {
                    g.drawImage(run1Sprite, dino.x, dino.y, null);

                }
                if (alternate == false) {
                    g.drawImage(run2Sprite, dino.x, dino.y, null);
                }
            }
        } else if (crouch == true && dino.y >= groundLevel - dino.height) { // CROUCH
            g.drawImage(crouchSprite, dino.x, dino.y, null);
            if (start == true && dino.y == groundLevel - dino.height) {
                if (alternate == true) {
                    g.drawImage(crouch1Sprite, dino.x, dino.y, null);

                }
                if (alternate == false) {
                    g.drawImage(crouch2Sprite, dino.x, dino.y, null);
                }
            }
        }
        //Objects ==============================================================
        if (start == true || dead == true) {
            //Object 0 =========================================================
            if (objectLocation[1] <= objectDisplacement[1]) {
                cycle[0] = true;
            }
            if (cycle[0] == true && dead == false) {
                objectLocation[0] -= scrollSpeed;
            }
            // Random Sprite
            if (objectDead[0] == false) {
                switch (objectRand[0]) {
                    case 1:
                        cactus11.x = objectLocation[0];
                        g.drawImage(cactus1Sprite1, cactus11.x, cactus11.y, null);
                        break;
                    case 2:
                        cactus12.x = objectLocation[0];
                        g.drawImage(cactus1Sprite2, cactus12.x, cactus12.y, null);
                        break;
                    case 3:
                        cactus21.x = objectLocation[0];
                        g.drawImage(cactus2Sprite1, cactus21.x, cactus21.y, null);
                        break;
                    case 4:
                        cactus22.x = objectLocation[0];
                        g.drawImage(cactus2Sprite2, cactus22.x, cactus22.y, null);
                        break;
                    case 5:
                        cactus3.x = objectLocation[0];
                        g.drawImage(cactus3Sprite1, cactus3.x, cactus3.y, null);
                        break;
                    case 6:
                        cactus4.x = objectLocation[0];
                        g.drawImage(cactus4Sprite1, cactus4.x, cactus4.y, null);
                        break;
                    case 7:
                    case 8:
                        bird.x = objectLocation[0];
                        flightLevel[0] = flightLevelRand[0];
                        bird.y = flightLevelRand[0];
                        if (alternate == true) {
                            g.drawImage(birdSprite1, bird.x, bird.y, null);
                        }
                        if (alternate == false) {
                            flightLevel[0] += 15;
                            g.drawImage(birdSprite2, bird.x, bird.y + 15, null);
                        }
                        break;
                }
            }
            if (objectLocation[0] <= -200) {
                // Flight Height
                flightLevelRand[0] = random.nextInt(flightLevelMin, flightLevelMax);
                flightLevelRand[0] = flightLevelArr[flightLevelRand[0]];
                // Objects
                objectLocation[0] = 1400;
                objectRand[0] = random.nextInt(1, randMax);
                objectDisplacement[0] = random.nextInt(displacementMin - hitboxWidth[objectRand[0]], displacementMax - hitboxWidth[objectRand[0]]);
                cycle[0] = false;
            }
            //Object 1 =========================================================
            if (objectLocation[0] <= objectDisplacement[0]) {
                cycle[1] = true;
            }
            if (cycle[1] == true && dead == false) {
                objectLocation[1] -= scrollSpeed;
            }
            // Random Sprite
            if (objectDead[1] == false) {
                switch (objectRand[1]) {
                    case 1:
                        cactus11.x = objectLocation[1];
                        g.drawImage(cactus1Sprite1, cactus11.x, cactus11.y, null);
                        break;
                    case 2:
                        cactus12.x = objectLocation[1];
                        g.drawImage(cactus1Sprite2, cactus12.x, cactus12.y, null);
                        break;
                    case 3:
                        cactus21.x = objectLocation[1];
                        g.drawImage(cactus2Sprite1, cactus21.x, cactus21.y, null);
                        break;
                    case 4:
                        cactus22.x = objectLocation[1];
                        g.drawImage(cactus2Sprite2, cactus22.x, cactus22.y, null);
                        break;
                    case 5:
                        cactus3.x = objectLocation[1];
                        g.drawImage(cactus3Sprite1, cactus3.x, cactus3.y, null);
                        break;
                    case 6:
                        cactus4.x = objectLocation[1];
                        g.drawImage(cactus4Sprite1, cactus4.x, cactus4.y, null);
                        break;
                    case 7:
                    case 8:
                        bird.x = objectLocation[1];
                        flightLevel[1] = flightLevelRand[1];
                        bird.y = flightLevelRand[1];
                        if (alternate == true) {
                            g.drawImage(birdSprite1, bird.x, bird.y, null);
                        }
                        if (alternate == false) {
                            flightLevel[1] += 15;
                            g.drawImage(birdSprite2, bird.x, bird.y + 15, null);
                        }
                        break;
                }
            }
            if (objectLocation[1] <= -200) {
                // Flight Height
                flightLevelRand[1] = random.nextInt(flightLevelMin, flightLevelMax);
                flightLevelRand[1] = flightLevelArr[flightLevelRand[1]];
                // Objects
                objectLocation[1] = 1400;
                objectRand[1] = random.nextInt(1, randMax);
                objectDisplacement[1] = random.nextInt(displacementMin - hitboxWidth[objectRand[1]], displacementMax - hitboxWidth[objectRand[1]]);
                cycle[1] = false;
            }
        }
        // Collision ===========================================================
        // Object 0
        if (objectRand[0] != 7 && objectRand[0] != 8) { // Cactus
            if (dino.x + dino.width - collisionError >= objectLocation[0] + collisionError && dino.x <= objectLocation[0] + hitboxWidth[objectRand[0]] - collisionError && dino.y + dino.height > groundLevel - hitboxHeight[objectRand[0]]) {
                dead = true;
                objectDead[0] = true;
            }
        } else { // Pterodactyl
            if (dino.x + dino.width - collisionError * 2 >= objectLocation[0] + collisionError && dino.x <= objectLocation[0] + bird.width - collisionError * 2 && dino.y < flightLevel[0] + bird.height && dino.y + dino.height > flightLevel[0]) {
                dead = true;
                objectDead[0] = true;
            }
        }
        // Object 1
        if (objectRand[1] != 7 && objectRand[1] != 8) { // Cactus
            if (dino.x + dino.width - collisionError >= objectLocation[1] + collisionError && dino.x <= objectLocation[1] + hitboxWidth[objectRand[1]] - collisionError && dino.y + dino.height > groundLevel - hitboxHeight[objectRand[1]]) {
                dead = true;
                objectDead[1] = true;
            }
        } else { // Pterodactyl
            if (dino.x + dino.width - collisionError * 2 >= objectLocation[1] + collisionError && dino.x <= objectLocation[1] + bird.width - collisionError * 2 && dino.y < flightLevel[1] + bird.height && dino.y + dino.height > flightLevel[1]) {
                dead = true;
                objectDead[1] = true;
            }
        }
        //Falling
        vSpeed += gravity;
        dino.y += vSpeed;
        if (dino.y > groundLevel - dino.height) {
            dino.y = groundLevel - dino.height;
        }
        if (down == true && dino.y < groundLevel - dino.height) {
            vSpeed += 12;
        }
        //High Jump
        if (crouch == true) {
            jumpCount = 0;
        }
        if (jumpCount >= 1) {
            jumpHeight = highJumpHeight;
        } else {
            jumpHeight = lowJumpHeight;
        }
        //Jump
        if (jump == true && dino.y == groundLevel - dino.height && down == false) {
            vSpeed = -jumpHeight;
            jump = false;
            jumpCount++;

        }
        //Crouch
        if (down == true && dino.y == groundLevel - dino.height) {
            crouch = true;
            dino.height = 60;
            dino.width = 130;
        } else {
            crouch = false;
            dino.height = 100;
            dino.width = 95;
        }
        //Dead =================================================================
        if (dead == true) {
            if (down == false) {
                g.drawImage(deadSprite, dino.x, dino.y, null);
            }
            if (down == true) {
                g.drawImage(deadCrouchSprite, dino.x, dino.y, null);
            }
            g.drawImage(restartSprite, 600, 300, null);
            g.setFont(new Font("Franklin Gothic Demi", 1, 50));
            if (day == true) {
                g.setColor(dayGrayColor);
            } else {
                g.setColor(nightGrayColor);
            }
            g.drawString("G A M E   O V E R", 510, height / 2 - 150);
            if (score == 0) {
                g.drawString("00000", 1150, height / 2 - 300);
            }
            if (score < 10) {
                g.drawString("0000" + roundScore, 1150, height / 2 - 300);
            }
            if (score >= 10 && score < 100) {
                g.drawString("000" + roundScore, 1150, height / 2 - 300);
            }
            if (score >= 100 && score < 1000) {
                g.drawString("00" + roundScore, 1150, height / 2 - 300);
            }
            if (score >= 1000 && score < 10000) {
                g.drawString("0" + roundScore, 1150, height / 2 - 300);
            }
            if (score >= 10000) {
                g.drawString("" + roundScore, 1150, height / 2 - 300);
            }
            // High Score
            if (score > highScore || score == 99999) {
                highScore = score;
                try {
                    FileWriter fw = new FileWriter("highscore.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(highScore);
                    pw.close();
                } catch (IOException e) {
                }
            }
            try {
                FileReader fr = new FileReader("highscore.txt");
                BufferedReader br = new BufferedReader(fr);
                strHighScore = br.readLine();
                br.close();
            } catch (IOException e) {
                System.out.println("Error");
            }
            deadCount++;
            if (deadCount >= 100 && restart == true) {
                gameOver();
                deadCount = 0;
            }
        }
        //Text =================================================================
        //Starting Text
        if (start == false && dead == false) {
            g.setFont(new Font("Franklin Gothic Demi", 1, 50));
            if (day == true) {
                g.setColor(dayGrayColor);
            } else {
                g.setColor(nightGrayColor);
            }
            g.drawString("Press SPACE To Play", width / 3, height / 2 - 100);
        }
        //Score ================================================================
        g.setFont(new Font("Franklin Gothic Demi", 1, 50));
        if (day == true) {
            g.setColor(dayGrayColor);
        } else {
            g.setColor(nightGrayColor);
        }
        if (start == true || dead == true) {
            //Score Count
            if (dead == false) {
                timeStart++;
                if (timeStart == timeScore) {
                    timeStart = 0;
                    score++;
                    if (alternate == false) {
                        alternate = true;
                    } else {
                        alternate = false;
                    }
                }
                // Day & Night
                if (score % 700 == 0 && score > 0) {
                    if (day == true) {
                        day = false;
                    } else {
                        day = true;
                    }
                    score++;
                }
                // Checkpoints
                if (score == 50) {
                    randMax = 4;
                }
                if (score == 50) {
                    randMax = 5;
                }
                if (score == 100) {
                    scrollSpeed = 11;
                    randMax = 6;
                }
                if (score == 200) {
                    timeScore = 7;
                    scrollSpeed = 12;
                    randMax = 7;
                }
                if (score == 300) {
                    scrollSpeed = 13;
                    randMax = 8;
                }
                if (score == 350) {
                    scrollSpeed = 14;
                }
                if (score == 400) {
                    scrollSpeed = 15;
                }
                if (score == 450) {
                    scrollSpeed = 16;
                }
                if (score == 500) {
                    timeScore = 6;
                    scrollSpeed = 17;
                    randMax = 9;
                }
                if (score == 600) {
                    scrollSpeed = 18;
                }
                if (score == 700) {
                    scrollSpeed = 20;
                }
                if (score == 900) {
                    scrollSpeed = 21;
                }
                if (score == 1100) {
                    scrollSpeed = 22;
                }
                if (score == 1300) {
                    scrollSpeed = 23;
                    displacementMax = 900;
                }
                if (score == 1800) {
                    scrollSpeed = 24;
                }
                if (score == 2000) {
                    timeScore = 5;
                    scrollSpeed = 25;
                }
                if (score == 2250) {
                    scrollSpeed = 26;
                }
                if (score == 2750) {
                    scrollSpeed = 27;
                }
                if (score == 3000) {
                    scrollSpeed = 28;
                }
                if (score == 99999) {
                    score = 0;
                }
            }
            //Print High Score
            switch (strHighScore.length()) {
                case 1:
                    g.drawString("HI 0000" + strHighScore, 920, height / 2 - 300);
                    break;
                case 2:
                    g.drawString("HI 000" + strHighScore, 920, height / 2 - 300);
                    break;
                case 3:
                    g.drawString("HI 00" + strHighScore, 920, height / 2 - 300);
                    break;
                case 4:
                    g.drawString("HI 0" + strHighScore, 920, height / 2 - 300);
                    break;
                case 5:
                    g.drawString("HI " + strHighScore, 920, height / 2 - 300);
                    break;
            }
            //Print Score
            roundScore = score;
            pauseScore = false;
            for (int loop = 0; loop < 10; loop++) {
                if ((score - loop) == 100 || (score - loop) == 200 || (score - loop) == 300 || (score - loop) == 500 || (score - loop) == 99999 || (score - loop) % 1000 == 0 || (score - loop) % 700 == 0) { // (score % 100 == 0 || (score - loop) % 100 == 0) && score > 10
                    if (score >= 100) {
                        pauseScore = true;
                        roundScore = score - loop;
                    }
                }
            }
            if (pauseScore == false) { // Display Score
                if (score == 0) {
                    g.drawString("00000", 1150, height / 2 - 300);
                }
                if (score < 10) {
                    g.drawString("0000" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 10 && score < 100) {
                    g.drawString("000" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 100 && score < 1000) {
                    g.drawString("00" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 1000 && score < 10000) {
                    g.drawString("0" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 10000) {
                    g.drawString("" + roundScore, 1150, height / 2 - 300);
                }
            }
            if (pauseScore == true && score % 2 == 0) { // Flash Score
                if (score == 0) {
                    g.drawString("00000", 1150, height / 2 - 300);
                }
                if (score < 10) {
                    g.drawString("0000" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 10 && score < 100) {
                    g.drawString("000" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 100 && score < 1000) {
                    g.drawString("00" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 1000 && score < 10000) {
                    g.drawString("0" + roundScore, 1150, height / 2 - 300);
                }
                if (score >= 10000) {
                    g.drawString("" + roundScore, 1150, height / 2 - 300);
                }
            }
        }
    }

    public void gameOver() {
        // Restart =============================================================
        dead = false;
        restart = false;
        pauseScore = false;
        day = true;
        darrenAwesome = false;
        score = 0;
        roundScore = 0;
        objectLocation[0] = 2000; //Displacement: 500-1200
        objectLocation[1] = 1400;
        timeStart = 0;
        terrainLocation = 0;
        scrollSpeed = 10;
        timeScore = 8;
        // Objects
        dino = new Rectangle(width / 14, groundLevel - 100, 100, 95);
        cactus11 = new Rectangle(1400, groundLevel - 110, 55, 110);
        cactus12 = new Rectangle(1400, groundLevel - 70, 35, 70);
        cactus21 = new Rectangle(1400, groundLevel - 70, 70, 70);
        cactus22 = new Rectangle(1400, groundLevel - 110, 110, 110);
        cactus3 = new Rectangle(1400, groundLevel - 80, 120, 80);
        cactus4 = new Rectangle(1400, groundLevel - 110, 180, 110);
        // Random
        randMax = 4;
        Random random = new Random();
        objectRand[0] = random.nextInt(0, randMax);
        objectRand[1] = random.nextInt(1, randMax);
        objectDisplacement[0] = random.nextInt(0, 900);
        cycle[0] = true;
        objectDead[0] = false;
        objectDead[1] = false;
        // Timer
        update_timer = new Timer(DELAY, this);
        update_timer.start();
    }

    //===== OUTPUTS =========
    public void pressUp() {
        if (dead == false) {
            jump = true;
            start = true;
        }
        if (dead == true && deadCount > 100) {
            restart = true;
        }
    }

    public void pressDown() {
        if (dead == false) {
            down = true;
            start = true;
        }
        if (dead == true && deadCount > 100) {
            restart = true;
        }
    }

    public void releaseUp() {
        jump = false;
        jumpCount = 0;
    }

    public void releaseDown() {
        down = false;
    }

    //===== KEYBINDS =========
    @Override
    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            releaseUp();
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            releaseDown();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            pressUp();
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            pressDown();
        }
        // Darren.
        switch (keyCode) {
            case KeyEvent.VK_D:
                codeEnter(0, true);
                break;
            case KeyEvent.VK_A:
                codeEnter(1, false);
                break;
            case KeyEvent.VK_R:
                if (codeStep == 3) {
                    codeEnter(3, false);
                }
                if (codeStep == 2) {
                    codeEnter(2, false);
                }
                break;
            case KeyEvent.VK_E:
                codeEnter(4, false);
                break;
            case KeyEvent.VK_N:
                codeEnter(5, false);
                if (codeStep == 6) {
                    darrenAwesome = true;
                }
                break;
        }
    }

    // Darren.
    public void codeEnter(int step, Boolean first) {
        if (codeStep == step) {
            codeStep += 1;
        } else {
            codeStep = 0;
        }

        if (first) {
            codeStep = 1;
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    //===== MOUSEBINDS =========
    @Override
    public void mousePressed(MouseEvent event) {
        pressUp();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        releaseUp();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

}
