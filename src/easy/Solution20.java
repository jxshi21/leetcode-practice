package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 		1.左括号必须用相同类型的右括号闭合。
 * 		2.左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * */

public class Solution20 {

	public static void main(String[] args) {
		String str1 = "()";		// true
		String str2 = "()[]{}";	// true
		String str3 = "(]";		// false
		String str4 = "([)]";	// false
		String str5 = "{[]}";	// true
		String str6 = "(({([])}"; // false
		
		long start = System.currentTimeMillis();
		System.out.println(new Solution().isValid(str1));
		System.out.println(new Solution().isValid(str2));
		System.out.println(new Solution().isValid(str3));
		System.out.println(new Solution().isValid(str4));
		System.out.println(new Solution().isValid(str5));
		System.out.println(new Solution().isValid(str6));
		System.out.println("【我的solu】执行时间：" + (System.currentTimeMillis()-start) + " 毫秒\n");
		
		long start1 = System.currentTimeMillis();
		System.out.println(new Solu1().isValid(str1));
		System.out.println(new Solu1().isValid(str2));
		System.out.println(new Solu1().isValid(str3));
		System.out.println(new Solu1().isValid(str4));
		System.out.println(new Solu1().isValid(str5));
		System.out.println(new Solu1().isValid(str6));
		System.out.println("【我的solu】执行时间：" + (System.currentTimeMillis()-start1) + " 毫秒\n");
		
	}
	
	// 方法一：我自己写的......
	// emmm思路也是用栈，但是复杂度似乎太高了......原因是因为我不知道java有自带的Stack数据结构......
	private static class Solution {
		
	    public boolean isValid(String s) {
	    	Map<Character, Character> map = new HashMap<Character, Character>();
			map.put('(', ')');
			map.put('{', '}');
			map.put('[', ']');
	    	
	    	if (s == "") return true;
	    	
	    	List<Character> list = new ArrayList<Character>(); // 栈，存放压入当前遍历到的所有左括号
	    	for (int i = 0; i < s.length(); i++) {
	    		if (map.containsKey(s.charAt(i))) {
	    			list.add(s.charAt(i)); // 左括号压入
	    		} else if (list.isEmpty()) { // 当前字符是右括号且list为空
	    			return false;
	    		} else if (map.get(list.get(list.size()-1)) != s.charAt(i)) { // 当前字符是右括号且list为空，但是匹配不上
	    			return false;
	    		} else { // 字符i匹配上了
	    			list.remove(list.size()-1);
	    		}
	    	}
	    	
	    	if (list.isEmpty()) 
	    		return true;
	    	else
	    		return false;
	    }
	}
	
	// 官方解法：栈
	
	// 时间：O(S)；空间：O(1)；S是所有字符串中字符数量的总和（最坏的情况是n个字符串相同，要进行S次字符比较）
	private static class Solu1 {
		
		private HashMap<Character, Character> mappings;
		
		public Solu1() {
			this.mappings = new HashMap<Character, Character>();
			this.mappings.put(')', '(');
		    this.mappings.put('}', '{');
		    this.mappings.put(']', '[');
		}
		
		public boolean isValid(String s) {
			Stack<Character> stack = new Stack<Character>();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (this.mappings.containsKey(c)) {
					char topChar = stack.isEmpty() ? '#' : stack.pop();
					if (topChar != this.mappings.get(c)) {
						return false;
					}
				} else {
					stack.push(c);
				}
			}
			return stack.isEmpty();
		}
	}	

}
