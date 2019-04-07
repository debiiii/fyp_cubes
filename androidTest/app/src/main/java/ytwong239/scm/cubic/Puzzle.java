package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 7/4/2019.
 */

public class Puzzle {
    private static final int PUZZLEPIECENUM = 8;
    private static final int ROUNDTOGETPIECE = 2;

    private static int playedRound = 0;
    private boolean[] coverIsShown = new boolean[PUZZLEPIECENUM];


    public Puzzle(){
        for(int i = 0; i < PUZZLEPIECENUM; i++){
            coverIsShown[i] = true;
        }
    }

    public void update(){
        for(int i = 0; i < PUZZLEPIECENUM; i++){
            if(playedRound >= (i + 1) * ROUNDTOGETPIECE){
                coverIsShown[i] = false;
            }
        }
    }

    public static void setPlayedRound(int val){
        playedRound = val;
    }

    public int getPlayedRound(){
        return playedRound;
    }

    public boolean getCoverIsShown(int id){
        return coverIsShown[id];
    }
}
