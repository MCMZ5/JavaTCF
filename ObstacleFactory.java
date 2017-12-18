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
        int width = rand.nextInt(10-4) + 4;                 //nmax 10, nmin 4
        int lenght = rand.nextInt(4-1) + 1;                 //nmax 4, nmin 1;
        double speed = rand.nextDouble() * (8.-2.) + 2.;    //nmax 8, nmin 2
        Obstacle obs = new Obstacle(width, lenght, map);
        return obs;
    }

}