package sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Produto;

public class RadixSort {
    private static long comps = 0;

    public static void resetarComparacoes() {
        comps = 0;
    }

    public static long getNumeroComparacoes() {
        return comps;
    }

    public static void radixSort(List<Produto> lista) {
        int n = lista.size();
        if (n == 0) return; 

        int[] precos = new int[n];
        Map<Integer, List<Produto>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            // Converte o preço para um inteiro (ex: R$ 35,50 -> 3550)
            int p = lista.get(i).getPreco().multiply(new java.math.BigDecimal(100)).intValue();
            precos[i] = p;
            map.computeIfAbsent(p, k -> new ArrayList<>()).add(lista.get(i));
        }

        radixSortPrecos(precos);

        lista.clear();
        for (int p : precos) {
            lista.add(map.get(p).remove(0));
        }
    }

    private static void radixSortPrecos(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        
        // Adiciona as N-1 comparações feitas pelo .max()
        if (arr.length > 1) {
            comps += arr.length - 1;
        }


        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int j : arr) {
            count[(j / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}