import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static Relation[] relations;
    static int[] parent;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Relation relation : relations){
            
            if(find(relation.from) != find(relation.to)){
                union(relation.from, relation.to);
                answer -= relation.love;
            }
        }
    }
    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x > y){
            parent[x] = y;
        }else{
            parent[y] = x;
        }
    }

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        relations = new Relation[M];
        parent = new int[N+1];

        for(int n = 0; n <=N; n++){
            parent[n] = n;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int love = Integer.parseInt(st.nextToken());
            int relation = Integer.parseInt(st.nextToken());

            answer += love;

            relations[m] = new Relation(from, to, love, relation);
        }
        Arrays.sort(relations);
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Relation implements Comparable<Relation>{
        int from, to;
        int love, relation;

        public Relation(int from, int to, int love, int relation){
            this.from = from;
            this.to = to;
            this.love = love;
            this.relation = relation;
        }

        @Override
        public String toString() {
            return "Relation [from=" + from + ", to=" + to + ", love=" + love + ", relation=" + relation + "]";
        }

        @Override
        public int compareTo(Relation o){
            if(this.relation == o.relation){
                return o.love - this.love;
            }

            return o.relation - this.relation;
        }
        
    }
}