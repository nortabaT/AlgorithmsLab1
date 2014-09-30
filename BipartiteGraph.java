import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class BipartiteGraph {
	
	public ArrayList<TicNode> tics;
	public ArrayList<TacNode> tacs;
	public Set<Set<Edge>> Edges;
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		Edges = new HashSet<Set<Edge>>();
		setUpEdges();
	}
	
	private void setUpEdges(){
		
		for(TicNode tic : tics){
			Set<Edge> possibleMatches = new HashSet<Edge>();
			
			for(TacNode tac : tacs){
				if(tac.val >= tic.low && tac.val <= tic.high){
					possibleMatches.add(new Edge(tic.val, tac.val, tic.weight, tac.weight));
				}
			}
			
			if(possibleMatches.size() >= 1){
				Edges.add(possibleMatches);
			}
		}
	}
	
}
