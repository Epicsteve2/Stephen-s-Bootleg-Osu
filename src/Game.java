/* Class that the game runs on.*/

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.Color;

public class Game implements Runnable{
    // Frame
    private int width, height, score = 0, combo = 0, maxCombo = 0;
    private String title;

    // Variables for running the program
    private Thread thread;
    private boolean running = false;

    // Images and graphics
    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState, menuState, helpState, scoreState;

    //Input
    private MouseManager mouseManager;

    // Handler
    private Handler handler;

    // Creates the display
    public Game (String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        mouseManager = new MouseManager();
    }

    // Initializes display
    private void init(){
        handler = new Handler(this);
        handler.getDisplay().getFrame().addMouseListener(mouseManager);
        handler.getDisplay().getFrame().addMouseMotionListener(mouseManager);
        handler.getDisplay().getCanvas().addMouseMotionListener(mouseManager);
        handler.getDisplay().getCanvas().addMouseListener(mouseManager);

        Assets.init();
        menuState = new MenuState(handler);
        helpState = new HelpState(handler);
        scoreState = new ScoreState(handler);
        State.setState(menuState);
    }

    // What to do for every frame of the game, most classes has this method
    private void tick(){
        mouseManager.tick();
        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    // Method for drawing anything, most classes has this method
    private void render(){
        bs = handler.getDisplay().getCanvas().getBufferStrategy();
        if (bs == null) {
            handler.getDisplay().getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // If statement for rendering if a state is set currently
        if (State.getState() != null) {
            State.getState().render(g);
            State.getState().postRender(g);
        }

        // If the game is being played, render the scores and combos
        if (State.getState() == handler.getRunningGame()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Score: " + score, 1000, 50);
            g.drawString(combo + "x", 50, 850);
            g.drawString("Max combo: " + maxCombo + "x", 950, 850);
        }

        bs.show();
        g.dispose();
    }

    // Method for running the program at the right speed
    public void run(){
        init();
        int fps = 60;
        double timerPerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timerPerTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    // Methods for starting and stopping the pgoram
    public synchronized void start(){
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // Method for adding the scores from the game
    public void addScore (int type){
        if (type == 1){
            score += 300;
        } else if (type == 2) {
            score += 100;
        }
    }
    public void addCombo (){
        combo += 1;
        if (combo > maxCombo) {
            maxCombo = combo;
        }
    }

    public void removeCombo() {
        combo = 0;
    }

    // Getters and Setters
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public State getGameState() {
        return gameState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCombo() {
        return maxCombo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public void setMaxCombo(int maxCombo) {
        this.maxCombo = maxCombo;
    }
}
