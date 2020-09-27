package assg2;

import java.io.*;
import java.util.*;

public class Solver {
	
	private String PATH = "src/assg2/data/test_case_01.in";
	ArrayList<int[]> sortedArray = new ArrayList<int[]>();
	/**
	 * 
	 * sortedArray()[0] = index Value;
	 * sortedArray()[1] = starting Bid;
	 * sortedArray()[2] = final Bid;
	 * sortedArray()[3] = Price;
	 * 
	 */
	
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		int maxIncome = 0;
		return findMaxIncome(maxIncome);
		
	}
	
	/**
	 * 
	 * @param maxIncome
	 * @return returns the maximum income.
	 * 
	 * Initiates a lookup table to be used by the recursive method
	 * and goes through each array at least once to find potential sub-trees
	 * The income value serves as a way to get the returned value from the recursion
	 * 
	 * The maxIncome is continually updated when the returned value(income) from the recursion is greater than it.
	 * The Final lot variable is also initialized here which is useful for the comparison that happens in the recursion
	 */
	
	int findMaxIncome(int maxIncome) {
		
		HashMap<Integer, Integer> lookUpTable = new HashMap<>();
		int size = (sortedArray.size())/2;
		int finalLot;
		int income = 0;
		
		for (int i = 0; i < size; i++) {
			finalLot = sortedArray.get(i)[2];
			
			income = maxIncomeRecursion(i, finalLot, income, sortedArray, lookUpTable);
			if (income > maxIncome) {
				maxIncome = income;
			}
		}
		return maxIncome;
	}
	
	
	/**
	 * 
	 * @param i this is the i variable from the for loop in findMaxIncome
	 * @param finalLot this is the finalLot variable initialized in fixMaxIncome. It is the finalLot of the parent subtree of the recursion
	 * @param income
	 * @param array the sorted Array
	 * @param lookUpTable the LookUpTable which stores subtree solutions
	 * @return returns income which is then used to check in findMaxIncome for the Maximum possible income gotten
	 * 
	 * a startLot and FinalLotOfJ is initialized. Start lot is used for the comparison to allow us to choose the right subtree
	 * FinalLotOfJ is simply initializing the FinalLot at the J array to use in the recursion
	 * 
	 * we have a lookUp table here which stores subtree solutions if it has not encountered it
	 * And if the value gotten from the recursion/in the lookUp is greater than the income/total we substitute the value in income/total
	 */
	int maxIncomeRecursion(int i, int finalLot, int income, ArrayList<int[]> array, Map<Integer,Integer> lookUpTable) {
		
		int startLot, price, total;
		
		total = 0;
		price = array.get(i)[3];
		
		for (int j = i+1; j < array.size(); j++) {
			
			startLot = array.get(j)[1];
			
			if (startLot>finalLot) {
				
				int finalLotOfJ = array.get(j)[2];
				Integer storedSubtree = lookUpTable.getOrDefault(j, null);
				
				if (storedSubtree == null) {
					storedSubtree = maxIncomeRecursion(j, finalLotOfJ, income, array, lookUpTable);
				}
				
				if (storedSubtree > total) {
					total = storedSubtree;
				}
				
				lookUpTable.put(j , storedSubtree);
			}
		}
		income = price+total;
		
		return income;
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
	 * 
	 * Here the file is read and parsed. Then it is added into a global array(of type ArrayList)
	 * sorting it to making it easier to find the optimal solution.
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
   				int startBid = in.nextInt();
   				int finalBid = in.nextInt();
   				int lotPrice = in.nextInt();
   	   			
   				int[] arr = {index, startBid, finalBid, lotPrice};
   				sortedArray.add(arr);
   			}
   	   		// Using lambda function to sort arrayList
   	   		// Sorts based on start bids.
   	   		sortedArray.sort(Comparator.comparingInt(c -> c[1]));
   		}
   		in.close();
	}
}
