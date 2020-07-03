import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DB db = new DB();
        try {
            db.getUsers();
            db.getItems1();
            db.getItems2();
            db.insertSQL();
            db.insertSQL2();
            db.writeRes("orders");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
