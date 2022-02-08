import javax.swing.*;

public class Main {

    public static void main (String argv[]){
        MainForm form = new MainForm();
        form.pack();
        form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        form.setVisible(true);

    }
}
