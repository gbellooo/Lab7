import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    private String url = "jdbc:postgresql://db.sauhxzvckiggmowoolfm.supabase.co:5432/postgres";
    private String username = "postgres";
    private String password = "mackenzie";
    protected Connection con;

    protected void init() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Registra o driver manualmente
            this.con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado!");
        }
    }    

    public abstract T create(T obj);
    public abstract T busca(int id);
    public abstract void delete(int id);
    public abstract List<T> buscaTudo();
}