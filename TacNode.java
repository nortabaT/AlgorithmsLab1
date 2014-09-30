public class TacNode {
	public int val;
	public int weight;
	
	public TacNode(int val, int weight){
		this.val = val;
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return Integer.toString(val);
	}
}
