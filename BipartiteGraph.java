import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.HashSet;

public class BipartiteGraph {
	
	private ArrayList<TicNode> tics;
	private ArrayList<TacNode> tacs;
	
	private Map<Integer, TicNode> ticMap = new HashMap<Integer, TicNode>();
	private Map<Integer, TacNode> tacMap = new HashMap<Integer, TacNode>();
	
	private ArrayList<ArrayList<Edge>> edges;
	private ArrayList<ArrayList<Edge>> solutions;
	
	public HashSet<HashSet<Edge>> setSolutions;
	public String solutionString;
	
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		edges = new ArrayList<ArrayList<Edge>>();
		solutions = new ArrayList<ArrayList<Edge>>();
		solutionString = "";
		for(TicNode t : tics) ticMap.put(t.val, t);		// fill maps
		for(TacNode t : tacs) tacMap.put(t.val, t);
	}
	
	public void solve(){
		setUpEdges();
		reduce();
		reduceToMCM();
		reduceToMaxWeight();
		dumpToSet();
		setSolutionString();
		System.out.println(solutionString);
	}
	
	public String getSolutionString(){
		return solutionString;
	}
	
	private void setSolutionString(){
		String display = "";
		display += setSolutions.size() + "\n";
		
		for(HashSet<Edge> edges : setSolutions){
			for(Edge e : edges){
				display += e + " ";
			}
			display += "\n";
		}
		
		solutionString = display;
	}
	
	private void dumpToSet(){
		setSolutions = new HashSet<HashSet<Edge>>();
		
		for(ArrayList<Edge> sol : solutions){
			HashSet<Edge> setSol = new HashSet<Edge>(sol);
			setSolutions.add(setSol);
		}
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
			}
			solutions.add(sol);
		}
		else{
			for(int i = 0; i < input.get(n).size(); i++){
				current.add(count, input.get(n).get(i));
				combine(input, current, (n+1)%(edges.size()), count+1);
			}
		}
	}
	
	private void reduce(){
		ArrayList<Integer> usedTacs;
		ArrayList<Edge>	replacement;
		ArrayList<ArrayList<Edge>> reducedSolutions = new ArrayList<ArrayList<Edge>>();
		
		for(ArrayList<Edge> sol : solutions){
			usedTacs = new ArrayList<Integer>();
			replacement = new ArrayList<Edge>();
			
			for(int i = 0; i < sol.size(); i++){
				if(!usedTacs.contains(sol.get(i).tac)){
					replacement.add(sol.get(i));
					usedTacs.add(sol.get(i).tac);
				}
			}
			reducedSolutions.add(replacement);
		}
		solutions = reducedSolutions;
	}
	
	private void reduceToMCM(){
		int maxLen = -1;
		for(ArrayList<Edge> sol : solutions){
			maxLen = (sol.size() > maxLen) ? sol.size() : maxLen;
		}
		
		Iterator<ArrayList<Edge>> itr = solutions.iterator();
		while(itr.hasNext()){
			ArrayList<Edge> curSol = itr.next();
			
			if(curSol.size() != maxLen){
				itr.remove();
			}
		}
	}
	
	private void reduceToMaxWeight(){
		
		int maxWeight = Integer.MIN_VALUE;
		ArrayList<Integer> weights = new ArrayList<Integer>();
		
		for(ArrayList<Edge> sol : solutions){
			int curWeight = 0;
			for(Edge e : sol){
				curWeight += e.weight;
			}
			weights.add(curWeight);		// saving this solutions weight for comparison later
			maxWeight = (curWeight > maxWeight) ? curWeight : maxWeight;
		}
		
		Iterator<ArrayList<Edge>> itr = solutions.iterator();
		Iterator<Integer> weightItr = weights.iterator();
		
		while(itr.hasNext()){
			itr.next();
			int curWeight = weightItr.next();
			
			if(curWeight != maxWeight){
				itr.remove();
				weightItr.remove(); // removing just to be sure both arrays maintain same length
			}
		}
	}
	
}
