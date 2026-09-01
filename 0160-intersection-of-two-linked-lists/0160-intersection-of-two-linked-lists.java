public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tailA=headA;
        int a=0;
        int diff;
        while(tailA!=null){
         a++;
         if(tailA.next==null)break;
         tailA=tailA.next;
        }
        System.out.println(a);

        ListNode tailB=headB;
        int b=0;
        while(tailB!=null){
        b++;
        if(tailB.next==null)break;
        tailB=tailB.next;
        }

        if(a>b){
            diff=a-b;
            while(diff!=0){
                headA=headA.next;
                diff--;
            }

        }else{
            diff=b-a;
            while(diff!=0){
                headB=headB.next;
                diff--;
            }
        }

        while(headA!=null&&headB!=null){
            if(headA==headB){
                return headA;
            }
            headA=headA.next;
            headB=headB.next;
        }

        return null;


    }
}