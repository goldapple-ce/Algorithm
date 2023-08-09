
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb= new StringBuilder();
		PriorityQueue<Num> pq = new PriorityQueue<>();

		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			int originN = Integer.parseInt(br.readLine());
			int absN = Math.abs(originN);
			
			if(originN==0) {
				if(pq.isEmpty()) {
					System.out.println("0");
					continue;
				}
				System.out.println(pq.poll().getOriginNum());
				continue;
			}
			
			Num temp = new Num(absN,originN);
			pq.add(temp);
		}
		
	}

}

class Num implements Comparable<Num>{

	private int absNum;
	private int originNum;
	
	public Num(int absNum, int originNum) {
		super();
		this.absNum = absNum;
		this.originNum = originNum;
	}
	

	public int getAbsNum() {
		return absNum;
	}


	public void setAbsNum(int absNum) {
		this.absNum = absNum;
	}


	public int getOriginNum() {
		return originNum;
	}


	public void setOriginNum(int originNum) {
		this.originNum = originNum;
	}


	@Override
	public String toString() {
		return "Num [absNum=" + absNum + ", originNum=" + originNum + "]";
	}


	@Override
	public int compareTo(Num o) {
		if(this.absNum < o.absNum)
			return -1;
		else if(this.absNum==o.absNum) {
			return this.originNum-o.originNum;
		}else {
			return 1;
		}
	}
	
	
}