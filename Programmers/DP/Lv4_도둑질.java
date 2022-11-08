class Solution {
    
    public int solution(int[] money) {
        int len = money.length;
        int[] dp_first = new int[len];
        int[] dp_second = new int[len];
        
        dp_first[0] = dp_first[1] = money[0];
        dp_second[1] = money[1];
        
        for (int i = 2; i < len; i++) {
            dp_first[i] = Math.max(dp_first[i - 1], dp_first[i - 2] + money[i]);
            dp_second[i] = Math.max(dp_second[i - 1], dp_second[i - 2]  + money[i]);
        }
        
        return Math.max(dp_first[len - 2], dp_second[len - 1]);
    }
}
