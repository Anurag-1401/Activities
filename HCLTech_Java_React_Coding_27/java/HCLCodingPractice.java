import java.io.*;
import java.nio.file.*;
import java.util.*;

public class HCLCodingPractice {
    // 1. Reverse string
    static String reverseString(String s) { return new StringBuilder(s).reverse().toString(); }

    // 2. Palindrome (ignores spaces, punctuation and case)
    static boolean isPalindrome(String s) {
        String t = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int i = 0, j = t.length() - 1;
        while (i < j) if (t.charAt(i++) != t.charAt(j--)) return false;
        return true;
    }

    // 3. Second-largest DISTINCT element; null if it does not exist
    static Integer secondLargest(int[] a) {
        Integer max = null, second = null;
        for (int x : a) {
            if (max == null || x > max) { second = max; max = x; }
            else if (x != max && (second == null || x > second)) second = x;
        }
        return second;
    }

    // 4. Remove duplicates, preserve order
    static int[] removeDuplicates(int[] a) {
        Set<Integer> s = new LinkedHashSet<>();
        for (int x : a) s.add(x);
        return s.stream().mapToInt(Integer::intValue).toArray();
    }

    // 5. Frequency of elements
    static Map<Integer,Integer> frequencies(int[] a) {
        Map<Integer,Integer> m = new LinkedHashMap<>();
        for (int x : a) m.put(x, m.getOrDefault(x, 0) + 1);
        return m;
    }

    // 6. First non-repeating character; null if none
    static Character firstNonRepeating(String s) {
        Map<Character,Integer> m = new LinkedHashMap<>();
        for (char c : s.toCharArray()) m.put(c, m.getOrDefault(c, 0) + 1);
        for (char c : s.toCharArray()) if (m.get(c) == 1) return c;
        return null;
    }

    // 7. Anagram check (ignores spaces and case)
    static boolean areAnagrams(String a, String b) {
        char[] x = a.replaceAll("\\s+", "").toLowerCase().toCharArray();
        char[] y = b.replaceAll("\\s+", "").toLowerCase().toCharArray();
        Arrays.sort(x); Arrays.sort(y);
        return Arrays.equals(x, y);
    }

    // 8. Missing number in range 1..n; input has n-1 distinct values
    static int missingNumber(int[] a, int n) {
        long expected = (long)n * (n + 1) / 2, actual = 0;
        for (int x : a) actual += x;
        return (int)(expected - actual);
    }

