/*This class contains all of the images that needs to be used in the program.*/

import java.awt.image.BufferedImage;

public class Assets {
    // Creating and initalizing all of the images
    public static BufferedImage  hitCircle,supercellBg, intro, title, help, score, information;
    public static void init() {
        hitCircle = ImageLoader.loadImage("hitcircle.png");
        supercellBg = ImageLoader.loadImage("supercell bg.jpg");
        intro = ImageLoader.loadImage("intro.jpg");
        title = ImageLoader.loadImage("title.jpg");
        help = ImageLoader.loadImage("how.jpg");
        score = ImageLoader.loadImage("scores.jpg");
        information = ImageLoader.loadImage("information.png");
    }
}
