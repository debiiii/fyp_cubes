package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 24/2/2019.
 */

public class GameManager {

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    Integer[] qFrontView = new Integer[MAXGRIDSNUM];
    Integer[] qSideView = new Integer[MAXGRIDSNUM];
    Integer[] qTopView = new Integer[MAXGRIDSNUM];

    public GameManager(){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            qFrontView[i] = 0;
            qSideView[i] = 0;
            qTopView[i] = 0;
        }
    }

    public int getQFrontView(int grid){
        return qFrontView[grid];
    }

    public int getQSideView(int grid){
        return qSideView[grid];
    }

    public int getQTopView(int grid){
        return qTopView[grid];
    }

}
