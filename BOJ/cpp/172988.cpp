#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main(){
	int n;
	cin >> n;
	vector<int> r(n);
	vector<int> input(n);
	stack<int> stk;
	for(int i=0; i<n; i++)
		cin >> input[i];
	
	for(int i=n-2; i>=0; i--){
		if(input[i]<input[i+1])
			stk.push(input[i+1]);
		else{
			while(!stk.empty() && input[i]>=stk.top()){stk.pop();}
			if(stk.empty()){
				r[i]=-1;
				continue;
			}
		}
		r[i]=stk.top();
	}
	r[n-1]=-1;
	
	for(int i=0; i<n; i++)
		cout<<r[i]<<" ";
	
}
