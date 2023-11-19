/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.poo.librarian;

import br.edu.ifnmg.poo.credential.Credential;
import br.edu.ifnmg.poo.credential.CredentialDAO;
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
public class LibrarianDAO extends Dao<Librarian> {

    public static final String TABLE = "librarian";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE
                + " (name, email, birthdate)"
                + " values (?, ?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE
                + " set name = ?, email = ?, birthdate = ?"
                + " where ID = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Librarian e) {
        try {
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getEmail());
            pstmt.setObject(3, e.getBirthdate(), java.sql.Types.DATE);

            if (e.getId() != null) {
                pstmt.setLong(8, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select ID, name, email, birthdate"
                + " from " + TABLE + " where ID = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select * from user "
                + "inner join credential on credential.ID = user.ID "
                + "inner join role on role.ID = user.ID "
                + "where role.name = 'Bibliotecário'";
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where ID = ?";
    }

    public List<Librarian> getFindByRole() {

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(getFindAllStatment())) {

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
    public Librarian extractObject(ResultSet resultSet) {
        Librarian librarian = null;
        try {
            librarian = new Librarian();
            librarian.setId(resultSet.getLong("user.ID"));
            librarian.setName(resultSet.getString("user.name"));
            librarian.setEmail(resultSet.getString("user.email"));
            librarian.setBirthdate(resultSet.getObject("user.birthdate", LocalDate.class));
            librarian.setRole_ID(resultSet.getLong("user.role_ID"));

            librarian.setCredential(new CredentialDAO().findById(librarian.getId()));
            librarian.setRole(new RoleDAO().findById(librarian.getId()));
           
            librarian.getCredential().setUser(librarian);

        } catch (SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return librarian;
    }

}
