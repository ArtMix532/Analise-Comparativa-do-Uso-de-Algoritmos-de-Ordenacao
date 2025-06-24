import dao.ProdutoDAO;
import model.Produto;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            Produto produto = new Produto();
            produto.setNome("Notebook");
            produto.setDescricao("Notebook gamer com 16GB RAM");
            produto.setCategoria("Informática");
            produto.setPreco(new BigDecimal("4500.00"));
            produto.setQuantidadeEstoque(10);
            produto.setAtivo(true);

            ProdutoDAO dao = new ProdutoDAO();
            dao.inserir(produto);
            System.out.println("Produto inserido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
