package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 24/2/2019.
 */

public class GameManager {

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private static final int MAXQUESTIONSNUM = 6;

    private QuestionBank questionBank = new QuestionBank();
    private Question[] questions = new Question[MAXQUESTIONSNUM];

    public GameManager(){

    }


}
