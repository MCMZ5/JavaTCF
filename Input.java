import java.io.IOException;

public class Input{

    public static void main(String[] args){
        try{
            System.in.read();
        }
        catch(IOException e){

        }
        System.out.println("Funziona!");
    }
}