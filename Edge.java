public class Edge implements Comparable<Edge>{
	public int tic;
	public int tac;
	public int weight;
	
	public Edge(int tic, int tac, int weight1, int weight2){
		this.tic = tic;
		this.tac = tac;
		weight = weight1 + weight2;
	}
	
	public String toString(){
		return "["+tic + " : " + tac+"]";
	}

	@Override
	public int compareTo(Edge arg0) {
		// TODO Auto-generated method stub
		if(this.hashCode() == arg0.hashCode()){
			return 0;
		}
		return -1;
	}

}
