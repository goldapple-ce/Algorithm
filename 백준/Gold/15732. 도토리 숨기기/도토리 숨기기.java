import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, K;
    static long D;
    static Rule[] rules;
    static int[] box;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int left = 1, right = N;

        while(left <= right){
            int mid = (left+ right) / 2;

            long sum = findSum(mid);

            if(D <= sum){
                right = mid -1;
                answer = mid;
            }else{
                left = mid+1;
            }
            // System.out.println(mid+", "+answer + ", "+ sum);

        }

    }

    static long findSum(int idx){
        long sum = 0;

        for(Rule rule : rules){
            if(idx < rule.from){
                continue;
            }

            sum += (Math.min(idx, rule.to) - rule.from) / rule.gap + 1;
        }

        return sum;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        rules = new Rule[K];

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int gap = Integer.parseInt(st.nextToken());

            rules[k] = new Rule(from, to, gap);
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Rule {
        int from, to, gap;

        public Rule(int from, int to, int gap) {
            this.from = from;
            this.to = to;
            this.gap = gap;
        }

        @Override
        public String toString() {
            return "Rule [from=" + from + ", to=" + to + ", gap=" + gap + "]";
        }
    }
}