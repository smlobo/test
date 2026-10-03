import static java.lang.System.out;

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}

public class linkedListPalindrome {

  public static void printLL(ListNode head) {
    while (head != null) {
      out.print(head.val + ", ");
      head = head.next;
    }
    out.println();
  }

  static ListNode reverse = null;
  static ListNode cRnode = null;
/*
  public boolean isPalindrome(ListNode head) {
    boolean retVal = false;

    // end of list
    if (head.next == null) {
      // 1 long list
      if (reverse == null) {
        retVal = true;
      }
      // gone past the reverse end
      else if (cRnode == null) {
      }
      // check palindrome
      else if (head.val == cRnode.val) {
        retVal = true;
      }
      // check palindrome - odd list length middle
      else if (cRnode == reverse && cRnode.next != null && 
               head.val == cRnode.next.val) {
        retVal = true;
      }
    }
    // not end of list
    else {
      ListNode next = head.next;

      // setup reverse list
      head.next = reverse;
      reverse = head;

      if (cRnode == null) {
        cRnode = head;
      }
      // check palindrome
      else if (head.val == cRnode.val) {
        cRnode = cRnode.next;
      }
      // check palindrome - odd list length middle
      else if (cRnode == reverse && cRnode.next != null && 
               head.val == cRnode.next.val) {
        cRnode = cRnode.next.next;
      }
      // not palindrome - reset
      else {
        cRnode = reverse;
      }

      retVal = isPalindrome(next);
    }

    // 
    return retVal;
  }
*/
/*
  public boolean isPalindrome(ListNode head) {
    ListNode reverseN = null;
    ListNode currentN = null;

    boolean retVal = true;

    while (head != null) {
      ListNode next = head.next;

      head.next = reverseN;
      reverseN = head;

      if (head.next == null && next == null) {
      }
      else if (currentN == null) {
        currentN = reverseN;
        retVal = false;
      }
      else {
        if (currentN.val == head.val) {
          currentN = currentN.next;
          retVal = true;
        }
        else if (currentN == reverseN.next && currentN.next != null && 
                 currentN.next.val == head.val) {
          currentN = currentN.next.next;
          retVal = true;
        }
        else if (reverseN.next != null && reverseN.next.next != null && 
                 reverseN.next.next.next == currentN && 
                 reverseN.val == reverseN.next.next.val) {
          //currentN = reverseN.next.next.next;
          retVal = true;
        }
        else {
          currentN = reverseN;
          retVal = false;
        }
      }

      head = next;
    }

    if (currentN != null) {
      retVal = false;
    }

    return retVal;
  }
*/

  public boolean isPalindrome(ListNode head) {
    // Count list size
    int count = 0;
    ListNode iter = head;
    while (iter != null) {
      count++;
      iter = iter.next;
    }
    out.println("List size: " + count);

    // List <= 1
    if (count <= 1)
      return true;

    // Go to the middle reversing the LHS
    iter = head;
    ListNode reverse = null;
    for (int i=0; i<count/2; i++) {
      ListNode next = iter.next;
      iter.next = reverse;
      reverse = iter;
      iter = next;
    }

    // Adjust for odd length lists
    if (count%2 != 0)
      iter = iter.next;

    out.print("Reverse: "); printLL(reverse);
    out.print("Forward: "); printLL(iter);

    // Check palindrome
    for (int i=0; i<count/2; i++) {
      if (reverse.val != iter.val)
        return false;
      reverse = reverse.next;
      iter = iter.next;
    }

    return true;
  }
  
  public static void main(String[] args) {
    linkedListPalindrome lLP = new linkedListPalindrome();

    ListNode l1 = new ListNode(9);
    printLL(l1);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l1));

    ListNode l2 = new ListNode(9);
    l2.next = new ListNode(9);
    printLL(l2);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l2));

    ListNode l3 = new ListNode(9);
    l3.next = new ListNode(5);
    l3.next.next = new ListNode(9);
    printLL(l3);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l3));

    l3 = new ListNode(1);
    l3.next = new ListNode(0);
    l3.next.next = new ListNode(0);
    printLL(l3);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l3));

    ListNode l4 = new ListNode(5);
    l4.next = new ListNode(9);
    l4.next.next = new ListNode(9);
    l4.next.next.next = new ListNode(5);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));

    l4 = new ListNode(5);
    l4.next = new ListNode(10);
    l4.next.next = new ListNode(9);
    l4.next.next.next = new ListNode(5);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));

    l4 = new ListNode(5);
    l4.next = new ListNode(9);
    l4.next.next = new ListNode(9);
    l4.next.next.next = new ListNode(5);
    l4.next.next.next.next = new ListNode(5);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));

    l4 = new ListNode(6);
    l4.next = new ListNode(9);
    l4.next.next = new ListNode(11);
    l4.next.next.next = new ListNode(9);
    l4.next.next.next.next = new ListNode(5);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));

    l4 = new ListNode(1);
    l4.next = new ListNode(2);
    l4.next.next = new ListNode(2);
    l4.next.next.next = new ListNode(2);
    l4.next.next.next.next = new ListNode(1);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));

    l4 = new ListNode(5);
    l4.next = new ListNode(9);
    l4.next.next = new ListNode(11);
    l4.next.next.next = new ListNode(9);
    l4.next.next.next.next = new ListNode(5);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));

    l4 = new ListNode(5);
    l4.next = new ListNode(9);
    l4.next.next = new ListNode(8);
    l4.next.next.next = new ListNode(9);
    l4.next.next.next.next = new ListNode(8);
    l4.next.next.next.next.next = new ListNode(9);
    l4.next.next.next.next.next.next = new ListNode(5);
    printLL(l4);
    reverse = null; cRnode = null;
    out.println(lLP.isPalindrome(l4));


  }
}
