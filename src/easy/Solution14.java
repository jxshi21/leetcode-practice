package easy;

/**
 * 14. 最长公共前缀
 * 
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 一个复杂情况：前缀树。详见第208题。
 * */

public class Solution14 {

	public static void main(String[] args) {
		
		String[] strs = {"flower", "flow", "flight"};
		String[] strs2 = {"dog", "racecar", "car"};
		String[] strs3 = {"flow", "flow", "flow"};
		
		long start = System.currentTimeMillis();
		System.out.println("longest common prefix: " + new Solution().longestCommonPrefix(strs));
		System.out.println("longest common prefix: " + new Solution().longestCommonPrefix(strs2));
		System.out.println("longest common prefix: " + new Solution().longestCommonPrefix(strs3));
		System.out.println("【我的solu】执行时间：" + (System.currentTimeMillis()-start) + " 毫秒\n");
		
		long start1 = System.currentTimeMillis();
		System.out.println("longest common prefix: " + new Solu1().longestCommonPrefix(strs));
		System.out.println("longest common prefix: " + new Solu1().longestCommonPrefix(strs2));
		System.out.println("longest common prefix: " + new Solu1().longestCommonPrefix(strs3));
		System.out.println("【官方Solu1】执行时间：" + (System.currentTimeMillis()-start1) + " 毫秒\n");
		
		long start2 = System.currentTimeMillis();
		System.out.println("longest common prefix: " + new Solu2().longestCommonPrefix(strs));
		System.out.println("longest common prefix: " + new Solu2().longestCommonPrefix(strs2));
		System.out.println("longest common prefix: " + new Solu2().longestCommonPrefix(strs3));
		System.out.println("【官方Solu2】执行时间：" + (System.currentTimeMillis()-start2) + " 毫秒\n");
		
		long start3 = System.currentTimeMillis();
		System.out.println("longest common prefix: " + new Solu3().longestCommonPrefix(strs));
		System.out.println("longest common prefix: " + new Solu3().longestCommonPrefix(strs2));
		System.out.println("longest common prefix: " + new Solu3().longestCommonPrefix(strs3));
		System.out.println("【官方Solu3】执行时间：" + (System.currentTimeMillis()-start3) + " 毫秒\n");
		
		long start4 = System.currentTimeMillis();
		System.out.println("longest common prefix: " + new Solu4().longestCommonPrefix(strs));
		System.out.println("longest common prefix: " + new Solu4().longestCommonPrefix(strs2));
		System.out.println("longest common prefix: " + new Solu4().longestCommonPrefix(strs3));
		System.out.println("【官方Solu4】执行时间：" + (System.currentTimeMillis()-start4) + " 毫秒\n");

	}
	
	// 方法一：我自己写的......
	// 复杂度太高了......最坏情况是每个字符串都相等的时候，时间为O(S^2*N)，其中S是最长的字符串的长度；空间O(1)
	private static class Solution {
	    public String longestCommonPrefix(String[] strs) {
	    	String str = "";
	    	if (strs == null || strs.length != 0) { // 首先保证数组非空，否则会溢出
	    		int min_length = Integer.MAX_VALUE;
	    		for (String s : strs)
	    			min_length = Math.min(min_length, s.length());
		    	flag : for (int i = 1; i <= min_length; i++) { // 复杂度增加在这里：每增加子串的长度就会多很多重复的字符比较
		    		String sub = strs[0].substring(0, 0 + i);
		    		for (int j = 1; j < strs.length; j++) {
		    			String currsub = strs[j].substring(0, 0 + i);
		    			if (sub.compareTo(currsub) != 0) {
		    				break flag;
		    			}
		    		}
		    		str = sub;
		    	}
	    	}
	    	return str;
	    }
	}
	
