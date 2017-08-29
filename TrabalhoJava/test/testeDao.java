
import dao.ClienteDao;
import entidades.Cliente;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elida
 */
public class testeDao {
     public static void main(String[] args) throws SQLException {
        // pronto para gravar
        // grave nessa conexão!!!
        ClienteDao dao = new ClienteDao();
        // método elegante
        System.out.println("Gravado!");
        Cliente outroC = dao.obterCliente(1);
        System.out.println(outroC.getNome());
        dao.excluir(outroC);
        
        
        
    }
}
