package endlessrunningtcf;

import java.util.Vector;

public class Obstacle extends Object{

    public Obstacle(int width_, int lenght_){
        width = width_;
        lenght = lenght_;
        //bisogna anche inizializzare box
    }
    public Vector<Vector<Point>> UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //per il momento non si considerano accelerazioni e si calcola per MRU
        int newpos = 0;
        newpos = (int)(speed * (time/1000));       //(int) avverte il compilatore che sono
                                                  //consapevole della conversione double->int
        for (Vector<Point> vp : box) {
            for (Point p : vp) {
                p.setX(p.getX()-newpos);    // - non + perché si muove da destra verso sinistra
            }
        }

        return map;
    }  
    
}