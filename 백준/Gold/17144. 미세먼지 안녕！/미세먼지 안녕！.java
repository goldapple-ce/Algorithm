import java.io.*;
import java.util.*;

class Main {
    static int maxRow, maxCol;
    static int[][] map;
    static int[][][] dirType = {
            { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } },
            { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } } };

    static Position[] airCleaner = new Position[2];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int airCleanerCnt = 0;
        int answer = 2;
        map = new int[maxRow][maxCol];
        for (int row = 0; row < maxRow; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < maxCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == -1)
                    airCleaner[airCleanerCnt++] = new Position(row, col);
            }
        }

        for (int t = 0; t < T; t++)
            spread();

        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                answer += map[row][col];
            }
        }
        System.out.println(answer);
    }

    public static void spread() {
        int[][] spreadMap = new int[maxRow][maxCol];
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                if (map[row][col] > 0) {
                    int dust = map[row][col] / 5, cnt = 0;
                    for (int dir = 0; dir < dirType[0].length; dir++) {
                        int nRow = row + dirType[0][dir][0];
                        int nCol = col + dirType[0][dir][1];

                        if (inRange(nRow, nCol) && map[nRow][nCol] != -1) {
                            spreadMap[nRow][nCol] += dust;
                            cnt++;
                        }
                    }
                    spreadMap[row][col] -= dust * cnt;
                }
            }
        }

        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++)
                map[row][col] += spreadMap[row][col];
        }
        runAirCleaner();
    }

    public static void runAirCleaner() {
        int maxN = (maxCol + airCleaner[0].row - 1) * 2;
        for (int n = 0; n < maxN; n++) {
            int dust = map[airCleaner[0].row][airCleaner[0].col];
            map[airCleaner[0].row][airCleaner[0].col] = airCleaner[0].dust;
            airCleaner[0].dust = dust;
            int nRow = airCleaner[0].row + dirType[0][airCleaner[0].dir][0];
            int nCol = airCleaner[0].col + dirType[0][airCleaner[0].dir][1];
            if (!inRange(nRow, nCol)) {
                airCleaner[0].dir = (airCleaner[0].dir + 1) % dirType[0].length;
                nRow = airCleaner[0].row + dirType[0][airCleaner[0].dir][0];
                nCol = airCleaner[0].col + dirType[0][airCleaner[0].dir][1];
            }
            airCleaner[0].row = nRow;
            airCleaner[0].col = nCol;
        }

        maxN = (maxCol - 1) * 2 + (maxRow - airCleaner[1].row - 1) * 2;
        for (int n = 0; n < maxN; n++) {
            int dust = map[airCleaner[1].row][airCleaner[1].col];
            map[airCleaner[1].row][airCleaner[1].col] = airCleaner[1].dust;
            airCleaner[1].dust = dust;
            int nRow = airCleaner[1].row + dirType[1][airCleaner[1].dir][0];
            int nCol = airCleaner[1].col + dirType[1][airCleaner[1].dir][1];
            if (!inRange(nRow, nCol)) {
                airCleaner[1].dir = (airCleaner[1].dir + 1) % dirType[1].length;
                nRow = airCleaner[1].row + dirType[1][airCleaner[1].dir][0];
                nCol = airCleaner[1].col + dirType[1][airCleaner[1].dir][1];
            }
            airCleaner[1].row = nRow;
            airCleaner[1].col = nCol;
        }

        for (int i = 0; i < 2; i++) {
            airCleaner[i].dust = 0;
            airCleaner[i].dir = 0;
            map[airCleaner[i].row][airCleaner[i].col] = -1;
            map[airCleaner[i].row][airCleaner[i].col + 1] = 0;
        }
    }

    public static boolean inRange(int row, int col) {
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
}

class Position {
    int row, col;
    int dir, dust;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "{row : " + row + ", col : " + col + ", dir : " + dir + ", dust : " + dust + "}";
    }
}
