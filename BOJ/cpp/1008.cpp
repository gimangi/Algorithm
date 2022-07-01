#include <iostream>
#include <cstdlib>
using namespace std;

int main(){
    int num1, num2;
	double result;
	cin >> num1 >> num2;
	result = (double)num1 / num2;
	printf("%.9f\n", result);   
}
