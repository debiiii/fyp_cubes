package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 24/2/2019.
 */

public class QuestionBank {

    private static final int MAXQUESTIONSNUM = 6;
    private Question questions[] = new Question[MAXQUESTIONSNUM];

    private int currQNum = 0;

    public QuestionBank(){
        for(int i = 0; i < MAXQUESTIONSNUM; i++){
            questions[i] = new Question();
        }

        questions[0].setQuestionVal(1, 0);
        questions[0].setQuestionVal(4, 0);
        questions[0].setQuestionVal(7, 0);
        questions[0].setQuestionVal(7, 1);

        questions[0].updateView();
    }

    public int getFrontView(int grid){
        return questions[currQNum].getFrontView(grid);
    }

    public int getTopView(int grid){
        return questions[currQNum].getTopView(grid);
    }

    public int getSideView(int grid){
        return questions[currQNum].getSideView(grid);
    }








}
