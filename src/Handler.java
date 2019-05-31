/* Handles all of the variables across the program*/

public class Handler {
    private Game game;
    private State state;
    private Display display;
    private GameState gameState;
    private boolean isGameMusicPlaying = false;

    // Initalizes the display
    public Handler (Game game) {
        this.game = game;
        display = new Display("Bootleg Osu!",  1200 ,900);
    }

    // Getters and setters
    public Game getGame() {
        return game;
    }

    public Display getDisplay() {
        return display;
    }

    public State getRunningGame () {
        return gameState;
    }

    public void setNewGame () {
        gameState = new GameState(this);
        state.setState(gameState);
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public boolean isGameMusicPlaying() {
        return isGameMusicPlaying;
    }

    public void setGameMusicPlaying(boolean gameMusicPlaying) {
        isGameMusicPlaying = gameMusicPlaying;
    }
}
