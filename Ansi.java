public class Ansi{
    public static void main(String[] args){
        System.out.println("Test-Test-Test");
        System.out.print("\033[A");     //sposta il cursore in su di una riga
        System.out.print("\033[2K");    //cancella la riga corrente
        System.out.println("Prova");
    }
}