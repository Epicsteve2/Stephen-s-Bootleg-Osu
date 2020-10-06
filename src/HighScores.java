/* Class for finding the scores of the game*/

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class HighScores {
    private PrintWriter out;
    private Handler handler;
    private String scores [][];
    private String wholeLine[], temp[];
    private int lowestIndex = 0;
    private boolean bigger = false;
    private final int FILELENGTH = 6;

    public HighScores (Handler handler) {
        this.handler = handler;
        scores = new String[3][FILELENGTH];
        wholeLine = new String [FILELENGTH];
    }

    // Checks if user beat a highscore
    public void checkScore (int userScore){
        scores = getScores();
        for (int i = 0; i < FILELENGTH; i++) {
            if (userScore > Integer.parseInt(scores[1][i])) {
                bigger = true;
            }
        }
    }

    public boolean isBigger() {
        return bigger;
    }

    // Writes the scores to a file
    public void writeScores (String name, int userScore, int userCombo) {


        readScores("res/text/scores.txt");
        this.checkScore(userScore);

        if (bigger) {
            wholeLine [lowestIndex] = name + " " + userScore + " " + userCombo;
        }

        try {
            out = new PrintWriter(new FileOutputStream("res/text/scores.txt", false));
            for (int i = 0; i < FILELENGTH; i++) {
                out.println(wholeLine[i]);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bigger = false;
    }


    // Reads the scores from a file
    public void readScores (String path) {
        String file = Utils.loadFileAsString(path);
        String []tokens = file.split("\\s+");
        for (int y = 0; y < (tokens.length)/3; y++) {
            for (int x = 0; x < 3; x++) {
                scores [x][y] = tokens[x+y*3];
            }
        }

        for (int i = 0 ; i < FILELENGTH; i++) {
            wholeLine[i] = scores[0][i] + " " + scores[1][i] + " " + scores[2][i] ;
        }

        // Finds the lowest scores
        for (int i = 0 ; i < FILELENGTH - 1; i++) {
            if (Integer.parseInt(scores[1][i]) > Integer.parseInt(scores[1][i+1])) {
                lowestIndex = i + 1;
            }
        }
    }

    // Getters for the scores
    public String [][] getScores (){
        return scores;
    }
}
