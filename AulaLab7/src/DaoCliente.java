import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente extends DAO<Cliente> {

    @Override
    public Cliente create(Cliente cliente) {
        try {
            init();
            String sql = "INSERT INTO CLIENTES (id, nome, email, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

    @Override
    public Cliente busca(int id) {
        Cliente cliente = null;
        try {
            init();
            String sql = "SELECT * FROM CLIENTES WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

    @Override
    public void delete(int id) {
        try {
            init();
            String sql = "DELETE FROM CLIENTES WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Cliente> buscaTudo() {
        List<Cliente> lista = new ArrayList<>();
        try {
            init();
            String sql = "SELECT * FROM CLIENTES";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}