import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        Stack<Integer> deleted = new Stack<>();
        int queueSize = n;

        for(String cmd : cmds){
            char order = cmd.charAt(0);
            switch(order){
                case 'U':
                    k -= Integer.valueOf(cmd.substring(2));
                    break;
                case 'D':
                    k += Integer.valueOf(cmd.substring(2));
                    break;
                case 'C':
                    deleted.add(k);
                    if(k == --queueSize) k--;
                    break;
                case 'Z':
                    if(deleted.pop() <= k) k++;
                    queueSize++;
                    break;
            }
        }
        StringBuilder answer = new StringBuilder("O".repeat(queueSize));
        while(!deleted.isEmpty())
            answer.insert(deleted.pop(), "X");
        return answer.toString();
    }
}