#include <iostream>
#define MAX 5
using namespace std;

class Matrix{
  public:
    int n, m;
    long long mtrx[MAX][MAX] = {0,};
    Matrix(long long n, long long m){
        this->n = n;
        this->m = m;
    }

  public :
    void setMatrix(long long arr[MAX][MAX]){
        for(int row=0; row<n; ++row)
            for(int col=0; col<m; ++col)
                this->mtrx[row][col] = arr[row][col];
    }

    void printMatrix(){
        for(int row=0; row<n; row++){
            for(int col=0; col<m; col++)
                cout << this->mtrx[row][col] << " ";
            cout << "\n";
        }
    }
    
    int size(){
        return this->n;
    }

    Matrix operator*(const Matrix &ref){
        Matrix ret = Matrix(this->n, ref.m);
        for(int i=0; i<this->n; i++)
            for(int j=0; j<ref.m; j++)
                for(int k=0; k<ref.n; k++)
                    ret.mtrx[i][j] = (ret.mtrx[i][j] + this->mtrx[i][k]*ref.mtrx[k][j])%1000; 
                    
        return ret;
    }

};

Matrix getIdentity(long long n){
    Matrix ret = Matrix(n, n);
    long long  arr[MAX][MAX] = {0,};
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
            if(i==j)
                arr[i][j] = 1;
                
    ret.setMatrix(arr);
    return ret;
}

Matrix power(Matrix& mat, long long n){
    if(n==0) return getIdentity(mat.size());
    if(n%2!=0) return power(mat, n-1)*mat;
    if(n==2) return mat*mat;
    Matrix half = power(mat, n/2);
    return half*half;
}

int main() {
    int n;
    long long b;
    long long arr[MAX][MAX] = {0,};
    cin >> n >> b;
    Matrix mat = Matrix(n, n);
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)  
            cin >> arr[i][j];
    mat.setMatrix(arr);

    Matrix result = power(mat, b);
    result.printMatrix();
    
}


