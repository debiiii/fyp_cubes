package ytwong239.scm.cubicdebug;

/**
 * Created by DebbieWong on 7/3/2019.
 */

public class Question_2D3D {
    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private Integer[] frontView = new Integer[MAXGRIDSNUM];
    private Integer[] sideView = new Integer[MAXGRIDSNUM];
    private Integer[] topView = new Integer[MAXGRIDSNUM];

    private Boolean[][] isCubePresent = new Boolean [MAXGRIDSNUM][MAXHEIGHTNUM];

    public Question_2D3D(){

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                isCubePresent[i][j] = false;
            }
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            frontView[i] = 0;
            sideView[i] = 0;
            topView[i] = 0;
        }
    }

    public void setQuestionVal(int grid, int height){
        isCubePresent[grid][height] = true;
    }

    public void updateView(){
        //update front view
        for(int i = 2, j = 0; i >= 0 && j < 9; i--, j+=3){
            if(isCubePresent[6][i]){
                frontView[j] = 1;
            }
            else if(isCubePresent[3][i]){
                frontView[j] = 1;
            }
            else if(isCubePresent[0][i]){
                frontView[j] = 1;
            }
            else{
                frontView[j] = 0;
            }

            if(isCubePresent[7][i]){
                frontView[j + 1] = 1;
            }
            else if(isCubePresent[4][i]){
                frontView[j + 1] = 1;
            }
            else if(isCubePresent[1][i]){
                frontView[j + 1] = 1;
            }
            else{
                frontView[j + 1] = 0;
            }

            if(isCubePresent[8][i]){
                frontView[j + 2] = 1;
            }
            else if(isCubePresent[5][i]){
                frontView[j + 2] = 1;
            }
            else if(isCubePresent[2][i]){
                frontView[j + 2] = 1;
            }
            else{
                frontView[j + 2] = 0;
            }
        }

        //update side view
        for(int i = 2, j = 0; i >= 0 && j < 9; i--, j+=3){
            if(isCubePresent[0][i]){
                sideView[j] = 1;
            }
            else if(isCubePresent[1][i]){
                sideView[j] = 1;
            }
            else if(isCubePresent[2][i]){
                sideView[j] = 1;
            }
            else{
                sideView[j] = 0;
            }

            if(isCubePresent[3][i]){
                sideView[j + 1] = 1;
            }
            else if(isCubePresent[4][i]){
                sideView[j + 1] = 1;
            }
            else if(isCubePresent[5][i]){
                sideView[j + 1] = 1;
            }
            else{
                sideView[j + 1] = 0;
            }

            if(isCubePresent[6][i]){
                sideView[j + 2] = 1;
            }
            else if(isCubePresent[7][i]){
                sideView[j + 2] = 1;
            }
            else if(isCubePresent[8][i]){
                sideView[j + 2] = 1;
            }
            else{
                sideView[j + 2] = 0;
            }
        }

        //update top view
        for(int i = 0; i < 9; i++){
            if(isCubePresent[i][2]){
                topView[i] = 1;
            }
            else if(isCubePresent[i][1]){
                topView[i] = 1;
            }
            else if(isCubePresent[i][0]){
                topView[i] = 1;
            }
            else{
                topView[i] = 0;
            }
        }

    }

    public int getFrontView(int grid){
        return frontView[grid];
    }

    public int getSideView(int grid){
        return sideView[grid];
    }

    public int getTopView(int grid){
        return topView[grid];
    }

    public boolean getIsCubePresent(int grid, int height){
        return isCubePresent[grid][height];
    }
}
