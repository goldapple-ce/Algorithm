import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    static int[] dI = {1,0,-1,0};
    static int[] dJ = {0,-1,0,1};
    static ArrayList<Integer> arr = new ArrayList<>();

    public int[] solution(String[] maps) {

        int[][] map = new int[maps.length+2][maps[0].length()+2];
        Boolean[][] visit = new Boolean[maps.length+2][maps[0].length()+2];
        for(int[] temp: map) {
            Arrays.fill(temp, 0);
        }
        for(Boolean[] temp: visit) {
            Arrays.fill(temp, true);
        }

        Boolean flag =false;

        for(int i = 1; i <= maps.length; i++) {
            for(int j = 1; j <= maps[0].length(); j++) {
                if(maps[i-1].charAt(j-1) == 'X') {
                    map[i][j] = 0;
                }
                else {
                    map[i][j] = maps[i-1].charAt(j-1) - '0';
                    visit[i][j] = false;
                    flag = true;
                }
            }
        }

        if(!flag) {
            return new int[] {-1};
        }

        for(int i = 1; i <= maps.length; i++) {
            for(int j = 1; j <= maps[0].length(); j++) {
                if(map[i][j] > 0 && !visit[i][j]) {
                    DFS(visit, map, i, j,0);
                }
            }
        }



        int ans[] = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++) {
            ans[i] = arr.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }

    int DFS(Boolean[][] visit, int[][] map, int i, int j, int depth) {
        if(visit[i][j] || map[i][j] == 0) {
            return 0;
        }

        visit[i][j] = true;
        int ans = map[i][j];
        for(int k = 0; k < 4; k++) {
            ans += DFS(visit,map,i+dI[k],j+dJ[k], depth+1);
        }
        if(depth == 0) {
        arr.add(ans);
        }
        return ans;
    }
}