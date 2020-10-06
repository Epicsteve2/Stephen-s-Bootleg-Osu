/* State for the score screen*/

import java.awt.*;

public class ScoreState extends State{
    private float mouseX, mouseY;
    private String inputFile = "res/text/scores.txt";
    private String clap = "res/audio/clap.wav";
    private String [][] scores;
    private MusicManager music;
    private HighScores highScores;
    private boolean musicPlaying = false;

    // Reads the scores from a file
    public ScoreState(Handler handler){
        super (handler);
        highScores = new HighScores(handler);
        highScores.readScores(inputFile);
        scores = highScores.getScores();
        music = new MusicManager(clap, handler);
    }

    @Override
    public void tick(){
        // Checks if it should start clapping
        if (!musicPlaying) {
            music.playMusic();
            musicPlaying = true;
        }

        // Back button
        mouseX = handler.getMouseManager().getMouseX();
        mouseY = handler.getMouseManager().getMouseY();
        if (mouseX > 0 && mouseX < 200
                && mouseY > 820 && mouseY < 900 && handler.getMouseManager().isLeftPressed()) {
            State.setState(handler.getGame().menuState);
            music.stopMusic();
            musicPlaying = false;
        }
    }

    // Renders all of the scores
    @Override
    public void render(Graphics g) {
        highScores.readScores(inputFile);
        scores = highScores.getScores();
        g.drawImage(Assets.score,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        //first
        g.drawString("Name: " + scores[0][0] ,200,250);
        g.setColor(Color.GREEN);
        g.drawString(" Score: " + scores[1][0], 500,250);
        g.setColor(Color.RED);
        g.drawString(" Max Combo: " + scores[2][0], 800,250);
        //second
        g.setColor(Color.WHITE);
        g.drawString("Name: " + scores[0][1] ,200,350);
        g.setColor(Color.GREEN);
        g.drawString(" Score: " + scores[1][1], 500,350);
        g.setColor(Color.RED);
        g.drawString(" Max Combo: " + scores[2][1], 800,350);
        //third
        g.setColor(Color.WHITE);
        g.drawString("Name: " + scores[0][2] ,200,450);
        g.setColor(Color.GREEN);
        g.drawString(" Score: " + scores[1][2], 500,450);
        g.setColor(Color.RED);
        g.drawString(" Max Combo: " + scores[2][2], 800,450);
        //forth
        g.setColor(Color.WHITE);
        g.drawString("Name: " + scores[0][3] ,200,550);
        g.setColor(Color.GREEN);
        g.drawString(" Score: " + scores[1][3], 500,550);
        g.setColor(Color.RED);
        g.drawString(" Max Combo: " + scores[2][3], 800,550);
        //fifth
        g.setColor(Color.WHITE);
        g.drawString("Name: " + scores[0][4] ,200,650);
        g.setColor(Color.GREEN);
        g.drawString(" Score: " + scores[1][4], 500,650);
        g.setColor(Color.RED);
        g.drawString(" Max Combo: " + scores[2][4], 800,650);
        // 6
        g.setColor(Color.WHITE);
        g.drawString("Name: " + scores[0][5] ,200,750);
        g.setColor(Color.GREEN);
        g.drawString(" Score: " + scores[1][5], 500,750);
        g.setColor(Color.RED);
        g.drawString(" Max Combo: " + scores[2][5], 800,750);

    }
    @Override
    public void postRender(Graphics g) {

    }
}
