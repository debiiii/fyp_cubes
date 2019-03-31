package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 7/3/2019.
 */

public class QuestionBank_SPType4_Ans {

    private static final int MAXQUESTBANKSPTYPE4SUM = 2;
    private Question_SPType4 questionSPType4s[] = new Question_SPType4[MAXQUESTBANKSPTYPE4SUM];
    
    private static int currQuestBankSPType4Num;
    
    public QuestionBank_SPType4_Ans(){
        
        for(int i = 0; i < MAXQUESTBANKSPTYPE4SUM; i++){
            questionSPType4s[i] = new Question_SPType4();
        }

        questionSPType4s[0].setQuestionVal(0, 0);
        questionSPType4s[0].setQuestionVal(0, 1);
        questionSPType4s[0].setQuestionVal(0, 2);
        questionSPType4s[0].setQuestionVal(1, 0);
        questionSPType4s[0].setQuestionVal(2, 0);
        questionSPType4s[0].setQuestionVal(3, 0);
        questionSPType4s[0].setQuestionVal(3, 1);

        questionSPType4s[1].setQuestionVal(2, 0);
        questionSPType4s[1].setQuestionVal(2, 1);
        questionSPType4s[1].setQuestionVal(3, 0);
        questionSPType4s[1].setQuestionVal(4, 0);
        questionSPType4s[1].setQuestionVal(5, 0);
        questionSPType4s[1].setQuestionVal(5, 1);
        questionSPType4s[1].setQuestionVal(8, 0);
    }

    public static void setCurrQuestBankSPType4Num(int num){
        currQuestBankSPType4Num = num;
    }

    public int getCurrQuestBankSPType4Num(){
        return currQuestBankSPType4Num;
    }

    public int getQuestionSPType4sLength(){
        return questionSPType4s.length;
    }

    public boolean getIsCubePresent(int grid, int height){
        return questionSPType4s[currQuestBankSPType4Num].getIsCubePresent(grid, height);
    }
    
}
