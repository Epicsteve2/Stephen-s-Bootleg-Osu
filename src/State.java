/* Abstract class for the states of the game*/

import java.awt.*;

public abstract class State {
    private static State currentState = null;
    protected Handler handler;

    // Every class that extends needs to tick and render
    public State (Handler handler) {
        this.handler = handler;
    }
    public abstract void tick ();
    public abstract void render (Graphics g);
    // Method for rendering to make the graphics go on top
    public abstract void postRender (Graphics g);

    // Getters and setters
    public static void setState (State state) {
        currentState = state;
    }

    public static State getState (){
        return currentState;
    }
}
