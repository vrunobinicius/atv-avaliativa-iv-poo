/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.poo.role;

import br.edu.ifnmg.poo.entity.Entity;
import br.edu.ifnmg.poo.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class Role extends Entity {

    private String name;
    private List<User> users;

    public Role() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + '}';
    }
}
