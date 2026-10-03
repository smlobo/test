/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var swapPairs = function(head) {
    if (head === null)
        return null;
    if (head.next === null)
        return head;
    
    // Recurse
    var rv = swapPairs(head.next.next);
    
    var ncache = head.next;
    head.next = rv;
    ncache.next = head;
    
    return ncache;
};

