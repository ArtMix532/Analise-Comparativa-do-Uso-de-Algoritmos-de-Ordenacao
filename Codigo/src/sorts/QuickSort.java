package sorts;

import java.util.Collections;
import java.util.List;

import model.Produto;

public class QuickSort {
    private static long comps = 0;

    public static long getNumeroComparacoes() {
        return comps;
    }

    public static void resetarComparacoes() {
        comps = 0; 
    }

    public static void quickSort(List<Produto> lista, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(lista, inicio, fim);
            quickSort(lista, inicio, p - 1);
            quickSort(lista, p + 1, fim);
        }
    }

    private static int particionar(List<Produto> lista, int inicio, int fim) {
        var pivo = lista.get(fim).getPreco();
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            comps++;
            if (lista.get(j).getPreco().compareTo(pivo) < 0) {
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, fim);
        return i + 1;
    }

}
