package algorithm.programmers;

public class FoldingBills {

    public int solution(int[] wallet, int[] bill) {
        int walletLong = wallet[0] < wallet[1] ? wallet[1] : wallet[0];
        int walletShort = wallet[0] < wallet[1] ? wallet[0] : wallet[1];

        int billLong = bill[0] < bill[1] ? bill[1] : bill[0];
        int billShort = bill[0] < bill[1] ? bill[0] : bill[1];

        int answer = 0;

        while (walletLong < billLong || walletShort < billShort) {
            int temp = (int) (billLong / 2);

            if (billShort <= temp) {
                billLong = temp;
            } else {
                billLong = billShort;
                billShort = temp;
            }
            answer++;
        }

        return answer;
    }
}
