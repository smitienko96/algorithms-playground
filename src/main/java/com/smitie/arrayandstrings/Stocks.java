package com.smitie.arrayandstrings;

import java.util.Arrays;

public class Stocks {

    public static void main(String[] args) {
        int[] prices = {200, 500, 1000, 700, 30, 400, 900, 400, 550};
//        System.out.println(maxProfitOneTransaction(prices));
        System.out.println(maxProfitTwoTransactions(prices));
//        System.out.println(maxProfitInUnlimitedTransactions(prices));
        System.out.println(maxProfitInKTransactions(prices, 2));


    }

    private Stocks() {
        throw new AssertionError("Cannot be instantiated.");
    }

    /**
     * One transaction, O(n)
     *
     * @return max profit
     */
    public static int maxProfitOneTransaction(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        int result = 0;

        for (int price : prices) {
            result = Math.max(result, price - min);
            min = Math.min(min, price);
        }

        return result;
    }

    /**
     * two transactions, O(n)
     *
     * @param prices input array of stock prices
     * @return max profit with 2 transactions
     */
    public static int maxProfitTwoTransactions(
            int[] prices
    ) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        System.out.println(Arrays.toString(prices));


        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        left[0] = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        System.out.println(Arrays.toString(left));

        int max = prices[prices.length - 1];
        right[prices.length - 1] = 0;

        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        System.out.println(Arrays.toString(right));

        int result = 0;

        for (int i = 0; i < prices.length; i++) {
            result = Math.max(result, left[i] + right[i]);
        }

        return result;
    }

    public static int maxProfitInUnlimitedTransactions(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int result = 0;

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                result += diff;
            }
        }
        return result;
    }

    /**
     * K transactions, O(kn)
     *
     * @param prices
     * @param k
     * @return
     */
    public static int maxProfitInKTransactions(int[] prices, int k) {

        if (prices == null || prices.length <= 1 || k <= 0) {
            return 0;
        }

        int[] temp = new int[k + 1];
        int[] result = new int[k + 1];

        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int p = k; p >= 1; p--) {
                temp[p] = Math.max(result[p - 1] + Math.max(diff, 0), temp[p] + diff);
                result[p] = Math.max(temp[p], result[p]);
            }
        }
        return result[k];
    }

}
