#include <iostream>
#include <cmath>
using namespace std;

int main() {
    int a,b,v;
    double result;
    int output;
    cin >> a >> b >> v;
    result = ceil((double(v-a)/(double)(a-b)));
    output=  (int)result + 1;
    cout << output ;
}
