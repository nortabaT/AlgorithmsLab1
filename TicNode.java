import java.util.ArrayList;

public class TicNode {
	public int val;
	public int weight;
	public int low;
	public int high;
	public ArrayList<TicTac> suitableMatches;
	public boolean matched;
	
	public TicNode(int val, int low, int high, int weight){
		this.val = val;
		this.low = low;
		this.high = high;
		this.weight = weight;
		suitableMatches = new ArrayList<TicTac>();
		matched = false;
	}
	
	public void setMatchList(ArrayList<TicTac> matches){
		suitableMatches = matches;
	}
	
	@Override
	public String toString(){
		return Integer.toString(val);
	}
}
