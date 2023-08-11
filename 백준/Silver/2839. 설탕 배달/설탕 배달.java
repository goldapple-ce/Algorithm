
import java.io.*;
import java.util.*;

class Main {
	static Map<Integer,Integer> dict = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int three = 0; three <=5000/3; three++) {
			for(int five = 0; five <= 1000;five++) {
				if((three*3)+(five*5) == N) {
					System.out.println(three+five);
					return;
				}
			}
		}
		System.out.println(-1);
	}
}
