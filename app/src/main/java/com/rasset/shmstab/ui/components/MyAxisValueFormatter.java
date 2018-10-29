package com.rasset.shmstab.ui.components;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    public MyAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,###");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if(axis!=null)
            axis.setTextSize(16f);
        return mFormat.format(value);
    }
}
