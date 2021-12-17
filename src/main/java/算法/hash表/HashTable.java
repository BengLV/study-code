package 算法.hash表;

import java.util.*;

/**
 * @author lupeng
 * 限制了数值的大小:数组
 * 不限制数值的的大小:set(不包含重复元素),只能存value,不能存储key
 * map: key-value,数值大小不限制
 */
public class HashTable {

    /**
     * 242. 有效的字母异位词
     * https://leetcode-cn.com/problems/valid-anagram/
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
     *
     * 示例1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     *
     */
    public static boolean isAnagram(String s, String t) {
        //法一:hash表计数
        int[] nums = new int[26];
        if (s == null || t == null) {
            return false;
        }
        for (char c : s.toCharArray()) {
            nums[c - 'a'] ++;
        }
        for (char c : t.toCharArray()) {
            nums[c - 'a'] --;
        }
        for (int i : nums) {
            if (i != 0) {
                return false;
            }
        }

        //法二:排序比较法
        char[] s1 = s.toCharArray();
        Arrays.sort(s1);
        char[] s2 = t.toCharArray();
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }

    /**
     * 49. 字母异位词分组
     * https://leetcode-cn.com/problems/group-anagrams/
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     *
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
     *
     * 示例 1:
     *
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        //法一:stream 排序分组法
        /*return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            return new String(array);
        })).values());*/

        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }


    /**
     * 383. 赎金信
     * https://leetcode-cn.com/problems/ransom-note/
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     *
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     *
     * 你可以假设两个字符串均只含有小写字母
     * 示例 1：
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     *
     * 示例 2：
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     *
     * 示例 3：
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     *
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        char[] arr1 = ransomNote.toCharArray();
        char[] arr2 = magazine.toCharArray();
        int[] nums = new int[26];
        for (char c1 : arr1) {
            nums[c1 - 'a'] ++;
        }
        for (char c2 : arr2) {
            nums[c2 - 'a'] --;
        }
        for (int i : nums) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1002. 查找常用字符
     * https://leetcode-cn.com/problems/find-common-characters/
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     *
     * 示例 2：
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     *
     */
    public static List<String> commonChars(String[] words) {
        int[] nums = new int[26];
        //默认设置最大值
        Arrays.fill(nums, Integer.MAX_VALUE);
        for (String str : words) {
            int[] temp = new int[26];
            char[] array = str.toCharArray();
            for (char c : array) {
                temp[c - 'a']++;
            }
            for (int i = 0; i < 26 ; i++) {
                //比较交换,储存较小的值
                nums[i] = nums[i] > temp[i] ? temp[i] : nums[i];
            }
        }
        List<String> list = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            for (int k = 0; k < nums[j]; k++) {
                //转换成字符
                list.add(String.valueOf((char) (j + 'a')));
            }
        }
        return list;
    }

    /**
     * 349. 两个数组的交集
     * https://leetcode-cn.com/problems/intersection-of-two-arrays/
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     *
     * 示例 2：
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     *
     * 说明：
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     *
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }
        set1.retainAll(set2);
        //retainAll 取交集
        //removeAll 取差集
        //addAll    取交集
        int[] re = new int[set1.size()];
        int index = 0;
        for (int i : set1) {
            re[index++] = i;
        }
        return re;
    }

    /**
     * 202. 快乐数
     * https://leetcode-cn.com/problems/happy-number/
     * 「快乐数」定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     * 示例 1：
     * 输入：19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     *
     * 示例 2：
     * 输入：n = 2
     * 输出：false
     *
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        //时间复杂度 logn
        return n == 1;
    }

    private static int getNext (int n) {
        int total = 0;
        while ( n > 0) {
            //余数
            int remain = n % 10;
            //去一位
            n = n / 10;
            total += remain * remain;
        }
        return total;
    }

    /**
     * 1.两数之和
     * https://leetcode-cn.com/problems/two-sum/
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     *
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 454. 四数相加 II
     * https://leetcode-cn.com/problems/4sum-ii/
     * 给定四个包含整数的数组列表A , B , C , D ,计算有多少个元组 (i, j, k, l)，使得A[i] + B[j] + C[k] + D[l] = 0。
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过231 - 1 。
     *
     * 例如:
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     *
     * 输出: 2
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //分两组,计算出第一组的和值, 再跟第二组的和值进行比较
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                //存储和值, 存在的话加一,不存在的话默认给1
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        int res = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                //判断是否存在map里
                if (map.containsKey( -c - d)) {
                    res += map.get(-c -d);
                }
            }
        }
        return res;
    }



    public static void main(String[] args) {
        String a = "aaadbad";
        String b = "aadabad";
        //isAnagram(a, b);

        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        //groupAnagrams(strs);
        //System.out.println(canConstruct(a, b));
        //commonChars(strs);
        System.out.println(isHappy(2));
        int[] nums = new int[]{2,7,11,15};
        System.out.println(Arrays.toString(twoSum(nums, 9)));


    }

}
