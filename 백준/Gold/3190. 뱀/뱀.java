import java.io.*;
import java.util.*;

class Main {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int answer = 0;
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}

		int L = Integer.parseInt(br.readLine());
		Map<Integer,Character> orders = new HashMap<>();
		Deque<Position> snake = new ArrayDeque<>();
		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			orders.put(sec,dir);
		}
		snake.offer(new Position(0, 0, 0));
		map[snake.peek().row][snake.peek().col] = 2;
		while (true) {
			answer++;
			Position head = snake.peekLast();
			Position newHead = new Position(head.row,head.col,head.dir);
			newHead.go();
			if(!inRange(newHead.row,newHead.col)||map[newHead.row][newHead.col] == 2) break;
			if(map[newHead.row][newHead.col] != 1) {
				Position tail = snake.poll();
				map[tail.row][tail.col] = 0;
			}

			if(orders.containsKey(answer)) {
				newHead.turn(orders.get(answer));
			}
			map[newHead.row][newHead.col] = 2;
			snake.offer(newHead);
		}
		System.out.println(answer);
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
	
	public static void printMap(int[][] map) {
		for(int[] row : map) {
			System.out.println(Arrays.toString(row));
		}System.out.println();
	}
}

class Order {
	int sec;
	char dir;

	public Order(int sec, char dir) {
		this.sec = sec;
		this.dir = dir;
	}
}

class Position {
	int row, col;
	int dir;

	static int[][] dirType = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public Position(int row, int col, int dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void go() {
		this.row += dirType[dir][0];
		this.col += dirType[dir][1];
	}
	
	public void turn(char dir) {
		if(dir == 'L') this.dir =(this.dir+1)%4;
		else this.dir = (4+(this.dir-1))%4;
	}

	
	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + ", dir=" + dir + "]";
	}
	
}
