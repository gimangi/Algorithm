#include <string>
#include <vector>

#define LARGE 8
#define INF 9999

using namespace std;

int getMin(int a, int b, int c, int d);
int repeatDigit(int d, int cnt);

int dp (int N, int number, int curNum, int count) {
    int min = INF;
    
    if (count > LARGE)   return INF;
    if (number == curNum)   return count;
    
    for (int c=1; c <= LARGE-count; c++) {
        int plus = dp(N, number, curNum + repeatDigit(N, c), count + c);
        int minus = dp(N, number, curNum - repeatDigit(N, c), count + c);
        int multiply = dp(N, number, curNum * repeatDigit(N, c), count + c);
        int divide = dp(N, number, curNum / repeatDigit(N, c), count + c);
        
        int minResult = getMin(plus, minus, multiply, divide);
    
        if (min > minResult)    
            min = minResult;
    }
    
    return min;
}

int solution(int N, int number) {
    int result = dp(N, number, 0, 0);
    if (result == INF)
        return -1;
    return result;
}


int getMin(int a, int b, int c, int d) {
    if (a<=b && a<=c && a<=d)   return a;
    else if (b<=a && b<=c && b<=d)  return b;
    else if (c<=a && c<=b && c<=d)  return c;
    return d;
}

int repeatDigit(int d, int cnt) {
    int ret = d;
    while (--cnt > 0) {
        ret = (ret * 10) + d;
    }
    return ret;
}
