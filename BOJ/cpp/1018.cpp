#include <iostream>
#include <vector>
#include <string>
using namespace std;


int fixBlocks(vector<string> &v){
    int a = 0;
    int b = 0;
    int min;
    for(int i=0; i<8; i++)
        for(int j=0; j<8; j++){
            char val = v[i].at(j);
            if(i%2==0){ //짝수 행
                if(j%2==0){
                    if(val!='W') a++; else b++;} //짝수 열
                else{ //홀수 열
                    if(val!='B') a++; else b++;}
            }
            else{   //홀수 행
                if(j%2==0){  //짝수 열
                    if(val!='B') a++; else b++;}
                else{ //홀수 열
                    if(val!='W') a++; else b++;}
            }
        }
    if(a>b) min = b;
    else min = a;
    return min;
}

int main() {
    int n,m;
    int min = 64;
    string str;
    cin >> n >> m;
    vector<string> v;
    while(cin >> str){
        v.push_back(str);
    }
    for(int sx=0; sx<=m-8; sx++){
        for(int sy=0; sy<=n-8; sy++){   // (sx,sy)에서 8*8 탐색
            vector<string> tmp;
            for(int i=0; i<8; i++)
                tmp.push_back(v[sy+i].substr(sx,8));
            int fixBlock = fixBlocks(tmp);
            if(min>fixBlock) min = fixBlock;
        } 
    }
    cout << min;
}
