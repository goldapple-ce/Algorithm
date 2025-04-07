import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol, P;
    static char[][] map;
    static Player[] players;
    static int bossHp;
    
    static boolean[][][] visited;
    static boolean[] isAttack;
    static int answer, damage;

    static Deque<Player> queue = new ArrayDeque<>(); 

    static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};
    

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Player player : players){
            queue.offer(player);
            visited[player.id][player.row][player.col] = true;
        }

        while(!queue.isEmpty()){
            bossHp -= damage;

            if(bossHp <= 0){
                return;
            }

            Deque<Player> nextQueue = new ArrayDeque<>();

            while(!queue.isEmpty()){
                Player now = queue.poll();

                if(isAttack[now.id]){
                    continue;
                }
    
                if(map[now.row][now.col] == 'B'){
                    isAttack[now.id] = true;
                    damage += now.dps;
                    answer++;
                    continue;
                }
    
                for(int[] dir : dirType){
                    int nRow = now.row + dir[0];
                    int nCol = now.col + dir[1];
    
                    if(inRange(nRow, nCol) && map[nRow][nCol] != 'X' && !visited[now.id][nRow][nCol]){
                        visited[now.id][nRow][nCol] = true;
                        nextQueue.offer(new Player(now.id, nRow, nCol, now.dps, now.cnt + 1));
                    }
                }
            }

            queue = nextQueue;
        }
    }
    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        map = new char[maxRow][maxCol];
        players = new Player[P];
        visited = new boolean[P][maxRow][maxCol];
        isAttack = new boolean[P];

        Map<Character, Integer> ids = new HashMap<>();
        int idx = 0;

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                map[row][col] = strRow.charAt(col);
                if(Character.isLowerCase(map[row][col])){
                    players[idx] = new Player(idx, row,col,0,0);
                    ids.put(map[row][col],idx);
                    idx++;
                }
            }
        }

        for(int p = 0; p < P; p++){
            st = new StringTokenizer(br.readLine());
            char id = st.nextToken().charAt(0);
            int dps = Integer.parseInt(st.nextToken());
            
            players[ids.get(id)].dps = dps;
        }

        bossHp = Integer.parseInt(br.readLine());
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Player {
        int id;
        int row, col;
        int dps, cnt;

        public Player(int id, int row, int col, int dps, int cnt) {
            this.id = id;
            this.row = row;
            this.col = col;
            this.dps = dps;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Player [id=" + id + ", row=" + row + ", col=" + col + ", cnt=" + cnt + "]";
        }
        
    }
}