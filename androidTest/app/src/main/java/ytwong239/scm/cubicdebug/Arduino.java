package ytwong239.scm.cubicdebug;

/**
 * Created by DebbieWong on 26/2/2019.
 */

public class Arduino {

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    protected Base[] bases = new Base[MAXBASESNUM];

    //27 arduino value string
    private String[] ardAllString = new String[MAXCUBESNUM];

    //divided to 3 arduino value string for 3 bases
    private String[] ardBase0String = new String[MAXGRIDSNUM];
    private String[] ardBase1String = new String[MAXGRIDSNUM];
    private String[] ardBase2String = new String[MAXGRIDSNUM];

    //for the final isCubePresent according to the 3 bases info
    private Boolean[][] isCubePresent = new Boolean [MAXGRIDSNUM][MAXHEIGHTNUM];

    public Arduino(){
        for(int i = 0; i < MAXBASESNUM; i++){
            bases[i] = new Base();
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                isCubePresent[i][j] = false;
            }
        }
    }

    public void run(){
        convertArdForBase();

        bases[0].update(ardBase0String);
        bases[1].update(ardBase1String);
        bases[2].update(ardBase2String);

        updateCubesFinal();
    }

    //convert the long string to 3 strings for bases
    private void convertArdForBase(){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            ardBase0String[i] = ardAllString[i];
            ardBase1String[i] = ardAllString[i + MAXGRIDSNUM];
            ardBase2String[i] = ardAllString[i + MAXGRIDSNUM * 2];
        }
    }

    //update the isCubePresent according to the 3 bases info
    private void updateCubesFinal() {

        /*-------------grid0-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[0].isCubePresent(i) || bases[1].grids[i].isCubePresent(0) || bases[2].grids[i].isCubePresent(2)){
                isCubePresent[0][i] = true;
            }
            else{
                isCubePresent[0][i] = false;
            }
        }

        /*-------------grid1-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[1].isCubePresent(i) || bases[1].grids[i + 3].isCubePresent(0) || bases[2].grids[i].isCubePresent(1)){
                isCubePresent[1][i] = true;
            }
            else{
                isCubePresent[1][i] = false;
            }
        }

        /*-------------grid2-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[2].isCubePresent(i) || bases[1].grids[i + 3 + 3].isCubePresent(0) || bases[2].grids[i].isCubePresent(0)){
                isCubePresent[2][i] = true;
            }
            else{
                isCubePresent[2][i] = false;
            }
        }

        /*-------------grid3-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[3].isCubePresent(i) || bases[1].grids[i].isCubePresent(1) || bases[2].grids[i + 3].isCubePresent(2)){
                isCubePresent[3][i] = true;
            }
            else{
                isCubePresent[3][i] = false;
            }
        }

        /*-------------grid4-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[4].isCubePresent(i) || bases[1].grids[i + 3].isCubePresent(1) || bases[2].grids[i + 3].isCubePresent(1)){
                isCubePresent[4][i] = true;
            }
            else{
                isCubePresent[4][i] = false;
            }
        }

        /*-------------grid5-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[5].isCubePresent(i) || bases[1].grids[i + 3 + 3].isCubePresent(1) || bases[2].grids[i + 3].isCubePresent(0)){
                isCubePresent[5][i] = true;
            }
            else{
                isCubePresent[5][i] = false;
            }
        }

        /*-------------grid6-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[6].isCubePresent(i) || bases[1].grids[i].isCubePresent(2) || bases[2].grids[i + 3 + 3].isCubePresent(2)){
                isCubePresent[6][i] = true;
            }
            else{
                isCubePresent[6][i] = false;
            }
        }

        /*-------------grid7-------------*/

        for(int i = 0; i < 3; i++){
            if(bases[0].grids[7].isCubePresent(i) || bases[1].grids[i + 3].isCubePresent(2) || bases[2].grids[i + 3 + 3].isCubePresent(1)){
                isCubePresent[7][i] = true;
            }
            else{
                isCubePresent[7][i] = false;
            }
        }

        /*-------------grid8-------------*/
        
        for(int i = 0; i < 3; i++){
            if(bases[0].grids[8].isCubePresent(i) || bases[1].grids[i + 3 + 3].isCubePresent(2) || bases[2].grids[i + 3 + 3].isCubePresent(0)){
                isCubePresent[8][i] = true;
            }
            else{
                isCubePresent[8][i] = false;
            }
        }

    }

    public void setArdAllString(String[] ard){
        ardAllString = ard;
    }

    public String getArdBase0String(int grid){
        return ardBase0String[grid];
    }

    public String getArdBase1String(int grid){
        return ardBase1String[grid];
    }

    public String getArdBase2String(int grid){
        return ardBase2String[grid];
    }

    public Boolean getIsCubePresent(int grid, int height){
        return isCubePresent[grid][height];
    }
}
