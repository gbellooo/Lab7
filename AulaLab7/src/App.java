import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        // Testando DAO de Conta
        DAO<Conta> daoConta = new DaoConta();
        Conta c1 = new Conta(300, new BigDecimal(9000));
        daoConta.create(c1);
        System.out.println(daoConta.buscaTudo());

        // Testando DAO de Cliente
        DAO<Cliente> daoCliente = new DaoCliente();
        Cliente cliente = new Cliente(1, "Gabriel Bello", "gabriel@example.com", "99999-9999");
        daoCliente.create(cliente);
        System.out.println(daoCliente.buscaTudo());
    }
}