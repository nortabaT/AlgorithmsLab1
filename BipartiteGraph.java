import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;


public class BipartiteGraph {
	
	public ArrayList<TicNode> tics;
	public ArrayList<TacNode> tacs;
	public TreeSet<HashMap<TacNode, TicTac>> edgeCombinations;
	
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		edgeCombinations = new TreeSet<HashMap<TacNode, TicTac>>();
		
		setUpMatches();
		setUpMaps();
	}

	private void setUpMaps(){
		for(int i = 0; i < tics.size(); i++){						// for every tic in this graph
			TicNode curTic = tics.get(i);
			for(int j = 0; j < curTic.suitableMatches.size(); j++){	// for every possible match this tic has, create a edgeMap starting with this edge
				TicTac curEdge = curTic.suitableMatches.get(j);
				fillEdges(i, curEdge, null);					// initial call to recursive 'find all combinations of edges starting with this edge'
			}
		}
	}
	
	private TreeSet<HashMap<TacNode, TicTac>> fillEdges(int ticsIndex, TicTac curEdge, HashMap<TacNode, TicTac> curEdges){
		//TreeSet<HashMap<TacNode, TicTac>> edgeMaps = (list != null) ? new TreeSet<HashMap<TacNode, TicTac>>(list) : new  TreeSet<HashMap<TacNode, TicTac>>();
		HashMap<TacNode, TicTac> edges = (curEdges != null) ? new HashMap<TacNode, TicTac>(curEdges) : new HashMap<TacNode, TicTac>();
		int i = (ticsIndex == 0) ? 1 : ticsIndex;
		while(i < tics.size()){
			if(curEdge != null){
				edges.put(curEdge.tac, curEdge);
				curEdge.tic.matched = true;
			}
			TicNode curTic = tics.get(i);

			if(!curTic.matched){
				for(int j = 0; j < curTic.suitableMatches.size(); j++){
					TicTac edge = curTic.suitableMatches.get(j);
					TacNode tac = edge.tac;
					// TODO: add if is not in map, then add to hashmap and recurse for next index
					
					if(!edges.containsKey(tac)){
						fillEdges(i, edge, edges);
						cleanTics(i);
					}
				}
			}
			
			fillEdges(i+1, null, edges);
			
			if(i+1 == tics.size()){
				edgeCombinations.add(edges);
				break;
			}
		}
		
		edgeCombinations.add(edges);
		return null;
	}
	
	private void cleanTics(int i){
		for(i = 0; i<tics.size(); i++){
			tics.get(i).matched = false;
		}
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
