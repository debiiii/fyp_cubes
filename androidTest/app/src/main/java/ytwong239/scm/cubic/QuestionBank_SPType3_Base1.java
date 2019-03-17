package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 17/3/2019.
 */

public class QuestionBank_SPType3_Base1 {

    private static final int MAXQUESTBANKSPTYPE3SUM = 2;
    private Question_SPType3 questionSPType3s[] = new Question_SPType3[MAXQUESTBANKSPTYPE3SUM];

    private static int currQuestBankSPType3Num;

    public QuestionBank_SPType3_Base1(){
        for(int i = 0; i < MAXQUESTBANKSPTYPE3SUM; i++){
            questionSPType3s[i] = new Question_SPType3();
        }

        questionSPType3s[0].setQuestionVal(2, 0);
        questionSPType3s[0].setQuestionVal(2, 1);
        questionSPType3s[0].setQuestionVal(1, 0);
        questionSPType3s[0].setQuestionVal(5, 0);

        questionSPType3s[1].setQuestionVal(8, 0);
        questionSPType3s[1].setQuestionVal(8, 1);
        questionSPType3s[1].setQuestionVal(8, 2);
        questionSPType3s[1].setQuestionVal(7, 2);
    }

    public static void setCurrQuestBankSPType3Num(int num){
        currQuestBankSPType3Num = num;
    }

    public int getCurrQuestBankSPType3Num(){
        return currQuestBankSPType3Num;
    }

    public int getQuestionSPType3sLength(){
        return questionSPType3s.length;
    }

    public boolean getIsCubePresent(int grid, int height){
        return questionSPType3s[currQuestBankSPType3Num].getIsCubePresent(grid, height);
    }
}
