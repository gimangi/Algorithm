#include <iostream>
#include <algorithm>
using namespace std;

#define MAX 502
#define BLOCK -1

int d[MAX][MAX] = { 0, };

int max(int a, int b) {
    if (a < b) return b;
    return a;
}

int main() {
    
    int N;
    int result = 0;
    cin >> N;

    // init array
    fill(&d[0][0], &d[MAX-1][MAX-1], BLOCK);

    // input
    for (int i=1; i<=N; i++) {
        // i만큼 cin 하도록
        for (int j=1; j<=i; j++) {
            cin >> d[i][j];
        }
    }

    // dp
    for (int i=2; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            int max_num = max(d[i-1][j-1], d[i-1][j]);
            if (max_num != BLOCK)
                d[i][j] += max_num;
        }
    }

    // result
    for (int i=1; i<=N; i++) {
        if (result < d[N][i])
            result = d[N][i];
    }
    cout << result;

    return 0;
}
