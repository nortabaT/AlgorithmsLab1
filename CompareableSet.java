import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


@SuppressWarnings("serial")
public class CompareableSet extends TreeSet<Edge> implements Comparable<TreeSet<Edge>> {

	public CompareableSet(ArrayList<Edge> sol) {
		// TODO Auto-generated constructor stub
		super(sol);
	}

	@Override
	public int compareTo(TreeSet<Edge> o) {
		Iterator<Edge> t1 = this.iterator();
		Iterator<Edge> t2 = o.iterator();
		
		while(t1.hasNext() && t2.hasNext()){
			Edge e1 = (Edge) t1.next();
			Edge e2 = (Edge) t2.next();
			
			if(e1.weight > e2.weight) return 1;
			else if(e1.weight < e2.weight) return -1;
		}
		return 0;
	}

}
