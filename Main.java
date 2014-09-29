import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) throws IOException{
		FileReader inputFile = new FileReader(new File("input1.txt"));
		Scanner in = new Scanner(inputFile);
		
		int graphs = Integer.parseInt(in.nextLine());
		for(int i=0; i<graphs; i++){
			solveMCM(in);
		}
	}
	
	public static void solveMCM(Scanner input){
		int numTics = input.nextInt();
		int numTacs = input.nextInt();
		
		input.nextLine();
		
		ArrayList<TicNode> ticList = initTic(input, numTics);
		ArrayList<TacNode> tacList = initTac(input, numTacs);
		setUpTicMatches(ticList, tacList);
		ArrayList<TicTac> mcmSolution = setUpMatching(ticList, tacList);
		
		System.out.println(mcmSolution);
	}

	public static ArrayList<TicTac> setUpMatching(ArrayList<TicNode> tics, ArrayList<TacNode> tacs){
		HashMap<TacNode, TicTac> tacMap = new HashMap<TacNode, TicTac>(); // kev, val -> tac, matching (for fast lookup)
		
		// nested for loop of setting up first grouping of tic tac matchings
		for(int i = 0; i < tics.size(); i++){
			TicNode currentTic = tics.get(i);
			
			for(int j = 0; j < currentTic.suitableMatches.size(); j++){
				TicTac currentMatch = currentTic.suitableMatches.get(j);
				TacNode currentTac = currentMatch.tac;
				
				if(!tacMap.containsKey(currentTac)){			// if the first tac we check has not been matched, just pair it up with this tic and move on
					tacMap.put(currentTac, currentMatch); 	// create mapping for this tac
					break;
				}
				else if(tacMap.containsKey(currentTac)){
					TicTac otherMatch = tacMap.get(currentTac);
					TicNode otherTic = otherMatch.tic;
					if(currentMatch.edge > otherMatch.edge){	// if we have a better match, replace this mapping and find a replacement
						tacMap.put(currentTac, currentMatch);
						otherTic.suitableMatches.remove(otherMatch);
						tacMap = findReplacement(otherTic, tacMap);
						break;
					}
				}
			}
		}
		
		
		ArrayList<TicTac> solution = new ArrayList<TicTac>();
		for(Entry<TacNode, TicTac> entry : tacMap.entrySet()){
			solution.add(entry.getValue());
		}
		
		return solution;
	}
	
	private static HashMap<TacNode, TicTac> findReplacement(TicNode currentTic, HashMap<TacNode, TicTac> tacMap){
		for(TicTac match : currentTic.suitableMatches){
			if(!tacMap.containsKey(match.tac)){							// base case, if there is no match for this current tac, match and move on
				tacMap.put(match.tac, match);
				return tacMap;
			}
			else if(tacMap.containsKey(match.tac)){
				TicTac otherMatch = tacMap.get(match.tac);
				TicNode otherTic = otherMatch.tic;
				if(match.edge > otherMatch.edge){
					tacMap.put(match.tac, match);
					otherTic.suitableMatches.remove(otherMatch);
					tacMap = findReplacement(otherMatch.tic, tacMap);	// recursive, find a new replacement for the one we lost
					break;
				}
			}
		}
		
		return tacMap;
	}

	public static ArrayList<TicNode> initTic(Scanner t, int tics){
		ArrayList<TicNode> ticList = new ArrayList<TicNode>();
		
		for(int i = 0; i < tics; i++){
			ticList.add(new TicNode(t.nextInt(), t.nextInt(), t.nextInt(), t.nextInt()));
			if(t.hasNext()){
				t.nextLine();
			}
		}
		return ticList;
	}
	
	public static ArrayList<TacNode> initTac(Scanner t, int tacs){
		ArrayList<TacNode> tacList = new ArrayList<TacNode>();
		for(int i = 0; i < tacs; i++){
			tacList.add(new TacNode(t.nextInt(),t.nextInt()));
			if(t.hasNext()){
				t.nextLine();
			}
		}
		return tacList;
	}
	
	private static void setUpTicMatches(ArrayList<TicNode> ticList, ArrayList<TacNode> tacList){
		ArrayList<TicTac> possibleMatches;
		
		for(int i = 0; i < ticList.size(); i++){
			possibleMatches = new ArrayList<TicTac>();
			for(TacNode tac : tacList){
				if(tac.val >= ticList.get(i).low && tac.val <= ticList.get(i).high){
					possibleMatches.add(new TicTac(ticList.get(i), tac));
				}
			}
			
			Collections.sort(possibleMatches);
			ticList.get(i).setMatchList(possibleMatches);
		}
	}
	
}