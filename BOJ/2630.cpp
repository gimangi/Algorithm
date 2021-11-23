#include <iostream>
#define MAX 128
using namespace std;


enum Color {
	WHITE,
	BLUE
};

int N;
bool isBlue[MAX][MAX];

int countColor(int row, int col, int n, Color c);
bool isAllColor(int row, int col, int n, Color c);

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> isBlue[i][j];
		}
	}

	cout << countColor(0, 0, N, WHITE) << "\n" << countColor(0, 0, N, BLUE);
}

// 배열의 크기 n은 2의 배수 또는 1만 입력된다는 가정
int countColor(int row, int col, int n, Color c) {
	int ret = 0;
	// end point
	if (n == 1) {
		if (c == BLUE) return isBlue[row][col];
		if (c == WHITE) return !isBlue[row][col];
	}
	if (isAllColor(row, col, n, c)) return 1;
	
	// 1사분면
	ret += countColor(row, col + n / 2, n / 2, c);
	// 2사분면
	ret += countColor(row, col, n / 2, c);
	// 3사분면
	ret += countColor(row + n / 2, col, n / 2, c);
	// 4사분면
	ret += countColor(row + n / 2, col + n / 2, n / 2, c);

	return ret;
}

bool isAllColor(int row, int col, int n, Color c) {
	for (int y = row; y < row + n; y++) {
		for (int x = col; x < col + n; x++) {
			if (c == BLUE && !isBlue[y][x]) return false;
			if (c == WHITE && isBlue[y][x]) return false;
		}
	}
	return true;
}

