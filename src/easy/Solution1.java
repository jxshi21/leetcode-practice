package easy;

/**
 * 1. 两数之和
 * 
 * 给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * */

import java.util.Map;
import java.util.HashMap;

public class Solution1 {

	public static void main(String[] args) {
		
		int[] nums = {2,7,11,15};
        int target = 26;
        
        // 方法一：暴力法
        long start1 = System.currentTimeMillis();
        Solu1 solu1 = new Solu1();
        int[] rst1 = solu1.twoSum(nums, target);
        for (int n : rst1) {
            System.out.print(n + " ");
        }
        System.out.println("执行时间：" + (System.currentTimeMillis()-start1) + " 毫秒");
        
        // 方法二：两遍哈希表
        long start2 = System.currentTimeMillis();
        Solu2 solu2 = new Solu2();
        int[] rst2 = solu2.twoSum(nums, target);
        for (int n : rst2) {
            System.out.print(n + " ");
        }
        System.out.println("执行时间：" + (System.currentTimeMillis()-start2) + " 毫秒");
        
        // 方法三：一遍哈希表
        long start3 = System.currentTimeMillis();
        Solu3 solu3 = new Solu3();
        int[] rst3 = solu3.twoSum(nums, target);
        for (int n : rst3) {
            System.out.print(n + " ");
        }
        System.out.println("执行时间：" + (System.currentTimeMillis()-start3) + " 毫秒");

	}
	
	// 方法一：暴力法
	// 时：O(n^2)	空：O(1)
	private static class Solu1 {
		public int[] twoSum(int[] nums, int target) {
			for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1 ; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[] {i, j};
                    }
                }
            }
            throw new IllegalArgumentException("No two sum solution");
		}
	}
	
	// 方法二：两遍哈希表
	// 时：O(n)	空：O(n)
	private static class Solu2 {
		public int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				map.put(nums[i], i);
			}
			for (int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];
				if (map.containsKey(complement) && map.get(complement) != i) {
					return new int[] {i, map.get(complement)};
				}
			}
			throw new IllegalArgumentException("No two sum solution");
		}
	}
	
	// 方法三：一遍哈希表
	// 时：O(n)	空：O(n)
	private static class Solu3 {
		public int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];
				if (map.containsKey(complement)) {
					return new int[] {map.get(complement), i};
				}
				map.put(nums[i], i);
			}
			throw new IllegalArgumentException("No two sum solution");
		}
	}
	
}