#include <iostream>
using namespace std;
#define MAX 101

long dp(int n) {
    long d[MAX] = {0,};

    d[1] = 1;
    d[2] = 1;
    d[3] = 1;

    for (int i = 4; i <= n; i++) {
        d[i] = d[i-2] + d[i-3];
    }

    return d[n];
}

int main() {
    int T;    
    cin >> T;

    while (T--) {
        int N;
        cin >> N;
        cout << dp(N) << endl;
    }
    

    return 0;
}
