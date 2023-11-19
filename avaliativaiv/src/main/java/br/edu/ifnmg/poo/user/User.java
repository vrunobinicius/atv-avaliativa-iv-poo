package br.edu.ifnmg.poo.user;

import br.edu.ifnmg.poo.credential.Credential;
import br.edu.ifnmg.poo.entity.Entity;
import br.edu.ifnmg.poo.role.Role;
import java.time.LocalDate;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class User extends Entity {

    private String name;
    private String email;
    private LocalDate birthdate;
    private Credential credential;
    private Role role;
    private Long role_ID;

    public User() {
    }

    public Long getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(Long role_ID) {
        this.role_ID = role_ID;
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERS/SETTTERS">
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() > 255 || name == null) {
            throw new Exception();
        } else {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email.length() > 255 || email == null) {
            throw new Exception();
        } else {
            this.email = email;
        }
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) throws Exception {
        if (birthdate == null) {
            throw new Exception();
        } else {
            this.birthdate = birthdate;
        }
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + ", birthdate=" 
                + birthdate + ", role=" + role + ", role_ID=" + role_ID + '}';
    }

    

}
