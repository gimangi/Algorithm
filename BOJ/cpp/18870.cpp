#include <iostream>
#include <algorithm>
#include <vector>
#include <tuple>
using namespace std;

bool comp(tuple<int,int,int> &t1, tuple<int,int,int> &t2){
    if(get<1>(t1)<get<1>(t2))
        return true;
    return false;
}

int main() {
    int n;
    cin >> n;
    vector<tuple<int,int,int>> v(n);   //num, index, result
    for(int i=0; i<n; i++){
        int temp;
        cin >> temp;
        tuple<int,int,int> t = make_tuple(temp, i, 1);
        v[i] = t;
    }
    sort(v.begin(), v.end());
    int prev = 198765432;
    int sp = 0;
    int ep = 0;
    int cnt = -1;
    for(int i=0; i<n; i++){
        if(prev!=get<0>(v[i])){ sp = i; cnt++;}
        else    ep=i;
        if(sp<ep){
            if((i+1<n&&get<0>(v[i])!=get<0>(v[i+1])) || i==n-1){
                for(int j=sp; j<=ep; j++)
                    get<2>(v[j]) = cnt;
            }
        }
        else{
            get<2>(v[i]) = cnt;
        }
        prev = get<0>(v[i]);
    }
    
    sort(v.begin(), v.end(), comp);
        
    for(int i=0; i<n; i++)
        cout << get<2>(v[i]) << " ";
    
}
