/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

	/**
	 * Find tweets written by a particular user.
	 * 
	 * @param tweets
	 *            a list of tweets with distinct ids, not modified by this
	 *            method.
	 * @param username
	 *            Twitter username, required to be a valid Twitter username as
	 *            defined by Tweet.getAuthor()'s spec.
	 * @return all and only the tweets in the list whose author is username, in
	 *         the same order as in the input list.
	 */
	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
		int length = tweets.size();
		List<Tweet> result = new ArrayList<Tweet>();
		for (int i = 0; i < length; i++) {
			if (username.equalsIgnoreCase(tweets.get(i).getAuthor()))
				result.add(tweets.get(i));
		}
		return result;
	}

	/**
	 * Find tweets that were sent during a particular timespan.
	 * 
	 * @param tweets
	 *            a list of tweets with distinct ids, not modified by this
	 *            method.
	 * @param timespan
	 *            timespan
	 * @return all and only the tweets in the list that were sent during the
	 *         timespan, in the same order as in the input list.
	 */
	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
		// throw new RuntimeException("not implemented");
		int length = tweets.size();
		Instant initialtime = Instant.parse("2010-12-30T12:00:00Z");
		long time0 = ChronoUnit.MINUTES.between(initialtime, timespan.getStart());
		long time1 = ChronoUnit.MINUTES.between(initialtime, timespan.getEnd());
		long temp;
		List<Tweet> result = new ArrayList<Tweet>();
		for (int i = 0; i < length; i++) {
			temp = ChronoUnit.MINUTES.between(initialtime, tweets.get(i).getTimestamp());
			if (time0 < temp && time1 > temp) {
				result.add(tweets.get(i));
			}
		}
		return result;
	}

	/**
	 * Find tweets that contain certain words.
	 * 
	 * @param tweets
	 *            a list of tweets with distinct ids, not modified by this
	 *            method.
	 * @param words
	 *            a list of words to search for in the tweets. A word is a
	 *            nonempty sequence of nonspace characters.
	 * @return all and only the tweets in the list such that the tweet text
	 *         (when represented as a sequence of nonempty words bounded by
	 *         space characters and the ends of the string) includes *at least
	 *         one* of the words found in the words list. Word comparison is not
	 *         case-sensitive, so "Obama" is the same as "obama". The returned
	 *         tweets are in the same order as in the input list.
	 */
	public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
		int length0 = tweets.size();// tweets的长度
		List<Tweet> result = new ArrayList<Tweet>();
		int length1 = words.size();// 特定字符串的数量
		int flag = 0;
		for (int i = 0; i < length0; i++) {
			List<String> temp = new ArrayList<String>();
			int length2 = tweets.get(i).getText().length();// tweets中text字符串的长度
			String tstr = tweets.get(i).getText();// 得到每个tweets中text的内容
			for (int j = 0; j < length2; j++) {
				if (tstr.charAt(j) == ' ') {
					temp.add(tstr.substring(flag, j));// 按照空格将每个text中的单词分割开，然后加入到temp中
					flag = j + 1;
				}
			}
			temp.add(tstr.substring(flag, length2));
			flag = 0;
			int count = 0;
			for (int j = 0; j < length1; j++) {
				int length3 = temp.size();// temp中字符串的个数
				for (int t = 0; t < length3; t++) {
					if (words.get(j).equalsIgnoreCase(temp.get(t))) {
						result.add(tweets.get(i));
						count = 1;
						break;
					}
				}
				if (count == 1)
					break;
			}
		}
		return result;
	}

}
