#include <iostream>
using namespace std;

#define LARGE 1000000000
#define MAX 101

int main() {

    int N;
    long d[MAX][10] = { 0, };
    int result = 0;
    cin >> N;

    for (int i=1; i<10; i++) {
        d[1][i] = 1;
    }

    for (int i=2; i<=N; i++) {

        d[i][0] = d[i-1][1];
        d[i][9] = d[i-1][8];

        for (int j=1; j<9; j++) {
            d[i][j] = (d[i-1][j-1] + d[i-1][j+1]) % LARGE;
        }

    }

    for (int i=0; i<10; i++) {
        result = (result + d[N][i]) % LARGE;
    }

    cout << result;

    return 0;
}

