public class TicNode {
	public int val;
	public int weight;
	public int low;
	public int high;
	
	public TicNode(int val, int low, int high, int weight){
		this.val = val;
		this.low = low;
		this.high = high;
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return Integer.toString(val);
	}
}
