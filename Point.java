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
    public Point(int x_, int y_, char c_, Boolean ischaracter_, Boolean isobstacle_){
        x = x_;
        y = y_;
        c = c_;
        ischarcter = ischaracter_;
        isobstacle = isobstacle_;
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
    public void setChar(char c_){
        c = c_;
    }
    public char getChar(){
        return c;
    }
    public void setCharacter(Boolean tf){
        ischarcter = tf;
    }
    public void setObstacle(Boolean tf){
        isobstacle = tf;
    }
    public Boolean isCharacter(){
        return ischarcter;
    }
    public Boolean isObstacle(){
        return isobstacle;
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