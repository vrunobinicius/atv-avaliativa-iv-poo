/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.poo.credential;

import br.edu.ifnmg.poo.repository.Dao;
import br.edu.ifnmg.poo.repository.DbConnection;
import br.edu.ifnmg.poo.role.Role;
import br.edu.ifnmg.poo.role.RoleDAO;
import br.edu.ifnmg.poo.user.User;
import br.edu.ifnmg.poo.user.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class CredentialDAO extends Dao<Credential> {

    public static final String TABLE = "credential";
    private static String SALT = "lasdf";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE
                + " (username, password, lastAccess, enabled, user_ID)"
                + " values (?, MD5(?), ?, ?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE
                + " set username = ?, password = MD5(?), lastAccess = ?, anabled = ?, user_ID = ?"
                + " where ID = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Credential e) {
        try {
            pstmt.setString(1, e.getUsername());
            String password = e.getPassword() + SALT;
            pstmt.setString(2, password);
            pstmt.setObject(3, e.getLastAcces(), java.sql.Types.DATE);
            pstmt.setBoolean(4, e.isEnabled());
            pstmt.setLong(5, e.getUser_ID());

            if (e.getId() != null) {
                pstmt.setLong(6, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CredentialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select ID, username, password, lastAccess, enabled, user_ID"
                + " from " + TABLE + " where ID = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select ID, username, password, lastAccess, enabled, user_ID"
                + " from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where ID = ?";
    }

    public List<Credential> getFindByRole(String name) {

        final String SQL = "select *"
                + " from " + TABLE
                + " inner join " + RoleDAO.TABLE
                + " on credential.ID = role.ID"
                + " where name = " + "'" + name + "'";

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(SQL)) {

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object
            return extractObjects(resultSet);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public Credential extractObject(ResultSet resultSet) {
        Credential credential = null;
        try {
            credential = new Credential();
            credential.setId(resultSet.getLong("ID"));
            credential.setUsername(resultSet.getString("username"));
            credential.setPassword(resultSet.getString("password"));
            credential.setLastAcces(resultSet.getObject("lastAccess", LocalDate.class));
            credential.setEnabled(resultSet.getBoolean("enabled"));
            credential.setUser_ID(resultSet.getLong("user_ID"));

            UserDAO userDao = new UserDAO();
            User user = userDao.findById(credential.getId());
            credential.setUser(user);
            user.setCredential(credential);

        } catch (SQLException ex) {
            Logger.getLogger(CredentialDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CredentialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return credential;
    }

    public User authenticate(Credential credential) {
        Credential c = this.findById(credential.getId());
        if (c.getUser() != null) {
            return c.getUser();
        }

        return null;
    }

}
