/* State for the game, all extends the state class*/

import java.awt.*;

public class GameState extends State{
    private boolean startTicking = false;
    private NoteLoader noteLoader = new NoteLoader(handler);

    public GameState(Handler handler){
        super (handler);
    }

    @Override
    public void tick() {
        // If the game is about to start, start ticking the game in order to sync with the music
        if (!startTicking) {
            if (handler.getGame().getGameState() == handler.getGame().gameState) {
                noteLoader.setLastTime(System.currentTimeMillis());
                startTicking = true;
            } else {
                startTicking = false;
            }
        }
        noteLoader.tick();
    }

    // Renders the background and song info
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.supercellBg, 0,0,null);
        g.drawImage(Assets.information,20,20,708,200,null);
    }
    // Postrender to render the notes in front of the background
    public void postRender (Graphics g) {
        noteLoader.render(g);
    }
}
