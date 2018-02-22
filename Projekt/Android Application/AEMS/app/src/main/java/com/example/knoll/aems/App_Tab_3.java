package com.example.knoll.aems;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by knoll on 26.08.2017.
 */

public class App_Tab_3 extends ChartViewTab {

    public App_Tab_3() {
        super(R.layout.app_tab_3, R.id.chart3);
    }

    public String period = "day";
    public boolean vorperiode = false;
    public boolean anomalie = true;
    String[] xVals;

    @Override
    public void onCreateChart(Chart chart1) {
    CombinedChart chart = (CombinedChart)chart1;
        chart.setSaveEnabled(true);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.animateY(2000, Easing.EasingOption.EaseInBack);
        chart.setHighlightFullBarEnabled(false);
        chart.setTouchEnabled(false);

        chart.setDrawOrder(new CombinedChart.DrawOrder[]{
        CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
    });

    //Legend Format
    Legend legend = chart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

    //Left Y-Axis
    YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(8f);


    //Anomalie Axis
        if(anomalie == true){
        //Right Y-Axis
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(15f);
        rightAxis.setAxisMaximum(30f);
    }


    //X-Axis
    XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        if(period.equals("day")){
        xVals = new String[]{"0-2 Uhr", "2-4 Uhr", "4-6 Uhr", "6-8 Uhr", "8-10 Uhr", "10-12 Uhr", "12-14 Uhr", "14-16 Uhr", "16-18 Uhr", "18-20 Uhr", "20-22 Uhr", "22-24 Uhr"};
        xAxis.setAxisMaximum(12f);
        xAxis.setLabelCount(12);
        xAxis.setLabelRotationAngle(25f);
        xAxis.setTextSize(7f);

        if(vorperiode == true){
            xAxis.setAxisMinimum(0.01f);
        }
        else {
            xAxis.setAxisMinimum(0.5f);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVals[(int) value-1];
            }
        });
    }
        else if(period.equals("week")){
        xVals = new String[]{"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
        xAxis.setAxisMaximum(7f);
        xAxis.setLabelCount(7);
        xAxis.setLabelRotationAngle(20f);
        xAxis.setTextSize(7f);

        if(vorperiode == true){
            xAxis.setAxisMinimum(0.01f);
        }
        else {
            xAxis.setAxisMinimum(0.5f);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVals[(int) value-1];
            }
        });
    }
        else if(period.equals("month")){
        xVals = new String[]{"Woche 1", "Woche 2", "Woche 3", "Woche 4"};
        xAxis.setAxisMaximum(4f);
        xAxis.setLabelCount(4);
        xAxis.setLabelRotationAngle(15f);
        xAxis.setTextSize(7f);

        if(vorperiode == true){
            xAxis.setAxisMinimum(0.01f);

            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xVals[(int) value-1];
                }
            });
        }
        else {
            xAxis.setAxisMinimum(0.5f);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xVals[(int) value];
                }
            });
        }
    }
        else if(period.equals("year")){
        xVals = new String[]{"Jänner", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
        xAxis.setAxisMaximum(12f);
        xAxis.setLabelCount(12);
        xAxis.setLabelRotationAngle(25f);
        xAxis.setTextSize(7f);

        if(vorperiode == true){
            xAxis.setAxisMinimum(0.01f);
        }
        else {
            xAxis.setAxisMinimum(0.5f);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVals[(int) value-1];
            }
        });
    }


    //Chart Object
    CombinedData data = new CombinedData();

        if(period.equals("day") && vorperiode == true && anomalie == true){
        data.setData(generateBarDataDayWithPriorPeriod());
        data.setData(generateLineDataDay());
    }
        else if(period.equals("day") && vorperiode == true && anomalie == false){
        data.setData(generateBarDataDayWithPriorPeriod());
    }
        else if(period.equals("day") && vorperiode == false && anomalie == true){
        data.setData(generateBarDataDayWithoutPriorPeriod());
        data.setData(generateLineDataDaySingleBar());
    }
        else if(period.equals("day") && vorperiode == false && anomalie == false){
        data.setData(generateBarDataDayWithoutPriorPeriod());
    }
        else if(period.equals("week") && vorperiode == true && anomalie == true){
        data.setData(generateBarDataWeekWithPriorPeriod());
        data.setData(generateLineDataWeek());
    }
        else if(period.equals("week") && vorperiode == true && anomalie == false){
        data.setData(generateBarDataWeekWithPriorPeriod());
    }
        else if(period.equals("week") && vorperiode == false && anomalie == true){
        data.setData(generateBarDataWeekWithoutPriorPeriod());
        data.setData(generateLineDataWeekSingleBar());
    }
        else if(period.equals("week") && vorperiode == false && anomalie == false){
        data.setData(generateBarDataWeekWithoutPriorPeriod());
    }
        else if(period.equals("month") && vorperiode == true && anomalie == true){
        data.setData(generateBarDataMonthWithPriorPeriod());
        data.setData(generateLineDataMonth());
    }
        else if(period.equals("month") && vorperiode == true && anomalie == false){
        data.setData(generateBarDataMonthWithPriorPeriod());
    }
        else if(period.equals("month") && vorperiode == false && anomalie == true){
        data.setData(generateBarDataMonthWithoutPriorPeriod());
        data.setData(generateLineDataMonthSingleBar());
    }
        else if(period.equals("month") && vorperiode == false && anomalie == false){
        data.setData(generateBarDataMonthWithoutPriorPeriod());
    }
        else if(period.equals("year") && vorperiode == true && anomalie == true){
        data.setData(generateBarDataYearWithPriorPeriod());
        data.setData(generateLineDataYear());
    }
        else if(period.equals("year") && vorperiode == true && anomalie == false){
        data.setData(generateBarDataYearWithPriorPeriod());
    }
        else if(period.equals("year") && vorperiode == false && anomalie == true){
        data.setData(generateBarDataYearWithoutPriorPeriod());
        data.setData(generateLineDataYearSingleBar());
    }
        else if(period.equals("year") && vorperiode == false && anomalie == false){
        data.setData(generateBarDataYearWithoutPriorPeriod());
    }


        xAxis.setAxisMaximum(data.getXMax() + 0.2f);

        chart.setData(data);
        chart.invalidate();
}


    @Override
    public String getStatisticTitle() {
        return ((TextView)getView().findViewById(R.id.statisticTitle)).getText().toString();
    }


    private BarData loadGoupedDataSet(ArrayList<BarEntry> entries1, ArrayList<BarEntry> entries2) {
        BarDataSet set1 = new BarDataSet(entries1, "Aktueller Verbrauch");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(5f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);


        BarDataSet set2 = new BarDataSet(entries2, "Verbrauch Vorperiode");
        set2.setColor(Color.rgb(61, 165, 255));
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(5f);

        float groupSpace = 0.15f; // Space between Bar Groups
        float barSpace = 0.05f;
        float barWidth = 0.385f;

        BarData barD = new BarData(set1, set2);
        barD.setBarWidth(barWidth);

        // Makes BarData object grouped
        barD.groupBars(0, groupSpace, barSpace); // Start at x = 0

        return barD;
    }

    private BarData loadSingleBarDataSet(ArrayList<BarEntry> entries1) {
        BarDataSet set1 = new BarDataSet(entries1, "Aktueller Verbrauch");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(8f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        float barWidth = 0.5f;

        BarData barD = new BarData(set1);
        barD.setBarWidth(barWidth);

        return barD;
    }

    private LineData loadLineData(ArrayList<Entry> entries){

        LineDataSet dataSet = new LineDataSet(entries, "Außentemperatur");

        dataSet.setColor(Color.LTGRAY);
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setCircleRadius(3f);
        dataSet.setFillColor(Color.BLUE);
        dataSet.setMode(LineDataSet.Mode.LINEAR);
        dataSet.setDrawValues(false);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

        LineData lineD = new LineData(dataSet);

        return lineD;
    }



    //Day
    private BarData generateBarDataDayWithPriorPeriod() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));
        entries1.add(new BarEntry(5f, 3));
        entries1.add(new BarEntry(6f, 7));
        entries1.add(new BarEntry(7f, 2));
        entries1.add(new BarEntry(8f, 6));
        entries1.add(new BarEntry(9f, 5));
        entries1.add(new BarEntry(10f, 4));
        entries1.add(new BarEntry(11f, 3));
        entries1.add(new BarEntry(12f, 2));

        entries2.add(new BarEntry(1f, 2));
        entries2.add(new BarEntry(2f, 6));
        entries2.add(new BarEntry(3f, 5));
        entries2.add(new BarEntry(4f, 4));
        entries2.add(new BarEntry(5f, 3));
        entries2.add(new BarEntry(6f, 2));
        entries2.add(new BarEntry(7f, 2));
        entries2.add(new BarEntry(8f, 6));
        entries2.add(new BarEntry(9f, 4));
        entries2.add(new BarEntry(10f, 7));
        entries2.add(new BarEntry(11f, 3));
        entries2.add(new BarEntry(12f, 7));

        BarData barD = loadGoupedDataSet(entries1, entries2);

        return barD;
    }


    private BarData generateBarDataDayWithoutPriorPeriod() {
        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));
        entries1.add(new BarEntry(5f, 3));
        entries1.add(new BarEntry(6f, 7));
        entries1.add(new BarEntry(7f, 5));
        entries1.add(new BarEntry(8f, 2));
        entries1.add(new BarEntry(9f, 6));
        entries1.add(new BarEntry(10f, 4));
        entries1.add(new BarEntry(11f, 7));
        entries1.add(new BarEntry(12f, 3));

        BarData barD = loadSingleBarDataSet(entries1);

        return barD;
    }


    //Line Data
    private LineData generateLineDataDay() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0.6f, 21f));
        entries.add(new Entry(1.6f, 23f));
        entries.add(new Entry(2.6f, 24f));
        entries.add(new Entry(3.6f, 26f));
        entries.add(new Entry(4.6f, 25f));
        entries.add(new Entry(5.6f, 21f));
        entries.add(new Entry(6.65f, 23f));
        entries.add(new Entry(7.65f, 21f));
        entries.add(new Entry(8.65f, 23f));
        entries.add(new Entry(9.65f, 24f));
        entries.add(new Entry(10.65f, 26f));
        entries.add(new Entry(11.65f, 25f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }

    private LineData generateLineDataDaySingleBar() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(1f, 21f));
        entries.add(new Entry(2f, 23f));
        entries.add(new Entry(3f, 24f));
        entries.add(new Entry(4f, 26f));
        entries.add(new Entry(5f, 25f));
        entries.add(new Entry(6f, 21f));
        entries.add(new Entry(7f, 23f));
        entries.add(new Entry(8f, 21f));
        entries.add(new Entry(9f, 23f));
        entries.add(new Entry(10f, 24f));
        entries.add(new Entry(11f, 26f));
        entries.add(new Entry(12f, 25f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }



    //Week
    private BarData generateBarDataWeekWithPriorPeriod() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));
        entries1.add(new BarEntry(5f, 3));
        entries1.add(new BarEntry(6f, 7));
        entries1.add(new BarEntry(7f, 5));

        entries2.add(new BarEntry(1f, 2));
        entries2.add(new BarEntry(2f, 6));
        entries2.add(new BarEntry(3f, 5));
        entries2.add(new BarEntry(4f, 4));
        entries2.add(new BarEntry(5f, 3));
        entries2.add(new BarEntry(6f, 2));
        entries2.add(new BarEntry(7f, 5));

        BarData barD = loadGoupedDataSet(entries1, entries2);

        return barD;
    }

    private BarData generateBarDataWeekWithoutPriorPeriod() {
        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));
        entries1.add(new BarEntry(5f, 3));
        entries1.add(new BarEntry(6f, 7));
        entries1.add(new BarEntry(7f, 5));

        BarData barD = loadSingleBarDataSet(entries1);

        return barD;
    }

    //Line Data
    private LineData generateLineDataWeek() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0.6f, 21f));
        entries.add(new Entry(1.6f, 23f));
        entries.add(new Entry(2.6f, 24f));
        entries.add(new Entry(3.6f, 26f));
        entries.add(new Entry(4.6f, 25f));
        entries.add(new Entry(5.6f, 21f));
        entries.add(new Entry(6.6f, 23f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }

    private LineData generateLineDataWeekSingleBar() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(1f, 21f));
        entries.add(new Entry(2f, 23f));
        entries.add(new Entry(3f, 24f));
        entries.add(new Entry(4f, 26f));
        entries.add(new Entry(5f, 25f));
        entries.add(new Entry(6f, 21f));
        entries.add(new Entry(7f, 23f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }


    //Month
    private BarData generateBarDataMonthWithPriorPeriod() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));

        entries2.add(new BarEntry(1f, 2));
        entries2.add(new BarEntry(2f, 6));
        entries2.add(new BarEntry(3f, 5));
        entries2.add(new BarEntry(4f, 4));

        BarData barD = loadGoupedDataSet(entries1, entries2);

        return barD;
    }


    private BarData generateBarDataMonthWithoutPriorPeriod() {
        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));

        BarData barD = loadSingleBarDataSet(entries1);

        return barD;
    }

    //Line Data
    private LineData generateLineDataMonth() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0.6f, 21f));
        entries.add(new Entry(1.6f, 23f));
        entries.add(new Entry(2.6f, 24f));
        entries.add(new Entry(3.6f, 26f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }

    private LineData generateLineDataMonthSingleBar() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(1f, 21f));
        entries.add(new Entry(2f, 23f));
        entries.add(new Entry(3f, 24f));
        entries.add(new Entry(4f, 26f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }


    //Year
    private BarData generateBarDataYearWithPriorPeriod() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));
        entries1.add(new BarEntry(5f, 3));
        entries1.add(new BarEntry(6f, 7));
        entries1.add(new BarEntry(7f, 2));
        entries1.add(new BarEntry(8f, 6));
        entries1.add(new BarEntry(9f, 5));
        entries1.add(new BarEntry(10f, 4));
        entries1.add(new BarEntry(11f, 3));
        entries1.add(new BarEntry(12f, 2));

        entries2.add(new BarEntry(1f, 2));
        entries2.add(new BarEntry(2f, 6));
        entries2.add(new BarEntry(3f, 5));
        entries2.add(new BarEntry(4f, 4));
        entries2.add(new BarEntry(5f, 3));
        entries2.add(new BarEntry(6f, 2));
        entries2.add(new BarEntry(7f, 2));
        entries2.add(new BarEntry(8f, 6));
        entries2.add(new BarEntry(9f, 4));
        entries2.add(new BarEntry(10f, 7));
        entries2.add(new BarEntry(11f, 3));
        entries2.add(new BarEntry(12f, 7));

        BarData barD = loadGoupedDataSet(entries1, entries2);

        return barD;
    }

    private BarData generateBarDataYearWithoutPriorPeriod() {
        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();

        entries1.add(new BarEntry(1f, 2));
        entries1.add(new BarEntry(2f, 6));
        entries1.add(new BarEntry(3f, 4));
        entries1.add(new BarEntry(4f, 7));
        entries1.add(new BarEntry(5f, 3));
        entries1.add(new BarEntry(6f, 7));
        entries1.add(new BarEntry(7f, 5));
        entries1.add(new BarEntry(8f, 2));
        entries1.add(new BarEntry(9f, 6));
        entries1.add(new BarEntry(10f, 4));
        entries1.add(new BarEntry(11f, 7));
        entries1.add(new BarEntry(12f, 3));

        BarData barD = loadSingleBarDataSet(entries1);

        return barD;
    }

    //Line Data
    private LineData generateLineDataYear() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0.6f, 21f));
        entries.add(new Entry(1.6f, 23f));
        entries.add(new Entry(2.6f, 24f));
        entries.add(new Entry(3.6f, 26f));
        entries.add(new Entry(4.6f, 25f));
        entries.add(new Entry(5.6f, 21f));
        entries.add(new Entry(6.65f, 23f));
        entries.add(new Entry(7.65f, 21f));
        entries.add(new Entry(8.65f, 23f));
        entries.add(new Entry(9.65f, 24f));
        entries.add(new Entry(10.65f, 26f));
        entries.add(new Entry(11.65f, 25f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }

    private LineData generateLineDataYearSingleBar() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(1f, 21f));
        entries.add(new Entry(2f, 23f));
        entries.add(new Entry(3f, 24f));
        entries.add(new Entry(4f, 26f));
        entries.add(new Entry(5f, 25f));
        entries.add(new Entry(6f, 21f));
        entries.add(new Entry(7f, 23f));
        entries.add(new Entry(8f, 21f));
        entries.add(new Entry(9f, 23f));
        entries.add(new Entry(10f, 24f));
        entries.add(new Entry(11f, 26f));
        entries.add(new Entry(12f, 25f));

        LineData lineD = loadLineData(entries);

        return lineD;
    }


}
