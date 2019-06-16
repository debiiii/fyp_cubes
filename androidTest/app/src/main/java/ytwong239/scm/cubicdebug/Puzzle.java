package ytwong239.scm.cubicdebug;

/**
 * Created by DebbieWong on 7/4/2019.
 */

public class Puzzle {
    private static final int PUZZLEPIECENUM = 8;
    private static final int ROUNDTOGETPIECE = 2;

    private static int playedRound = 0;
    private boolean[] coverIsShown = new boolean[PUZZLEPIECENUM];
    private static boolean showNoti = false;
    private int lastPlayedRound = 0;



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

        if(playedRound != 0 && playedRound <= PUZZLEPIECENUM * ROUNDTOGETPIECE && playedRound % 2 == 0 && playedRound != lastPlayedRound){
            showNoti = true;
            lastPlayedRound = playedRound;
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

    public static void setShowNoti(boolean val){
        showNoti = val;
    }

    public boolean isShowNoti(){
        return showNoti;
    }
}
