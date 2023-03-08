import java.lang.Math;
class Solution {
    public int solution(int[][] sizes) {
        int width = 0,height = 0;
        for (int[] size : sizes){
            if (width < Math.max(size[0],size[1])) width = Math.max(size[0],size[1]);
            if (height < Math.min(size[0],size[1])) height = Math.min(size[0],size[1]);
            
        }
        return width*height;
    }
}