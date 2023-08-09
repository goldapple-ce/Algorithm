
import java.io.*;
import java.util.*;

class Main {
	static int[][] originMap;
	static int[][] dirType = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N, M, K;
	static boolean[][] visited;
	static Order[][] permut;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		originMap = new int[N][M];
		Order[] orders = new Order[K];
		permut = new Order[K][K];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++)
				originMap[row][col] = Integer.parseInt(st.nextToken());
		}

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			orders[k] = new Order(r, c, s);
		}

		for (int i = 0; i < K; i++) {
			Order[] outputs = new Order[K];
			boolean[] visited = new boolean[K];
			visited[i] = true;
			outputs[0] = orders[i];
			permutation(i,orders, outputs, 1, visited);
		}
		System.out.println(answer);
	}

	public static void permutation(int num,Order[] orders, Order[] outputs, int depth, boolean[] visited) {
		if (depth == K) {
			rotate(outputs);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				outputs[depth] = orders[i];
				permutation(num,orders, outputs, depth + 1, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void rotate(Order[] orders) {
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++)
			map[i] = Arrays.copyOf(originMap[i], M);

		for (Order order : orders) {
			visited = new boolean[N][M];
			for (int n = 0; n < order.s; n++)
				rotate(order,map, n);
		}
		for (int[] row : map)
			answer = Math.min(answer, Arrays.stream(row).sum());
	}

	public static void rotate(Order order,int[][] map, int n) {
		Position now = new Position(order.r - order.s + n, order.c - order.s + n,
				map[order.r - order.s + n][order.c - order.s + n]);

		for (int j = 0; j < 4 * (2 * (order.s-n) + 1) - 4; j++) {
			if(j != 0 && j % (2*(order.s-n)) == 0)
				now.dir += 1;
			now.row += dirType[now.dir][0];
			now.col += dirType[now.dir][1];
			
			visited[now.row][now.col] = true;
			int temp = map[now.row][now.col];
			map[now.row][now.col] = now.val;
			now.val = temp;
		}
	}

	public static boolean inRange(int row, int col, Order order, int n) {
		return order.r - order.s + n <= row && row <= order.r + order.s - n && order.c - order.s + n <= col
				&& col <= order.c + order.s - n;
	}
}

class Order {
	int r, c, s;

	public Order(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}

	public String toString() {
		return "{r : " + r + ", c : " + c + ", s : " + s + "}";
	}
}

class Position {
	int row, col;
	int dir, val;

	public Position(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.dir = 0;
	}

	public String toString() {
		return "{row : " + row + ", col : " + col + ", dir : " + dir + ", val :" + val + "}";
	}
}