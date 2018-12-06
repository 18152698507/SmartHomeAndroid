package cn.zybwz.smarthomeandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.List;


import cn.refactor.lib.colordialog.PromptDialog;

public class recyclerViewadapter extends RecyclerView.Adapter {
    private List<ViewDataBean> lists;
    private Context context;
    private Handler handler;

    public recyclerViewadapter(List<ViewDataBean> lists, Context context, Handler handler) {
        this.lists = lists;
        this.context = context;
        this.handler =handler;
    }

    class myholder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private TextView value;
        private CardView cardView;
        public myholder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.view_item_name);
            value=(TextView) itemView.findViewById(R.id.view_item_tv_value);
            cardView=(CardView)itemView.findViewById(R.id.view_item_card);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        final myholder holder =new myholder(view);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ViewDataBean viewDataBean = lists.get(holder.getAdapterPosition());
                if (viewDataBean.getCode()==1){
                    new PromptDialog(view.getContext())
                            .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                            .setAnimationEnable(true)
                            .setTitleText("警告")
                            .setContentText(viewDataBean.getMessage())
                            .setPositiveListener("确认", new PromptDialog.OnPositiveListener() {
                                @Override
                                public void onClick(PromptDialog dialog) {
                                    dialog.dismiss();
                                }
                            }).show();
                }else if (viewDataBean.getCode()==2){
                    new PromptDialog(view.getContext())
                            .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                            .setAnimationEnable(true)
                            .setTitleText("危险！")
                            .setContentText(viewDataBean.getMessage())
                            .setPositiveListener("确认", new PromptDialog.OnPositiveListener() {
                                @Override
                                public void onClick(PromptDialog dialog) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
                return false;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewDataBean viewDataBean = lists.get(holder.getAdapterPosition());
                Message message = new Message();
                message.what=1;
                message.obj=holder.getAdapterPosition();
                handler.sendMessage(message);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAutor());
        if (lists.get(position).getCode()==1){
            ((myholder)holder).cardView.setCardBackgroundColor(Color.YELLOW);
        }
        else if (lists.get(position).getCode()==2){
            ((myholder)holder).cardView.setCardBackgroundColor(Color.RED);
        }
        ((myholder)holder).tv1.setText(lists.get(position).getArg());
        ((myholder)holder).value.setText(lists.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
