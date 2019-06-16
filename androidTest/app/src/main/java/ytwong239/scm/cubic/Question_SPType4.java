package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 7/3/2019.
 */

public class Question_SPType4 {
    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private Boolean[][] isCubePresent = new Boolean [MAXGRIDSNUM][MAXHEIGHTNUM];

    public Question_SPType4(){

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                isCubePresent[i][j] = false;
            }
        }
    }

    public void setQuestionVal(int grid, int height){
        this.isCubePresent[grid][height] = true;
    }

    public boolean getIsCubePresent(int grid, int height){
        return isCubePresent[grid][height];
    }
}
