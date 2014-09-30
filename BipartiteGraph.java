import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BipartiteGraph {
	
	public ArrayList<TicNode> tics;
	public ArrayList<TacNode> tacs;
	
	private Map<Integer, TicNode> ticMap = new HashMap<Integer, TicNode>();
	private Map<Integer, TacNode> tacMap = new HashMap<Integer, TacNode>();
	
	public ArrayList<ArrayList<Edge>> edges;
	public ArrayList<ArrayList<Edge>> solutions;
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		edges = new ArrayList<ArrayList<Edge>>();
		solutions = new ArrayList<ArrayList<Edge>>();
		
		for(TicNode t : tics) ticMap.put(t.val, t);		// fill maps
		for(TacNode t : tacs) tacMap.put(t.val, t);
		setUpEdges();
	}
	
	private void setUpEdges(){
		
		for(TicNode tic : tics){
			ArrayList<Edge> possibleMatches = new ArrayList<Edge>();
			
			for(TacNode tac : tacs){
				if(tac.val >= tic.low && tac.val <= tic.high){
					possibleMatches.add(new Edge(tic.val, tac.val, tic.weight, tac.weight));
				}
			}
			
			if(possibleMatches.size() >= 1){
				edges.add(possibleMatches);
			}
		}
		for(int i = 0; i < edges.size(); i++){
			combine(edges, new ArrayList<Edge>(), i, 0);
		}
		
	}
	
	private void combine(ArrayList<ArrayList<Edge>> input, ArrayList<Edge> current, int n, int count){
		ArrayList<Edge> sol;
		if(count == input.size()){
			sol = new ArrayList<Edge>();
			for(int i = 0; i < count; i++){
				sol.add(current.get(i));
				System.out.print(current.get(i) + ", ");
			}
			solutions.add(sol);
			System.out.println();
		}
		else{
			for(int i = 0; i < input.get(n).size(); i++){
				current.add(count, input.get(n).get(i));
				combine(input, current, (n+1)%(edges.size()), count+1);
			}
		}
	}
	
}
