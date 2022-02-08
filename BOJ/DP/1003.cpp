#include <iostream>
using namespace std;

#define MAX 41

int d[MAX] = { 0, };

int fibonacci(int n) {
    if (n==-1)  // N = 0일 때 케이스
        return 1;
    if (n==0)
        return 0;
    if (n==1)
        return 1;
    if (d[n] != 0) return d[n];
    
    return d[n] = fibonacci(n-1)+fibonacci(n-2);

}

int main() {

    int T;
    cin >> T;

    while (T--)
    {
        int n;
        cin >> n;

        cout << fibonacci(n-1) << " " << fibonacci(n) << endl;
    }

    return 0;
}
