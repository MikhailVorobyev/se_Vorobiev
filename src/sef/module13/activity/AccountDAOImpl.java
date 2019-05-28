package sef.module13.activity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    @SuppressWarnings("unused")
    private Connection conn;

    public AccountDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Account> findAccount(String firstName, String lastName)
            throws AccountDAOException {
        List<Account> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("" +
                "SELECT * FROM ACCOUNT  WHERE FIRST_NAME LIKE ? AND LAST_NAME LIKE ?")){
            ps.setString(1, firstName + "%");
            ps.setString(2, lastName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new AccountImpl(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_NAME, e);
        }
        return result;
    }

    public Account findAccount(int id) throws AccountDAOException {
        Account result = null;
        try (PreparedStatement ps = conn.prepareStatement("" +
                "SELECT * FROM ACCOUNT  WHERE ID = ?")){
            ps.setString(1, Integer.toString(id));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = new AccountImpl(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, e);
        }
        return result;
    }


    public boolean insertAccount(String firstName, String lastName, String email)
            throws AccountDAOException {
        int result = -1;
        try {

            PreparedStatement statement = conn.prepareStatement("" +
                    "INSERT INTO ACCOUNT (ID, FIRST_NAME, LAST_NAME, E_MAIL) VALUES (ACCOUNT_SEQ.nextVal, ?, ?, ?)");

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);

            result = statement.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, e1);
            }
        }
        return result == 1;
    }
}
