import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int C, N;
    static City[] cities;
    static int[][] costs;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int c = 1; c <= C; c++){
            costs[0][c] = c-cities[0].weight < 0 ? cities[0].cost  : costs[0][c-cities[0].weight]+cities[0].cost;
        }
        // System.out.println(Arrays.toString(costs[0]));
        for(int n = 1; n < N; n++){
            for(int c = 1; c <= C; c++){
                if(c-cities[n].weight < 0){
                    costs[n][c] = Math.min(cities[n].cost, costs[n-1][c]);
                }else{
                    // System.out.println(String.format("cost[%d][%d] = math.min(%d,%d)", n,c,costs[n][c-cities[n].weight]+cities[n].cost,costs[n-1][c]));
                    costs[n][c] = Math.min(costs[n][c-cities[n].weight]+cities[n].cost, costs[n-1][c]);
                }
            }
        }
        // for(int[] cost : costs){
        //     System.out.println(Arrays.toString(cost));
        // }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cities = new City[N];
        costs = new int[N][C+1];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            cities[n] = new City(weight, cost);
        }
    }
    
    static void print() throws Exception {
        System.out.println(costs[N-1][C]);
    }

    static class City {
        int weight, cost;

        public City(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "City [weight=" + weight + ", cost=" + cost + "]";
        }
    }
}