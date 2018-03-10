package P3;

import java.util.Arrays;
import P3.FriendshipGraph;
import P3.Person;

public class FriendshipGraphTest {
	
	private static FriendshipGraph graph =new FriendshipGraph();
	
	public static void main(String[] args){

		Arrays.fill(graph.visit, false);
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel); 
		graph.addVertex(ross); 
		graph.addVertex(ben);
	    graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross)); 
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));  
		System.out.println(graph.getDistance(rachel, kramer));
	}
}
