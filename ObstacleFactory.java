package endlessrunningtcf;

import java.util.Random;
import java.util.Vector;

import endlessrunningtcf.Obstacle;

public class ObstacleFactory{

    /**
     * - genera larghezza, altezza e velocità casuali
     * - inizializza un nuovo ostacolo e lo restituisce
     */
    public static Obstacle NewObstacle(Vector<Vector<Point>> map){
        Random rand = new Random();
        int width = rand.nextInt(3-1) + 1;                  //nmax 3, nmin 1
        int lenght = rand.nextInt(6-2) + 2;                //nmax 6, nmin 2
        double speed = rand.nextDouble() * (16.-12.) + 12.;   //nmax 16, nmin 12
        double acc = rand.nextDouble() * (4.-.8) + .8;      //nmax 4, nmin 0.8
        Obstacle obs = new Obstacle(width, lenght, speed, acc, map);
        return obs;
    }

}