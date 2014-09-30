import java.util.ArrayList;
import java.util.Collections;


public class BipartiteGraph {
	
	public ArrayList<TicNode> tics;
	public ArrayList<TacNode> tacs;
	
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
	}
	
	private void setUpMatches(){
		ArrayList<Edge> possibleMatches;
		
		for(int i = 0; i < tics.size(); i++){
			possibleMatches = new ArrayList<Edge>();
			for(TacNode tac : tacs){
				if(tac.val >= tics.get(i).low && tac.val <= tics.get(i).high){
					possibleMatches.add(new Edge(tics.get(i), tac));
				}
			}
			
			Collections.sort(possibleMatches);
		}
	}
	
}
