#include <iostream>
using namespace std;

#define MAX 10001

int main(){
    bool prime[MAX];
    int n, input;
    
    for(int i=0; i<MAX; i++)
        prime[i] = true;
    
    prime[1] = false;
        
    for(int i=2; i<MAX; i++){
        if(prime[i])
            for(int j=2; i*j<MAX; j++)
                prime[i*j] = false;
    }
    
    cin >> n;
    for(; n>0; n--){
        int small, large;
        cin >> input;
        
        small = input/2;
        large = input/2;
        
        while(!(prime[small]&&prime[large])){
            small--;
            large++;
            
        }
        cout << small << " " << large << endl;
    }
    
    
}
