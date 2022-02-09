#include <iostream>
using namespace std;

#define MAX 1001
int d[MAX][3] = {0,};

int min(int a, int b) {
    if (a < b)  return a;
    return b;
}

int min(int a, int b, int c) {
    if (a <= b && a <= c)
        return a;
    else if (b <= a && b <= c)
        return b;
    return c;
}

int main() {
    int N;
    cin >> N;

    for (int i=1; i<=N; i++) {
        cin >> d[i][0] >> d[i][1] >> d[i][2];
    }

    for (int i=2; i<=N; i++) {
        d[i][0] += min(d[i-1][1], d[i-1][2]);
        d[i][1] += min(d[i-1][0], d[i-1][2]);
        d[i][2] += min(d[i-1][0], d[i-1][1]);
    }
    cout << min(d[N][0], d[N][1], d[N][2]);

    return 0;
}
