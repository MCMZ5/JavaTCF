package endlessrunningtcf;

import java.util.Random;
import java.util.Vector;

import endlessrunningtcf.Obstacle;

public class ObstacleFactory{

    /**
     * - genera larghezza, altezza, velocità e accelerazioni casuali
     * - a seconda del livello il moto dell'ostacolo e via via più complesso
     * - inizializza un nuovo ostacolo e lo restituisce
     */
    public static Obstacle NewObstacle(Vector<Vector<Point>> map, int level){
        Random rand = new Random();
        int width = rand.nextInt(3-1) + 1;                      //nmax 3, nmin 1
        int lenght = rand.nextInt(6-2) + 2;                     //nmax 6, nmin 2
        double speedx = rand.nextDouble() * (16.-12.) + 12.;    //nmax 16, nmin 12
        double speedy = 0;
        double acc = 0;
        int y = 0; 
        if(level>1){
            acc = rand.nextDouble() * (4.-.8) + .8;             //nmax 3, nmin 1
        }
        if(level>2){
            y = rand.nextInt(map.size()-5)+width;               //nmax 3, nmin 1
        }
        if(level>3){
            speedy = rand.nextDouble() * (16.-12.);             //nmax 3, nmin 1
        }
        Obstacle obs = new Obstacle(y , width, lenght, speedx, speedy , acc, map);
        return obs;
    }

}