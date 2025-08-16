import java.util.*;
import java.io.*;

class cup implements Comparable<cup>
{
	int dead;
	int count;
	public cup(int dead,int count)
	{
		this.dead=dead;
		this.count=count;
	}
	public int compareTo(cup o)
	{
		if(dead==o.dead)
		{
			return o.count-count;
		}
		else
		{
			return dead-o.dead;
		}
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		String[] s;
		PriorityQueue<Integer> q=new PriorityQueue<Integer>();
		ArrayList<cup> arr=new ArrayList<cup>();
		for(int i=0;i<N;i++)
		{
			s=br.readLine().split(" ");
			int dead=Integer.parseInt(s[0]);
			int count=Integer.parseInt(s[1]);
			arr.add(new cup(dead,count));
		}
		Collections.sort(arr);
		for(int i=0;i<N;i++)
		{
			int dead=arr.get(i).dead;
			int count=arr.get(i).count;
			q.add(count);
			if(dead<q.size())
			{
				q.poll();
			}
		}
		long result=0;
		while(!q.isEmpty())
		{
			result+=q.poll();
		}
		System.out.println(result);
	}
}