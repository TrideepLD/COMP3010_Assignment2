package assg2;

import java.io.*;
import java.util.*;

public class Solver_with_lookup {

	int bids, lots;
	List<Bid> bidList;

	public void setBids(int bids) {
		this.bids = bids;
	}

	public void setLots(int lots) {
		this.lots = lots;
	}

	public static class Bid {
		int start, end, price;

		public Bid(int start, int end, int price) {
			this.start = start;
			this.end = end;
			this.price = price;
		}
	}

	/**
	 * You can use this to test your program without running the jUnit test,
	 * and you can use your own input file. You can of course also make your
	 * own tests and add it to the jUnit tests.
	 */
	public static void main(String[] args) {
		Solver_with_lookup m = new Solver_with_lookup();
		int answer = m.solve(null);
		System.out.println(answer);
	}

	/**
	 * The readData method accepts a String containing the
	 * path to the input file for the problem.
	 * Please see the assignment specification for more information
	 * on the input format.
	 *
	 * You should use this method to populate this class with
	 * the information that you need to solve the problem.
	 *
	 * @param infile the input file containing the problem
	 * @throws Exception if file is not found or if there is an input reading error
	 */
	public void readData(String infile) throws Exception {
		Scanner scanner = new Scanner(new File(infile));
		setLots(scanner.nextInt());
		setBids(scanner.nextInt());
		bidList = new ArrayList<>();
		for (int i = 0; i < bids; i++) {
			scanner.nextInt();
			bidList.add(new Bid(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
		}
	}

	/** The solve method accepts a String containing the 
	 * path to the input file for the problem (as described
	 * in the assignment specification) and returns an integer
	 * denoting the maximum income 
	 * 
	 * @param infile the file containing the input
	 * @return maximum income for this set of input
	 */
	
	public int solve(String infile) {
		try {
			readData(infile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		bidList.sort(Comparator.comparingInt(bid -> bid.end));
		HashMap<Integer, Integer> lookUp = new HashMap<>();
		int highestBid = 0;
		for (int i = 0; i < bids; i++) {
			int compatibleBids = getMaximumCompatiblePrice(bidList.get(i).end, i, bidList, lookUp);
			if (compatibleBids > highestBid)
				highestBid = compatibleBids;
		}
		return highestBid;
	}

	private int getMaximumCompatiblePrice(int end, int n, List<Bid> bidList, Map<Integer,Integer> lookUp) {
		Bid bid = bidList.get(n);
		int value = 0;
		for (int i = n + 1; i < bidList.size(); i++) {
			Bid candidateBid = bidList.get(i);
			if (candidateBid.start > end) {
				Integer maxValue = lookUp.getOrDefault(i, null);
				int subTreeTotal = 0;
				if (maxValue == null)
					subTreeTotal = getMaximumCompatiblePrice(candidateBid.end, i, bidList, lookUp);
				else
					subTreeTotal = maxValue;
				if (subTreeTotal > value) {
					value = subTreeTotal;
					lookUp.put(i , value);
				}
			}
		}
		return bid.price + value;
	}
}
