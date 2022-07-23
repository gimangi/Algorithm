#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

#define endl '\n'

bool compare(pair<int, int> &p1, pair<int, int> &p2);
int getLIS(vector<int> &v);
int max(int a, int b);

int solution(vector< pair<int, int> > &v) {
    int N = v.size(); 
    sort(v.begin(), v.end(), compare);
    vector<int> d(N, 0);

    for (int i=0; i<N; i++) {
        d[i] = v[i].second;
    }

    return N - getLIS(d);

}

int main() {
    int N;

    cin >> N;
    vector< pair<int, int> > cord(N, pair<int, int> (0, 0));

    for (int i=0; i<N; i++) {
        cin >> cord[i].first >> cord[i].second;
    }

    cout << solution(cord);

    return 0;
}

bool compare(pair<int, int> &p1, pair<int, int> &p2) {
    return p1.first < p2.first;
}

int getLIS(vector<int> &v) {
    int N = v.size();
    int result = 0;
    vector<int> d(N, 1);

    for (int i=1; i<N; i++) {
        for (int j=0; j<i; j++) {
            if (v[j] < v[i])
                d[i] = max(d[i], d[j]+1);
        }
    }

    // max d[i]
    for (int i=0; i<N; i++) {
        if (result < d[i])
            result = d[i];
    }
    return result;
}

int max(int a, int b) {
    if (a < b)
        return b;
    return a;
}
