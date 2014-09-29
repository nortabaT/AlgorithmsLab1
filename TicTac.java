public class TicTac implements Comparable<TicTac>{
	public TicNode tic;
	public TacNode tac;
	public int debugTic;
	public int debugTac;
	public int edge;
	
	public TicTac(TicNode tic, TacNode tac){
		this.tic = tic;
		this.tac = tac;
		debugTic = tic.val;
		debugTac = tac.val;
		edge = tic.weight + tac.weight;
	}
	
	public String toString(){
		return tic + " : " + tac;
	}

	@Override
	public int compareTo(TicTac o){
		if(this.edge > o.edge){
			return -1;
		}
		else if(this.edge < o.edge){
			return 1;
		}
		
		return 0;
	}
}
