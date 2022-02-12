#include <iostream>
#include <vector>
using namespace std;

#define MAX_LENGTH 1001

int max(int a, int b);

int solution(vector<int> &v) {
    int N = v.size();
    int result = 0;
    vector<int> d(N, 1);

    for (int i=1; i<N; i++) {
        for (int j=0; j<i; j++) {
            if (v[j] < v[i]) 
                d[i] = max(d[i], d[j]+1);
        
        }
    }

    for (int i=0; i<N; i++) {
        if (result < d[i])
            result = d[i];
    }
    return result;
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
