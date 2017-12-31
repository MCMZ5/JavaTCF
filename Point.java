package endlessrunningtcf;

public class Point{

    private int x;
    private int y;
    private char c=' ';
    private Boolean ischarcter;
    private Boolean isobstacle;

    public Point(int x_, int y_){
        x = x_;
        y = y_;
        ischarcter = false;
        isobstacle = false;
    }
    public Point(int x_, int y_, Boolean ischaracter_, Boolean isobstacle_){
        x = x_;
        y = y_;
        ischarcter = ischaracter_;
        isobstacle = isobstacle_;
        if(ischarcter==true){
            setChar('@');
        }
        if(isobstacle==true){
            setChar('#');
        }
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
        if(ischarcter==true){
            setChar('@');
        }
        if(ischarcter==false){
            setChar(' ');
        }
    }
    public void setObstacle(Boolean tf){
        isobstacle = tf;
        if(isobstacle==true){
            setChar('#');
        }
        if(isobstacle==false){
            setChar(' ');
        }
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