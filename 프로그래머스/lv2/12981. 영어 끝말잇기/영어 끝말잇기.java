import java.util.HashSet;
import java.lang.Math;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> set = new HashSet<>();
        set.add(words[0]);
        char end = words[0].charAt(words[0].length()-1);
        for(int i = 1;i<words.length;i++){
            char start = words[i].charAt(0);
            set.add(words[i]);
            if (end != start || set.size() != i+1){
                return new int[] {(int)(i%n)+1,(int)Math.ceil((double)(i+1)/n)};
            }
            end = words[i].charAt(words[i].length()-1);
        }
        return new int[] {0,0};
    }
}