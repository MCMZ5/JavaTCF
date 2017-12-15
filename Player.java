package endlessrunningtcf;

public class Player{

    //-----ATTRIBUTI------

    private String name;                    //nome del giocatore
    private Boolean crashed;                //bool se si è schiantato contro un ostacolo
    private Vector<Vector<Point>> map;      //mappa di gioco, rettangolo di punti
    private int width;                      //larghezza della mappa
    private int lenght;                     //altezza della mappa
    private Vector<Obstacle> obvect;        //vettore contenente tutti gli ostacoli

    //------METODI-------

    /**
     * - setta a falso la varibile crashed
     * - inizializza il vettore di ostacoli
     * - inizializza la mappa
     * - riempie la mappa di punti di coordinate 0,0 0,1 0,2 ...
     */
    public Player(){
        crushed = false;
        obvect = new Vector<Obstacle>();
        map = new Vector<Vector<Point>>();
        for(int i=0;i<width;i++){
            Vector<Point> r = new Vector<Point>();
            for(int j=0;j<lenght;j++){
                r.add(Point(i,j));
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

        UpdatePosition(this);
        for (Obstacle o : obvect) {
            UpdatePosition(o);
        }       
        for(int i=0;i<width;i++){
            Vector<Point> r=matrix.get(i);
            for(int j=0;j<lenght;j++){
                System.out.print(r.get(j).getChar()); 
            }
            System.out.println(); 
        }
    }
    private void CheckCrashed(){

    }
    /**
     * - disegna la mappa aggiornata
     * - inserisce (se necessario) un nuovo ostacolo nel vettore
     * - aspetta un tempo prefissato prima di rieseguirsi
     */
    public void Run(){
        int counter; //decide dopo quanti cicli mandare un nuovo ostacolo
        while(crashed == false){
            counter++;
            Draw();
            if(counter == 10){
                obvect.add(AddObstacle());
                counter = 0;
            }
            Thread.sleep(41); //41 millisecondi dovrebbero essere 1/24 di secondo
        }




        System.out.println("Run");
        crushed = true; 
    }

}