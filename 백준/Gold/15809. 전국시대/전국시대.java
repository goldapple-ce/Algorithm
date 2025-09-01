import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[] powers;
    static int[][] opers;
    static int[] countries;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int[] oper : opers){
            int o = oper[0], c1 = oper[1], c2 = oper[2];
            if(o == 1){
                union(c1, c2);
            }else{
                int pc1 = find(c1), pc2 = find(c2);
                int ppc1 = powers[pc1], ppc2 = powers[pc2];

                if(ppc1 == ppc2){
                    powers[pc1] = powers[pc2] = 0;
                }else if(ppc1 > ppc2){
                    powers[pc1] -= ppc2;
                    powers[pc2] = 0;
                    countries[pc2] = pc1;
                }else{
                    powers[pc2] -= ppc1;
                    powers[pc1] = 0;
                    countries[pc1] = pc2;
                }
            }
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x > y) {
            countries[x] = y;
            powers[y] += powers[x];
            powers[x] = 0;
        }else {
            countries[y] = x;
            powers[x] += powers[y];
            powers[y] = 0;
        }
    }

    static int find(int x){
        if(countries[x] == x) return x;
        return countries[x] = find(countries[x]);
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        powers = new int[N+1];
        countries = new int[N+1];
        opers = new int[M][3];

        for(int n = 1; n <= N; n++){
            powers[n] = Integer.parseInt(br.readLine());
            countries[n] = n;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int country1 = Integer.parseInt(st.nextToken());
            int country2 = Integer.parseInt(st.nextToken());

            opers[m] = new int[]{oper, country1, country2};
        }
    }
    
    static void print() throws Exception {
        int answer = 0;
        Arrays.sort(powers);

        for(int n = 0; n <= N; n++){
            if(powers[n] > 0){
                answer++;
                sb.append(powers[n] + " ");
            }
        }
        
        System.out.println(answer);
        System.out.println(sb);
    }

}