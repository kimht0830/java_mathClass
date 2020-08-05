package code;
import java.util.Scanner;
public class Matrix {
	public int[][] mat;
	private int wide;
	private int high;
	Matrix(int high,int wide){
		Scanner sc=new Scanner(System.in);
		this.mat=new int[high][wide];
		this.high=high;
		this.wide=wide;
		for(int i=0;i<high;i++) {
			for(int j=0;j<wide;j++) {
				mat[i][j]=sc.nextInt();
			}
		}
	}
	Matrix(int[][]arr){
		wide=arr[0].length;
		high=arr.length;
		mat=new int[high][wide];
		for(int i=0;i<high;i++) {
			for(int j=0;j<wide;j++) {
				mat[i][j]=arr[i][j];
			}
		}
	}
	boolean cross(Matrix b) {
		if(this.getWide()!=this.getHigh()) {
			return false;
		}
		int[][] one=this.mat;
		int[][] two=b.mat;
		int [][]arr=new int[this.getHigh()][b.getWide()];
		for(int i=0;i<this.getHigh();i++) {
			for(int j=0;j<b.getWide();j++) {
				for(int k=0;k<this.getWide();k++) {
					arr[i][j]+=one[i][k]*two[k][j];
				}
			}
		}
		this.mat=new int[arr.length][arr[0].length];
		this.wide=this.mat[0].length;
		this.high=this.mat.length;
		for(int i=0;i<high;i++) {
			for(int j=0;j<high;j++) {
				this.mat[i][j]=arr[i][j];
			}
		}
		return true;
	}
	boolean plus(Matrix b) {
		if(this.getWide()!=b.getWide()||this.getHigh()!=b.getHigh()) {
			return false;
		}
		for(int i=0;i<this.high;i++) {
			for(int j=0;j<this.wide;j++) {
				this.mat[i][j]+=b.mat[i][j];
			}
		}
		return true;
	}
	void showMat() {
		for(int i=0;i<high;i++) {
			for(int j=0;j<wide;j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}
	private long subdet(int[][] a) {
		long sum=0;
		if(a.length==1) {
			sum=a[0][0];
			return sum;
		}
		for(int i=0;i<a.length;i++) {
			int[][] t=new int[a.length-1][a.length-1];
			for(int j=1;j<a.length;j++) {
				int minus=0;
				for(int k=0;k<a.length;k++) {
					if(k==i) {
						minus=1;
						continue;
					}
					t[j-1][k-minus]=a[j][k];
				}
			}
			sum+=(i%2==1?-1:1)*a[0][i]*subdet(t);
		}
		return sum;
	}
	public long det() {
		if(this.wide!=this.high) {
			return 1987654321;
		}
		long sum=0;
		for(int i=0;i<this.wide;i++) {
			int[][] a=new int[this.wide-1][this.wide-1];
			for(int j=1;j<this.wide;j++) {
				int minus=0;
				for(int k=0;k<this.wide;k++) {
					if(k==i) {
						minus=1;
						continue;
					}
					a[j-1][k-minus]=this.mat[j][k];
				}
			}
			sum+=(i%2==1?-1:1)*this.mat[0][i]*subdet(a);
		}
		return sum;
	}
	int getWide() {
		if(mat==null) {
			return 0;
		}
		if(mat[0]==null) {
			return 0;
		}
		return wide;
	}
	int getHigh() {
		if(mat==null) {
			return 0;
		}
		return high;
	}
}
