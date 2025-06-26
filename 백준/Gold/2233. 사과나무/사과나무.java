import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[] way, nodeIdx;
    static int X, Y;
    static int DEPTH;
    static int[][] parent;
    static int[] depths;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        findParent();
        fillParent();
        answer = lca(nodeIdx[X], nodeIdx[Y]);
    }

    static int lca(int x, int y){
        if(depths[x] > depths[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int d = DEPTH-1; d >= 0; d--){
            if(Math.pow(2, d) <= depths[y] - depths[x]){
                y = parent[d][y];
            }
        }

        if(x == y){
            return x;
        }

        for(int d = DEPTH-1; d >= 0; d--){
            if(parent[d][x] != parent[d][y]){
                x = parent[d][x];
                y = parent[d][y];
            }
        }

        return parent[0][x];
    }

    static void fillParent(){
        for(int d = 1; d < DEPTH; d++){
            for(int n = 1; n <= N; n++){
                parent[d][n] = parent[d-1][parent[d-1][n]];
            }
        }
    }

    static void findParent(){
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        int d = -1;
        for(int n = 0; n <= 2 * N; n++){
            if(way[n] == 0){
                nodeIdx[n] = idx;
                depths[idx] = d++;
                stack.add(idx++);
            }else{
                int p = stack.pop();
                nodeIdx[n] = p;
                parent[0][p] = stack.peek();
                d--;
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        DEPTH = (int) (Math.log(N) / Math.log(2)) +1;

        way = new int[2 * N+ 1];
        nodeIdx = new int[2 * N +1];
        parent = new int[DEPTH][N+1];
        depths = new int[N+1];

        String strWay = br.readLine();
        for(int n = 0; n < 2 * N ;n++){
            way[n+1] = strWay.charAt(n) - '0';
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
    }
    
    static void print() throws Exception {
        for(int i=1; i<= N*2; i++) {
			if(nodeIdx[i] == answer) {
				System.out.print(i+" ");
			}
		}
    }
}