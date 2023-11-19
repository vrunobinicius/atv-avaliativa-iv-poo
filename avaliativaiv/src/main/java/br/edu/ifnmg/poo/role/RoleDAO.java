/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.poo.role;

import br.edu.ifnmg.poo.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class RoleDAO extends Dao<Role> {

    public static final String TABLE = "role";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE
                + " (name)"
                + " values (?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE
                + " set name = ?"
                + " where ID = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Role e) {
        try {

            pstmt.setString(1, e.getName());

            if (e.getId() != null) {
                pstmt.setLong(2, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select ID, name"
                + " from " + TABLE + " where ID = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select ID, name"
                + " from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where ID = ?";
    }

    @Override
    public Role extractObject(ResultSet resultSet) {
        Role role = null;
        try {
            role = new Role();
            role.setId(resultSet.getLong("ID"));
            role.setName(resultSet.getString("name"));
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return role;
    }
}
