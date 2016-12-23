package com.nhatminh.playgame;

import android.util.Log;

import com.nhatminh.vocabulary.MyApplication;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by caonh on 10/26/2016.
 */
public class RandomArray {
    int i = 0;
    ArrayList<Value> l = new ArrayList<Value>();
    public void initList(int soluong)
    {
        for(int i=0; i<soluong; i++)
        {
            Value ii = new Value(i);
            this.l.add(ii);
        }
    }
    public RandomArray(int soluong)
    {
        initList(soluong);
    }
    public void Swap(int n, int m)
    {
        int temp = this.l.get(n).getV();
        this.l.get(n).setV(this.l.get(m).getV());
        this.l.get(m).setV(temp);
    }

    public void RandomValue1()
    {
        i++;
        Random r= new Random();

        int n = r.nextInt(l.size());
        int m = r.nextInt(l.size());
        Log.i("random",l.size()+" - "+ i+" :--: " + n +" - "+m);
        Swap(n,m);

    }
    public void RandomValue()
    {
        int n = l.size();
        for (int i =0; i< n; i++)
        {
            RandomValue1();
        }

    }

    public ArrayList<Integer> getValue()
    {
        RandomValue();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i =0 ; i< this.l.size(); i++)
        {
            list.add(l.get(i).getV());
            Log.i("random", i+": " + l.get(i).getV());
        }
        return list;
    }
}
