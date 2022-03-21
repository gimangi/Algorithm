#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

vector<int> placed;
int n;

bool possible(int col){
	for(int i=0; i<placed.size(); i++){
		int px = placed[i];
		if(px==col || abs(px-col)==abs(i-(int)placed.size()))
				return false;
	}
	return true;
}

int nQueen(int row){
	if(row==n) return 1;	//base case
	int ret = 0;
	for(int x=0; x<n; x++){
		if(possible(x)){
			placed.push_back(x);
			ret+=nQueen(row+1);
			placed.pop_back();
		}
	}
	return ret;
}

int main(){
	cin >> n;
	cout << nQueen(0);
}
