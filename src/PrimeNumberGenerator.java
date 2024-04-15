public class PrimeNumberGenerator {
    PrimeNode head;

    @Override
    public String toString() {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator() {
        head = new PrimeNode(2);
    }

    public int currentPrime() {
        return head.value;
    }

    public int nextPrime() {
        if (head.next == null) {
            sieveOfEratosthenes();
        }
        head = head.next;
        return head.value;
    }

//    private PrimeNode getTail() {
//        // Head should never be null
//        PrimeNode node = head;
//
//        while (node.next != null) {
//            node = node.next;
//        }
//        return node;
//    }

    public void sieveOfEratosthenes() {
        int numElements = head.value * 2 + 1;
        boolean [] notPrime = new boolean[numElements];
        int jump = 2;

        while (jump < numElements) {
            int counter = 2 * jump;
            while (counter < numElements) {
                notPrime[counter] = true;
                counter += jump;
            }
            jump++;
        }

        PrimeNode current = head;

        // Current.value is minimum 2
        for (int i = current.value+1; i < numElements; i++) {
            if (!notPrime[i])
                current = current.next = new PrimeNode(i);
        }
    }

}
