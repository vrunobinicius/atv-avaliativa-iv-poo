/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.poo.avaliativaiv;

import br.edu.ifnmg.poo.credential.Credential;
import br.edu.ifnmg.poo.credential.CredentialDAO;
import br.edu.ifnmg.poo.librarian.Librarian;
import br.edu.ifnmg.poo.librarian.LibrarianDAO;
import br.edu.ifnmg.poo.reader.Reader;
import br.edu.ifnmg.poo.reader.ReaderDAO;
import br.edu.ifnmg.poo.role.Role;
import br.edu.ifnmg.poo.role.RoleDAO;
import br.edu.ifnmg.poo.user.User;
import br.edu.ifnmg.poo.user.UserDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */

/*
        2. n métodos estáticos individuais e invocados no método main() para contemplar cada um dos testes a
        seguir e considerando-se as notas do diagrama de classes UML indicado:
        2.1. método testeA(): inclusão de um usuário com papel “Bibliotecário” e respectiva credencial,
        atualização de seu email, recuperação deste usuário;
        2.2. método testeB(): repetição do passo 2.1 mas para o papel “Leitor”;
        2.3. método testeC(): repetição do passo 2.1 mas para o papel “Leitor”;
        2.4. método testeD(): recuperação de todos os bibliotecários;
        2.5. método testeE(): recuperação de todos os leitores;
        2.6. método testeF(): autenticação de cada um dos usuários do sistema.
 */
public class Testes {

    public static UserDAO userDao = new UserDAO();
    public static CredentialDAO credentialDao = new CredentialDAO();
    public static RoleDAO roleDao = new RoleDAO();

    public static void testeA() {
        //2.1. método testeA(): inclusão de um usuário com papel “Bibliotecário” e respectiva credencial, atualização de seu email, recuperação deste usuário;
        Role role1 = new Role();
        User user1 = new User();
        Credential credential1 = new Credential();

        role1.addUser(user1);
        user1.setRole(role1);
        user1.setCredential(credential1);
        credential1.setUser(user1);

        credential1.setEnabled(true);
        credential1.setLastAcces(LocalDate.of(2021, 2, 10));
        credential1.setPassword("bruno123");
        credential1.setUsername("bvan");
        try {
            user1.setBirthdate(LocalDate.of(2003, 01, 20));
            user1.setEmail("bvan@aluno.ifnmg.gov.br");
            user1.setName("Bruno Vinícius");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        role1.setName("Bibliotecário");

        roleDao.saveOrUpdate(role1);
        user1.setRole_ID(role1.getId());
        userDao.saveOrUpdate(user1);
        credential1.setUser_ID(user1.getId());
        credentialDao.saveOrUpdate(credential1);

        Credential c = credentialDao.findById(credential1.getId());
        System.out.println("Antes da atualização: ");
        System.out.println(">>> " + c);
        System.out.println(">>> " + c.getUser());
        System.out.println(">>> " + c.getUser().getRole());

        try {
            user1.setEmail("bvan@aluno.ifnmg.edu.br");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Depois da atualização: ");
        userDao.saveOrUpdate(user1);
        c = credentialDao.findById(credential1.getId());
        System.out.println(">>> " + c.getUser());
    }

    public static void testeB() {
        //2.2. método testeB(): repetição do passo 2.1 mas para o papel “Leitor”;
        Role role2 = new Role();
        User user2 = new User();
        Credential credential2 = new Credential();

        role2.addUser(user2);
        user2.setRole(role2);
        user2.setCredential(credential2);
        credential2.setUser(user2);

        credential2.setEnabled(true);
        credential2.setLastAcces(LocalDate.of(2021, 2, 10));
        credential2.setPassword("jose123");
        credential2.setUsername("josefino");
        try {
            user2.setBirthdate(LocalDate.of(2001, 8, 15));
            user2.setEmail("jose@fino.com");
            user2.setName("Jojo Phino");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        role2.setName("Leitor");

        roleDao.saveOrUpdate(role2);
        user2.setRole_ID(role2.getId());
        userDao.saveOrUpdate(user2);
        credential2.setUser_ID(user2.getId());
        credentialDao.saveOrUpdate(credential2);

        Credential c2 = credentialDao.findById(credential2.getId());
        System.out.println("Antes da atualização: ");
        System.out.println(">>> " + c2);
        System.out.println(">>> " + c2.getUser());
        System.out.println(">>> " + c2.getUser().getRole());

        try {
            user2.setEmail("jose@fino.com.br");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Depois da atualização: ");
        userDao.saveOrUpdate(user2);
        c2 = credentialDao.findById(credential2.getId());
        System.out.println(">>> " + c2.getUser());
    }

    public static void testeC() {
        //2.3. método testeC(): repetição do passo 2.1 mas para o papel “Leitor”;
        Role role3 = new Role();
        User user3 = new User();
        Credential credential3 = new Credential();

        role3.addUser(user3);
        user3.setRole(role3);
        user3.setCredential(credential3);
        credential3.setUser(user3);

        credential3.setEnabled(true);
        credential3.setLastAcces(LocalDate.of(2021, 2, 10));
        credential3.setPassword("jesus123");
        credential3.setUsername("jesus");
        try {
            user3.setBirthdate(LocalDate.of(2003, 01, 20));
            user3.setEmail("jesus@cristo.com");
            user3.setName("Jesus Cristo");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        role3.setName("Leitor");

        roleDao.saveOrUpdate(role3);
        user3.setRole_ID(role3.getId());
        userDao.saveOrUpdate(user3);
        credential3.setUser_ID(user3.getId());
        credentialDao.saveOrUpdate(credential3);

        Credential c3 = credentialDao.findById(credential3.getId());
        System.out.println("Antes da atualização: ");
        System.out.println(">>> " + c3);
        System.out.println(">>> " + c3.getUser());
        System.out.println(">>> " + c3.getUser().getRole());

        try {
            user3.setEmail("jesus@cristo.com.br");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Depois da atualização: ");
        userDao.saveOrUpdate(user3);
        c3 = credentialDao.findById(credential3.getId());
        System.out.println(">>> " + c3.getUser());
    }

    public static void testeD() {
        //2.4. método testeD(): recuperação de todos os bibliotecários;
        LibrarianDAO librarianDao = new LibrarianDAO();
        List<Librarian> c4 = librarianDao.getFindByRole();
        for (Librarian a : c4) {
            System.out.println(">>> " + a);
        }
    }

    public static void testeE() {
        //2.5. método testeE(): recuperação de todos os leitores;
        ReaderDAO readerDao = new ReaderDAO();
        List<Reader> c5 = readerDao.getFindByRole();
        for (Reader a : c5) {
            System.out.println(">>> " + a.getCredential());
        }
    }

    public static void testeF() {
        //2.6. método testeF(): autenticação de cada um dos usuários do sistema.
        List<Credential> allCredentials = credentialDao.findAll();
        for (Credential allC : allCredentials) {
            System.out.println("> " + credentialDao.authenticate(allC));
        }
    }

}
