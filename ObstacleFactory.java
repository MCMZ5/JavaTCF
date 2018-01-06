package endlessrunningtcf;

import java.util.Random;
import java.util.Vector;

public class ObstacleFactory{

    /**
     * - genera larghezza, altezza, velocità e accelerazioni casuali
     * - a seconda del livello il moto dell'ostacolo e via via più complesso
     * - inizializza un nuovo ostacolo e lo restituisce
     */
    public static Obstacle NewObstacle(Vector<Vector<Point>> map, int level){
        Random rand = new Random();

        //i valori sono stati scelti dopo numerosi test per assicurare la corretta
        //difficoltà proporzionalmente al livello di gioco

        double a = 12.;
        double b = 0.8;
        if(level>2){
            a=a+level-1;
            b=b+((level-1)/10);
        }

        int width = rand.nextInt(4) + 1;                      //nmax 3, nmin 1
        int lenght = rand.nextInt(4) + 2;                     //nmax 6, nmin 2
        double speedx = rand.nextDouble() * (4.) + a;         //nmax 4+a, nmin a
        double speedy = 0;
        double acc = 0;
        int y = 0; 
        if(level>1){
            acc = rand.nextDouble() * (3.2) + b;             //nmax 3.2+b, nmin b
        }
        if(level>4){
            y = (rand.nextInt(map.size()-3))/2;              //nmax 7.5, nmin 0
            speedy = -3;
            if(y!=0){
                speedx = speedx+5;
            }
        }
        Obstacle obs = new Obstacle(y , width, lenght, speedx, speedy , acc, map);
        return obs;
    }

}
