import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static char[] cards;
    static int M, K;
    static List<Road>[] roads;

    static int answer;
    static int[][] scores;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Move> queue = new PriorityQueue<>((o1,o2)->{
            if(o1.score == o2.score){
                return o1.cnt - o2.cnt;
            }
            return o2.score - o1.score;
        });

        queue.offer(new Move(1, 0,0));

        while(!queue.isEmpty()){
            Move now = queue.poll();

            if(now.cnt == N){
                answer = Math.max(answer,now.score);
                continue;
            }

            for(Road road : roads[now.idx]){
                int nScore = now.score + (cards[now.cnt] == road.card ?10 :0);

                if(scores[now.cnt][road.idx] < nScore){
                    scores[now.cnt][road.idx] = nScore;
                    queue.offer(new Move(road.idx, now.cnt+1, nScore));
                }
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        cards = new char[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            cards[n] = st.nextToken().charAt(0);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        roads = new List[M+1];
        scores = new int[N][M+1];

        for(int m = 0; m <= M; m++){
            roads[m] = new ArrayList<>();
        }

        for(int k = 0;k < K; k++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            char card = st.nextToken().charAt(0);

            roads[from].add(new Road(to, card));
            roads[to].add(new Road(from, card));
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Move {
        int idx, cnt, score;

        public Move(int idx, int cnt, int score) {
            this.idx = idx;
            this.cnt = cnt;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Move [idx=" + idx + ", cnt=" + cnt + ", score=" + score + "]";
        }
        
    }

    static class Road {
        int idx;
        char card;

        public Road(int idx, char card){
            this.idx = idx;
            this.card = card;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", card=" + card + "]";
        }
        
    }
}