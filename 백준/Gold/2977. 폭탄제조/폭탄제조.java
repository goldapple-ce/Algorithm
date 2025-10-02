import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[] needs;
    static int[] remains;
    static Package[] sPackages, lPackages;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int left = 0, right = 1_000_000;
        while(left <= right){
            int mid = (left + right) / 2;
            if(makeBomb(mid)){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
    }

    static boolean makeBomb(int k){
        // System.out.println("k : " + k);
        int money = M;

        for(int n = 0; n < N; n++){
            int price = 1_000_000;
            int need = needs[n] * k - remains[n];
            
            int pCnt = need / sPackages[n].cnt;
            if(need % sPackages[n].cnt != 0) pCnt++;
            // System.out.println(String.format("need : %d, pCnt : %d", need, pCnt));
            
            for(int sp = 0; sp <= pCnt; sp++){
                int n_ = need - sp * sPackages[n].cnt;
                
                int lp = (n_ > 0) ? n_ / lPackages[n].cnt :0;
                if(n_ > 0 && n_ % lPackages[n].cnt != 0) lp++;

                price = Math.min(price, sp * sPackages[n].price + lp * lPackages[n].price);

                // System.out.println(String.format("sp : %d, lp : %d, price : %d", sp, lp, price));
            }

            money -= price;
            if(money < 0) return false;
        }
        // System.out.println("money : " + money);
        if(money < 0) return false;
        return true;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        needs = new int[N];
        remains = new int[N];
        sPackages = new Package[N];
        lPackages = new Package[N];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int need = Integer.parseInt(st.nextToken());
            int remain = Integer.parseInt(st.nextToken());
            int sPackageCnt = Integer.parseInt(st.nextToken());
            int sPackagePrice = Integer.parseInt(st.nextToken());
            int lPackageCnt = Integer.parseInt(st.nextToken());
            int lPackagePrice = Integer.parseInt(st.nextToken());

            needs[n] = need;
            remains[n] = remain;
            sPackages[n] = new Package(sPackagePrice, sPackageCnt);
            lPackages[n] = new Package(lPackagePrice, lPackageCnt);
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Package{
        int price, cnt;

        public Package(int price, int cnt){
            this.price = price;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Package [price=" + price + ", cnt=" + cnt + "]";
        }
    }

}