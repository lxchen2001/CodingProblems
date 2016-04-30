/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 CodeRevisited.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.codejam2016;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * User    : Suresh
 * Date    : 09/04/16
 * Version : v1
 */
public class CountingSheep {


    private static BufferedReader reader;
    private static StringTokenizer tokenizer;

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
            int i = nextInt();
            if (i == 0) {
                pw.println("Case #" + (t+1) + ": INSOMNIA");
            } else {
                int result = getSleepingNumber(i);
                pw.println("Case #" + (t+1) + ": " + result);
            }
        }

        reader.close();
        pw.close();

    }

    private static int getSleepingNumber(int i) {
        Set<Integer> set = new HashSet<>();
        int toObserve = i;
        while (set.size() != 10) {
            addDigitsInSet(toObserve, set);
            toObserve += i;
        }
        return toObserve - i;
    }

    private static void addDigitsInSet(int number, Set<Integer> set) {
        while (number != 0) {
            int lastDigit = number % 10;
            number = number / 10;
            set.add(lastDigit);
        }
    }


}