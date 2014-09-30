import java.util.ArrayList;

public class BipartiteGraph {
	
	public ArrayList<TicNode> tics;
	public ArrayList<TacNode> tacs;
	
	public ArrayList<ArrayList<Edge>> edges;
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		edges = new ArrayList<ArrayList<Edge>>();
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
		
		if(count == input.size()){
			for(int i = 0; i < count; i++){
				System.out.print(current.get(i) + ", ");
			}
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
