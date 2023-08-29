
import java.util.*;
import java.io.*;

public class Main {
	static int R, C, K;
	static int[][] A = new int[101][101];
	static int rLen=3, cLen=3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int row = 1; row <= 3; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= 3; col++)
				A[row][col] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}

	public static int solution() {
		for (int time = 0; time <= 100; time++) {
			if (A[R][C] == K)
				return time;
			operating();
		}
		return -1;
	}

	public static void operating() {
		if (rLen >= cLen) {
			for (int row = 1; row <= rLen; row++)
				ROper(row);
		} else {
			for (int col = 1; col <= cLen; col++)
				COper(col);
		}
	}

	public static void ROper(int row) {
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		Map<Integer, Integer> dict = new HashMap<>();

		for (int col = 1; col <= cLen; col++) {
			if (A[row][col] == 0)
				continue;
			dict.compute(A[row][col], (num, cnt) -> cnt == null ? 1 : cnt + 1);
		}

		dict.forEach((num, cnt) -> queue.offer(new Pair(num, cnt)));
//		System.out.println(queue);
		int col = 1;
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			A[row][col++] = pair.num;
			A[row][col++] = pair.cnt;
		}

		cLen = Math.max(cLen, col);

		while(col <= 99) {
			A[row][col++] =0;
			A[row][col++] =0;
		}
	}

	public static void COper(int col) {
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		Map<Integer, Integer> dict = new HashMap<>();

		for (int row = 1; row <= rLen; row++) {
			if (A[row][col] == 0)
				continue;
			dict.compute(A[row][col], (num, cnt) -> cnt == null ? 1 : cnt + 1);
		}

		dict.forEach((num, cnt) -> queue.offer(new Pair(num, cnt)));
//		System.out.println(queue);
		int row = 1;
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			A[row++][col] = pair.num;
			A[row++][col] = pair.cnt;
		}

		rLen = Math.max(rLen, row);
		while (row <= 99) {
			A[row++][col] = 0;
			A[row++][col] = 0;
		}
	}
}

class Pair implements Comparable<Pair> {
	int num, cnt;

	public Pair(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Pair o) {
		if (this.cnt == o.cnt)
			return this.num - o.num;
		return this.cnt - o.cnt;
	}

	@Override
	public String toString() {
		return "Pair [num=" + num + ", cnt=" + cnt + "]";
	}
	
	
}