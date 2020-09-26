package assg2;

import java.io.*;
import java.util.*;

public class Solver {
	
	private static String PATH = "src/assg2/data/test_case_01.in";
	ArrayList<int[]> arr = new ArrayList<int[]>();
	ArrayList<int[]> sortedArray = new ArrayList<int[]>();
	
	/**
	 * You can use this to test your program without running the jUnit test,
	 * and you can use your own input file. You can of course also make your
	 * own tests and add it to the jUnit tests.
	 */
	public static void main(String[] args) {
		Solver m = new Solver();
		int answer = m.solve(null);
		System.out.println(answer);
		
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
			printArray();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		int returnValue = 0;
//		int highestBid = 0;
//		int returnValue = firstForLoop(highestBid);
//		System.out.println(returnValue);
		return firstForLoopButForSortedArray(returnValue);
		
	}
	/**
	 * 
	 * arr()[0] = index Value;
	 * arr()[1] = starting Bid;
	 * arr()[2] = final Bid;
	 * arr()[3] = Price;
	 * 
	 */
	public void printArray() {
//		System.out.println(Arrays.deepToString(arr.toArray()));
		System.out.println(Arrays.deepToString(sortedArray.toArray()));
	}
	
	int secondForLoop(int i, int finalBid, ArrayList<int[]> array, Map<Integer,Integer> lookUp) {
		
		int startBid, lotPrice = 0;
		int total = 0;
		
		for (int j = i+1; j < array.size(); j++) {
			startBid = array.get(j)[1];
			lotPrice = array.get(j)[3];
//			System.out.println(" j: " + j);
			if (startBid > finalBid) {
				Integer maxValue = lookUp.getOrDefault(j, null);
				int subTreeTotal = 0;
				if (maxValue == null)
					subTreeTotal = secondForLoop(j, array.get(j)[2], array, lookUp);
				else
					subTreeTotal = maxValue;
				if (subTreeTotal > total) {
					total = subTreeTotal;
					lookUp.put(j , total);
				}
			}
		}
		return array.get(i)[3] + total;
		
	}
	
	int firstForLoop(int highestBid) {
		int max = 0;
//		int finalBid;
		HashMap<Integer, Integer> lookUp = new HashMap<>();
		for (int i = 0; i < arr.size(); i++) {
//			finalBid = arr.get(i)[2];
			// method call here
			// method returns highest value of that particular sub-tree
			int compatibleBids = secondForLoop(i, arr.get(i)[2], arr, lookUp);
			if (compatibleBids > highestBid)
				highestBid = compatibleBids;
		}
		return highestBid;
	}
	
	int firstForLoopButForSortedArray(int returnedValue) {
		int max = 0;
		HashMap<Integer, Integer> lookUp = new HashMap<>();
		for (int i = 0; i < arr.size(); i++) {
//			finalBid = arr.get(i)[2];
			// method call here
			// method returns highest value of that particular sub-tree
			int current = secondForLoop(i, sortedArray.get(i)[2], sortedArray, lookUp);
			if (current > returnedValue)
				returnedValue = current;
//				System.out.println(returnedValue);
		}
		return returnedValue;
	}
	
	int nestedForLoopWhichIsUsedForRecursion(int i, int finalBid, ArrayList<int[]> array, Map<Integer,Integer> lookUp) {
		int startBid, lotPrice = 0;
		int total = 0;
		
		for (int j = i+1; j < array.size(); j++) {
			startBid = array.get(j)[1];
			lotPrice = array.get(j)[3];
//			System.out.println(" j: " + j);
			if (startBid > finalBid) {
				Integer maxValue = lookUp.getOrDefault(j, null);
				int subTreeTotal = 0;
				if (maxValue == null)
					subTreeTotal = secondForLoop(j, array.get(j)[2], array, lookUp);
				else
					subTreeTotal = maxValue;
				if (subTreeTotal > total) {
					total = subTreeTotal;
					lookUp.put(j , total);
				}
			}
		}
		
		return array.get(i)[3] + total;
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
   		Scanner in = new Scanner(new FileReader(infile));
   		
   		while (in.hasNext()) {
   			int lots;
   	   		int bids;
   	   		lots = in.nextInt();
   	   		bids = in.nextInt();
   	   		System.out.println("Lots: " + lots + " bids: " + bids);
   	   			
   	   		for (int i = 0; i < bids ; i++) {
   	   	   		int index = in.nextInt();
   	   	   		System.out.print(index);
   	   	   		System.out.print(" ");
   				int startBid = in.nextInt();
   				System.out.print(startBid);
   				System.out.print(" ");
   				int finalBid = in.nextInt();
   				System.out.print(finalBid);
   				System.out.print(" ");
   				int lotPrice = in.nextInt();
   	   			System.out.print(lotPrice);
   	   			System.out.print(" ");
   	   			System.out.println("");
   	   			
   				int[] arr2 = {index, startBid, finalBid, lotPrice};
   				arr.add(arr2);
   				sortedArray.add(arr2);
   			}
   	   		arr.sort(Comparator.comparingInt(c -> c[2]));
   	   		sortedArray.sort(Comparator.comparingInt(c -> c[1]));
   		}
   		in.close();
	}
}
