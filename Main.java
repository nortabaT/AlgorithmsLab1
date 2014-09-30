import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) throws IOException{
		FileReader inputFile = new FileReader(new File("file0.in"));
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
		
		// init lists
		ArrayList<TicNode> ticList = initTic(input, numTics);
		ArrayList<TacNode> tacList = initTac(input, numTacs);
		BipartiteGraph g = new BipartiteGraph(ticList, tacList);
		
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

	
}