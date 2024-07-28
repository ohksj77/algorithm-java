package algorithm.programmers;

public class ImojiSaleEvent {

    private static final int[] RATE = {90, 80, 70, 60};
    private int emoticonPlus = 0;
    private int totalSales = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        getPrices(emoticons, users, 0, new int[emoticons.length]);
        return new int[] {emoticonPlus, totalSales};
    }

    private void getPrices(
            final int[] emoticons, final int[][] users, final int cur, final int[] rates) {
        if (cur == emoticons.length) {
            updateAnswer(emoticons, users, rates);
            return;
        }

        for (final int rate : RATE) {
            rates[cur] = rate;
            getPrices(emoticons, users, cur + 1, rates);
        }
    }

    private void updateAnswer(final int[] emoticons, final int[][] users, final int[] rates) {
        int ePlus = 0;
        int totalExpense = 0;

        for (final int[] user : users) {
            int expense = 0;
            final int rate = user[0];
            final int price = user[1];
            for (int i = 0; i < rates.length; i++) {
                if (100 - rates[i] >= rate) {
                    expense += emoticons[i] * rates[i] / 100;
                }
                if (expense >= price) {
                    ePlus += 1;
                    expense = 0;
                    break;
                }
            }
            totalExpense += expense;
        }

        if (ePlus > emoticonPlus) {
            emoticonPlus = ePlus;
            totalSales = totalExpense;
            return;
        }
        if (ePlus == emoticonPlus) {
            totalSales = Math.max(totalExpense, totalSales);
        }
    }
}
