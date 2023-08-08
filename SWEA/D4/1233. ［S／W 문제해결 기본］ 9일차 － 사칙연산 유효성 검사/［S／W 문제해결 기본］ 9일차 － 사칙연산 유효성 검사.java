
import java.util.*;
import java.io.*;

class Solution {
	static Set<String> dict = new HashSet<>();

	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\02_Java\\cote\\src\\cote\\swea\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		init();
		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder().append('#').append(test_case).append(' ');
			int N = Integer.parseInt(br.readLine());
			boolean flag = true;
			for (int n = 0; n < N; n++) {
				String[] strs = br.readLine().split(" ");
				if (strs.length == 4 && !dict.contains(strs[1]))
					flag = false;
			}
			if (flag)
				sb.append('1');
			else
				sb.append('0');
			System.out.println(sb);
		}
	}

	public static void init() {
		dict.add("-");
		dict.add("*");
		dict.add("/");
		dict.add("+");
	}
}