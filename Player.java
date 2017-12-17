package endlessrunningtcf;

import java.util.Vector;

public class Player{

    //-----ATTRIBUTI------

    private String name;                        //nome del giocatore
    private Boolean crashed;                    //bool se si è schiantato contro un ostacolo        
    private int width;                          //larghezza della mappa
    private int lenght;                         //altezza della mappa
    private Vector<Vector<Point>> map;          //mappa di gioco, rettangolo di punti
    private Vector<Obstacle> obvect;            //vettore contenente tutti gli ostacoli
    private Character character;                //è il nostro personaggio, un derivato al pari degli ostacoli
                                                  //della classe astratta Object

    //------METODI-------

    /**
     * - setta a falso la varibile crashed
     * - inizializza il vettore di ostacoli
     * - inizializza la mappa
     * - riempie la mappa di punti di coordinate 0,0 0,1 0,2 ...
     */
    public Player(){
        crashed = false;
        obvect = new Vector<Obstacle>();
        map = new Vector<Vector<Point>>();
        for(int i=0;i<width;i++){
            Vector<Point> r = new Vector<Point>();
            for(int j=0;j<lenght;j++){
                r.add(new Point(i,j));
            }
            map.add(r);
        }
    }
    /**
     * - aggiorna la posizione del giocatore
     * - aggiorna la posizione di tutti gli ostacoli
     * - disegna tutti i (char dei) punti della mappa 
     */
    private void Draw(){

        for(int i=0; i<lenght; i++){        //|
            System.out.print("\033[A");     //|  esegue il clear della 
            System.out.print("\033[2K");    //|  schermata prima di ridisegnare
        }                                   //|

        map = character.UpdatePosition(map);
        for (Obstacle o : obvect) {
            map = o.UpdatePosition(map);
        }       
        for(int i=0;i<width;i++){
            Vector<Point> r=map.get(i);
            for(int j=0;j<lenght;j++){
                System.out.print(r.get(j).getChar()); 
            }
            System.out.println(); 
        }
    }
    /**
     * - per ogni punto di ogni ostacolo verifica se c'è stata
     * la collisione
     */
    private void CheckCrashed(){
        for (Obstacle o : obvect) {
            if(o.CheckCollision()==true){
                crashed = true;
            }
        }
    }
    /**
     * - disegna la mappa aggiornata
     * - inserisce (se necessario) un nuovo ostacolo nel vettore
     * - aspetta un tempo prefissato prima di rieseguirsi
     * - esce quando il giocatore si schianta
     */
    public void Run(){
        int counter; //decide dopo quanti cicli mandare un nuovo ostacolo
        while(crashed == false){
            counter++;
            Draw();
            if(counter == 10){
                obvect.add(ObstacleFactory.NewObstacle());
                counter = 0;
            }
            Thread.sleep(41); //41 millisecondi dovrebbero essere 1/24 di secondo
        }
        System.out.println("Ti sei schiantato!");
    }

}