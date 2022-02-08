import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddUser extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtfName;
    private JTextField txtfNationality;

    IUserRepository repo = new JsonPersonRepository();

    public AddUser() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            boolean isDataIn = true;
            if(txtfName.getText().equals("")&&txtfNationality.getText().equals("")){
                System.out.println("Null value");
                isDataIn = false;
            }

            if(isDataIn){
                int id = repo.GetLastID();
                String name = txtfName.getText();
                String nationality = txtfNationality.getText();

                if(!repo.CheckId(id)){
                    User user = new User(id,name,nationality);
                    repo.Add(user);

                }else{
                    // TODO create new dialog if user exist.
                    System.out.println("User ID is already taken!");
                }

            }
            }catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddUser dialog = new AddUser();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
