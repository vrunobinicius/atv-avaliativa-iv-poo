package br.edu.ifnmg.poo.credential;

import br.edu.ifnmg.poo.entity.Entity;
import br.edu.ifnmg.poo.user.User;
import java.time.LocalDate;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class Credential extends Entity {

    private String username;
    private String password;
    private LocalDate lastAcces;
    private boolean enabled;
    private User user;
    private Long user_ID;

    public Credential() {
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERS/SETTERS">
    public Long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Long user_ID) {
        this.user_ID = user_ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastAcces() {
        return lastAcces;
    }

    public void setLastAcces(LocalDate lastAcces) {
        this.lastAcces = lastAcces;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        //this.user_ID = user.getId();
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Credential{" + "username=" + username + ", password=" + password 
                + ", lastAcces=" + lastAcces + ", enabled=" + enabled 
                + ", user=" + user + ", user_ID=" + user_ID + '}';
    }

    

}
