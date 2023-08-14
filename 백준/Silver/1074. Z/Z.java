import java.io.*;
import java.util.*;

class Main {
    static int N, R, C;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        devide(0, 0, (int) Math.pow(2, N));
        System.out.println(answer);
    }

    public static void devide(int row, int col, int size) {
        if (size == 2) {
            answer += (C - col) + ((R - row) << 1);
            return;
        }

        size /= 2;
        if (R < row + size && C < col + size) {
            devide(row, col, size);
        } else if (R < row + size) {
            answer += size * size;
            devide(row, col + size, size);
        } else if (C < col + size) {
            answer += size * size * 2;
            devide(row + size, col, size);
        } else {
            answer += size * size * 3;
            devide(row + size, col + size, size);
        }
    }
}
