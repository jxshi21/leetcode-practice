package easy;

/**
 * 9. 回文数
 * 
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * */

public class Solution9 {

	public static void main(String[] args) {
		int x = 120;
		
		long start1 = System.currentTimeMillis();
		Solu1 solu1 = new Solu1();
		System.out.println(solu1.isPalindrome(x));
		System.out.println("执行时间：" + (System.currentTimeMillis()-start1) + " 毫秒");
		
		long start2 = System.currentTimeMillis();
		Solu2 solu2 = new Solu2();
		System.out.println(solu2.isPalindrome(x));
		System.out.println("执行时间：" + (System.currentTimeMillis()-start2) + " 毫秒");

	}
	
	// 方法一：借助字符串
	private static class Solu1 {
		private boolean isPalindrome(int x) {
			// 因为是直接比较的字符串，因此不用考虑数字为负这样的数学条件
			String str = String.valueOf(x);
			String rev = new StringBuilder(str).reverse().toString();
			return str.compareTo(rev) == 0;
		}
	}
	
	// 方法二：反转一半数字（反转一半是因为防止溢出）
	private static class Solu2 {
		private boolean isPalindrome(int x) {
			if (x < 0 || (x % 10 == 0 && x != 0)) { // 临界情况处理
				return false;
			} else {
				int rev = 0;
				while (x > rev) {
					rev = rev * 10 + x % 10;
					x /= 10;
				}
				return x == rev || x == rev / 10; // rev / 10 表示数字长度为奇数的情况
			}
		}
	}
	
	
}
