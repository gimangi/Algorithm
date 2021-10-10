#include <iostream>
#include <cmath>
using namespace std;

int max(int a, int b){
    if(a>b)
        return a;
    return b;
}

int min(int a, int b){
    if(a>b)
        return b;
    return a;
}

int main() {
    int ax, ay, ar, bx, by, br;
    int n;
    cin >> n;
    for(; n>0; n--){
        cin >> ax >> ay >> ar >> bx >> by >> br;
        int dx = ax-bx;
        int dy = ay-by;
        if(dx<0)
            dx=-1*dx;
        if(dy<0)
            dy=-1*dy;
        
        int sqd = dx*dx+dy*dy;
        double d = sqrt(sqd);
        int sqr = ar*ar+br*br+2*ar*br;
        //cout << sqd << " " << sqr << endl;
         
        if(sqd==0 && ar==br)//일치
            cout <<"-1"<<endl;
        else if(sqd==sqr)   //접점
            cout <<"1"<<endl;
        else if(max(ar, br)==d+min(ar,br))   //안에서 접점
            cout << "1"<<endl;
        else if(sqd>sqr)    //만나지 않음
            cout <<"0"<<endl;
        else if(sqd<sqr){
            if(max(ar, br)>d+min(ar,br))    //내부에 있음
                cout <<"0"<<endl;
            else
                cout << "2"<<endl;
        }
    }
}
