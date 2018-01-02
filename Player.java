package endlessrunningtcf;

import java.util.Vector;
import java.io.IOException;

public class Player{

    //-----ATTRIBUTI------

    private String name;                        //nome del giocatore
    private Boolean crashed;                    //bool se si è schiantato contro un ostacolo        
    private int lenght=20;                      //larghezza della mappa
    private int width=130;                      //altezza della mappa
    private Vector<Vector<Point>> map;          //mappa di gioco, rettangolo di punti
    private Vector<Obstacle> obvect;            //vettore contenente tutti gli ostacoli
    private Character character;                //è il nostro personaggio, un derivato al pari degli ostacoli
                                                //della classe astratta Object
    private int excounter;                      //questo serve a contare le eccezioni per evitare un bug
                                                //dovuto ad eccezioni ripetute

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
        for(int i=0;i<lenght;i++){                  //non posso usare un for each perché devo
            Vector<Point> r = new Vector<Point>();  //riempire con i numeri e perché map all'inizio
            for(int j=0;j<width;j++){               //è vuota
                r.add(new Point(i,j));
            }
            map.add(r);
        }
        character = new Character(8,4, 0, 0, map);
    }
    /**
     * - aggiorna la posizione del giocatore
     * - aggiorna la posizione di tutti gli ostacoli
     */
    private void Update(){
        try{
            excounter = 0;
            character.UpdatePosition(map, 50);
            for (Obstacle o : obvect) {
                o.UpdatePosition(map, 50);
            }  
        }
        catch(ArrayIndexOutOfBoundsException exc){
            excounter++;
            if(excounter==1){                       //per evitare di rimuovere tanti ostacoli per n
                obvect.remove(obvect.size()-1);     //eccezioni dovute ad un solo ostacolo
            }
        }
    }
    /**
     * - disegna tutti i (char dei) punti della mappa 
     */
    private void DrawAndCheck(){

        for(int i=0; i<lenght; i++){        //sposto il cursore all'inizio della schermata di gioco
            System.out.print("\033[A");  
        }                             
     
        for (Vector<Point> v : map) {       //stampo la map con 2 barre verticali sui bordi
            System.out.print("|");
            for (Point p : v) {
                System.out.print(p.getChar()); 
                if(p.checkCollision()==true){  //per evitare troppi cicli for inseriamo
                    crashed = true;                                 //qui anche la check
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public static class Key implements Runnable{    
        Character character;
        Key(Character character_){
            character = character_;
        }
        public void run() {
            try{
                System.in.read();                   //attende un input seguito
                System.out.print("\033[A");         //da ENTER, quindi se riceve 
                if(character.getIntPosY()==0){      //solo enter va avanti e setta
                    character.setSpeed(8.);         //una velocità iniziale al character
                }                                   //quindi fisicamente gli fornisce un
            }                                       //impulso
            catch(IOException e){
            }
        }
    }

    /**
     * - aggiorna i punti
     * - controlla le collisioni
     * - disegna la mappa aggiornata
     * - inserisce (se necessario) un nuovo ostacolo nel vettore
     * - riceve l'input del giocatore
     * - esce quando il giocatore si schianta
     */
    public void Run(){
        int counter = 0; //decide dopo quanti cicli mandare un nuovo ostacolo
        int score = 0;   //tiene conto del punteggio
        int level = 1;   //livello di difficoltà
        while(crashed == false){
            Update();
            DrawAndCheck();
            Thread key = new Thread(new Key(character));    //in un thread separato si crea il Keylistener
            counter++;
            score++;
            if(score%300 == 0){     //il livello aumenta ogni 300 punti
                level++;
            }
            if(counter == 50){                                      //ogni 50 cicli crea
                obvect.add(ObstacleFactory.NewObstacle(map,level)); //un nuovo ostacolo
                counter = 0;
            }
            key.start();
            try{
                key.join(50);
                throw new InterruptedException();
            }
            catch(InterruptedException e){
            }
            if(key.isAlive()){
                key.interrupt();
            }
        }
        for(int i=0; i<((lenght+4)/2)-1; i++){              //quando il giocatore
            System.out.print("\033[A");                     //si schianta il programma scrive
        }                                                   //in mezzo allo schermo e a
        for(int i=0; i<(width/2)-5; i++){                   //colori invertiti un messaggio
            System.out.print("\033[C");                     //di game over e il punteggio
        }                                                   //a 5 cifre (max 99999 per evitare
        System.out.println("\033[7mTi sei schiantato!");    //problemi grafici)
        for(int i=0; i<(width/2)-5; i++){        
            System.out.print("\033[C");  
        }     
        System.out.printf("   Score: %05d   %n",score);
        for(int i=0; i<((lenght+4)/2)-2; i++){        
            System.out.print("\033[B");  
        } 
        for(int i=0; i<width/2; i++){        
            System.out.print("\033[D");  
        }     

    }

    //semplici metodi di get per ottenere le dimensioni di map

    public int getWidth(){
    return width;
    }

    public int getLenght(){
        return lenght;
    }

}