	// 官方解法一：水平扫描
	// 时间：O(S)；空间：O(1)；S是所有字符串中字符数量的总和（最坏的情况是n个字符串相同，要进行S次字符比较）
	private static class Solu1 {
	    public String longestCommonPrefix(String[] strs) {
	    	if (strs == null || strs.length == 0) return "";
	    	String prefix = strs[0];
	    	for (int i = 1; i < strs.length; i++) {
	    		while (strs[i].indexOf(prefix) != 0) { // String查找子串的方法：indexOf
	    			prefix = prefix.substring(0, prefix.length() - 1);
	    			if (prefix.isEmpty()) return "";
	    		}
	    	}
	    	return prefix;
	    }
	}
	
	// 官方解法二：水平扫描
	// 从前往后枚举字符串的每一列，先比较每个字符串相同列上的字符，然后再进行对下一列的比较
	// 时间：O(S)；空间：O(1)
	private static class Solu2 {
	    public String longestCommonPrefix(String[] strs) {
	    	if (strs == null || strs.length == 0) return "";
	    	for (int i = 0; i < strs[0].length(); i++) {
	    		char c = strs[0].charAt(i);
	    		for (int j = 0; j < strs.length; j++) {
	    			if (i == strs[j].length() || strs[j].charAt(i) != c) {
	    				return strs[0].substring(0, i);
	    			}
	    		}
	    	}
	    	return strs[0];
	    }
	}
	
	// 官方解法三：分治法
	// LCP操作的结合律：LCP(S1...Sn) = LCP( LCP(S1...Sk), LCP(Sk+1...Sn) )
	// 故可以将原问题分为两个子问题
	// 时间：O(S)；空间：O(m*log(n))
	private static class Solu3 {
	    public String longestCommonPrefix(String[] strs) {
	    	if (strs == null || strs.length == 0) return "";
	    	return longestCommonPrefix(strs, 0, strs.length - 1);
	    }
	    
	    private String longestCommonPrefix(String[] strs, int l, int r) {
	    	if (l == r) {
	    		return strs[l];
	    	} else {
	    		int mid = (l + r) / 2;
	    		String lcpleft = longestCommonPrefix(strs, l, mid );
	    		String lcpright = longestCommonPrefix(strs, mid + 1, r);
	    		return commonPrefix(lcpleft, lcpright);
	    	}
	    }
	    
	    private String commonPrefix(String left, String right) {
	    	int min = Math.min(left.length(), right.length());
	    	for (int i = 0; i < min; i++) {
	    		if (left.charAt(i) != right.charAt(i)) {
	    			return left.substring(0, i);
	    		}
	    	}
	    	return left;
	    }
	}
	
	// 官方解法四：二分查找治法
	// 应用二分查找法找到所有字符串的公共前缀的最大长度L
	// 算法的查找区间是 (0, minLen)，其中minLen是输入数据中最短的字符串的长度，同时也是答案的最长可能长度
	// 每一次将查找区间一分为二，然后丢弃一定不包含最终答案的那一个
	// 时间：O(S*log(n))；空间：O(1)
	private static class Solu4 {	    
	    private String longestCommonPrefix(String[] strs) {
	    	if (strs == null || strs.length == 0) return "";
	    	int minLen = Integer.MAX_VALUE;
    		for (String str : strs)
    			minLen = Math.min(minLen, str.length());
    		int low = 1;
    		int high = minLen;
    		
    		while (low < high) {
    			int mid = (low + high) / 2;
    			if (isCommonPrefix(strs, mid)) { // 如果(0, mid)是公共前缀，则查找范围定位到后半段
    				low = mid + 1;
    			} else {
    				high = mid - 1;
    			}
    		}
    		return strs[0].substring(0, (low + high) / 2); // substring(0, 0) = ""
	    }
	    
	    private boolean isCommonPrefix(String[] strs, int len) {
	    	String str1 = strs[0].substring(0, len);
	    	for (int i = 1; i < strs.length; i++)
	    		if (!strs[i].startsWith(str1))
	    			return false;
	    	return true;
	    }
	}
	

}
