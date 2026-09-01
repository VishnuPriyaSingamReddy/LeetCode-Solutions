/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode P1=headA;
        ListNode P2=headB;
        while(P1!=P2){
            P1=(P1==null)?headB:P1.next;
            P2=(P2==null)?headA:P2.next;
        }
        return P1;
    }
}