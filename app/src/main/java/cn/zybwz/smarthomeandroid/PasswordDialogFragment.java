package cn.zybwz.smarthomeandroid;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.Cipher;

public class PasswordDialogFragment extends DialogFragment {



    private MainActivity mActivity;
    private MyPassEditText myPassEditText;
    private Button button;

    /**
     * 标识是否是用户主动取消的认证。
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) getActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.password_dialog, container, false);
        myPassEditText = v.findViewById(R.id.password_dialog_mpe_password);
        button = v.findViewById(R.id.password_dialog_bt_sure);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),myPassEditText.getPass(),Toast.LENGTH_LONG).show();
            }
        });
        myPassEditText.setOnInputFinishListener(new MyPassEditText.OnInputFinishListener() {
            @Override
            public void onIsFinish(boolean isFinish) {
                button.setClickable(isFinish);
                if (isFinish){
                    button.setBackground(getResources().getDrawable(R.drawable.my_background));
                }
                else button.setBackgroundColor(Color.parseColor("#cccccc"));
            }
        });
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        // 开始指纹认证监听
    }

    @Override
    public void onPause() {
        super.onPause();

    }



}
//---------------------
//        作者：guolin
//        来源：CSDN
//        原文：https://blog.csdn.net/guolin_blog/article/details/81450114
//        版权声明：本文为博主原创文章，转载请附上博文链接！
