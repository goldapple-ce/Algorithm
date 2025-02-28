import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, L;
    static List<Integer>[] lines, stations;
    static int S, E;
    static boolean[] visitedOfStation, visitedOfLine;
    static int answer;
    static boolean isPosibble;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        Deque<Move> queue = new ArrayDeque<>();

        for(int line : stations[S]){
            if(visitedOfLine[line]) continue;

            visitedOfLine[line] = true;

            for(int next : lines[line]){
                if(visitedOfStation[next]) continue;

                visitedOfStation[next] = true;
                queue.offer(new Move(next, 0));
            }
        }

        while(!queue.isEmpty()){
            Move now = queue.poll();

            if(now.station == E){
                isPosibble = true;
                answer = now.cnt;
                break;
            }
            
            for(int line : stations[now.station]){
                if(visitedOfLine[line]) continue;

                visitedOfLine[line] = true;

                for(int next : lines[line]){
                    if(visitedOfStation[next]) continue;

                    visitedOfStation[next] = true;
                    queue.offer(new Move(next, now.cnt+1));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        lines = new List[L];
        visitedOfLine = new boolean[L];
        stations = new List[N+1];
        visitedOfStation = new boolean[N+1];

        for(int l = 0; l < L; l++){
            lines[l] = new ArrayList<>();
        }
        for(int n = 0; n <= N; n++){
            stations[n] = new ArrayList<>();
        }

        for(int l = 0; l < L; l++){
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                int station = Integer.parseInt(st.nextToken());
                if(station == -1){
                    break;
                }
                lines[l].add(station);
                stations[station].add(l);
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }
    
    static void print() throws Exception {
        System.out.println(isPosibble ? answer : -1);
    }

    static class Move {
        int station, cnt;

        public Move(int station, int cnt) {
            this.station = station;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Move [station=" + station + ", cnt=" + cnt + "]";
        }
        
    }
}