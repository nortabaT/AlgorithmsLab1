public class TicNode {
	public int val;
	public int weight;
	public int low;
	public int high;
	public boolean matched;
	
	public TicNode(int val, int low, int high, int weight){
		this.val = val;
		this.low = low;
		this.high = high;
		this.weight = weight;
		matched = false;
	}
	
	@Override
	public String toString(){
		return Integer.toString(val);
	}
}
