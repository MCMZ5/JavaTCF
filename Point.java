package endlessrunningtcf;

public class Point{

    private int x;
    private int y;
    private char c;
    private Boolean isplayer;
    private Boolean isobstacle;

    public Point(int x_, int y_){
        x = x_;
        y = y_;
        isplayer = false;
        isobstacle = false;
    }
    public char getChar(){
        return c;
    }
    public Boolean CheckCrashed(){
        if(isplayer == true && isobstacle == true){
            return true;
        }
        else{
            return false;
        }
    }


}