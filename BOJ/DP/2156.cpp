#include <iostream>
#include <vector>
using namespace std;

int max(int a, int b);

int solution(vector<int> &v) {
    int N = v.size();

    vector< vector<int> > dp(N, vector<int>(2, 0));

    dp[0][1] = v[0];

    // init
    if (N > 1) {
        dp[1][0] = dp[0][1];
        dp[1][1] = dp[0][1] + v[1];
    }

    for (int i=2; i<N; i++) {
        // not drink
        dp[i][0] = max(dp[i-1][0], dp[i-1][1]);

        // drink
        dp[i][1] = max(dp[i-2][0]+v[i-1]+v[i], dp[i-2][1]+v[i]);
    }

    return max(dp[N-1][0], dp[N-1][1]);
}

int main() {

    int N;
    cin >> N;
    vector<int> v(N);

    for (int i=0; i<N; i++) {
        cin >> v[i];
    }
    
    cout << solution(v);

    return 0;
}

int max(int a, int b) {
    if (a < b) return b;
    return a;
}
