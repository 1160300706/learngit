/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets
	 *            a list of tweets providing the evidence, not modified by this
	 *            method.
	 * @return a social network (as defined above) in which Ernie follows Bert
	 *         if and only if there is evidence for it in the given list of
	 *         tweets. One kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of
	 *            evidence may be used at the implementor's discretion. All the
	 *            Twitter usernames in the returned social network must be
	 *            either authors or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		int length = tweets.size();// tweets的数量
		Map<String, Set<String>> result = new HashMap<String, Set<String>>();
		// HashSet<String> name2 = new HashSet<String>();
		int length1;// 字符串的长度
		String firstname;
		int flag;// 记录截取名字的位置
		int count = 0;// 记录实际出现的人数
		for (int i = 0; i < length; i++) {
			List<String> name1 = new ArrayList<String>();
			firstname = tweets.get(i).getText(); // 得到txt的字符串
			length1 = firstname.length();
			for (int j = 0; j < length1; j++) {
				if (firstname.charAt(j) == '@')// 判断是否出现人名
				{
					flag = j;
					while (firstname.charAt(j) != ' ' || j != length1)
						j++;
					name1.add(firstname.substring(flag + 1, j));
					count++;// 数目增加
					for (int k = 0; k < j - flag; k++) {
						char t = name1.get(count - 1).charAt(k);
						if (!Character.isDigit(t) || !Character.isAlphabetic(t) || t != '_' || t != '-') {
							name1.remove(count - 1);
							count--;
							break;
						}
					}

				}
			}
			for (int k = 0; k < count; k++)// 判断大小写
			{
				firstname = name1.get(k);
				for (int j = k + 1; j < count; j++) {
					if (firstname.equalsIgnoreCase(name1.get(j))) {
						name1.remove(j);
						count--;
					}
				}
			}
			HashSet<String> result1 = new HashSet<String>();
			for (int j = 0; j < count; j++) 
				result1.add(name1.get(j));
			result.put(tweets.get(i).getAuthor(), result1);
	 }
		int flag1=0;
		for(int i=0;i<length;i++)
		{
			firstname = tweets.get(i).getAuthor();
			for(int j=i+1;j<length;j++)
			{
				if(firstname.equalsIgnoreCase(tweets.get(j).getAuthor()))
				{
					Set<String> temp1 = new HashSet<String>();
					Set<String> temp2 = new HashSet<String>();
					temp1=result.get(tweets.get(j).getAuthor());
					temp2 = result.get(firstname);
					for(String s0 : temp1)
					{
						for(String s1 : temp2)
						{
							if(s0.equalsIgnoreCase(s1))
								flag1=1;
						}
						if(flag1==0)
							result.get(firstname).add(s0);
					}
					
				}
			}
		}
		return result;
  }
	/**
	 * Find the people in a social network who have the greatest influence, in
	 * the sense that they have the most followers.
	 * 
	 * @param followsGraph
	 *            a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {
		//throw new RuntimeException("not implemented");
		List<date> temp = new ArrayList<date>();
		Set<String> str = followsGraph.keySet();
		long num;
		Iterator<String> it = str.iterator();
		long count=0;
		while(it.hasNext())
		 {
			count++;
			 String name1 = it.next();
			 num = followsGraph.get(name1).size();
			 date temp1 = new date();
			 temp1.name = name1;
			 temp1.number = num;
			 temp.add(temp1);
		 }
		Collections.sort(temp,new cmp());
		List<String> result = new ArrayList<String>();
		for(int i=0;i<count;i++)
		{
			result.add(temp.get(i).name);
		}
		return result;
	}
	static class date{
		String name;
		long number;
	}
	static class cmp implements Comparator<date>
	{
		public int compare (date A, date B)
		{
			if(A.number < B.number)
				return 1;
			return -1;
		}
	}

}
