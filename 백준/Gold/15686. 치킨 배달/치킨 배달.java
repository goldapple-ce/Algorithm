import java.io.*;
import java.util.*;

class Position {
	int row;
	int col;

	Position (int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static ArrayList<Position> house = new ArrayList<>();
	static ArrayList<Position> chicken = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static boolean[] open;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		// 미리 집과 치킨집에 해당하는 좌표를 ArrayList에 넣어 둠.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					house.add(new Position(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Position(i, j));
				}
			}
		}

		
		open = new boolean[chicken.size()];

		DFS(0, 0);
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void DFS(int start, int cnt) {
		if (cnt == M) {
			int res = 0;

			for (int i = 0; i < house.size(); i++) {
				int temp = Integer.MAX_VALUE;

				for (int j = 0; j < chicken.size(); j++) {
					if (open[j]) {
						int distance = Math.abs(house.get(i).row - chicken.get(j).row)
								+ Math.abs(house.get(i).col - chicken.get(j).col);
						temp = Math.min(temp, distance);
					}
				}
				res += temp;
			}
			answer = Math.min(answer, res);
			return;
		}

		// 백트래킹
		for (int i = start; i < chicken.size(); i++) {
			open[i] = true;
			DFS(i + 1, cnt + 1);
			open[i] = false;
		}
	}
}