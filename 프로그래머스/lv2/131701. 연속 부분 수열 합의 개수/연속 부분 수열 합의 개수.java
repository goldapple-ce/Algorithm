import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {

        HashSet<Integer> set = new HashSet<Integer>();
        
        for(int i = 0;i < elements.length; i++){
            int sum = elements[i];
            set.add(sum);
            for(int j = 1;j < elements.length; j++){
                sum += elements[(i+j)%elements.length];
                set.add(sum);
            }
        }
        return set.size();
    }
}