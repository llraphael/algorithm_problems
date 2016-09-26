// This problem can be decomposed to two subproblems:
// 1. How to get max rectangle sum: kadane algorithm
// 2. Given an array, obtain the maximum sum (sum[i...j]) that is under K.
// 
// To solve the first problem, we have to use dynamic programming:
// 1) Keep an array int[rowNumber] rowSum, the size of which is the row number of the matrix.
//    Each element in the array rowSum[i], keeps the accumulative sum of its row. 
// 2) For each possible rectangle left bound, the right bound start from left bound and move to the right
//    by one each time, and update the rowSum each time. Until all the possible rectangles has been checked. 
//    After each update of rowSum, update the maxSum. 
// 
// In kadane algorithm, we have to find the maximum sum in the rowSum each time the array has been updated.
// In order to solve the problem, another constraint need to be added: maxSum <= K. The adaptive solution
// is that: create an array arr, arr[i] means the sum from rowSum[0] to rowSum[i]. Therefore, we can easily
// obtain sum[i...j] by arr[j] - arr[i - 1], except i = 0. We can use a set to store scanned arr[i], when 
// it comes to j, just to find in the set that if there exsits arr[i], which satisfies arr[i] >= arr[j] - K.
// The perfect case is that there exsits arr[i] = arr[j] - k.

class MaxRectangleSumUnderK {

	public int kadaneUnderK(int[][] matrix, int k) {
		
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		// Array for the accumulated sum for each row.
		int[] rowSum = new int[matrix.length];

		// Result.
		int res = Integer.MIN_VALUE;

		// Through moveing left and right bound, find the sum under k.
		for(int left=0; left<matrix[0].length; left++) {

			// Reset rowSum when start with a new left bound.
			Arrays.fill(rowSum, 0);

			for(int right=left; right<matrix[0].length; right++) {
				
				// Compute accumulated row sum from matrix[left] to matrix[right].
				for(int row=0; row<matrix.length; row++)
					rowSum[row] += matrix[row][right];

				// Find maximum sum under k and update res.
				res = Math.max(res, maxArraySumUnderK(rowSum, k));
			}
		}
		
		return res;
	}


	private int maxArraySumUnderK(int[] arr, int k) {
		
		TreeSet<Integer> preSums = new TreeSet<Integer>();
		preSums.add(0);

		int res = Integer.MIN_VALUE;
		int curSum = 0;
		for(int i=0; i<arr.length; i++) {
			
			curSum += arr[i];
			
			// sum[0...j] - sum[0...i] <= k
			int lowerBound = curSum - k;
			Integer findPreSum = preSums.ceiling(lowerBound);

			if(findPreSum != null)
				res = Math.max(res, curSum - findPreSum);

			preSums.add(curSum);
		}

		return res;
	}

}
