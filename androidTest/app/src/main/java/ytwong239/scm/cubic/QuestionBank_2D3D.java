package ytwong239.scm.cubic;

import android.util.Log;

/**
 * Created by DebbieWong on 7/3/2019.
 */

public class QuestionBank_2D3D {

    private static final int MAXQUESTBANK2D3DSUM = 7;
    private Question_2D3D question2D3DS[] = new Question_2D3D[MAXQUESTBANK2D3DSUM];

    private static int currQuestBank2D3DNum;
    
    public QuestionBank_2D3D(){
        for(int i = 0; i < MAXQUESTBANK2D3DSUM; i++){
            question2D3DS[i] = new Question_2D3D();
        }

        question2D3DS[0].setQuestionVal(1, 0);
        question2D3DS[0].setQuestionVal(4, 0);
        question2D3DS[0].setQuestionVal(7, 0);
        question2D3DS[0].setQuestionVal(7, 1);
        question2D3DS[0].updateView();

        question2D3DS[1].setQuestionVal(2, 0);
        question2D3DS[1].setQuestionVal(5, 0);
        question2D3DS[1].setQuestionVal(4, 0);
        question2D3DS[1].setQuestionVal(7, 0);
        question2D3DS[1].updateView();

        question2D3DS[2].setQuestionVal(0, 0);
        question2D3DS[2].setQuestionVal(0, 1);
        question2D3DS[2].setQuestionVal(4, 0);
        question2D3DS[2].setQuestionVal(8, 0);
        question2D3DS[2].setQuestionVal(8, 1);
        question2D3DS[2].setQuestionVal(8, 2);
        question2D3DS[2].updateView();

        question2D3DS[3].setQuestionVal(3, 0);
        question2D3DS[3].setQuestionVal(4, 0);
        question2D3DS[3].setQuestionVal(4, 1);
        question2D3DS[3].setQuestionVal(6, 0);
        question2D3DS[3].setQuestionVal(8, 0);
        question2D3DS[3].updateView();

        question2D3DS[4].setQuestionVal(0, 2);
        question2D3DS[4].setQuestionVal(1, 1);
        question2D3DS[4].setQuestionVal(1, 2);
        question2D3DS[4].setQuestionVal(4, 0);
        question2D3DS[4].setQuestionVal(4, 1);
        question2D3DS[4].setQuestionVal(5, 0);
        question2D3DS[4].updateView();

        question2D3DS[5].setQuestionVal(3, 0);
        question2D3DS[5].setQuestionVal(3, 1);
        question2D3DS[5].setQuestionVal(3, 2);
        question2D3DS[5].setQuestionVal(4, 1);
        question2D3DS[5].setQuestionVal(5, 1);
        question2D3DS[5].setQuestionVal(6, 0);
        question2D3DS[5].setQuestionVal(6, 1);
        question2D3DS[5].updateView();

        question2D3DS[6].setQuestionVal(0, 0);
        question2D3DS[6].setQuestionVal(0, 1);
        question2D3DS[6].setQuestionVal(0, 2);
        question2D3DS[6].setQuestionVal(1, 0);
        question2D3DS[6].updateView();
    }

    public static void setCurrQuestBank2D3DNum(int num){
        currQuestBank2D3DNum = num;
    }

    public int getCurrQuestBank2D3DNum(){
        return currQuestBank2D3DNum;
    }

    public int getFrontView(int grid){
        return question2D3DS[currQuestBank2D3DNum].getFrontView(grid);
    }

    public int getSideView(int grid){
        return question2D3DS[currQuestBank2D3DNum].getSideView(grid);
    }

    public int getTopView(int grid){
        return question2D3DS[currQuestBank2D3DNum].getTopView(grid);
    }

    public int getQuestion2D3DSLength(){
        return question2D3DS.length;
    }

    public boolean getIsCubePresent(int grid, int height){
        return question2D3DS[currQuestBank2D3DNum].getIsCubePresent(grid, height);
    }
    
}
