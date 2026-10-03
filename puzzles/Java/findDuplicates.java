import static java.lang.System.out;
import java.lang.Math;
import java.util.Arrays;

public class findDuplicates {
  public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i=1; i<nums.length; i++) {
      if (nums[i-1] == nums[i])
	return true;
    }
    return false;
  }

  public static void main(String[] args) {
    findDuplicates fD = new findDuplicates();
    //int[] mTest = {9,8,1,2,3,4,5};
    int[] mTest = {-10};
    out.println(fD.containsDuplicate(mTest));
  }
}
