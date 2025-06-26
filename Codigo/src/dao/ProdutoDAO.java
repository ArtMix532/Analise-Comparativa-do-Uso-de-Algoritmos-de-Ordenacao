package dao;

import database.Conexao;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public List<Produto> listarTodos() throws Exception {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreco(rs.getBigDecimal("preco"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                p.setAtivo(rs.getBoolean("ativo"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                lista.add(p);
            }
        }

        return lista;
    }
}
