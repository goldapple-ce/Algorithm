import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] numStrs = new String[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            numStrs[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numStrs, new Comparator<String>(){
            public int compare(String o1, String o2){
                return (o2+o1).compareTo(o1+o2);
            }
        });
        
        for(String numStr:numStrs) answer += numStr;
        
        return answer.charAt(0) == '0' ? "0" :answer;
    }
}