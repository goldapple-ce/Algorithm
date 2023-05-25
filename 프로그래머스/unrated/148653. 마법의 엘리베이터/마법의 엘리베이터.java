class Solution {
    public int solution(int storey) {
        int answer = 0;
        while (storey != 0) {
            int upperNumber = (storey %100)/10;
            int number = storey % 10;
            if (number > 5 || number == 5 && upperNumber>=5) {
                storey += 10;
                answer += (10 - number);
            } else {
                answer += number;
            }
            storey /=  10;
        }  
        return answer;
    }
}