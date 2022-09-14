#include <iostream>
#include <vector>
#define OPERS 4
#define INF_POS 1234567890
#define INF_NEG -1234567890
using namespace std;

int n, numOperands;
int operators[OPERS];
vector<int> operands;
vector<int> result;

void set_exp(int idx, int interm){
	if(idx==numOperands) { result.push_back(interm); return;}
	
	for(int i=0; i<OPERS; i++){
		if(operators[i]>0){
			int next = interm;
			
			if(i==0)
				next+=operands[idx];
			else if(i==1)
				next-=operands[idx];
			else if(i==2)
				next*=operands[idx];
			else if(i==3)
				next/=operands[idx];
				
			operators[i]--;
			set_exp(idx+1, next);
			operators[i]++;
		}
	}
	
}

int main(){
    int max = INF_NEG;
    int min = INF_POS;
    cin >> n;
    for(int i=0; i<n; i++){
    	int num;
    	cin >> num;
    	operands.push_back(num);
    }
    for(int i=0; i<OPERS; i++){
    	cin >> operators[i];
    }
    numOperands = operands.size();
    
    set_exp(1,operands[0]);
    
    for(int i=0; i<result.size(); i++){
    	if(result[i]>max)
    		max = resul
