import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) throws IOException{
		String inputPath = args[0];															// name of this input file
		String outputPath = args[0].substring(0, args[0].lastIndexOf('.')) + ".out";		// removing the ending file type and replacing it with .out (to signify it's an output of this input file)
		
		FileReader inputFile = new FileReader(new File(inputPath));
		FileWriter outputFile = new FileWriter(new File(outputPath));
		
		Scanner in = new Scanner(inputFile);
		
		int graphs = Integer.parseInt(in.nextLine());	// number of graphs in this file
		
		for(int i=0; i<graphs; i++){
			solveGraph(in, outputFile);
		}
		
		outputFile.flush();
		outputFile.close();
	}
	
	public static void solveGraph(Scanner input, FileWriter outputFile) throws IOException{
		int numTics = input.nextInt();
		int numTacs = input.nextInt();
		
		input.nextLine();	// move scanner to the next line (start of node definitions)
		
		ArrayList<TicNode> ticList = initTic(input, numTics);	// convert all our input strings into node objects
		ArrayList<TacNode> tacList = initTac(input, numTacs);	
		
		BipartiteGraph g = new BipartiteGraph(ticList, tacList);
		g.solve();												// takes all our tics and tacs and finds all possible MWMCM solutions
		
		outputFile.write(g.getSolutionString());
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