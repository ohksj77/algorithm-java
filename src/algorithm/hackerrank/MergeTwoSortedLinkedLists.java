package algorithm.hackerrank;

public class MergeTwoSortedLinkedLists {

    public static void main(String[] args) throws Exception {}

    private static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        int first = -1;
        if (head1.data >= head2.data) {
            first = head2.data;
            head2 = head2.next;
        } else {
            first = head1.data;
            head1 = head1.next;
        }

        SinglyLinkedListNode newList = new SinglyLinkedListNode(first);
        SinglyLinkedListNode head = newList;

        while (head1 != null && head2 != null) {

            while (head1 != null && head2 != null && head1.data <= head2.data) {
                newList.next = new SinglyLinkedListNode(head1.data);
                head1 = head1.next;
                newList = newList.next;
            }

            while (head1 != null && head2 != null && head1.data >= head2.data) {
                newList.next = new SinglyLinkedListNode(head2.data);
                head2 = head2.next;
                newList = newList.next;
            }
        }
        while (head1 != null) {
            newList.next = new SinglyLinkedListNode(head1.data);
            head1 = head1.next;
            newList = newList.next;
        }
        while (head2 != null) {
            newList.next = new SinglyLinkedListNode(head2.data);
            head2 = head2.next;
            newList = newList.next;
        }

        return head;
    }
}
