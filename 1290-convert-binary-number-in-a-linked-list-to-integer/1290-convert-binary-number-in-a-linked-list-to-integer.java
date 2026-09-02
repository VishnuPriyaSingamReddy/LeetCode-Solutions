class Solution {
    public int getDecimalValue(ListNode head) {
        ListNode temp = head;
        int count=0;
        int ans=0;
        while(temp != null){
        count++;
        if(temp.next == null) break;
        temp=temp.next;         
        }
        
      temp=head;

        while(count >=0){
            ans+=Math.pow(2,count-1)*temp.val;
            count--;
            if(temp.next==null) break;
            temp=temp.next;
        }
        return ans;
    }
}