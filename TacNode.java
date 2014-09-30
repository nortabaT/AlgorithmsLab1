public class TacNode implements Comparable<TacNode>{
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

	@Override
	public int compareTo(TacNode o) {
		return this.hashCode() - o.hashCode();
	}
}
