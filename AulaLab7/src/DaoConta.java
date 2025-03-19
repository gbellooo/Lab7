import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoConta extends DAO<Conta> {

    @Override
    public Conta create(Conta conta) {
        try {
            init();
            String sql = "INSERT INTO CONTAS (numero, saldo) VALUES (?, ?)";
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            pstmt.setInt(1, conta.getNumero());
            pstmt.setBigDecimal(2, conta.getSaldo());
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
        return conta;
    }

    @Override
    public Conta busca(int numero) {
        Conta conta = null;
        try {
            init();
            String sql = "SELECT * FROM CONTAS WHERE numero = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, numero);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                conta = new Conta(rs.getInt("numero"), rs.getBigDecimal("saldo"));
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
        return conta;
    }

    @Override
    public void delete(int numero) {
        try {
            init();
            String sql = "DELETE FROM CONTAS WHERE numero = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, numero);
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
    public List<Conta> buscaTudo() {
        List<Conta> lista = new ArrayList<>();
        try {
            init();
            String sql = "SELECT * FROM CONTAS";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Conta(rs.getInt("numero"), rs.getBigDecimal("saldo")));
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