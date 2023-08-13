import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[N + 1][N + 1];
        int[][][] dp = new int[3][N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++)
                table[row][col] = Integer.parseInt(st.nextToken());
        }
        dp[0][1][2] = 1;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                // System.out.println("row : " + row + ", col :" + col);
                if (table[row][col] == 1)
                    continue;

                dp[0][row][col] += dp[0][row][col - 1] + dp[2][row][col - 1];
                dp[1][row][col] += dp[1][row - 1][col] + dp[2][row - 1][col];

                if (table[row - 1][col] == 0 && table[row][col - 1] == 0)
                    dp[2][row][col] += dp[0][row - 1][col - 1] + dp[1][row - 1][col - 1] + dp[2][row - 1][col - 1];
            }
        }
        System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
    }

    public static void print(int[][] dpRow, int[][] dpCol) {
        for (int row = 1; row < dpRow.length; row++) {
            for (int col = 1; col < dpCol.length; col++) {
                System.out.print(dpRow[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int row = 1; row < dpRow.length; row++) {
            for (int col = 1; col < dpCol.length; col++) {
                System.out.print(dpCol[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
