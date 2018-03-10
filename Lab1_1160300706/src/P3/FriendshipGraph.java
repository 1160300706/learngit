package P3;

//import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FriendshipGraph {
	private int N = 10000;
	int[][] Map = new int[N][N];
	String[] nameList = new String[N];
	boolean[] visit = new boolean[N];
	int count = 0;

	public static void main(String[] args) {
		// FriendshipGraph graph =new FriendshipGraph();
		// Arrays.fill(graph.visit, false);
		// Person rachel = new Person("Rachel");
		// Person ross = new Person("Ross");
		// Person ben = new Person("Ben");
		// Person kramer = new Person("Kramer");
		// graph.addVertex(rachel);
		// graph.addVertex(ross);
		// graph.addVertex(ben);
		// graph.addVertex(kramer);
		// graph.addEdge(rachel, ross);
		// graph.addEdge(ross, rachel);
		// graph.addEdge(ross, ben);
		// graph.addEdge(ben, ross);
		// System.out.println(graph.getDistance(rachel, ross));
		// System.out.println(graph.getDistance(rachel, ben));
		// System.out.println(graph.getDistance(rachel, rachel));
		// System.out.println(graph.getDistance(rachel, kramer));
		// should print -1

	}

	/**
	 * 将人加入到图中
	 * 
	 */
	public void addVertex(Person name) {
		nameList[this.count] = name.Name;
		this.count++;
	}

	class position {
		int p1 = -1;
		int p2 = -1;
	}

	/**
	 * 确定需要求解的两个点的位置
	 */
	public position getPosition(Person name1, Person name2) {
		position temp = new position();
		int ter = 0;
		for (int i = 0; i < N; i++) {
			if (name1.Name.equals(nameList[i])) {
				temp.p1 = i;
				ter++;
			}
			if (name2.Name.equals(nameList[i])) {
				temp.p2 = i;
				ter++;
			}
			if (ter == 2)
				break;
		}
		return temp;
	}

	/**
	 * 将边加入到两点之间
	 */
	public void addEdge(Person name1, Person name2) {
		position Po = getPosition(name1, name2);
		if (Po.p1 != -1 && Po.p2 != -1)
			Map[Po.p1][Po.p2] = 1;
	}

	/**
	 * 求解出两个点之间的最小距离
	 */
	public int getDistance(Person name1, Person name2) {
		// System.out.println(count);
		position temp = getPosition(name1, name2);
		if (name1.Name.equals(name2.Name))
			return 0;
		return BFS(nameList, temp);
		// return BFS(nameList,temp);
	}

	/**
	 * 进行广度搜索
	 */
	public int BFS(String[] nameList, position temp) {
		int j;
		int i;
		int k = temp.p1;
		Queue<Integer> Q = new LinkedList<Integer>();
		visit[k] = true; // 给vk作访问过标记
		Q.offer(k); // vk进队列
		int dis = 1;
		while (!Q.isEmpty()) {
			i = Q.poll();
			for (j = 0; j < count; j++) {
				if (!visit[j] && Map[i][j] == 1) {
					if (nameList[temp.p2].equals(nameList[j]))
						return dis;
					visit[j] = true; // 给vj作访问过标记
					Q.offer(j); // 访问过的vj入队
				}
			}
			dis++;
		}
		dis = 0;
		return -1;
	}
}