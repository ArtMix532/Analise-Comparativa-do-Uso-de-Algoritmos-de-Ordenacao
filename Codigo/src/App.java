import dao.ProdutoDAO;
import model.Produto;
import sorts.HeapSort;
import sorts.MergeSort;
import sorts.QuickSort;
import sorts.RadixSort;

import java.util.ArrayList; // Importe o ArrayList
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<Produto> produtos = produtoDAO.listarTodos();

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto encontrado para ordenar.");
                return;
            }

            // --- Teste com MergeSort ---
            List<Produto> listaParaMerge = new ArrayList<>(produtos);
            MergeSort.resetarComparacoes();
            MergeSort.mergeSortComTempo(listaParaMerge);

            // --- Teste com HeapSort ---
            List<Produto> listaParaHeap = new ArrayList<>(produtos);
            HeapSort.resetarComparacoes();
            HeapSort.heapSort(listaParaHeap);

            // --- Teste com QuickSort ---
            List<Produto> listaParaQuick = new ArrayList<>(produtos);
            QuickSort.resetarComparacoes();
            QuickSort.quickSort(listaParaQuick);

            // --- Teste com RadixSort ---
            List<Produto> listaParaRadix = new ArrayList<>(produtos);
            RadixSort.resetarComparacoes();
            RadixSort.radixSort(listaParaRadix);

            // --- Imprime os resultados ---
            System.out.println("Número de comparações feitas (para " + produtos.size() + " produtos):");
            System.out.println("Merge Sort: " + MergeSort.getNumeroComparacoes());
            System.out.println("Tempo de execução: " + MergeSort.getTempoExecucao());
            System.out.println("Heap Sort: " + HeapSort.getNumeroComparacoes());
            System.out.println("Tempo de execução: " + HeapSort.getTempoExecucao());
            System.out.println("Quick Sort: " + QuickSort.getNumeroComparacoes());
            System.out.println("Tempo de execução: " + QuickSort.getTempoExecucao());
            System.out.println("Radix Sort: " + RadixSort.getNumeroComparacoes());
            System.out.println("Tempo de execução: " + RadixSort.getTempoExecucao());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}