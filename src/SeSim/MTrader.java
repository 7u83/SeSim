package SeSim;

import java.util.Random;

public class MTrader implements Trader {

    Exchange ex;
    Random rand;

    public MTrader(Exchange ex1, long shares, double money) {
        account.money = money;
        account.shares = shares;
        this.ex = ex;
        rand = new Random();
    }

    public void DoBuy() {
        //	System.out.println("AAA");

        if (account.orderpending) {
            return;
        }

        if (account.money <= 0) {
            return;
        }

        double perc = rand.nextDouble() * 1.0;
        double lp = ex.lastprice;
        double limit = lp / 100 * perc + lp;

//			System.out.println("HW");
        long size = (int) (account.money / limit);

        account.Buy(size, limit, ex);
        return;
    }

    public void DoSell() {
//			System.out.println("SoSell");

        if (account.orderpending) {
            return;
        }

        if (account.shares <= 0) {
            return;
        }

        double perc = rand.nextDouble() * 1.0;
        double lp = ex.lastprice;
        double limit = lp - lp / 100 * perc;

        long size = (int) (account.shares);

        account.Sell(size, limit, ex);
        return;
    }

    public void trade() {

        // What to do?
        int action = rand.nextInt(3);
        //	System.out.print("Action");
        //	System.out.println(action);
        if (action == 0) {
            return;
        }

        if (action == 1) {
            DoBuy();
            return;
        }

        if (action == 2) {
            DoSell();
            return;
        }

        //System.out.printf("MyPrice: %.2f\n",price);
    }

}