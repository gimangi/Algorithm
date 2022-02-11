#include <iostream>
#include <vector>
using namespace std;

#define INF 123456789

int min(int a, int b, int c);

int main() {

    int N;
    cin >> N;
    vector<int> d(N+1);

    d[1] = 0;

    for (int i=2; i<=N; i++) {
        int case_one = INF, case_two = INF, case_three;

        if (i%3 == 0) 
            case_one = d[i/3]+1;
        if (i%2 == 0)
            case_two = d[i/2]+1;
        case_three = d[i-1]+1;

        d[i] = min(case_one, case_two, case_three);

    }

    cout << d[N];

    return 0;
}

int min(int a, int b, int c) {
    if (a <= b && a <= c)
        return a;
    else if (b <= a && b <= c)
        return b;
    return c;
}
