import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[] costs;
    static List<Road>[] roads;

    static final int INF = 8500;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(0, 0));
        costs[0] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(now.cost > costs[now.idx]){
                continue;
            }

            for(Road road : roads[now.idx]){
                int nCost = now.cost + road.cost;

                if(nCost < costs[road.idx]){
                    costs[road.idx] = nCost;
                    queue.offer(new Road(road.idx, nCost));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        Position S = new Position(row, col);

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        Position E = new Position(row, col);

        N = Integer.parseInt(br.readLine()) + 2;

        costs = new int[N];
        roads = new List[N];

        for(int n = 0; n < N; n++){
            roads[n] = new ArrayList<>();
            costs[n] = INF;
        }

        Position[] villages = new Position[N];
        villages[0] = S;
        villages[N-1] = E;

        for(int n = 1; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            villages[n] = new Position(row, col);
        }

        boolean[] notPrimes = new boolean[INF];
        findPrime(notPrimes);

        for(int from = 0; from < N-1; from++){
            for(int to = from+1; to < N; to++){
                int cost = (int) Math.sqrt(Math.pow(villages[from].row - villages[to].row,2) + Math.pow(villages[from].col - villages[to].col, 2));
                
                if(!notPrimes[cost]){
                    roads[from].add(new Road(to, cost));
                    roads[to].add(new Road(from, cost));
                }
            }
        }

    }
    
    static void print() throws Exception {
        System.out.println(costs[N-1] == INF ?-1 : costs[N-1]);
    }

    static void findPrime(boolean[] notPrimes){
        notPrimes[0] = notPrimes[1] = true;

        for(int i = 2; i < Math.sqrt(notPrimes.length); i++){
            if(!notPrimes[i]){
                for(int j = i*i; j < notPrimes.length; j += i){
                    notPrimes[j] = true;
                }
            }
        }
    }

    static class Road implements Comparable<Road>{
        int idx, cost;

        public Road(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.cost - o.cost;
        }
    }

    static class Position {
        int row, col;

        public Position(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + "]";
        }
    }
}