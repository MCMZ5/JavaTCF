package endlessrunningtcf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI{

    private Frame window;
    private TextArea display;
    private Label instructions;
    private Label statusLabel;


    public void create(int height,int width){
        window = new Frame("PHISYCS RUN");
        window.setSize(1000, 400);
        window.setLayout(new BorderLayout());
        window.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	    });
        instructions = new Label();
        instructions.setText("premere ENTER per saltare");
        statusLabel = new Label();
        display = new TextArea(height,width);
        window.add(instructions, BorderLayout.SOUTH);
        window.add(display, BorderLayout.CENTER);
        window.add(statusLabel, BorderLayout.CENTER);
        window.setVisible(true);
    }

    public void show(){
        display.setText(display.getText());
    }

    public boolean listen(){
        boolean keyPressed = false;
        return keyPressed;
    }

    public void print(String line){
        display.append(line);
    }

    public void println(String line){
        display.append(line+"\n");
    }

    public void inizialize(){
        int height = display.getRows();
        int width = display.getColumns();
        display = new TextArea(null,height,width);
    }
}