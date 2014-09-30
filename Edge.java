public class Edge implements Comparable<Edge>{
	public TicNode tic;
	public TacNode tac;
	public int edge;
	
	public Edge(TicNode tic, TacNode tac){
		this.tic = tic;
		this.tac = tac;
		edge = tic.weight + tac.weight;
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
