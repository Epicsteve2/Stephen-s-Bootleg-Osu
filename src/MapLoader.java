/* Class for loading the beatmap of the game*/

public class MapLoader {
    private int[][] map;
    public MapLoader (String path){
        getSong(path);
    }

    public int [] getCurrentNote(int order) {
        int []returnMap = new int [3];
        returnMap[0] = map[0][order];
        returnMap[1] = map[1][order];
        returnMap[2] = map[2][order];
        return returnMap;
    }

    // Uses the util class to get the map from a text file
    private void getSong(String path){
        String file = Utils.loadFileAsString(path);
        String []tokens = file.split("\\s+");
        map = new int [3][(tokens.length)/3];
        for (int y = 0; y < (tokens.length)/3; y++) {
            for (int x = 0; x < 3; x++) {
                map [x][y] = Utils.parseInt(tokens[x+y*3]);
            }
        }
    }

    public int objNum () {
        return map[1].length - 1;
    }
}
