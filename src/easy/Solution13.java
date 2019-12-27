package easy;

/**
 * 13. 罗马数字转整数
 * 
 * 给定一个罗马数字，将其转换成整数。输入确保在1到3999的范围内。
 * */

import java.util.Map;
import java.util.HashMap;

public class Solution13 {

	public static void main(String[] args) {
		String str = "XXI";
		
		long start1 = System.currentTimeMillis();
		Solu1 solu1 = new Solu1();
		System.out.println(solu1.romanToInt(str));
		System.out.println("执行时间：" + (System.currentTimeMillis()-start1) + " 毫秒");
		
		long start2 = System.currentTimeMillis();
		Solu2 solu2 = new Solu2();
		System.out.println(solu2.romanToInt(str));
		System.out.println("执行时间：" + (System.currentTimeMillis()-start2) + " 毫秒");

	}
	
	// 方法一：把特殊情况直接放入字典
	private static class Solu1 {
	    public int romanToInt(String s) {
	    	Map<String, Integer> map = new HashMap<>();
	    	map.put("I", 1);
	    	map.put("V", 5);
	    	map.put("X", 10);
	    	map.put("L", 50);
	    	map.put("C", 100);
	    	map.put("D", 500);
	    	map.put("M", 1000);
	    	map.put("IV", 4);
	    	map.put("IX", 9);
	    	map.put("XL", 40);
	    	map.put("XC", 90);
	    	map.put("CD", 400);
	    	map.put("CM", 900);
	    	
	    	int num = 0;
	    	
	    	for (int i = 0; i < s.length();) {
	    		if ( i < s.length() - 1 && map.containsKey(s.substring(i, i + 2))) {
	    			num += map.get(s.substring(i, i + 2));
	    			i+=2;
	    		} else {
	    			num += map.get(s.substring(i, i + 1));
	    			i++;
	    		}
	    	}

	    	return num;
	    }
	}
	
	// 方法二：特殊情况单独判断
	private static class Solu2 {
	    public int romanToInt(String s) {
	    	Map<String, Integer> map = new HashMap<>();
	    	map.put("I", 1);
	    	map.put("V", 5);
	    	map.put("X", 10);
	    	map.put("L", 50);
	    	map.put("C", 100);
	    	map.put("D", 500);
	    	map.put("M", 1000);
	    	
	    	int num = 0;
	    	
	    	for (int i = 0; i < s.length();) {
	    		if (i < s.length() - 1) {
	    			String curr = s.substring(i, i + 1);
		    		String next = s.substring(i + 1, i + 2);
		    		if (map.get(curr) < map.get(next)) {
		    			num += map.get(next) - map.get(curr);
		    		} else {
		    			num += map.get(next) + map.get(curr);
		    		}
		    		i+=2;
	    		} else {
	    			num += map.get(s.substring(i, i + 1));
	    			i++;
	    		}
	    	}

	    	return num;
	    }
	}

}
