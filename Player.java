package endlessrunningtcf;

import java.util.Vector;

public class Player{

    //-----ATTRIBUTI------

    private String name;                        //nome del giocatore
    private Boolean crashed;                    //bool se si è schiantato contro un ostacolo        
    private int width=20;                          //larghezza della mappa
    private int lenght=20;                         //altezza della mappa
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
     * - inizializza il personaggio
     */
    public Player(){

        crashed = false;
        obvect = new Vector<Obstacle>();
        map = new Vector<Vector<Point>>();
        for(int i=0;i<width;i++){                   //non posso usare un for each perché devo
            Vector<Point> r = new Vector<Point>();  //riempire con i numeri e perché map all'inizio
            for(int j=0;j<lenght;j++){              //è vuota
                r.add(new Point(i,j));
            }
            map.add(r);
        }
        character = new Character(10,10, 0, -9.81, map);
    }
    /**
     * - aggiorna la posizione del giocatore
     * - aggiorna la posizione di tutti gli ostacoli
     */
    private void Update(){
        map = character.UpdatePosition(map, 42);
        for (Obstacle o : obvect) {
            map = o.UpdatePosition(map, 42);
        }  
    }
    /**
     * - disegna tutti i (char dei) punti della mappa 
     */
    private void Draw(){

        for(int i=0; i<lenght; i++){        //|
            System.out.print("\033[A");     //|  esegue il clear della 
            System.out.print("\033[2K");    //|  schermata prima di ridisegnare
        }                                   //|
     
        for (Vector<Point> v : map) {
            for (Point p : v) {
                System.out.print(p.getChar()); 
            }
            System.out.println();
        }
    }
    /**
     * - per ogni punto della mappa verifica se c'è stata collisione
     */
    private void CheckCrashed(){
        for (Vector<Point> v : map) {
            for (Point p : v) {
                if(p.isObstacle()==true && p.isCharacter()==true){
                    crashed = true;
                }
            }
        }
    }
    /**
     * - aggiorna i punti
     * - controlla le collisioni
     * - disegna la mappa aggiornata
     * - inserisce (se necessario) un nuovo ostacolo nel vettore
     * - aspetta un tempo prefissato prima di rieseguirsi
     * - esce quando il giocatore si schianta
     */
    public void Run(){
        int counter = 0; //decide dopo quanti cicli mandare un nuovo ostacolo
        while(crashed == false){
            Update();
            CheckCrashed();
            Draw();
            counter++;
            if(counter == 10){
                obvect.add(ObstacleFactory.NewObstacle(map));
                counter = 0;
            }
            try{
                Thread.sleep(41); //41 millisecondi dovrebbero essere 1/24 di secondo
                throw new InterruptedException();
         
            }
            catch(InterruptedException e){

            }
        }
        System.out.println("Ti sei schiantato!");
    }

}