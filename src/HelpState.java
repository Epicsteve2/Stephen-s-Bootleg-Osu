/* State for the help screen*/

import java.awt.*;

public class HelpState extends State{
    private float mouseX, mouseY;

    public HelpState(Handler handler){
        super (handler);
    }
    @Override
    public void tick(){
        // If the user clicks the back button, go to the menu
        mouseX = handler.getMouseManager().getMouseX();
        mouseY = handler.getMouseManager().getMouseY();
        if (mouseX > 0 && mouseX < 200
           && mouseY > 820 && mouseY < 900 && handler.getMouseManager().isLeftPressed()) {
            State.setState(handler.getGame().menuState);
        }
    }

    // Draws the help screen
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.help,0,0,null);
    }
    @Override
    public void postRender(Graphics g) {}
}
