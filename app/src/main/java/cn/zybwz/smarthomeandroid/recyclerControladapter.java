package cn.zybwz.smarthomeandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.List;

public class recyclerControladapter extends RecyclerView.Adapter {
    private List<ControlDataBean> lists;
    private Context context;

    public recyclerControladapter(List<ControlDataBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    class myholder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private SwitchButton switchButton;
        public myholder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.control_item_name);
            switchButton=(SwitchButton)itemView.findViewById(R.id.control_item_switch);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final myholder holder =new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.control_item,parent,false));
        holder.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked){
                    Log.i("状态",holder.tv1.getText().toString()+"开");
                }
                else Log.i("状态",holder.tv1.getText().toString()+"关");
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAutor());
        ((myholder)holder).tv1.setText(lists.get(position).getRoomName());
        ((myholder)holder).switchButton.setChecked(lists.get(position).isSwitchStatus());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
