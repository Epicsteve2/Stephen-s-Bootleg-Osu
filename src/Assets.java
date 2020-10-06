/*This class contains all of the images that needs to be used in the program.*/

import java.awt.image.BufferedImage;

public class Assets {
    // Creating and initalizing all of the images
    public static BufferedImage  hitCircle,supercellBg, intro, title, help, score, information;
    public static void init() {
        hitCircle = ImageLoader.loadImage("res/textures/hitcircle.png");
        supercellBg = ImageLoader.loadImage("res/textures/supercell bg.jpg");
        intro = ImageLoader.loadImage("res/textures/intro.jpg");
        title = ImageLoader.loadImage("res/textures/title.jpg");
        help = ImageLoader.loadImage("res/textures/how.jpg");
        score = ImageLoader.loadImage("res/textures/scores.jpg");
        information = ImageLoader.loadImage("res/textures/information.png");
    }
}
