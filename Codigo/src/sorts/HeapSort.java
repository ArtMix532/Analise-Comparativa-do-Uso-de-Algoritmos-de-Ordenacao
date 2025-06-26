package sorts;

import java.util.Collections;
import java.util.List;

import model.Produto;

public class HeapSort {
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

    public static void heapSort(List<Produto> lista) {
        int n = lista.size();
        long start = System.currentTimeMillis();

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(lista, n, i);

        for (int i = n - 1; i >= 0; i--) {
            Collections.swap(lista, 0, i);
            heapify(lista, i, 0);
        }
        elapsed = System.currentTimeMillis() - start;
    }

    private static void heapify(List<Produto> lista, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && lista.get(esq).getPreco().compareTo(lista.get(maior).getPreco()) > 0) {
            comps++;
            maior = esq;
        } else if (esq < n) {
            comps++;
        }

        if (dir < n && lista.get(dir).getPreco().compareTo(lista.get(maior).getPreco()) > 0) {
            comps++;
            maior = dir;
        } else if (dir < n) {
            comps++;
        }

        if (maior != i) {
            Collections.swap(lista, i, maior);

            heapify(lista, n, maior);
        }
    }

}
