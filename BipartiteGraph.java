import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class BipartiteGraph {
	
	private ArrayList<TicNode> tics;
	private ArrayList<TacNode> tacs;
	
	private ArrayList<ArrayList<Edge>> edges;
	private ArrayList<ArrayList<Edge>> solutions;
	
	public TreeSet<CompareableSet> finishedSolutions;
	public String solutionString;
	
	public BipartiteGraph(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		tics = ticList;
		tacs = tacList;
		edges = new ArrayList<ArrayList<Edge>>();
		solutions = new ArrayList<ArrayList<Edge>>();
		solutionString = "";
	}
	
	public void solve(){
		setUpEdges();
		reduce();
		reduceToMCM();
		reduceToMaxWeight();
		dumpToSet();
		setSolutionString();
		System.out.print(solutionString);
	}
	
	public String getSolutionString(){
		return solutionString;
	}
	
	private void setSolutionString(){
		String display = "";
		display += finishedSolutions.size() + "\n";
				
		for(CompareableSet solution : finishedSolutions){
			for(Edge e : solution){
				display += e + " ";
			}
			display += "\n";
		}
		
		solutionString = display;
	}
	
	private void dumpToSet(){
		finishedSolutions = new TreeSet<CompareableSet>();
		
		for(ArrayList<Edge> sol : solutions){
			CompareableSet setSol = new CompareableSet(sol);
			finishedSolutions.add(setSol);
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

		if(count == input.size()){
			solutions.add(new ArrayList<Edge>(current.subList(0, count)));		// Add this current solution from index 0 until # of tics (count)
		}
		else{
			for(int i = 0; i < input.get(n).size(); i++){
				current.add(count, input.get(n).get(i));						// add this Edge to our current possible solution (current)
				combine(input, current, (n+1)%(edges.size()), count+1);			// recursive call with index (n) that loops through all tics
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
