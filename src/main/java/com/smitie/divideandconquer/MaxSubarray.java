package com.smitie.divideandconquer;

import static java.lang.Integer.MIN_VALUE;

public class MaxSubarray {

    public void execute(int[] array) {

        int mid = array.length / 2;


    }

    private void maxCrossingSum(int[] array, int low, int mid, int high) {
        int leftMaxSum = MIN_VALUE;
        int rightMaxSum = MIN_VALUE;

        int maxLeftIndex = 0;
        int maxRightIndex = 0;

        int leftSum = 0;
        int rightSum = 0;

        for (int i = mid; i >= low; i--) {
            leftSum = array[i] + leftSum;
            if (leftSum > leftMaxSum){
                leftMaxSum = leftSum;
                maxLeftIndex = i;
            }
        }

        for (int i = mid + 1; i <= low; i++) {
            rightSum = array[i] + rightSum;
            if (rightSum > rightMaxSum){
                rightMaxSum = leftSum;
                maxRightIndex = i;
            }
        }
    }

    static class Result {

        int leftIndex;
        int rightIndex;
        int sum;

        public Result(int leftIndex, int rightIndex, int sum) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            this.sum = sum;
        }

    }

}


