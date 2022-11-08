class Solution {
    
    final static int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
        for (int p = 0; p < puddles.length; p++)
            dp[puddles[p][0]][puddles[p][1]] = -1;
        
        dp[1][1] = 1;
        
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                
                if (dp[x][y] == -1) {
                    dp[x][y] = 0;
                    continue;
                }
                
                dp[x][y] += dp[x - 1][y] % MOD;
                dp[x][y] += dp[x][y - 1] % MOD;
            }
        }
        
        return dp[m][n] % MOD;
    }
}
