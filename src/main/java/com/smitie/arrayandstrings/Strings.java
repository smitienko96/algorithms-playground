package com.smitie.arrayandstrings;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Strings {


    public static void main(String[] args) {

        String test = "dada";
//        System.out.println(isUnique0(test));
//        System.out.println(isUnique1(test));
//        System.out.println(isUnique2(test));
//        System.out.println(isUnique3(test));
//        System.out.println(isOneEditAway("doc", "doc"));
//        System.out.println(shrink("abbb vvvv s rttt rr eeee f"));

//        System.out.println(extract("4 k 2321 2 11 k4k2 66 4d"));

//        int[][] matrix = new int[4][4];
//
//        Random rnd = new Random();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                matrix[i][j] = rnd.nextInt(10);
//            }
//        }
//
//        printMatrix(matrix);
//
//        rotateWithTranspose(matrix);
//
//        System.out.println("========AFTER ROTATE============");
//
//        printMatrix(matrix);
//
//        printColumns(matrix);

//
//        String palindromePositive = "qweewq";
//
//        System.out.println(isPalindrome1(palindromePositive));

//        long start = System.nanoTime();
//        System.out.println(areAnagrams("polo", "pool"));
//        System.out.println(System.nanoTime() - start);
//        start = System.nanoTime();
//        System.out.println(areAnagrams1("polo", "pool"));
//        System.out.println(System.nanoTime() - start);
//        start = System.nanoTime();
//
//        System.out.println(areAnagrams3("polo", "pool"));
//
//        System.out.println(System.nanoTime() - start);


//        System.out.println(frequencySort("tree"));


//        System.out.println(Arrays.toString(twoSum1(new int[]{2, 7, 11, 15}, 9)));
//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));

//
//        String s = "    Start middle1 middle2 End         ";
//        char[] chars = s.toCharArray();
//
//        int end = chars.length - 1;
//
//        System.out.println(Arrays.toString(reverseListRange(chars, 0, end)));


        int[] a = {1, 2};
        int[] b = {5, 9, 4, 6, 5, 7};
//        System.out.println(Arrays.toString(merge(a, b)));

//        System.out.println(Arrays.toString(arrayOfProducts(a)));
//        System.out.println(Arrays.toString(largestRange(a)));

//        String[] arr = {"abc", "bavcc", "aaaa", "cde", "efg", "gead"};
//        minimumCharactersForWords(arr);
        String bigString = "abcdefghijklmnopqrstuvwxyz";
        String smallString = "aajjttwwxxzz";


//        smallestSubstringContaining(bigString, smallString);

//        System.out.println(smallestSubstringContaining(bigString, smallString));
//
//        System.out.println(needsSort(b, 0, b.length -1));
//
//        int[] shortest = new int[10];
//
//        System.out.println(Arrays.toString(shortest));
//
//        System.out.println(Arrays.toString(Arrays.copyOfRange(b, 0, 2)));


//        System.out.println(partition(b, 0, b.length- 1 ));


        int[][] array = {
                {1, 4, 7, 12, 15, 1000},
                {2, 5, 19, 31, 32, 1001},
                {3, 8, 24, 33, 35, 1002},
                {40, 41, 42, 44, 45, 1003},
                {99, 100, 103, 106, 128, 1004}
        };

        System.out.println(Arrays.toString(searchInSortedMatrix(array, 5)));

    }


    private static final int MAX_CODE = 65535;
    private static final char A_CHAR = 'a';

    public static boolean isUnique0(String input) {

        Map<Character, Boolean> cache = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.codePointAt(i) <= MAX_CODE) {
                char ch = input.charAt(i);
                if (!Character.isWhitespace(ch)) {
                    if (cache.put(ch, true) != null) {
                        return false;
                    }
                }
            } else {
                System.out.println("The given string contains unallowed characters");
                return false;
            }
        }
        return true;
    }

    public static boolean isUnique1(String input) {
        return input.chars().mapToObj(c -> (char) c).distinct().count() == input.length();
    }

    public static boolean isUnique2(String input) {

        Set<Character> chars = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!chars.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUnique3(String input) {
        int marker = 0;
        for (int i = 0; i < input.length(); i++) {
            System.out.println("marker: " + marker);
            int s = input.charAt(i) - A_CHAR;
            System.out.println("s: " + s);
            int mask = 1 << s;
            System.out.println("mask: " + mask);
            if ((marker & mask) > 0)
                return false;

            marker = marker | mask;
        }
        return true;
    }

    /**
     * One edit way.
     * Problem: Consider two given strings, q and p.
     * Write a snippet of code that determines whether we can obtain two identical strings by performing a single edit in q or p.
     * More precisely, we can insert, remove, or replace a single character in q or in p, and q will become equal to p.
     */
    public static boolean isOneEditAway(String p, String q) {

        if (Math.abs(p.length() - q.length()) > 1)
            return false;

        String shorter = p.length() > q.length() ? q : p;
        String longer = q.length() > p.length() ? q : p;

        int is = 0;
        int il = 0;

        boolean marker = false;
        while (is < shorter.length() && il < longer.length()) {
            if (shorter.charAt(is) != longer.charAt(il)) {
                if (marker) {
                    return false;
                }
                marker = true;
                if (shorter.length() == longer.length()) {
                    is++;
                }
            } else {
                is++;
            }
            il++;
        }
        return true;
    }


    /**
     * Problem: Consider a given string containing only letters a-z and whitespaces.
     * This string contains a lot of consecutive repeated characters.
     * Write a snippet of code that shrinks this string by counting the consecutive repeated characters and creating another string that appends each character and the number of consecutive occurrences.
     * The whitespaces should be copied in the resulting string as they are (don't shrink the whitespaces).
     * If the resulting string is not shorter than the given string, then return the given string.
     *
     * @param string
     * @return
     */
    public static String shrink(String string) {
        StringBuilder resultBuilder = new StringBuilder();

        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            count++;
            char current = string.charAt(i);
            if (!Character.isWhitespace(current)) {
                if ((i + 1) >= string.length() || current != string.charAt(i + 1)) {
                    resultBuilder.append(current).append(count);
                    count = 0;
                }
            } else {
                resultBuilder.append(current);
                count = 0;
            }
        }

        return resultBuilder.length() > string.length() ? string : resultBuilder.toString();
    }

    /**
     * Problem: Consider a given string containing whitespaces and a-z and 0-9 characters.
     * Write a snippet of code that extracts integers from this string.
     * You can assume that any sequence of consecutive digits forms a valid integer.
     *
     * @param string
     * @return
     */
    public static List<Integer> extract(String string) {
        List<Integer> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            if (Character.isDigit(current)) {
                temp.append(current);
            } else {
                if (!temp.isEmpty()) {
                    result.add(Integer.parseInt(temp.toString()));
                    temp.delete(0, temp.length());
                }
            }
        }
        return result;
    }

    /**
     * Consider a given n x n matrix of integers, M.
     * Write a snippet of code that rotates this matrix by 90 degrees in a counterclockwise direction without using any extra space.
     *
     * @param m - input matrix
     */
    private static void transpose(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
    }

    public static void rotateWithTranspose(int[][] m) {
        if (m == null || m.length == 0) {
            throw new IllegalArgumentException("The given matrix cannot be null or empty");
        }

        if (m.length != m[0].length) {
            throw new IllegalArgumentException("The given matrix must be of type nxn");
        }

        transpose(m);

        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0, k = m[0].length - 1; j < k; j++, k--) {
                int tmp = m[j][i];
                m[j][i] = m[k][i];
                m[k][i] = tmp;
            }
        }
    }


    public static boolean isPalindrome2(String s) {
        StringBuilder reversed = new StringBuilder(s).reverse();
        return reversed.toString().equals(s);
    }

    public static boolean isPalindrome1(String s) {
        int begin = 0;
        int end = s.length() - 1;

        while (begin < end) {
            if (s.charAt(begin) != s.charAt(end))
                return false;
            begin++;
            end--;
        }

        return true;
    }


    static boolean areAnagrams(String s1, String s2) {
        Map<Integer, List<Integer>> s1AsMap = s1.chars().boxed().collect(groupingBy(identity()));
        Map<Integer, List<Integer>> s2AsMap = s2.chars().boxed().collect(groupingBy(identity()));

        return s1AsMap.equals(s2AsMap);
    }

    static boolean areAnagrams1(String s1, String s2) {
        HashMap<Character, Integer> firstAsMap = new HashMap<>();
        HashMap<Character, Integer> secondAsMap = new HashMap<>();

        for (char c : s1.toCharArray()) {
            firstAsMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        for (char c : s2.toCharArray()) {
            secondAsMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        return Objects.equals(firstAsMap, secondAsMap);
    }

    static boolean areAnagrams3(String s1, String s2) {

        char[] firstArr = s1.toCharArray();
        char[] secondArr = s2.toCharArray();

        Arrays.sort(firstArr);
        Arrays.sort(secondArr);

        return Arrays.equals(firstArr, secondArr);

    }


    static String reverseWords(String input) {

        if (input.startsWith(" ")) {

        }
        return "";
    }


    static String frequencySort(String s) {

        return s.chars().mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> String.valueOf(entry.getKey()).repeat(entry.getValue().intValue()))
                .collect(joining());
    }

    public static int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length && i != j; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }

            }

        }

        return res;
    }

    public static int[] twoSum1(int[] numbers, int target) {
        int[] res = new int[2];

        Map<Integer, Integer> cache = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (cache.containsKey(target - numbers[i])) {
                res[0] = i;
                res[1] = cache.get(target - numbers[i]);
            } else {
                cache.put(numbers[i], i);
            }
        }

        return res;
    }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode current;

        Stack<Integer> first = new Stack<>();
        Stack<Integer> second = new Stack<>();

        while ((current = l1) != null) {
            first.push(current.val);
            current = current.next;
        }

        while ((current = l2) != null) {
            second.push(current.val);
            current = current.next;
        }

        Integer fNum = Integer.valueOf(first.stream().map(String::valueOf).collect(joining()));
        Integer sNum = Integer.valueOf(second.stream().map(String::valueOf).collect(joining()));

        return null;
    }


    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        // Write your code here.

