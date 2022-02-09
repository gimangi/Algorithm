#include <iostream>
using namespace std;

#define MAX 101
#define INDEX 50

int d[MAX][MAX][MAX] = {0,};


int w(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0)
        return 1;
    if (a > 20 || b > 20 || c > 20)
        return w(20, 20, 20);
    if (d[a+INDEX][b+INDEX][c+INDEX] != 0)
        return d[a+INDEX][b+INDEX][c+INDEX];


    if (a < b && b < c) 
        return d[a+INDEX][b+INDEX][c+INDEX] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
    
    return d[a+INDEX][b+INDEX][c+INDEX] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
}


int main() {

    int a, b, c;

    while (true) {
        cin >> a >> b >> c;
        if (a == -1 && b == -1 && c == -1)  break;

        cout << "w(" << a << ", " << b << ", " << c << ") = " << w(a,b,c) << endl;
    }

    return 0;
}
