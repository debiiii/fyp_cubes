package ytwong239.scm.cubicdebug;

/**
 * Created by DebbieWong on 17/3/2019.
 */

public class QuestionBank_SPType3_Quest {

    private static final int MAXQUESTBANKSPTYPE3SUM = 6;

    private Question_SPType3 questionSPType3s[] = new Question_SPType3[MAXQUESTBANKSPTYPE3SUM];

    private static int currQuestBankSPType3Num;

    public QuestionBank_SPType3_Quest(){
        for(int i = 0; i < MAXQUESTBANKSPTYPE3SUM; i++){
            questionSPType3s[i] = new Question_SPType3();
        }

        questionSPType3s[0].setQuestionVal(0, 0);
        questionSPType3s[0].setQuestionVal(3, 0);
        questionSPType3s[0].setQuestionVal(6, 0);
        questionSPType3s[0].setQuestionVal(7, 0);
        questionSPType3s[0].setQuestionVal(7, 1);

        questionSPType3s[1].setQuestionVal(0, 0);
        questionSPType3s[1].setQuestionVal(3, 0);
        questionSPType3s[1].setQuestionVal(4, 0);
        questionSPType3s[1].setQuestionVal(6, 0);

        questionSPType3s[2].setQuestionVal(6,0);
        questionSPType3s[2].setQuestionVal(6,1);
        questionSPType3s[2].setQuestionVal(7,0);

        questionSPType3s[3].setQuestionVal(2,0);
        questionSPType3s[3].setQuestionVal(4,0);
        questionSPType3s[3].setQuestionVal(6,0);

        questionSPType3s[4].setQuestionVal(2,0);
        questionSPType3s[4].setQuestionVal(2,1);
        questionSPType3s[4].setQuestionVal(6,0);

        questionSPType3s[5].setQuestionVal(4,0);
        questionSPType3s[5].setQuestionVal(4,1);
        questionSPType3s[5].setQuestionVal(6,0);
        questionSPType3s[5].setQuestionVal(8,0);




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
