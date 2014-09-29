import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class BipartiteGraph {
	
	public ArrayList<TicNode> tics;
	public ArrayList<TacNode> tacs;
	public ArrayList<HashMap<TacNode, TicTac>> edgeCombinations;
	
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		edgeCombinations = new ArrayList<HashMap<TacNode, TicTac>>();
		
		setUpMatches();
		setUpMaps();
	}

	private void setUpMaps(){
		for(int i = 0; i < tics.size(); i++){						// for every tic in this graph
			TicNode curTic = tics.get(i);
			for(int j = 0; j < curTic.suitableMatches.size(); j++){	// for every possible match this tic has, create a edgeMap starting with this edge
				TicTac curEdge = curTic.suitableMatches.get(j);
				fillEdges(i, curEdge);
			}
		}
	}
	
	private ArrayList<HashMap<TacNode, TicTac>> fillEdges(int ticsIndex, TicTac curEdge){
		ArrayList<HashMap<TacNode, TicTac>> edgeMaps = new ArrayList<HashMap<TacNode, TicTac>>();
		
		for(int i = ticsIndex; i < tics.size(); i++){
			TicNode curTic = tics.get(i);
			HashMap<TacNode, TicTac> edges = new HashMap<TacNode, TicTac>();
			for(int j = 0; j < curTic.suitableMatches.size(); i++){
				// TODO: add if is not in map, then add to hashmap and recurse for next index
			}
		}
		
		return null;
	}
	
	private void setUpMatches(){
		ArrayList<TicTac> possibleMatches;
		
		for(int i = 0; i < tics.size(); i++){
			possibleMatches = new ArrayList<TicTac>();
			for(TacNode tac : tacs){
				if(tac.val >= tics.get(i).low && tac.val <= tics.get(i).high){
					possibleMatches.add(new TicTac(tics.get(i), tac));
				}
			}
			
			Collections.sort(possibleMatches);
			tics.get(i).setMatchList(possibleMatches);
		}
	}
	
}