    // 9. Longest subarray with equal 0s and 1s: prefix sum + HashMap
    static int longestEqualZeroOne(int[] a) {
        Map<Integer,Integer> first = new HashMap<>();
        first.put(0, -1);
        int sum = 0, best = 0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] == 0 ? -1 : 1);
            if (first.containsKey(sum)) best = Math.max(best, i - first.get(sum));
            else first.put(sum, i);
        }
        return best;
    }

    // 10. Fruits Into Baskets: longest window with at most 2 distinct values
    static int fruitsIntoBaskets(int[] a) {
        Map<Integer,Integer> count = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < a.length; right++) {
            count.put(a[right], count.getOrDefault(a[right], 0) + 1);
            while (count.size() > 2) {
                int v = a[left++];
                count.put(v, count.get(v) - 1);
                if (count.get(v) == 0) count.remove(v);
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // 11. Two Sum: returns pair of indices, or {-1,-1}
    static int[] twoSum(int[] a, int target) {
        Map<Integer,Integer> seen = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int need = target - a[i];
            if (seen.containsKey(need)) return new int[]{seen.get(need), i};
            seen.put(a[i], i);
        }
        return new int[]{-1, -1};
    }

    // 12. Maximum subarray sum: Kadane's algorithm
    static int maxSubarraySum(int[] a) {
        if (a.length == 0) throw new IllegalArgumentException("Empty array");
        int current = a[0], best = a[0];
        for (int i = 1; i < a.length; i++) {
            current = Math.max(a[i], current + a[i]);
            best = Math.max(best, current);
        }
        return best;
    }

    // 13. Longest substring without repeating characters
    static int longestUniqueSubstring(String s) {
        Map<Character,Integer> last = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last.containsKey(c)) left = Math.max(left, last.get(c) + 1);
            last.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // 14. Move zeros to end, preserving non-zero order
    static void moveZeros(int[] a) {
        int write = 0;
        for (int x : a) if (x != 0) a[write++] = x;
        while (write < a.length) a[write++] = 0;
    }

    // 15. Subarray sum: positive numbers only; returns start/end indices
    static int[] subarrayWithSumPositive(int[] a, int target) {
        int left = 0, sum = 0;
        for (int right = 0; right < a.length; right++) {
            sum += a[right];
            while (sum > target && left <= right) sum -= a[left++];
            if (sum == target) return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }

    // 16. Duplicate values, each returned once
    static Set<Integer> findDuplicates(int[] a) {
        Map<Integer,Integer> freq = new HashMap<>();
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int x : a) {
            int n = freq.getOrDefault(x, 0) + 1;
            freq.put(x, n);
            if (n == 2) duplicates.add(x);
        }
        return duplicates;
    }

    // 17. File write and read (Java 11+)
    static void writeFile(String file, String content) throws IOException {
        Files.writeString(Path.of(file), content);
    }
    static String readFile(String file) throws IOException {
        return Files.readString(Path.of(file));
    }

    // 18. Count words and characters (characters include spaces/newlines)
    static void countFile(String file) throws IOException {
        String text = readFile(file);
        String trimmed = text.trim();
        int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
        System.out.println("Words = " + words);
        System.out.println("Characters = " + text.length());
    }

    // 19. Sort employees using Comparator
    static class Employee {
        int id; String name; double salary;
        Employee(int id, String name, double salary) {
            this.id = id; this.name = name; this.salary = salary;
        }
        public String toString() { return id + " " + name + " " + salary; }
    }
    static void sortEmployeesBySalary(List<Employee> employees) {
        employees.sort(Comparator.comparingDouble(e -> e.salary));
    }

    // 20. Remove duplicates using HashSet (order is not guaranteed)
    static <T> Set<T> uniqueUsingHashSet(List<T> list) {
        return new HashSet<>(list);
    }

    // 21. Sort even numbers, remove duplicates, and count
    static void sortedUniqueEvens(int[] a) {
        Set<Integer> evens = new TreeSet<>();
        for (int x : a) if (x % 2 == 0) evens.add(x);
        System.out.println("Even numbers = " + evens);
        System.out.println("Count = " + evens.size());
    }

    // 22. Palindrome words from an array
    static List<String> palindromeWords(String[] words) {
        List<String> result = new ArrayList<>();
        for (String word : words) if (isPalindrome(word)) result.add(word);
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("1 " + reverseString("hello"));
        System.out.println("2 " + isPalindrome("Madam"));
        System.out.println("3 " + secondLargest(new int[]{10, 5, 10, 8}));
        System.out.println("4 " + Arrays.toString(removeDuplicates(new int[]{1,2,2,3,1})));
        System.out.println("5 " + frequencies(new int[]{1,2,2,3,1,1}));
        System.out.println("6 " + firstNonRepeating("swiss"));
        System.out.println("7 " + areAnagrams("listen", "silent"));
        System.out.println("8 " + missingNumber(new int[]{1,2,4,5}, 5));
        System.out.println("9 " + longestEqualZeroOne(new int[]{0,0,1,0,1,1,0}));
        System.out.println("10 " + fruitsIntoBaskets(new int[]{1,2,1,2,3}));
        System.out.println("11 " + Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println("12 " + maxSubarraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("13 " + longestUniqueSubstring("abcabcbb"));
        int[] z = {0,1,0,3,12}; moveZeros(z);
        System.out.println("14 " + Arrays.toString(z));
        System.out.println("15 " + Arrays.toString(subarrayWithSumPositive(new int[]{1,2,3,7,5}, 12)));
        System.out.println("16 " + findDuplicates(new int[]{1,2,3,2,4,1,1}));

        List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(1,"Asha",50000), new Employee(2,"Ravi",40000),
            new Employee(3,"Neha",60000)));
        sortEmployeesBySalary(employees);
        System.out.println("19 " + employees);
        System.out.println("20 " + uniqueUsingHashSet(Arrays.asList(1,2,2,3,1)));
        sortedUniqueEvens(new int[]{7,2,4,2,9,6,8,3});
        System.out.println("22 " + palindromeWords(new String[]{"level","java","madam","code"}));

        // File exercise:
        // writeFile("sample.txt", "Java is easy. Java is powerful.");
        // System.out.println(readFile("sample.txt"));
        // countFile("sample.txt");
    }
}
