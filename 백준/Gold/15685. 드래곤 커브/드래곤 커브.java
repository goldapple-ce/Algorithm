
import java.util.*;
import java.io.*;

public class Main {
	static boolean[][] map = new boolean[101][101];
	// 방향 {>,^,<,v}
	static int[][] dirType = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			
			dragonCurve(x,y,D,G);
		}
		
		for(int y = 0; y < 100; y++) {
			for(int x = 0; x < 100; x++) {
				if(map[y][x] && map[y][x+1]&& map[y+1][x]&&map[y+1][x+1]) answer++;
			}
		}
		
		System.out.println(answer);
	}

	public static void dragonCurve(int x, int y, int D, int G) {
		List<Integer> curves = new ArrayList<>();
		curves.add(D);

		for (int g = 1; g <= G; g++) {
			for (int curve = curves.size() - 1; curve >= 0; curve--)
				curves.add((curves.get(curve) + 1) % 4);
		}

		map[y][x] = true;
		for (int curve : curves) {
			x += dirType[curve][0];
			y += dirType[curve][1];
			map[y][x] = true;
		}
	}
}
