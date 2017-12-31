import java.lang.Thread;
import java.io.IOException;

public class TestThread{
    private static class T implements Runnable{
        public void run() {
            try{
                System.out.println("Passing");
                System.in.read();
            }
            catch(IOException e){
            }
            System.out.println("Passed");
        }
    }
    public static void main(String[] args)  throws InterruptedException{
        //CREO IL LISTENER
        Thread key = new Thread(new T());
        //AVVIO IL LISTENER
        key.start();
        //ADESSO HO DUE THREAD CONTEMPORANEI, PER CUI VIENE SCRITTO SIA
        //PASSING ALLA RIGA 8, SIA CONTINUED ALLA RIGA SOTTOSTANTE
        System.out.println("Continued");
        //DOPO 5 SECONDI, SE IL LISTENER NON HA ANCORA TERMINATO, PROSEGUE
        //IL MAIN THREAD E INTERROMPI IL LISTENER
        key.join(5000);
        if(key.isAlive()){
            key.stop();
        }
        //PROGRAMMA TERMINATO, ESCI
        System.out.println("Ended");
        //System.exit(0);
    }
}