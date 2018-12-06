package cn.zybwz.smarthomeandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * 购水记录的 listview的适配器
 */

public class RecordAdapter extends ArrayAdapter<RecordDM> {
    private int resourceId;

    public RecordAdapter(Context context, int textViewResourceId, List<RecordDM> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        RecordDM recordDM = getItem(position);
        ViewHolder viewHolder;
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            /*
             * 找到对应的实例
             */
            viewHolder.roomName = (TextView) view.findViewById(R.id.list_tv_room_name);
            viewHolder.temp = (TextView) view.findViewById(R.id.list_tv_temp);
            viewHolder.illumination = (TextView) view.findViewById(R.id.list_tv_illumination);
            viewHolder.roomImage = (ImageView) view.findViewById(R.id.list_iv_room_image);
            viewHolder.co = (TextView) view.findViewById(R.id.list_tv_co);
            viewHolder.humidity=(TextView)view.findViewById(R.id.list_tv_humidity);
            view.setTag(viewHolder);
        }

        else {
            view=convertView;
            viewHolder = (RecordAdapter.ViewHolder) view.getTag();
        }

        /*
         * 传入数据到listview的一个item上
         */
        viewHolder.temp.setText(recordDM.getTemp()+"摄氏度");
        viewHolder.humidity.setText(recordDM.getHumidity());
        viewHolder.co.setText(recordDM.getCo());
        viewHolder.illumination.setText(recordDM.getIllumination());
        if (recordDM.getRoomName().equals("keting")){
            viewHolder.roomName.setText("客厅");
            viewHolder.roomImage.setImageResource(R.drawable.keting);
        }

//        /*
//         * 显示是否同步
//         */
//        if (recordDM.getSynchronize().equals("1")){
//            viewHolder.synchroniz.setTextColor(Color.parseColor("#00CC66"));
//            viewHolder.synchroniz.setText("已同步到蓝牙水表");
//        }else if (recordDM.getSynchronize().equals("0")){
//            viewHolder.synchroniz.setTextColor(Color.parseColor("#ff3300"));
//            viewHolder.synchroniz.setText("未同步到蓝牙水表");
//        }

        /*
         * 显示对应支付方式的 logo
         */
//        if (recordDM.getPayWay().equals("支付宝")){
//            viewHolder.image.setImageResource(R.drawable.alipict);
//        }else if(recordDM.getPayWay().equals("微信")){
//            viewHolder.image.setImageResource(R.drawable.wxpict);
//        }
//        else {
//            viewHolder.image.setImageResource(R.drawable.businesshall);
//        }
        return view;
    }
    class ViewHolder{
        TextView temp;
        TextView humidity;
        TextView roomName;
        ImageView roomImage;
        TextView co;
        TextView illumination;
    }
}
