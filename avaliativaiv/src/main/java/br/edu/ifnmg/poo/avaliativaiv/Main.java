package br.edu.ifnmg.poo.avaliativaiv;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("""
                           
                           TESTE A: 2.1. m\u00e9todo testeA(): inclus\u00e3o de um usu\u00e1rio com papel \u201cBibliotec\u00e1rio\u201d e respectiva credencial,
                                   atualiza\u00e7\u00e3o de seu email, recupera\u00e7\u00e3o deste usu\u00e1rio;
                           """);
        Testes.testeA();

        System.out.println("\nTESTE B: 2.2. método testeB(): repetição do passo 2.1 mas para o papel “Leitor”;\n");
        Testes.testeB();

        System.out.println("\nTESTE C: 2.3. método testeC(): repetição do passo 2.1 mas para o papel “Leitor”;\n");
        Testes.testeC();

        System.out.println("\nTESTE D: 2.4. método testeD(): recuperação de todos os bibliotecários;\n");
        Testes.testeD();

        System.out.println("\nTESTE E: 2.5. método testeE(): recuperação de todos os leitores;\n");
        Testes.testeE();

        System.out.println("\nTESTE F: 2.6. método testeF(): autenticação de cada um dos usuários do sistema.\n");
        Testes.testeF();

        System.out.println("\n");
    }
}
