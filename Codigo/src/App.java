import dao.ProdutoDAO;
import model.Produto;
import sorts.HeapSort;
import sorts.MergeSort;
import sorts.QuickSort;
import sorts.RadixSort;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class App {

    // Método auxiliar agora focado em rodar o teste e medir a MEMÓRIA
    private static void testarAlgoritmo(String nomeAlgoritmo, List<Produto> produtosOriginais, Consumer<List<Produto>> algoritmo) {
        List<Produto> listaParaTeste = new ArrayList<>(produtosOriginais);
        Runtime runtime = Runtime.getRuntime();

        System.gc();

        long memoriaUsadaAntes = runtime.totalMemory() - runtime.freeMemory();

        // Executa o algoritmo (que deve cuidar de medir o próprio tempo e comparações)
        algoritmo.accept(listaParaTeste);

        long memoriaUsadaDepois = runtime.totalMemory() - runtime.freeMemory();
        long memoriaConsumida = memoriaUsadaDepois - memoriaUsadaAntes;
        double memoriaConsumidaEmMB = memoriaConsumida / (1024.0 * 1024.0);


        System.out.println("--- Resultados para " + nomeAlgoritmo + " ---");
        System.out.printf("Memória utilizada: %d bytes (%.4f MB)%n", memoriaConsumida, memoriaConsumidaEmMB);
    }

    public static void main(String[] args) {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<Produto> produtos = produtoDAO.listarTodos();

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto encontrado para ordenar.");
                return;
            }

            System.out.println("Iniciando testes de ordenação para " + produtos.size() + " produtos...\n");

            testarAlgoritmo("Merge Sort", produtos, (lista) -> {
                MergeSort.resetarComparacoes();
                MergeSort.mergeSortComTempo(lista);
            });
            System.out.println("Tempo de execução: " + MergeSort.getTempoExecucao());
            System.out.println("Número de comparações: " + MergeSort.getNumeroComparacoes() + "\n");


            testarAlgoritmo("Heap Sort", produtos, (lista) -> {
                HeapSort.resetarComparacoes();
                HeapSort.heapSort(lista);
            });
            System.out.println("Tempo de execução: " + HeapSort.getTempoExecucao());
            System.out.println("Número de comparações: " + HeapSort.getNumeroComparacoes() + "\n");


            testarAlgoritmo("Quick Sort", produtos, (lista) -> {
                QuickSort.resetarComparacoes();
                QuickSort.quickSort(lista);
            });
            System.out.println("Tempo de execução: " + QuickSort.getTempoExecucao());
            System.out.println("Número de comparações: " + QuickSort.getNumeroComparacoes() + "\n");


            testarAlgoritmo("Radix Sort", produtos, (lista) -> {
                RadixSort.resetarComparacoes();
                RadixSort.radixSort(lista);
            });
            System.out.println("Tempo de execução: " + RadixSort.getTempoExecucao());
            System.out.println("Número de comparações: " + RadixSort.getNumeroComparacoes() + "\n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}