#include <iostream>
using namespace std;

#define MAX 1000001
int d[MAX] = {0,};

int main() {
    int N;    
    cin >> N;

    d[1] = 1;
    d[2] = 2;

    for (int i = 3; i <= N; i++) {
        d[i] = d[i-1] + d[i-2];
        d[i] %= 15746;
    }

    cout << d[N];

    return 0;
}
