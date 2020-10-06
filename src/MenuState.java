/* State for the main menu*/

import java.awt.*;

public class MenuState extends State {
    private MusicManager music;
    private String openingPath = "res/audio/startup.wav";
    private boolean musicPlaying = false, renderIntro = true;
    private long lastTime, timer;
    private float mouseX, mouseY;

    public MenuState(Handler handler){
        super (handler);
        music = new MusicManager(openingPath, handler);
        lastTime = System.currentTimeMillis();
        timer = 0;
        music.playMusic();
    }

    @Override
    public void tick(){
        mouseX = handler.getMouseManager().getMouseX();
        mouseY = handler.getMouseManager().getMouseY();
        // Checks if the music is playing
        if (!musicPlaying) {
            music.playMusic();
            musicPlaying = true;
        }
        // Timer for starting the game
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        // Button to play the game
        if (mouseX > 500 && mouseX < 690
        && mouseY > 150 && mouseY < 230 && handler.getMouseManager().isLeftPressed()) {
            music.stopMusic();
            musicPlaying = false;
            handler.setNewGame();
        }

        // Button for help
        if (mouseX > 500 && mouseX < 700
            && mouseY > 290 && mouseY < 380 && handler.getMouseManager().isLeftPressed()) {
            State.setState(handler.getGame().helpState);
        }

        // Button for score
        if (mouseX > 470 && mouseX < 740
                && mouseY > 480 && mouseY < 570 && handler.getMouseManager().isLeftPressed()) {
            State.setState(handler.getGame().scoreState);
        }

        // Button to exit
        if (mouseX > 520 && mouseX < 680
                && mouseY > 630 && mouseY < 710 && handler.getMouseManager().isLeftPressed()) {
            System.exit(0);
        }

        // Rendering the intro
        if (timer >= 2500) {
            renderIntro = false;
        }
    }

    // Renders the intro if needed, then the background
    @Override
    public void render(Graphics g) {
        if (renderIntro) {
            g.drawImage(Assets.intro,0,0,null);
        } else {
            g.drawImage(Assets.title,0,0,null);
        }
    }
    @Override
    public void postRender(Graphics g) {}

}
