import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private final String[] COLUMNS = {"Name", "Nationality"};
//    private Collection<User> users;
    private List<User>list;

    public UserTableModel(Collection<User> users) {
//        this.users = users;

        List<User> list = new ArrayList(users);
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch(columnIndex){
            case 0 -> list.get(rowIndex).getName();
            case 1 -> list.get(rowIndex).getNationality();
            default -> "-";
        };

        }


    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        } else {
            return Object.class;
        }
    }

}
