import java.util.*;
import java.io.*;

public class Main {
	static final int PLAYER_SIZE = 9;
	static int[][] players;
	static int N;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		players = new int[PLAYER_SIZE][N];
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < PLAYER_SIZE; i++)
				players[i][n] = Integer.parseInt(st.nextToken());
		}
		selectSequence(0, new int[PLAYER_SIZE], new boolean[PLAYER_SIZE]);
		System.out.println(answer);
	}

	public static void selectSequence(int depth, int[] sequence, boolean[] visited) {
		if (depth == PLAYER_SIZE) {
			play(sequence);
			return;
		}

		if (depth == 3) {
			visited[0] = true;
			sequence[depth] = 0;
			selectSequence(depth + 1, sequence, visited);
			return;
		}
		for (int player = 1; player < PLAYER_SIZE; player++) {
			if (!visited[player]) {
				visited[player] = true;
				sequence[depth] = player;
				selectSequence(depth + 1, sequence, visited);
				visited[player] = false;
			}
		}
	}

	public static void play(int[] sequence) {
//		System.out.println(Arrays.toString(sequence));
		int player = 0;
		int score = 0;

		for (int inning = 0; inning < N; inning++) {
			int numOfOut = 0;
			boolean[] baseMan = new boolean[3];
			while (numOfOut < 3) {
				switch (players[sequence[player]][inning]) {
				case 0:
					numOfOut++;
					break;
				case 1:
					if (baseMan[2])
						score++;
					for (int i = 1; i >= 0; i--)
						baseMan[i + 1] = baseMan[i];
					baseMan[0] = true;
					break;
				case 2:
					for (int i = 1; i < 3; i++) {
						if (baseMan[i]) {
							score++;
							baseMan[i] = false;
						}
					}
					if (baseMan[0]) {
						baseMan[0] = false;
						baseMan[2] = true;
					}
					baseMan[1] = true;
					break;
				case 3:
					for (int i = 0; i < 3; i++) {
						if (baseMan[i])
							score++;
						baseMan[i] = false;
					}
					baseMan[2] = true;
					break;
				case 4:
					for (int i = 0; i < 3; i++) {
						if (baseMan[i])
							score++;
						baseMan[i] = false;
					}
					score++;
					break;
				}

				player = (player + 1) % PLAYER_SIZE;

//				System.out.println("player :"+sequence[player]+", baseMan :"+Arrays.toString(baseMan)+", score :"+score+", numOfOut :"+numOfOut);
				
			}
		}
		answer = Math.max(answer, score);
	}
}
