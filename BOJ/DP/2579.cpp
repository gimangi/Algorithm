#include <iostream>
#include <vector>
using namespace std;

int max(int a, int b);


int main() {

    int T;
    cin >> T;
    vector<int> score(T);
    vector<int> dp(T);

    // input
    for (int i=0; i<T; i++) {
        cin >> score[i];
    }

    dp[0] = score[0];
    dp[1] = dp[0]+score[1];
    dp[2] = max(dp[0]+score[2], score[1]+score[2]);

    // dp
    for (int i=3; i<T; i++) {
        dp[i] = max(dp[i-2]+score[i], dp[i-3]+score[i-1]+score[i]);
    }

    cout << dp[T-1];

    return 0;
}


int max(int a, int b) {
    if (a < b)
        return b;
    return a;
}
