public class Edge implements Comparable<Edge>{
	public int tic;
	public int tac;
	public int edge;
	
	public Edge(int tic, int tac, int weight1, int weight2){
		this.tic = tic;
		this.tac = tac;
		edge = weight1 + weight2;
	}
	
	public String toString(){
		return tic + " : " + tac;
	}

	@Override
	public int compareTo(Edge o){
		if(this.edge > o.edge){
			return -1;
		}
		else if(this.edge < o.edge){
			return 1;
		}
		
		return 0;
	}
}
