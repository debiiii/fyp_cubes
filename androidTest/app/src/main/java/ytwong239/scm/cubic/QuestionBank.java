package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 24/2/2019.
 */

public class QuestionBank {

    private static final int MAXQUESTBANKSNUM = 10;
    private Question questions[] = new Question[MAXQUESTBANKSNUM];

    private int currQuestBankNum = 0;

    public QuestionBank(){
        for(int i = 0; i < MAXQUESTBANKSNUM; i++){
            questions[i] = new Question();
        }

        questions[0].setQuestionVal(1, 0);
        questions[0].setQuestionVal(4, 0);
        questions[0].setQuestionVal(7, 0);
        questions[0].setQuestionVal(7, 1);
        questions[0].updateView();

        questions[1].setQuestionVal(2, 0);
        questions[1].setQuestionVal(5, 0);
        questions[1].setQuestionVal(4, 0);
        questions[1].setQuestionVal(7, 0);
        questions[1].updateView();

        questions[2].setQuestionVal(0, 0);
        questions[2].setQuestionVal(0, 1);
        questions[2].setQuestionVal(4, 0);
        questions[2].setQuestionVal(8, 0);
        questions[2].setQuestionVal(8, 1);
        questions[2].setQuestionVal(8, 2);
        questions[2].updateView();

        questions[3].setQuestionVal(3, 0);
        questions[3].setQuestionVal(4, 0);
        questions[3].setQuestionVal(4, 1);
        questions[3].setQuestionVal(6, 0);
        questions[3].setQuestionVal(8, 0);
        questions[3].updateView();

        questions[4].setQuestionVal(0, 2);
        questions[4].setQuestionVal(1, 1);
        questions[4].setQuestionVal(1, 2);
        questions[4].setQuestionVal(4, 0);
        questions[4].setQuestionVal(4, 1);
        questions[4].setQuestionVal(5, 0);
        questions[4].updateView();

        questions[5].setQuestionVal(3, 0);
        questions[5].setQuestionVal(3, 1);
        questions[5].setQuestionVal(3, 2);
        questions[5].setQuestionVal(4, 1);
        questions[5].setQuestionVal(5, 1);
        questions[5].setQuestionVal(6, 0);
        questions[5].setQuestionVal(6, 1);
        questions[5].updateView();

        //sp type 3 question
        questions[6].setQuestionVal(0, 0);
        questions[6].setQuestionVal(1, 0);
        questions[6].setQuestionVal(2, 0);
        questions[6].setQuestionVal(3, 0);
        questions[6].setQuestionVal(3, 1);

        //sp type 3 question
        questions[7].setQuestionVal(2, 0);
        questions[7].setQuestionVal(4, 0);
        questions[7].setQuestionVal(5, 0);
        questions[7].setQuestionVal(8, 0);

        //sp type 4 question
        questions[8].setQuestionVal(0, 0);
        questions[8].setQuestionVal(1, 0);
        questions[8].setQuestionVal(1, 1);
        questions[8].setQuestionVal(1, 2);
        questions[8].setQuestionVal(4, 2);
        questions[8].setQuestionVal(7, 2);
        questions[8].setQuestionVal(8, 2);

        //sp type 4 question
        questions[9].setQuestionVal(0, 0);
        questions[9].setQuestionVal(0, 1);
        questions[9].setQuestionVal(1, 1);
        questions[9].setQuestionVal(2, 1);
        questions[9].setQuestionVal(3, 0);
        questions[9].setQuestionVal(5, 1);
        questions[9].setQuestionVal(6, 0);
        questions[9].setQuestionVal(8, 0);
        questions[9].setQuestionVal(8, 1);
        questions[9].setQuestionVal(8, 2);
    }

    public void setCurrQuestBankNum(int num){
        currQuestBankNum = num;
    }

    public int getFrontView(int grid){
        return questions[currQuestBankNum].getFrontView(grid);
    }

    public int getSideView(int grid){
        return questions[currQuestBankNum].getSideView(grid);
    }

    public int getTopView(int grid){
        return questions[currQuestBankNum].getTopView(grid);
    }

    public boolean getIsCubePresentFront(int grid) {
        return questions[currQuestBankNum].getIsCubePresentFront(grid);
    }

    public boolean getIsCubePresentSide(int grid){
        return questions[currQuestBankNum].getIsCubePresentSide(grid);
    }

    public boolean getIsCubePresentTop(int grid){
        return questions[currQuestBankNum].getIsCubePresentTop(grid);
    }

    public boolean getIsCubePresent(int grid, int height){
        return questions[currQuestBankNum].getIsCubePresent(grid, height);
    }









}
