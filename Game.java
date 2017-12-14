public class Game{
    public static void main(String[] args){

        Player p = new Player();
        
        while(p.getCrushed()==false){
            p.Run();
        }

        System.out.println("Gioco terminato!");


    }
}