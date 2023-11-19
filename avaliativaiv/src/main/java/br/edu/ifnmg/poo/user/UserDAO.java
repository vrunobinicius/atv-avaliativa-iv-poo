package br.edu.ifnmg.poo.user;

import br.edu.ifnmg.poo.credential.Credential;
import br.edu.ifnmg.poo.credential.CredentialDAO;
import br.edu.ifnmg.poo.repository.Dao;
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
public class UserDAO extends Dao<User> {

    public static final String TABLE = "user";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE
                + " (name, email, birthdate, role_ID)"
                + " values (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE
                + " set name = ?, email = ?, birthdate = ?, role_ID = ?"
                + " where ID = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, User e) {
        try {
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getEmail());
            pstmt.setObject(3, e.getBirthdate(), java.sql.Types.DATE);
            pstmt.setLong(4, e.getRole_ID());

            if (e.getId() != null) {
                pstmt.setLong(5, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select ID, name, email, birthdate, role_ID"
                + " from " + TABLE + " where ID = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select ID, name, email, birthdate, role_ID"
                + " from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where ID = ?";
    }

    @Override
    public User extractObject(ResultSet resultSet) {
        User user = null;
        try {
            user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setBirthdate(resultSet.getObject("birthdate", LocalDate.class));
            user.setRole_ID(resultSet.getLong("role_ID"));

            RoleDAO roleDao = new RoleDAO();
            Role role = roleDao.findById(user.getId());
            user.setRole(role);

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
}
