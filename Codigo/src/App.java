import dao.ProdutoDAO;
import model.Produto;
import sorts.MergeSort;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO(); 
            List<Produto> produtos = produtoDAO.listarTodos();
            MergeSort.mergeSort(produtos); 



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
