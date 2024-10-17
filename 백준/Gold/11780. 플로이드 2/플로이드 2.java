import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[][] costMap;
    static int INF = 1_000_000_000;
    static List<Integer>[][] ways;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        floydWarshall();
    }

    static void floydWarshall(){
        for(int mid = 1; mid <= N; mid++){
            for(int from = 1; from <= N; from++){
                for(int to = 1; to <= N; to++){
                    if(costMap[from][to] > costMap[from][mid] + costMap[mid][to]){
                        costMap[from][to] = costMap[from][mid] + costMap[mid][to];
                        ways[from][to].clear();
                        ways[from][to].addAll(ways[from][mid]);
                        ways[from][to].addAll(ways[mid][to]);
                    }
                }
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        costMap = new int[N+1][N+1];
        ways = new List[N+1][N+1];

        for(int from = 1; from <= N; from++){
            for(int to = 1; to <= N; to++){
                ways[from][to] = new ArrayList<>();

                if(from == to){
                    continue;
                }

                costMap[from][to] = INF;
            }
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            costMap[from][to] = Math.min(costMap[from][to],cost);

            if(ways[from][to].isEmpty()){
                ways[from][to].add(from);
            }
        }
    }
    
    static void print() throws Exception {
        for(int from = 1; from <= N; from++){
            for(int to = 1; to <= N; to++){
                sb.append(costMap[from][to] == INF ?0 :costMap[from][to]).append(' ');
            }
            sb.append('\n');
        }

        for(int from = 1; from <= N; from++){
            for(int to = 1; to <= N; to++){
                if(ways[from][to].isEmpty()){
                    sb.append(0);
                }else{
                    sb.append(ways[from][to].size()+1).append(' ');
                    for(int city : ways[from][to]){
                        sb.append(city).append(' ');
                    }
                    sb.append(to);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
}