/**Stephen Guo
 * May 28th, 2019
 * I have created a video game called Bootleg Osu!, where the goal of the game
 * is to click circles to the rhythm of the song playing in the background.
 * You want to get the highest score and max combo possible, in order to
 * get into the leaderboards.
 * */

/* This class is the launcher of the program. The only function it has is to launch the game*/
public class Launcher {
    public static void main(String[]args) {
        // Creates the game
        Game game = new Game("Bootleg Osu!", 1200, 900);
        game.start();
    }
}
