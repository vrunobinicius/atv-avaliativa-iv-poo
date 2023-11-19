/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.poo.reader;

import br.edu.ifnmg.poo.credential.Credential;
import br.edu.ifnmg.poo.credential.CredentialDAO;
import br.edu.ifnmg.poo.repository.Dao;
import br.edu.ifnmg.poo.repository.DbConnection;
import br.edu.ifnmg.poo.role.Role;
import br.edu.ifnmg.poo.role.RoleDAO;
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
public class ReaderDAO extends Dao<Reader> {

    public static final String TABLE = "reader";

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
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Reader e) {
        try {
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getEmail());
            pstmt.setObject(3, e.getBirthdate(), java.sql.Types.DATE);

            if (e.getId() != null) {
                pstmt.setLong(8, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                + "where role.name = 'Leitor'";
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where ID = ?";
    }
    
    public List<Reader> getFindByRole() {

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
    public Reader extractObject(ResultSet resultSet) {
        Reader reader = null;
        try {
            reader = new Reader();
            reader.setId(resultSet.getLong("user.ID"));
            reader.setName(resultSet.getString("user.name"));
            reader.setEmail(resultSet.getString("user.email"));
            reader.setBirthdate(resultSet.getObject("user.birthdate", LocalDate.class));
            reader.setRole_ID(resultSet.getLong("user.role_ID"));

            reader.setCredential(new CredentialDAO().findById(reader.getId()));
            reader.setRole(new RoleDAO().findById(reader.getId()));

            reader.getCredential().setUser(reader);

        } catch (SQLException ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reader;
    }
}
