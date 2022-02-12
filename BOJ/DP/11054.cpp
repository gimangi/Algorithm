#include <iostream>
#include <vector>
using namespace std;

int max(int a, int b);

int solution(vector<int> &v) {
    int N = v.size();
    int result = 0;
    // (increase, decrease)
    vector< vector<int> > d(N, vector<int>(2, 1));

    for (int i=1; i<N; i++) {
        for (int j=0; j<i; j++) {
            if (v[j] < v[i]) 
                d[i][0] = max(d[i][0], d[j][0]+1);
        }
    }

    for (int i=N-2; i>=0; i--) {
        for (int j=N-1; j>i; j--) {
            if (v[j] < v[i])
                d[i][1] = max(d[i][1], d[j][1]+1);
        }
    }

    for (int i=0; i<N; i++) {
        result = max(result, d[i][0]+d[i][1]);
    }

    return result - 1;
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
