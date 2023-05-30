import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length];
        ArrayList<Integer> cands = new ArrayList<>();

        for(int i = 0; i < cards.length; i++){
            int now = i;
            int cnt = 0;
            while(!visited[now]){
                visited[now] = true;
                now = cards[now]-1;
                cnt++;
            }
            cands.add(cnt);
        }
        Collections.sort(cands,Comparator.reverseOrder());
        return cands.size() != 1 ?cands.get(0)*cands.get(1) :0;
    }
}