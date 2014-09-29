public class TacNode {
	public int val;
	public int weight;
	public boolean matched;
	
	public TacNode(int val, int weight){
		this.val = val;
		this.weight = weight;
		matched = false;
	}
	
	@Override
	public String toString(){
		return Integer.toString(val);
	}
}
