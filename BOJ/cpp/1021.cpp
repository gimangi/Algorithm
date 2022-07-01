#include <iostream>
#include <deque>
#include <vector>
using namespace std;

int shiftLeft(deque<int> &dq, int n){
    int cnt = 0;
    if(dq.empty())  return 0;
    while(dq.front()!=n){
        dq.push_back(dq.front());
        dq.pop_front();
        cnt++;
    }
    return cnt;
}

int shiftRight(deque<int> &dq, int n){
    int cnt = 0;
    if(dq.empty())  return 0;
    while(dq.front()!=n){
        dq.push_front(dq.back());
        dq.pop_back();
        cnt++;
    }
    return cnt;
}

int main() {
    deque<int> dq;
    vector<int> find;
    int n, m;
    int input;
    int totalCnt = 0;
    cin >> n >> m;
    for(int i=1; i<=n; i++) dq.push_back(i);    //setup
    for(int i=0; i<m; i++) { cin >> input; find.push_back(input); } //setup
    
    for(int idx=0; idx<find.size(); idx++){
        if(dq.empty())  break;
        if(dq.front()==find[idx]) //find
            dq.pop_front();
        else{
            deque<int> l_tmp = dq;
            deque<int> r_tmp = dq;
            int leftCnt = shiftLeft(l_tmp, find[idx]);
            int rightCnt = shiftRight(r_tmp, find[idx]);
            if(leftCnt<rightCnt){
                totalCnt+=leftCnt;
                shiftLeft(dq, find[idx]);
                dq.pop_front();
            }
            else{
                totalCnt+=rightCnt;
                shiftRight(dq, find[idx]);
                dq.pop_front();
            }
        }
    }
    cout << totalCnt;
}
