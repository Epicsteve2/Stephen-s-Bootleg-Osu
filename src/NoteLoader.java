/* Method for loading the notes*/

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class NoteLoader {
    private MapLoader supercell = new MapLoader("supercell.txt");
    private HighScores highScores;
    private Handler handler;
    private MusicManager music;
    private ArrayList<Notes> notes;
    private float mouseX, mouseY;
    public final long APPROACH_RATE = 750;
    public int [] currentNote;
    public int counter = 0;
    private long lastTime, timer;
    private boolean musicPlaying = false;
    private final long OFFSET = -20;

    public NoteLoader(Handler handler) {
        currentNote = supercell.getCurrentNote(counter);
        lastTime = System.currentTimeMillis();
        timer = 0;
        notes = new ArrayList<>();
        this.handler = handler;
        // Checks if the music is playing
        if (!handler.isGameMusicPlaying()) {
            music = new MusicManager("supercell.wav", handler); // This shit is causing memory leaks... how to fix? add to handler?
        }
            highScores = new HighScores(handler);
    }

    public void tick() {
        mouseX = handler.getMouseManager().getMouseX();
        mouseY = handler.getMouseManager().getMouseY();
        // Button for quitting
        if (mouseX > 0 && mouseX < 150
                && mouseY > 660 && mouseY < 750 && handler.getMouseManager().isLeftPressed()) {
            music.stopMusic();
            musicPlaying = false;
            handler.setGameMusicPlaying(false);
            State.setState(handler.getGame().menuState);
            return;
        }

        // Checks if the music is playing
        if (!musicPlaying) {
            music.playMusic();
            handler.setGameMusicPlaying(true);
            musicPlaying = true;
        }

        // Timer for stopping the game after the chorus
        if (timer >= 98209) {
            timer = 0;
            musicPlaying = false;
            handler.setGameMusicPlaying(false);

            // Reads and writes the scores if the user made a high score
            highScores.readScores("scores.txt");
            highScores.checkScore(handler.getGame().getScore());
            if (highScores.isBigger()) {
                handler.getDisplay().makeNewScore();
                highScores.writeScores(handler.getDisplay().getName(), handler.getGame().getScore(), handler.getGame().getCombo());
                highScores.readScores("scores.txt");
            } else {
                handler.getDisplay().makeSadScore();
            }

            // Stops the music and sets the state to the score screen
            music.stopMusic();
            State.setState(handler.getGame().scoreState);


            handler.getGame().setScore(0);
            handler.getGame().setMaxCombo(0);
            handler.getGame().setCombo(0);
        }

        // Using an iterator to render the notes
        Iterator<Notes> it = notes.iterator();
        while (it.hasNext()) {
            Notes n = it.next();
            n.tick();
            if (!n.isActive()) {
                it.remove();
            }
        }

        // Time to know when does it need to render the circles
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer + APPROACH_RATE + OFFSET >= currentNote[2]) { // Offset needs to be adjusted every time you run?
            if (counter < supercell.objNum()) {
                counter++;
            currentNote = supercell.getCurrentNote(counter);
            notes.add(new Notes(handler, currentNote[0],currentNote[1]));
            }
        }
    }

    public void render(Graphics g) {
        for(Notes e : notes){
            e.render(g);
        }
    }

    public void setLastTime (long lastTime) {
        this.lastTime = lastTime;
    }
}
