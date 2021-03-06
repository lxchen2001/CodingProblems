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
 *
 */

package com.coderevisited.arrays.rotation;

/**
 * http://www.cs.bell-labs.com/cm/cs/pearls/s02b.pdf
 */
public class ArrayRotationReversal
{
    public static void main(String[] args)
    {

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rotateArray(array, 4);
        System.out.println("contents after rotation : ");
        for (int i : array) {
            System.out.print(i + " ");
        }

    }

    private static void rotateArray(int[] array, int d)
    {
        reverse(array, 0, d - 1);
        reverse(array, d, array.length - 1);
        reverse(array, 0, array.length - 1);
    }


    private static void reverse(int[] array, int start, int end)
    {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;

        }
    }
}
