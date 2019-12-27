package easy;

/**
 * 7. 整数反转
 * 
 * 镜像问题：反转字符串
 * 
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * */

public class Solution7 {

	public static void main(String[] args) {
		int num = -123;
		
		long start1 = System.currentTimeMillis();
		Solu1 solu1 = new Solu1();
		int rst1 = solu1.reverse(num);
		System.out.println(rst1);
		System.out.println("执行时间：" + (System.currentTimeMillis()-start1) + " 毫秒");
		
		long start2 = System.currentTimeMillis();
		Solu2 solu2 = new Solu2();
		int rst2 = solu2.reverse(num);
		System.out.println(rst2);
		System.out.println("执行时间：" + (System.currentTimeMillis()-start2) + " 毫秒");

	}
	
	// 方法一（官方）：使用数学方法模拟堆栈操作 + 溢出前检查
	// O(log(x)) O(1)
	private static class Solu1 {
		public int reverse(int x) {
			int rev = 0;			
			while(x != 0) {
				int pop = x % 10;
				x /= 10;
				if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;	// 不知道末位的时候可以直接计算Integer.MAX_VALUE % 10
				if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop < -8)) return 0;	// 不知道末位的时候可以直接计算Integer.MIN_VALUE % 10
				rev = rev * 10 + pop;
			}
			return rev;
		}
	}
	
	// 方法二：字符串
	private static class Solu2 {
		public int reverse(int x) {
			StringBuilder str = new StringBuilder("");
			if (x < 0) {
				str.append("-");
			}
			try {
				return Integer.parseInt(str.toString() + new StringBuilder(String.valueOf(Math.abs(x))).reverse().toString());
			} catch(Exception e) {
				return 0;
			}
		}
	}
	

}
