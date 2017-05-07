"""
Find all the duplicates in an array. 
Since 1 <= a[i] <= n and n is the length of the array, if there is no
duplicate, the array should contain every number from 1 to n and we can make
the correspondance between the value and its place in the array as (val, val - 1).
Thus, the general idea is to check each element in the array and put the element 
to its corresponding place. If the corresponding place already has the right value, a duplicate exists, the current element should be put to -1 to avoid over count. If not, just swap the current element and the element in the corresponding position.
"""

class Solution(object):
	def findDuplicates(self, nums):
		"""
		:type nums: List[int]
		:rtype: List[int]
		"""

		res = []
		i = 0
		while i < len(nums):
			if nums[i] != i + 1 and nums[i] != -1:
				index = nums[i] - 1
				if nums[index] == nums[i]:
					res.append(nums[i])
					nums[i] = -1
				else:
					nums[i], nums[index] = nums[index], nums[i]
					i = i - 1
			i = i + 1
		return res
