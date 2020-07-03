import javax.xml.transform.sax.SAXResult;
import java.sql.*;

public class DB {
    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "module_4";
    private final String LOGIN = "mysql";
    private final String PASS = "mysql";

    private Connection dbConn;

    private Connection getDbConn() throws SQLException, ClassNotFoundException {
        String str = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConn = DriverManager.getConnection(str, LOGIN, PASS);
        return dbConn;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbConn = getDbConn();
        System.out.println(dbConn.isValid(1000));
    }

    static String user_id;
    static String items_id_1;
    static String items_id_2;

    public void getUsers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT `login` FROM `users` WHERE `login` = 'john'";
        Statement st = getDbConn().createStatement();
        ResultSet res_1 = st.executeQuery(sql);
        while (res_1.next()) {
            user_id = res_1.getString("login");
        }
    }

    public void getItems1() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `items` WHERE `category` = 'hats' AND `id` = 2 ";
        Statement st = getDbConn().createStatement();
        ResultSet res_2 = st.executeQuery(sql);
        while (res_2.next()) {
            items_id_1 = res_2.getString("title");

        }
    }
    public void getItems2() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `items` WHERE `category` = 'hats' AND `id` = 3 ";
        Statement st = getDbConn().createStatement();
        ResultSet res_2 = st.executeQuery(sql);
        while (res_2.next()) {
            items_id_2= res_2.getString("title");

        }
    }

    public void insertSQL() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `orders` (user_id, items_id) VALUES (?,?)";
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.setString(1, user_id);
        prSt.setString(2, items_id_1);
        prSt.executeUpdate();
    }
    public void insertSQL2() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `orders` (user_id, items_id) VALUES (?,?)";
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.setString(1, user_id);
        prSt.setString(2, items_id_2);
        prSt.executeUpdate();
    }

    public void writeRes(String table) throws SQLException, ClassNotFoundException {
        System.out.println("Все заказы: ");
        String sql = "SELECT * FROM " + table;
        Statement statement = getDbConn().createStatement();
        ResultSet res = statement.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString("user_id") + " - " + res.getString("items_id"));
        }
    }

}
