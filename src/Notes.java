/* Class for the notes itself*/

import java.awt.*;

public class Notes {
    private Handler handler;
    private float x, y, t, mouseX, mouseY;
    private long lastTime, timer;
    private boolean active = true, score = false, wasPressed  = false;
    private int whatScore = 0, circleWidth = 512, circleHeight = 512, xOffset = 34, yOffset = 74;

    public Notes(Handler handler, float x, float y) {
        this.x = x;
        this.y = y;
        this.t = t;
        this.handler = handler;
        lastTime = System.currentTimeMillis();
    }

    // If the note isn't active, remove it
    public boolean isActive () {
        return active;
    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        // after 0.75 seconds, remove the circle and sets score as miss.
        if (timer >= 750 && !score) {
            score = true;
            whatScore = 3;
            wasPressed = true;
        }

        // After 1.75 seconds, remove note
        if (timer >= 1750) {
            handler.getGame().addScore(whatScore);
            active = false;
            if (whatScore != 3) {
                handler.getGame().addCombo();
            } else {
                handler.getGame().removeCombo();
            }
        }
        // Speed to decrease the approach circle
        circleWidth -= 9;
        circleHeight -= 9;

        mouseX = x - handler.getMouseManager().getMouseX() + 64;
        mouseY = y - handler.getMouseManager().getMouseY() + 64;

        // uses the pythagorean theorem to check if the mouse is in the circle
        if (Math.sqrt((mouseX*mouseX) + (mouseY*mouseY)) < 64 && !wasPressed) {
            if (handler.getMouseManager().isLeftPressed()) {
                wasPressed = true;
                // Calculates if the score is perfect, 100, or miss
                if (timer >= 650) {
                    score = true;
                    whatScore = 1;
                } else if (timer >= 450) {
                    score = true;
                    whatScore = 2;
                } else if (timer >= 300) {
                    score = true;
                    whatScore = 3;
                }
            }
        }
    }

    // Checks to render the circle or score
    public void render (Graphics g) {
        if (!score) {
            g.drawImage(Assets.hitCircle, (int) x, (int) y, null);
            g.setColor(Color.WHITE);
            g.drawOval((int) x - (circleWidth / 2) + 64, (int) y - (circleHeight / 2) + 64, circleWidth, circleHeight);
        }
        else if (score) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            if (whatScore == 1) {
                g.setColor(Color.WHITE);
                g.drawString("Perfect!", (int)x + xOffset, (int)y + yOffset);
            } else if (whatScore == 2) {
                g.setColor(Color.GREEN);
                g.drawString("100", (int)x + xOffset, (int)y + yOffset);
            } else if (whatScore == 3) {
                g.setColor(Color.RED);
                g.drawString("Miss..." , (int)x + xOffset, (int)y + yOffset);
            }
        }
    }
}