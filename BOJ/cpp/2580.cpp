#include <iostream>
#include <utility>
#include <vector>
using namespace std;

int board[9][9] = {0,};
int blanks = 0;
bool completed = false;
vector<int> result;
vector<pair<int,int>> place;

void printResult();
int range(int);

void sudoku(int n){
	if(completed)	return;
	
	if(n==blanks){
		printResult();
		completed = true;
		return;
	}
	bool impossible[10] = {false,};
	int row = place[n].first;
	int col = place[n].second;
	for(int i=0; i<9; i++){	//board[i][j] 는 0~9까지 
		impossible[board[row][i]] = true;
		impossible[board[i][col]] = true;
	}
	for(int y=range(row); y<range(row)+3; y++)
		for(int x=range(col); x<range(col)+3; x++)
			impossible[board[y][x]] = true;
	for(int i=1; i<10; i++){
		if(!impossible[i]){
			board[row][col] = i;
			result.push_back(i);
			sudoku(n+1);
			board[row][col] = 0;
			result.pop_back();
		}
	}
}

void printResult(){
	for(int y=0; y<9; y++){
		for(int x=0; x<9; x++){
			cout<<board[y][x]<<" ";
		}
	cout << "\n";
	}
}

int range(int n){
	if(n<=2)
		return 0;
	else if(n<=5)
		return 3;
	else
		return 6;
}

int main(){
	for(int y=0; y<9; y++){
		for(int x=0; x<9; x++){
			cin >> board[y][x];
			if(board[y][x]==0){
				blanks++;
				place.push_back(make_pair(y,x));
			}
		}
	}
	sudoku(0);
	
}
