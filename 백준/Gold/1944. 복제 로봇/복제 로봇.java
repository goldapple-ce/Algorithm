import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[][] map;
    static Position start;
    static PriorityQueue<Road> roads;
    static int[] parent;
    static int[][] dirType = {{0,1},{1,0},{0,-1},{-1,0}};
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                if(map[row][col] > 1){
                    findRoad(new Position(row, col, 0));
                }
            }
        }

        while(!roads.isEmpty()){
            Road road = roads.poll();

            if(find(road.from) != find(road.to)){
                union(road.from, road.to);
                answer += road.dist;
            }
        }

        for(int m = 2; m <= M+1; m++){
            if(parent[m] != parent[m+1]){
                answer = -1;
                break;
            }
        }
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }

    static void findRoad(Position s){
        int sIdx = map[s.row][s.col];
        Deque<Position> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(s);
        visited[s.row][s.col] = true;

        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(map[now.row][now.col] > 1){
                roads.add(new Road(sIdx,map[now.row][now.col], now.depth));
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];
                if(inRange(nRow, nCol) && map[nRow][nCol] != 1 && !visited[nRow][nCol]){
                    visited[nRow][nCol] = true;
                    queue.offer(new Position(nRow, nCol,now.depth+1));
                }
            }
        }
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < N && 0 <= col && col < N;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        roads = new PriorityQueue<>();
        parent = new int[M+3];

        for(int m = 2; m <= M+2;m++){
            parent[m] = m;
        }

        int idx = 3;

        for(int row = 0; row < N; row++){
            String strRow = br.readLine();
            for(int col = 0; col < N; col++){
                char state = strRow.charAt(col);
                if(state == '1'){
                    map[row][col] = 1;
                }else if(state == '0'){
                    map[row][col] = 0;
                }else if(state == 'S'){
                    start = new Position(row, col,0);
                    map[row][col] = 2;
                }else{
                    map[row][col] = idx++;
                }
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer == 0 ?-1 : answer);
    }

    static class Position {
        int row, col;
        int depth;

        public Position(int row, int col,int depth){
            this.row = row;
            this.col = col;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + "]";
        }
        
    }

    static class Road implements Comparable<Road>{
        int from, to, dist;

        public Road(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Road [from=" + from + ", to=" + to + ", dist=" + dist + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.dist - o.dist;
        }
        
    }
}