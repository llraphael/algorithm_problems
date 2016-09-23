// This problem can also be categorized into divide-conquer class problem. The crucial part is how to 
// divide the problem into sub-problems. Suppose we divide the problem by the first ballon we burst, ith ballon,
// we would have the problem that the left and right element of ith ballon would become adjcent and affect each 
// other, which makes the division of the problem fail. However, we can think of the problem in a reverse direction:// the ith ballon is the one we burst at last. Thus, the left subproblem and right subproblem would not affect each
// other and we can divide the problem successfully. We can solve the problem by following steps:
// 1. Add bound value 1 for the array nums so that in nums[i..j], nums[i] and nums[j] are bound values, not ballons.
// 2. Create table[n][n]. table[i][j] is the max coins obtained from burst ballons in nums[i...j]. 
// 3. Update table from the last row to the first row and only update the right upper triangle:
//    table[i][j] = argmax_k (table[i][k] + nums[i] * nums[k] * nums[j] + table[k][j])

public class BurstBallons {
	public int maxCoins(int[] orgNums) {

		if(orgNums == null)
			return 0;
		
		int[] nums = new int[orgNums.length + 2];
		int n = 1;
		for(int num : orgNums)
			nums[n++] = num;
		nums[0] = nums[n++] = 1;

		int[][] table = new int[n][n];

		for(int i=n-1; i>=0; i--) {
			for(int j=i; j<n; j++) {
				if(i + 1 >= j)
					continue;
				for(int k=i+1; k<j; k++) {
					table[i][j] = Math.max(table[i][j], table[i][k] + nums[i] * nums[k] * nums[j] + table[k][j]);
				}
			}
		}
		return table[0][n-1];
	}
}
