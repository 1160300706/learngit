/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {
	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets
	 *            list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of
	 *         every tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {

		int length = tweets.size();
		Instant time0 = Instant.parse("2010-12-30T12:00:00Z");
		List<result> interval = new ArrayList<result>();
		Instant time1;
		for (int i = 0; i < length; i++) {
			result temp = new result();
			time1 = tweets.get(i).getTimestamp();
			temp.name = time1;
			temp.minutes = ChronoUnit.MINUTES.between(time0, time1);
			interval.add(temp);
		}
		long start = interval.get(0).minutes, end = interval.get(0).minutes;
		int min = 0, max = 0;
		for (int i = 0; i < length; i++) {
			if (interval.get(i).minutes < start) {
				start = interval.get(i).minutes;
				min = i;
			}
			if (interval.get(i).minutes > end) {
				end = interval.get(i).minutes;
				max = i;
			}
		}
		Timespan K = new Timespan(interval.get(min).name, interval.get(max).name);
		return K;
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets
	 *            list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets.
	 *         A username-mention is "@" followed by a Twitter username (as
	 *         defined by Tweet.getAuthor()'s spec). The username-mention cannot
	 *         be immediately preceded or followed by any character valid in a
	 *         Twitter username. For this reason, an email address like
	 *         bitdiddle@mit.edu does NOT contain a mention of the username mit.
	 *         Twitter usernames are case-insensitive, and the returned set may
	 *         include a username at most once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
		int length = tweets.size();// tweets的数量
		List<String> name1 = new ArrayList<String>();
		int length1;// 字符串的长度
		String firstname;
		int flag;// 记录截取名字的位置
		int count = 0;// 记录实际出现的人数
		for (int i = 0; i < length; i++) {
			firstname = tweets.get(i).getText();// 得到txt的字符串
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
		}
		// 判断大小写
		for (int i = 0; i < count; i++) {
			firstname = name1.get(i);
			for (int j = i + 1; j < count; j++) {
				if (firstname.equalsIgnoreCase(name1.get(j))) {
					name1.remove(j);
					count--;
				}
			}
		}
		Set<String> result1 = new HashSet<String>();
		for (int i = 0; i < count; i++) {
			result1.add(name1.get(i));
		}
		return result1;
	}
}

class result {
	long minutes;
	Instant name;
}
