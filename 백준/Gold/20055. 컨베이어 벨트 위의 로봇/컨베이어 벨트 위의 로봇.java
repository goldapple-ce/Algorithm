
import java.io.*;
import java.util.*;

class Main {
	static int N, K;
	static Belt[] queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		queue = new Belt[N*2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++)
			queue[i] = new Belt(Integer.parseInt(st.nextToken()));

		while (checkDuration(queue)) {
			rotate(queue);

			for (int i = N -2 ; i > 0; i--) {
				Belt now = queue[i], next = queue[i+1];
				if (now.robot && next.dur > 0 && !next.robot) {
					next.robot = now.robot;
					next.dur--;
					now.robot = false;
				}
			}
			queue[N-1].robot = false;

			if (queue[0].dur > 0) {
				queue[0].robot = true;
				queue[0].dur--;
			}
			answer++;
		}
		System.out.println(answer);
	}

	public static void rotate(Belt[] queue) {
		Belt belt = queue[queue.length-1];
		for(int i = queue.length - 1; i > 0; i--)
			queue[i] = queue[i-1];
		queue[0] = belt;
		queue[N-1].robot = false;
	}

	public static boolean checkDuration(Belt[] queue) {
		int cnt = 0;
		for (int i = 0; i < 2 * N; i++) {
			if (queue[i].dur == 0)
				cnt++;
		}
		return cnt < K;
	}
}

class Belt {
	int dur;
	boolean robot;

	public Belt(int dur) {
		this.dur = dur;
		this.robot = false;
	}

	public String toString() {
		return "{dur : " + dur + ", robot :" + robot + "}";
	}
}
