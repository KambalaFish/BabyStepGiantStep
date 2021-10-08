package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class BabyStepGiantStep {
    private final int a, p, y;
    public BabyStepGiantStep(int a, int p, int y){
        this.a = a;
        this.p = p;
        this.y = y;
    }

    private int calculatePowerByMod(int base, int power) {
        int result = 1;
        while (power > 0) {
            if ((power & 1) == 1)
                result = (result * base) % p;
            base = (base * base) % p;
            power = power >> 1;
        }
        return result;
    }

    private void goBabySteps(ArrayList<RowElement> row, int m){
        int element = y;
        for (int i = 0; i<m; i++){
            row.add(new RowElement(element, i, RowType.BabyRow));
            element = (a * element) % p;
        }
    }

    private void goGiantSteps(ArrayList<RowElement> row, int m, int k){
        int am = calculatePowerByMod(a, m);
        int element = am;
        for (int i = m; i <= m*k; i+=m){
            row.add(new RowElement(element, i, RowType.GiantRow));
            element = (element * am) % p;
        }
    }

    public void calculateX(){
        int m = (int) Math.sqrt(p) + 1, k = m;
        ArrayList<RowElement> row = new ArrayList<>();
        goBabySteps(row, m);
        goGiantSteps(row, m, k);
        Collections.sort(row);
        RowElement cur, next;
        int im = 0, j = 0;
        for(int i=0; i<row.size();i++){
            cur = row.get(i);
            next = row.get(i+1);
            if (cur.compareTo(next)==0){
                if(cur.getRowType()==RowType.BabyRow){
                    j = cur.getIndex();
                    im = next.getIndex();
                } else {
                    j = next.getIndex();
                    im = cur.getIndex();
                }
                break;
            }
        }
        int x = im - j;
        System.out.println("x = "+x);

        int checkY = calculatePowerByMod(a, x);
        System.out.println("a^x mod p = "+a+"^"+x+" mod "+p+" = " + checkY);
        if (y == checkY){
            System.out.println("x was found successfully");
        } else
            System.out.println("x was not found");
    }
}
