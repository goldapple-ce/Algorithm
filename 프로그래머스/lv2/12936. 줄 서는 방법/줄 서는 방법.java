import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> order = new ArrayList<>();
        int total = n;
        
        for(int i = 1; i <= n;i++) order.add(i);

        while(n > 0){
            long facto = factorial(n-1);
            answer[total-n--] = order.remove(k != 0 ?(int)((k-1)/facto) :n);
            k %= facto;
        }
        
        return answer;
    }
    
    public long factorial(int n){
        long value = 1;
        for(int i = 1; i <= n;  i++){
            value *= i;
        }
        return value;
    }
}
    // order = list(range(1,n+1))
    // while n!=0 :
    //     fact = factorial(n-1)
    //     answer.append(order.pop((k-1)//fact))
    //     n,k = n-1, k%fact