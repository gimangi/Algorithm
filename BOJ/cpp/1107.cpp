#include <iostream>
#include <vector>
#include <cmath>
#define MAX 1000001
#define BUTTON 10
#define INF 1234567890
using namespace std;

int targetCh;
int brokens;
const int nowCh = 100;
bool isBroken[BUTTON] = {false,};

int length(int n){
	if(n==0) return 1; 
	int cnt = 0;
	while(n>0) {n/=10; cnt++;}
	return cnt;
}

bool isPossible(int n){
	if(n==0){
		if(!isBroken[0]) return true;
		else return false;
	}
	while(n>0){
		if(isBroken[n%10]) return false;
		n/=10;
	}
	return true;
}

int opers(int n){
	int min = INF;
	for(int i=0; i<MAX; i++){	//채널 이동하는 경우
		if(isPossible(i)){
			int move = length(i) + abs(targetCh-i);
			if(min>move)
				min=move;
			
		}
	}
	int noneNumber = abs(targetCh-nowCh);
	if(min>noneNumber)
		min = noneNumber;
	
	return min;
}

int main(){
	cin >> targetCh;
	cin >> brokens;
	for(int i=0; i<brokens; i++){
		int num;
		cin >> num;
		isBroken[num] = true;
	}
	
	
	cout << opers(targetCh);
}
