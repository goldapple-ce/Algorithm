import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, T, M, L;
    static int[][] times, moneys;
    static List<Road>[] roads;
    static PriorityQueue<Road> queue;

    static final int INF = 1_000_000_001;
    static int answer = INF;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dijkstra(1,moneys[0],times[0]);
        dijkstra(N,moneys[1],times[1]);
        // if(moneys[0][N] != INF){
        //     answer = times[0][N];
        //     return;
        // }
        // System.out.println(Arrays.toString(times[0]));
        // System.out.println(Arrays.toString(times[1]));
        // System.out.println(Arrays.toString(moneys[0]));
        // System.out.println(Arrays.toString(moneys[1]));

        for(int n = 1; n <= N;n++){
            int sumTime = times[0][n] + times[1][n];
            int sumMoney = moneys[0][n] + moneys[1][n];
            if(sumTime <= T){
                answer = Math.min(answer, sumMoney);
            }
        }
    }

    static void dijkstra(int start,int[] moneys,int[] times){
        queue = new PriorityQueue<>();
        queue.offer(new Road(start, 0, 0));
        moneys[start] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();
            // System.out.println(now);

            if(moneys[now.idx] < now.money) continue;

            for(Road to : roads[now.idx]){
                int nTime = times[now.idx] + to.time;
                int nMoney = moneys[now.idx] + to.money;

                if(isValid(nTime, nMoney) && moneys[to.idx] > nMoney){
                    moneys[to.idx] = nMoney;
                    times[to.idx] = nTime;

                    queue.offer(new Road(to.idx, nTime, nMoney));
                }
            }
        }
    }

    static boolean isValid(int time , int money){
        return money <= M && time <= T;
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        times = new int[2][N+1];
        moneys = new int[2][N+1];
        roads = new List[N+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
        }
        Arrays.fill(moneys[0], INF);
        Arrays.fill(moneys[1], INF);

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        L = Integer.parseInt(br.readLine());

        for(int l = 0; l < L; l++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            
            roads[from].add(new Road(to, time, money));
            roads[to].add(new Road(from, time, money));
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer == INF ?-1 : answer);
    }

    static class Road implements Comparable<Road>{
        int idx, time, money;

        public Road(int idx, int time, int money) {
            this.idx = idx;
            this.time = time;
            this.money = money;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", time=" + time + ", money=" + money + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.money - o.money;
        }
        
    }
}