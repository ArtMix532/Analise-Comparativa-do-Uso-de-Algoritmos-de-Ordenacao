package sorts;

import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class MergeSort {
    private static long comps = 0;
    private static long elapsed;

    public static long getNumeroComparacoes() {
        return comps;
    }

    public static void resetarComparacoes() {
        comps = 0;
    }

    public static long getTempoExecucao() {
        return elapsed;
    }

    public static List<Produto> mergeSortComTempo(List<Produto> lista) {
        long start = System.currentTimeMillis();
        List<Produto> resultado = mergeSort(lista);
        elapsed = System.currentTimeMillis() - start;
        return resultado;
    }

    private static List<Produto> mergeSort(List<Produto> lista) {
        if (lista.size() <= 1)
            return lista;

        int meio = lista.size() / 2;
        List<Produto> esquerda = mergeSort(new ArrayList<>(lista.subList(0, meio)));
        List<Produto> direita = mergeSort(new ArrayList<>(lista.subList(meio, lista.size())));

        return merge(esquerda, direita);
    }

    private static List<Produto> merge(List<Produto> esq, List<Produto> dir) {
        List<Produto> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < esq.size() && j < dir.size()) {
            comps++;
            if (esq.get(i).getPreco().compareTo(dir.get(j).getPreco()) < 0) {
                resultado.add(esq.get(i++));
            } else {
                resultado.add(dir.get(j++));
            }
        }

        while (i < esq.size())
            resultado.add(esq.get(i++));
        while (j < dir.size())
            resultado.add(dir.get(j++));

        return resultado;
    }

}