//            Arrays

        return null;
    }

    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Write your code here.
        int arrIdx = 0;
        int seqIdx = 0;
        while (arrIdx < array.size() && seqIdx < sequence.size()) {

            if (array.get(arrIdx).equals(sequence.get(seqIdx)))
                seqIdx++;

            arrIdx++;
        }

        return seqIdx == sequence.size();
    }

    public int[] sortedSquaredArray(int[] array) {
        // Write your code here.
        int[] res = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            res[i] = array[i] * array[i];
        }
        Arrays.sort(res);

        return res;
    }

    public String tournamentWinner(
            ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {

        Map<String, Integer> scores = new HashMap<>();

        for (int i = 0; i < results.size(); i++) {
            Integer res = results.get(i);
            ArrayList<String> comp = competitions.get(i);

            if (res == 1) {
                scores.compute(comp.get(0), (k, v) -> v == null ? 3 : v + 3);
            } else {
                scores.compute(comp.get(1), (k, v) -> v == null ? 3 : v + 3);
            }
        }

        TreeMap<Integer, String> reverse = new TreeMap<>(Comparator.reverseOrder());

        scores.forEach((k, v) -> {
            reverse.put(v, k);
        });

        return reverse.firstEntry().getValue();

    }

    public int nonConstructibleChange(int[] coins) {
        // Write your code here.
        Arrays.sort(coins);
        int current = 0;
        for (int coin : coins) {
            if (coin > current + 1)
                return current + 1;
            current += coin;
        }
        return current + 1;
    }

    public static int findClosestValueInBst(BST tree, int target) {


        return findClosestValueInBst0(tree, target, tree.value);
    }

    public static int findClosestValueInBst0(BST tree, int target, int closest) {
        if (Math.abs(target - closest) > Math.abs(target - tree.value))
            closest = tree.value;

        if (target < tree.value && tree.left != null) {
            return findClosestValueInBst0(tree.left, target, closest);
        } else if (target > tree.value && tree.right != null) {
            return findClosestValueInBst0(tree.right, target, closest);
        } else return closest;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }


    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> branchSums(BinaryTree root) {
        // Write your code here.

        List<Integer> sums = new ArrayList<>();
        calculateBranchSums(root, 0, sums);
        return sums;
    }

    private static void calculateBranchSums(BinaryTree tree, int runningSum, List<Integer> sums) {

        if (tree == null) return;

        int newRunningSum = runningSum + tree.value;

        if (tree.left == null && tree.right == null) {
            sums.add(newRunningSum);
            return;
        }

        calculateBranchSums(tree.left, newRunningSum, sums);
        calculateBranchSums(tree.right, newRunningSum, sums);

    }

    public static int nodeDepths(BinaryTree root) {
        // Write your code here.
        return nodeDepth0(root, 0);
    }


    private static int nodeDepth0(BinaryTree node, int level) {


        if (node == null) return 0;

        return level + nodeDepth0(node.left, level + 1) + nodeDepth0(node.right, level + 1);

    }


    public static int binarySearch(int[] array, int target) {
        return binarySearch0(array, target, 0, array.length - 1);
    }

    private static int binarySearch0(int[] array, int target, int left, int right) {

        if (left > right) return -1;
        int mid = (left + right) / 2;
        int potentialMatch = array[mid];

        if (target == potentialMatch)
            return mid;
        else if (target < potentialMatch)
            return binarySearch0(array, target, left, mid - 1);
        else
            return binarySearch0(array, target, mid + 1, right);
    }

    public static int[] findThreeLargestNumbers(int[] array) {
        // Write your code here.
        Arrays.sort(array);
        if (array.length > 2)
            return new int[]{array[array.length - 3], array[array.length - 2], array[array.length - 1]};
        return null;
    }

    public static int[] findThreeLargestNumbers1(int[] array) {

        int[] threeLargest = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for (int i : array) {
            updateLargest(threeLargest, i);
        }
        return threeLargest;
    }


    private static void updateLargest(int[] threeLargest, int num) {
        if (num > threeLargest[2])
            shiftAndUpdate(threeLargest, num, 2);
        else if (num > threeLargest[1])
            shiftAndUpdate(threeLargest, num, 1);
        else if (num > threeLargest[0])
            shiftAndUpdate(threeLargest, num, 0);
    }

    private static void shiftAndUpdate(int[] array, int num, int idx) {
        for (int i = 0; i < array.length; i++) {
            if (i == idx)
                array[idx] = num;
            else
                array[i] = array[i + 1];
        }
    }

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {

        // Write your code here.
        return null;
    }


    public static int productSum(List<Object> array) {

        return productSumHelper(array, 1);
    }

    private static int productSumHelper(List<Object> array, int multiplier) {
        int sum = 0;

        for (Object o : array) {
            if (o instanceof ArrayList)
                sum += productSumHelper((ArrayList<Object>) o, multiplier + 1);
            else
                sum += (int) o;
        }

        return sum * multiplier;
    }


    public static boolean isPalindrome4(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start <= end) {

            if (str.charAt(start) != str.charAt(end))
                return false;

            start++;
            end--;

        }

        return true;
    }

    public String runLengthEncoding(String string) {

        int currentLength = 1;
        StringBuilder res = new StringBuilder();


        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            char prev = string.charAt(i - 1);

            if (current != prev || currentLength == 9) {
                res.append(currentLength).append(prev);
                currentLength = 0;
            }

            currentLength++;

        }
        res.append(currentLength).append(string.charAt(string.length() - 1));
        return res.toString();
    }

    public int firstNonRepeatingCharacter(String string) {
        // Write your code here.
        Map<Character, Long> collect = string.chars().mapToObj(c -> (char) c).collect(groupingBy(identity(), counting()));

        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            if (collect.get(current) == 1)
                return i;
        }
        return -1;
    }

    public String reverseWordsInString(String string) {
        // Write your code here.
        char[] characters = string.toCharArray();

        int length = characters.length;
        reverseListRange(characters, 0, length - 1);

        int startOfWord = 0;
        while (startOfWord < length) {
            int endOfWord = startOfWord;
            while (endOfWord < length && characters[endOfWord] != ' ') {
                endOfWord++;
            }

            reverseListRange(characters, startOfWord, endOfWord - 1);
            startOfWord = endOfWord + 1;
        }

        return new String(characters);
    }

    private static char[] reverseListRange(char[] list, int start, int end) {
        while (start < end) {
            char temp = list[start];
            list[start] = list[end];
            list[end] = temp;
            start++;
            end--;
        }
        return list;
    }

    public static List<List<String>> groupAnagrams(List<String> words) {
        // Write your code here.
        Collection<List<String>> res = words.stream()
                .map(w -> new Pair<>(sorted(w), w))
                .collect(groupingBy(pair -> pair.first, mapping(pair -> pair.second, toList())))
                .values();
        return new ArrayList<>(res);
    }


    private static String sorted(String input) {
        return input.chars().mapToObj(c -> (char) c).map(String::valueOf).sorted().collect(joining());
    }


    static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

    }

    public boolean classPhotos(
            ArrayList<Integer> redShirtHeights, ArrayList<Integer> blueShirtHeights) {

        redShirtHeights.sort(Collections.reverseOrder());
        blueShirtHeights.sort(Collections.reverseOrder());

        String colorInFirstRow = blueShirtHeights.get(0) > redShirtHeights.get(0) ? "RED" : " BLUE";

        for (int i = 0; i < redShirtHeights.size(); i++) {
            int redShirtHeight = redShirtHeights.get(i);
            int blueShirtHeight = blueShirtHeights.get(i);
            if ("RED".equals(colorInFirstRow)) {
                if (redShirtHeight >= blueShirtHeight)
                    return false;
            } else {
                if (redShirtHeight <= blueShirtHeight)
                    return false;
            }
        }
        return true;
    }


    public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
        // Write your code here.

        Arrays.sort(redShirtSpeeds);
        Arrays.sort(blueShirtSpeeds);


        return -1;
    }

    static int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                merged[k++] = b[j++];
            } else {
                merged[k++] = a[i++];
            }
        }

        while (i < a.length) {
            merged[k++] = a[i++];
        }

        while (j < b.length) {
            merged[k++] = b[j++];
        }
        return merged;
    }

    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        // Write your code here.
        int min = Integer.MAX_VALUE;
