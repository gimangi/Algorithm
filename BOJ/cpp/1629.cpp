    #include <iostream>
    using namespace std;
    
    int c;
    
    long long power(long long a, int n){
        if(n==0) return 1;
        if(n%2!=0) return ((power(a,n-1)%c)*(a%c))%c;
        if(n==2) return ((a%c)*(a%c))%c;
        long long half = power(a,n/2)%c;
        return (half*half)%c;
    }
    
    int main() {
        long long a;
        int b;
        cin >> a >> b >> c;
        cout << power(a,b);
    }
    

