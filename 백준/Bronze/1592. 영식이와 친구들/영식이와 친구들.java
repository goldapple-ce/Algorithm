import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
                L = Integer.parseInt(st.nextToken());
        int[] friends = new int[N];
        int now = 0, answer = 0;
        while (true) {
            friends[now] += 1;
            if (friends[now] == M)
                break;

            if (friends[now] % 2 == 0) { // 짝수 시
                now -= L;
                if (now < 0)
                    now += N;
            } else { // 홀수 시
                now = (now + L) % N;
            }
            answer++;
        }

        System.out.println(answer);
    }

}