//        int current = 0;
        int[] res = new int[2];

        for (int el1 : arrayOne) {
            for (int el2 : arrayTwo) {
                int current;

                if (el1 > 0 && el2 > 0)
                    current = Math.abs(el1 - el2);
                else if (el1 < 0 && el2 > 0)
                    current = el2 - el1;
                else if (el1 > 0 && el2 < 0)
                    current = el1 - el2;
                else
                    current = Math.abs(el1 - el2);

                if (current < min) {
                    min = current;
                    res[0] = el1;
                    res[1] = el2;
                }

            }
        }
        return res;
    }

    public static List<Integer[]> threeNumberSum1(int[] array, int targetSum) {

        List<Integer[]> res = new ArrayList<>();

        Arrays.sort(array);

        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum == targetSum) {
                    res.add(new Integer[]{array[i], array[left], array[right]});
                    left++;
                    right--;
                } else if (currentSum > targetSum) {
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                }
            }
        }
        return res;
    }

    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int i = 0;
        int j = array.size() - 1;
        while (i < j) {
            while (i < j && array.get(j) == toMove) j--;
            if (array.get(i) == toMove) swap(array, i, j);
            i++;
        }
        return array;
    }

    private static void swap(List<Integer> list, int i, int j) {
        Integer tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    //
//    private static boolean beaksDirection(int direction, int previous, int current) {
//        var difference = current - previous;
//        if (direction > 0) r public static boolean isMonotonic ( int[] array){
//            if (array.length <= 2) return true;
//
//            int direction = array[1] - array[0];
//
//            for (int i = 2; i < array.length; i++) {
//                if (direction == 0) {
//                    direction = array[i] - array[i - 1];
//                    continue;
//                }
//                if (beaksDirection(direction, array[i - 1], array[i]))
//                    return false;
//            }
//
//            return true;
//
//        }
//        eturn difference <0;
//        return difference > 0;
//    }
    public static int[] arrayOfProducts(int[] array) {
        // Write your code here.
        int[] resArr = new int[array.length];

        for (int i = 0; i < array.length; i++) {

            int currentProduct = 1;
            for (int j = 0; j < array.length; j++) {

                if (i != j) {
                    System.out.println("multiplying on " + array[j]);
                    currentProduct *= array[j];
                }
            }
            resArr[i] = currentProduct;


        }
        return resArr;
    }

    public int firstDuplicateValue(int[] array) {
        Set<Integer> cache = new HashSet<>();
        for (int i : array) {
            if (cache.contains(i))
                return i;

            cache.add(i);
        }
        return -1;
    }

    public int tandemBicycle1(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
        // Write your code here
        Arrays.sort(redShirtSpeeds);
        Arrays.sort(blueShirtSpeeds);

        if (!fastest) {
            reverseArrayInPlace(redShirtSpeeds);
        }

        int totalSpeed = 0;

        for (int i = 0; i < redShirtSpeeds.length; i++) {
            int redSpeed = redShirtSpeeds[i];
            int blueSpeed = blueShirtSpeeds[blueShirtSpeeds.length - i - 1];
            totalSpeed += Math.max(redSpeed, blueSpeed);
        }

        return totalSpeed;
    }


    static void reverseArrayInPlace(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }

    }


    public int[] threeNumberSort(int[] array, int[] order) {
        // Write your code here.
        return null;
    }


    static int[] mergeTwoSorted(int[] a, int[] b) {

        int[] c = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < a.length) {
            c[k++] = a[i++];
        }

        while (j < b.length) {
            c[k++] = b[j++];
        }

        return c;
    }


    public static String longestPalindromicSubstring(String str) {
        String longest = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String substr = str.substring(i, j + 1);
                if (longest.length() < substr.length() && isPalindrome(substr)) {
                    longest = substr;
                }
            }
        }
        return longest;
    }

    private static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) == s.charAt(end))
                return false;
            start++;
            end--;
        }

        return true;
    }

    public boolean generateDocument(String characters, String document) {

        Map<Character, Integer> charactersCount = new HashMap<>();
        Map<Character, Integer> documentCharsCount = new HashMap<>();

        char[] chars = characters.toCharArray();
        char[] documentChars = document.toCharArray();

        for (char aChar : chars) {
            charactersCount.compute(aChar, (k, v) -> v == null ? 1 : v + 1);
        }

        for (char aChar : documentChars) {
            documentCharsCount.compute(aChar, (k, v) -> v == null ? 1 : v + 1);
        }

        final boolean[] possible = {true};
        documentCharsCount.forEach((k, v) -> {

            Integer i = charactersCount.get(k);

            if (i == null) {
                possible[0] = false;
                return;
            }

            if (i < v)
                possible[0] = false;
        });
        return possible[0];
    }

    public static char[] minimumCharactersForWords(String[] words) {

        Map<Character, Integer> count = new HashMap<>();
        Map<Character, Integer> current = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();

            for (char aChar : chars) {
                current.compute(aChar, (k, v) -> v == null ? 1 : v + 1);
            }
            System.out.println(current);

            for (char aChar : chars) {
                count.compute(aChar, (k, v) -> {
                    System.out.println("current char: " + k);
                    System.out.println("current count: " + current.get(k));
                    System.out.println("current max count: " + v);
                    return v == null ? current.get(k) : (v > current.get(k) ? v : current.get(k));
                });
                System.out.println(count);
            }

            current.clear();
        }

        int size = count.values().stream().mapToInt(i -> i).sum();

        char[] res = new char[size];
        int[] idx = {0};

        System.out.println(count);

        count.forEach((k, v) -> {

            for (int i = 0; i < v; i++) {
                res[idx[0]++] = k;
            }
        });

        return res;
    }

    public static String longestSubstringWithoutDuplication(String str) {
        String longest = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String substr = str.substring(i, j + 1);
                if (longest.length() < substr.length() && !hasDuplicates(substr)) {
                    longest = substr;
                }
            }
        }
        return longest;
    }

    static boolean hasDuplicates(String str) {
        Set<Character> cache = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.contains(str.charAt(i))) {
                return true;
            }
            cache.add(str.charAt(i));
        }
        return false;
    }

    public static String smallestSubstringContaining(String bigString, String smallString) {
        // Write your code here.

        String shortest = "";

        for (int i = 0; i < bigString.length(); i++) {
            for (int j = i; j < bigString.length(); j++) {
                String substr = bigString.substring(i, j + 1);
                System.out.println(substr);
                if (shortest.length() > substr.length() && containsAllChars(substr, smallString)) {
                    shortest = substr;
                } else if (shortest.isBlank() && containsAllChars(substr, smallString)) {
                    shortest = substr;
                }
                System.out.println(shortest);
            }
        }

        return shortest;
    }


    static boolean containsAllChars(String target, String chars) {

        Map<Character, Long> charToCount = chars.chars().mapToObj(c -> (char) c).collect(groupingBy(identity(), counting()));

        for (char c : target.toCharArray()) {
            if (charToCount.containsKey(c)) {
                charToCount.computeIfPresent(c, (k, v) -> v - 1);
            }
        }

        System.out.println(charToCount);
        return charToCount.values().stream().allMatch(v -> v <= 0);

    }


    public int longestBalancedSubstring(String string) {
        // Write your code here.
        return -1;
    }

    public static int[] subarraySort(int[] array) {
        // Write your code here.

        int[] shortest = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
//                Arrays.copyOfRange(array, i, )
//                if (shortest.length)
            }
        }

        return new int[]{};
    }

    static boolean needsSort(int[] array, int startIdx, int endIdx) {
        for (int i = startIdx + 1; i <= endIdx; i++) {
            if (array[i - 1] > array[i])
                return true;
        }
        return false;
    }

    public static int[] mergeSort(int[] array) {

        if (array.length <= 1) return array;

        int midIdx = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, midIdx);
        int[] right = Arrays.copyOfRange(array, midIdx, array.length);

        return merge0(mergeSort(left), mergeSort(right));
    }

    static int[] merge0(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                merged[k++] = left[i++];
            } else merged[k++] = right[j++];
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }
        while (j < right.length) {
            merged[k++] = right[j++];
        }
        return merged;
    }

    public static int[] quickSort(int[] array) {

        int begin = 0;
        int end = array.length - 1;

        int partitionIdx = partition(array, begin, end);

//        quickSort()
        return null;
    }

    static int partition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, end);

        return i + 1;
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        // Write your code here.
        int midColumn = matrix[0].length / 2;
        int midRow = matrix.length / 2;
        int mid = matrix[midRow][midColumn];


        return new int[]{mid, -1};
    }

    public int countInversions(int[] array) {
        // Write your code here.

        int res = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j){
                    if (i < j && array[i] > array[j] )
                        res++;
                }
            }
        }
        return res;
    }

    public static int kadanesAlgorithm0(int[] array) {
        // Write your code here.

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int[] subArray = Arrays.copyOfRange(array, i, j + 1);
                int sum = Arrays.stream(subArray).sum();
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static int kadanesAlgorithm(int[] array) {
        // Write your code here.
        int maxEndingHere = array[0];
        int maxSoFar = array[0];
        for (int i = 1; i < array.length; i++) {
            int num = array[i];
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public int minimumAreaRectangle(int[][] points) {
        // Write your code here.
        return 0;
    }


    public static int maxSubsetSumNoAdjacent(int[] array) {
        // Write your code here.
        return -1;
    }

    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        // Write your code here.
        return -1;
    }

}

class Tuple3<F, S, T> {
    F first;
    S second;
    T third;

    public Tuple3(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
