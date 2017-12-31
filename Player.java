package endlessrunningtcf;

import java.util.Vector;
import java.io.IOException;

public class Player{

    //-----ATTRIBUTI------

    private String name;                        //nome del giocatore
    private Boolean crashed;                    //bool se si è schiantato contro un ostacolo        
    private int lenght=20;                          //larghezza della mappa
    private int width=130;                         //altezza della mappa
    private Vector<Vector<Point>> map;          //mappa di gioco, rettangolo di punti
    private Vector<Obstacle> obvect;            //vettore contenente tutti gli ostacoli
    private Character character;                //è il nostro personaggio, un derivato al pari degli ostacoli
                                                  //della classe astratta Object
    private GUI myGUI;
    private int excounter;


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
        for(int i=0;i<lenght;i++){                   //non posso usare un for each perché devo
            Vector<Point> r = new Vector<Point>();  //riempire con i numeri e perché map all'inizio
            for(int j=0;j<width;j++){              //è vuota
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
            character.UpdatePosition(map, 50);
            for (Obstacle o : obvect) {
                o.UpdatePosition(map, 50);
            }  
        }
        catch(ArrayIndexOutOfBoundsException exc){
            obvect.remove(obvect.size()-1);
            excounter++;
            if(excounter>1){
                crashed = true;
            }

        }
    }
    /**
     * - disegna tutti i (char dei) punti della mappa 
     */
    private void Draw(){

        for(int i=0; i<lenght; i++){        //|
            System.out.print("\033[A");  
        }                                   //|
        myGUI.inizialize();
     
        for (Vector<Point> v : map) {
            System.out.print("|");
            String r = new String();
            for (Point p : v) {
                char[] c = {p.getChar()};
                r.concat(new String(c));
                System.out.print(p.getChar()); 
                if(p.isObstacle()==true && p.isCharacter()==true){
                    crashed = true;
                }
            }
            System.out.print("|");
            System.out.println();
            myGUI.println("Check");//r);
        }
        myGUI.show();
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
    public static class Key implements Runnable{
        Character character;
        Key(Character character_){
            character = character_;
        }
        public void run() {
            try{
                //System.out.println("Passing");
                System.in.read();
                System.out.print("\033[A");
                if(character.getIntPosY()==0){
                    character.setSpeed(14.);
                }
            }
            catch(IOException e){
            }
            //System.out.println("Passed");
        }
    }
    public void Run(){
        myGUI = new GUI();
        myGUI.create(lenght,width);
        int counter = 0; //decide dopo quanti cicli mandare un nuovo ostacolo
        int score = 0;
        while(crashed == false){
            Update();
            //CheckCrashed();
            Draw();
            Thread key = new Thread(new Key(character));
            counter++;
            score++;
            if(counter == 50){
                obvect.add(ObstacleFactory.NewObstacle(map));
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
            // try{
            //     Thread.sleep(50); //41 millisecondi dovrebbero essere 1/24 di secondo
            //     throw new InterruptedException();
         
            // }
            // catch(InterruptedException e){

            // }
        }
        for(int i=0; i<((lenght+4)/2)-1; i++){        //|
            System.out.print("\033[A");  
        } 
        for(int i=0; i<(width/2)-5; i++){        //|
            System.out.print("\033[C");  
        }     
        System.out.println("\033[7mTi sei schiantato! "+excounter);
        for(int i=0; i<(width/2)-5; i++){        //|
            System.out.print("\033[C");  
        }     
        System.out.printf("   Score: %05d   %n",score);
        for(int i=0; i<((lenght+4)/2)-2; i++){        //|
            System.out.print("\033[B");  
        } 
        for(int i=0; i<width/2; i++){        //|
            System.out.print("\033[D");  
        }     

    }

    public int getWidth(){
    return width;
    }

    public int getLenght(){
        return lenght;
    }

}

