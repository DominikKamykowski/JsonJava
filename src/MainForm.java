import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.util.Collection;
import java.util.Vector;

public class MainForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonADD;
    private JButton refreshButton;
    private JTable userTabel;
    private JButton removeButton;


    public MainForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonADD);

        buttonADD.addActionListener(e -> onOK());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onExit(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);



        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonPersonRepository repo = new JsonPersonRepository();
                Collection<User> users = repo.GetAll();

                UserTableModel model = new UserTableModel(users);
                userTabel.setModel(model);
                userTabel.setAutoCreateRowSorter(true);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(userTabel.getSelectedRow());
                JsonPersonRepository repo = new JsonPersonRepository();
                Collection<User> users  = repo.GetAll();

//                UserTableModel model = new UserTableModel(users);
//                userTabel.setModel(model);
//                userTabel.setAutoCreateRowSorter(true);

                System.out.println(userTabel.getSelectedRow());
                int id = userTabel.getSelectedRow();

                repo.Read(userTabel.getSelectedRow());

            }
        });
    }

    private void onOK() {
        AddUser addUser = new AddUser();
        addUser.pack();
        addUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addUser.setVisible(true);

    }

    private void onExit() {
        // add your code here if necessary
        System.exit(0);
    }

    public static void main(String[] args) {
        MainForm dialog = new MainForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
