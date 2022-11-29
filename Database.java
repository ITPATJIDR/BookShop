import java.sql.*;

public class Database {

    private static final String DB_DRIVER = "org.sqlite.JDBC";
    //private static final String DB_URL = "jdbc:sqlite:D:/com lang/Java project/BookShop/DB/BookShop.db";
    private static final String DB_URL = "jdbc:sqlite:/Users/sasikarn/Documents/ProjectOop/BookShop/DB/Bookshop.db";
    Connection conn = null;

    public Database() {

        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            if (conn != null) {
                System.out.println("Connect to Database");
            } else {
                System.out.println("Connect to Database Fail");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    public void getUsers() {
        String sql = "SELECT * FROM user";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            
            System.out.println("Get user by ID");
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getUser(int userId){
        String sql = "SELECT * FROM user WHERE id = ?";
        
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setInt(1,userId);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("Get user by ID");
            while (rs.next()){
                System.out.println(rs.getInt("id") + "\t" + rs.getString("username") + "\t" + rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean checkUserExist(String username){
        String sql = "SELECT * FROM user WHERE username = ?";

        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void register(String username, String password){
        String sql = "INSERT INTO user(username,password) VALUES(?,?)";
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);
            System.out.println("Register...");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean Login(String username, String password){
        String sql = "SELECT * FROM user WHERE username = ?";
        String UsernameDB = "";
        String PasswordDB = "";
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                UsernameDB = rs.getString("username");
                PasswordDB = rs.getString("password");
            }

            if (username.equals(UsernameDB) && password.equals(PasswordDB)){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getId(String username){
        String sql = "SELECT user.id FROM user WHERE username = ? ";
        int ID_DB = 0;

        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            ID_DB = rs.getInt("id");

            return ID_DB;
        } catch (SQLException e) {
            e.getMessage();
            return -1;
        }
    }

    public void addCart(int BookId, int UserId){
        String sql = "INSERT INTO cart(BookId,UserId) VALUES(?,?)";
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setInt(1,BookId);
            pstmt.setInt(2,UserId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean getCart(int CartId){
        String sql = "SELECT cart.CartId, book.BookName, book.Price, book.Category from cart inner join book on cart.BookId = book.BookId where CartId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,CartId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                System.out.println("Item ID" + "\t" + "BookName" + "\t" + "Price" + "\t" + "Category");
                System.out.println(rs.getString("CartId") + "\t" + rs.getString("BookName") + "\t\t" + rs.getString("Price") + "\t" + rs.getString("Category") );
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    public boolean checkItemInCart(int BookId){
        String sql = "SELECT * FROM cart WHERE BookId = ? ";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, BookId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch( Exception e){
            e.getMessage();
            return false;
        }

    }

    public void getAllCart(int userId){
        String sql = "SELECT cart.CartId, book.BookName, book.Price, book.Category FROM cart INNER JOIN book ON cart.BookId=book.BookId Where userId = ?";
        
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setInt(1,userId);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("Item ID" + "\t" + "BookName" + "\t" + "Price" + "\t" + "Category");
            while (rs.next()){
                System.out.println(rs.getInt("CartId") + "\t" + rs.getString("BookName") + "\t\t" + rs.getInt("Price") + "\t" + rs.getString("Category"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void DeleteFromCart(int cartId){
        String sql = "DELETE FROM cart WHERE CartId = ?";
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setInt(1,cartId);
            System.out.println("Delete From Cart by cartId");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean getBook(int bookId){
        String sql = "SELECT * FROM book WHERE BookId = ?";
        
        try(PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setInt(1,bookId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                System.out.println("Item ID" + "\t" + "BookName" + "\t" + "Price" + "\t" + "Category");
                System.out.println(rs.getString("BookId") + "\t" + rs.getString("BookName") + "\t\t" + rs.getString("Price") + "\t" + rs.getString("Category") );
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void getAllBook() {
        String sql = "SELECT * FROM book";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            
            System.out.println("Item ID" + "\t" + "BookName" + "\t\t" + "Price" + "\t" + "Category");
            while (rs.next()) {
                System.out.println(rs.getInt("BookId") + "\t" +
                        rs.getString("BookName") + "\t\t" +
                        rs.getInt("Price") + "\t" +
                        rs.getString("Category"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
