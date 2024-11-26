import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, A, B;
    static Team[] teams;

    public static void main(String[] args) throws Exception {
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if(N == 0 && A == 0 && B == 0)
                break;
            input();
            run();
        }
        print();
    }
    
    static void run() throws Exception {
        int answer = 0;

        for(int n = 0; n < N; n++){
            if(teams[n].distA > teams[n].distB){
                if(teams[n].k <= B){
                    answer += teams[n].k * teams[n].distB;
                    B -= teams[n].k;
                }else{
                    answer += B * teams[n].distB;
                    answer += (teams[n].k - B) * teams[n].distA;
                    A -= teams[n].k -B;
                    B -= B;
                }
            }else{
                if(teams[n].k <= A){
                    answer += teams[n].k * teams[n].distA;
                    A -= teams[n].k;
                }else{
                    answer += A * teams[n].distA;
                    answer += (teams[n].k-A) * teams[n].distB;
                    B -= teams[n].k - A;
                    A -= A;
                }
            }
        }
        sb.append(answer).append('\n');
    }
    
    static void input() throws Exception {
        teams = new Team[N];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int distA = Integer.parseInt(st.nextToken());
            int distB = Integer.parseInt(st.nextToken());

            teams[n] = new Team(k, distA, distB);
        }

        Arrays.sort(teams);
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Team implements Comparable<Team>{
        int k,distA, distB;

        public Team(int k, int distA, int distB) {
            this.k = k;
            this.distA = distA;
            this.distB = distB;
        }

        @Override
        public String toString() {
            return "Team [k=" + k + ", distA=" + distA + ", distB=" + distB + "]";
        }

        @Override
        public int compareTo(Team o) {
            return Math.abs(o.distA - o.distB) - Math.abs(this.distA - this.distB);
        }
    }
}