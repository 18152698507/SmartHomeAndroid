package cn.zybwz.smarthomeandroid;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class RoomDetailActivity extends DemoBase implements
        OnChartValueSelectedListener {

    private LineChart chart;
    private List<ControlDataBean> controlLists=new LinkedList<>();
    private List<ViewDataBean> viewLists=new LinkedList<>();

    private static class NoLeakHandler extends Handler {

        private WeakReference<RoomDetailActivity> mActivity;

        private NoLeakHandler(RoomDetailActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RoomDetailActivity roomDetailActivity = mActivity.get();
            switch (msg.what){
                case 1:
                    int i=(int) msg.obj;
                    roomDetailActivity.chart.getDescription().setText("摄氏度/小时"+i);
                    roomDetailActivity.setData(25+i,60-i*10);
                    break;
                default:
                    break;
            }
        }
    }
    private final RoomDetailActivity.NoLeakHandler mHandler = new RoomDetailActivity.NoLeakHandler(this);

//    private SeekBar seekBarX, seekBarY;
//    private TextView tvX, tvY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        RecyclerView recycler_control = findViewById(R.id.room_detail_rv_control);
        LinearLayoutManager m=new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        controlLists.add(new ControlDataBean("灯",true));
        controlLists.add(new ControlDataBean("空调",false));
        controlLists.add(new ControlDataBean("窗帘",true));
        controlLists.add(new ControlDataBean("电视",false));
        recycler_control.setLayoutManager(m);
        recyclerControladapter adapter=new recyclerControladapter(controlLists,this);
        recycler_control.setAdapter(adapter);
        RecyclerView recycler_view = findViewById(R.id.room_detail_rv_view);
        LinearLayoutManager m1=new LinearLayoutManager(this);
        m1.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewLists.add(new ViewDataBean("温度","22℃",1,"温度过高"));
        viewLists.add(new ViewDataBean("湿度","22℃",0,null));
        viewLists.add(new ViewDataBean("CO","0.25",2,"一氧化碳浓度过高"));
        viewLists.add(new ViewDataBean("光照","100LX",0,null));
        recycler_view.setLayoutManager(m1);
        recyclerViewadapter adapter1=new recyclerViewadapter(viewLists,this,mHandler);
        recycler_view.setAdapter(adapter1);
        {   // // Chart Style // //
            chart = findViewById(R.id.chart1);

            // background color
            chart.setBackground(getResources().getDrawable(R.drawable.my_chart_background));

            // disable description text
            chart.getDescription().setEnabled(true);
            chart.getDescription().setText("摄氏度/小时");

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);

            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(chart);
            chart.setMarker(mv);

            chart.setScaleEnabled(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(1f, 1f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(60f);
            yAxis.setAxisMinimum(-20f);
        }

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
        setData(25,60);
//        SwitchButton switchButton = (com.suke.widget.SwitchButton)
//                findViewById(R.id.switch_button);
//
//        switchButton.setChecked(true);
//        switchButton.isChecked();
//        switchButton.toggle();     //switch state
//        switchButton.toggle(true);//switch without animation
//        switchButton.setShadowEffect(true);//disable shadow effect
//        switchButton.setEnabled(true);//disable button
//        switchButton.setEnableEffect(true);//disable the switch animation
//        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                //TODO do your job
//            }
//        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int j=0;j<10;j++){
//                    try{
//                        setData(25,60+j);
//                        Thread.sleep(1000);
//                    }catch (InterruptedException E){
//                        E.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }

    private void setData(int count,float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val =(float) (Math.random() * range) - 10;
            values.add(new Entry(i, val, null));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
            chart.invalidate();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "温度");

            set1.setDrawIcons(false);
            set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(2f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            //set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawValues(false);
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
        }
    }


    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "LineChartActivity1");
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

}
