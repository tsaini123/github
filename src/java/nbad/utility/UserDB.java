package nbad.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nbad.business.User;

public class UserDB {

    public static User getUser(String email, String password, boolean isValidating) {
        ConnectionPool pool = ConnectionPool.getInstance();

        Connection connection = pool.getConnection();
        System.err.println("COnnection:--------------" + connection);
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//            // get a connection
//            String dbURL = "jdbc:mysql://localhost:3306/murach";
//            String username = "root";
//            String password = "root";
//            connection = DriverManager.getConnection(dbURL, username, password);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE Email = ? And Password= ?";
        if (!isValidating) {
            query = "SELECT * FROM User "
                    + "WHERE Email = ?";
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            if (isValidating) {
                ps.setString(2, password);
            }
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setType(rs.getString("Type"));
                user.setNumParticipation(rs.getInt("ParticipationCount"));
                user.setNumOfTotalParticipants(rs.getInt("TotalNumOfParticipants"));
                user.setNumCoins(rs.getInt("ParticipationCount") + rs.getInt("TotalNumOfParticipants"));
                user.setNumPostedStudies(0);
            }

            if (user != null) {
                query = "SELECT count(S.Studycode) FROM Study S where creatorEmail=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user.setNumPostedStudies(rs.getInt(1));
                }
            }

            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int addUser(User user, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO User (Name,Email, Password, type) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, password);
            ps.setString(4, user.getType());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public ArrayList<User> getUsers() {
        return null;
    }

}
