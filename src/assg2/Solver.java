package assg2;

import java.io.*;
import java.util.*;

public class Solver {
	
	private static String PATH = "src/assg2/data/test_case_01.in";
	ArrayList<int[]> arr = new ArrayList<int[]>();
	
	/**
	 * You can use this to test your program without running the jUnit test,
	 * and you can use your own input file. You can of course also make your
	 * own tests and add it to the jUnit tests.
	 */
	public static void main(String[] args) {
		Solver m = new Solver();
		m.solve(PATH);
//		int answer = m.solve(null);
//		System.out.println(answer);
		
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
			System.out.println(Arrays.deepToString(arr.toArray()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
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
   			}
   	   		arr.sort(Comparator.comparingInt(c -> c[1]));
   		}
   		in.close();
   		
	}

}
