package endlessrunningtcf;

public class Point{

    private int x;
    private int y;
    private char c;
    private Boolean ischarcter;
    private Boolean isobstacle;

    public Point(int x_, int y_){
        x = x_;
        y = y_;
        ischarcter = false;
        isobstacle = false;
    }
    public void setX(int x_){
        x = x_;
    }
    public void setY(int y_){
        y = y_;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public char getChar(){
        return c;
    }
    public Boolean CheckCollision(){
        if(ischarcter == true && isobstacle == true){
            return true;
        }
        else{
            return false;
        }
    }


}