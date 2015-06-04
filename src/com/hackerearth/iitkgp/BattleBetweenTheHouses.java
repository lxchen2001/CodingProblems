package com.hackerearth.iitkgp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Author : Suresh
 * Date : 04/06/15.
 */
public class BattleBetweenTheHouses {

    private static BufferedReader reader;
    private static StringTokenizer tokenizer;
    private static int MAX = 1000000000;
    private static int[] _array;
    static {
        int size = (int) Math.sqrt(MAX*2 - 1);
        _array = new int[size];

        for (int i = 1; i < size; i++) {
            _array[i] = (i*(i+1))/2;
        }
    }

    private static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    private static int nextInt() throws IOException {
        return parseInt(next());
    }

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));

        int T = nextInt();
        for (int t = 0; t < T; t++) {
            int D = nextInt();
            int fit = binarySearchBestFit(_array, 0, _array.length, D);
            if(fit % 3 == 0){
                pw.println("SL");
            }else if(fit % 3 == 1){
                pw.println("LB");
            }else if(fit % 3 == 2){
                pw.println("BS");
            }
        }




        reader.close();
        pw.close();
    }

    public static int binarySearchBestFit(int[] array, int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == key)
                return mid;
            else if (array[mid] > key) {
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return low;
    }

}
