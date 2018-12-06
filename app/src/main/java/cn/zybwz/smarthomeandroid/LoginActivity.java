package cn.zybwz.smarthomeandroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText account;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         account=findViewById(R.id.account);
         password=findViewById(R.id.password);
         login=findViewById(R.id.login);
         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         });
//        Drawable drawable1 = getResources().getDrawable(R.drawable.password_n);
//        drawable1.setBounds(0,0,password.getHeight()-30,password.getHeight()-30);
//        password.setCompoundDrawables(drawable1,null,null,null);
//        Drawable drawable = getResources().getDrawable(R.drawable.account_n);
//        drawable.setBounds(0,0,account.getHeight()-30,account.getHeight()-30);
//        account.setCompoundDrawables(drawable,null,null,null);
        account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Drawable drawable = getResources().getDrawable(R.drawable.account_f);
                    drawable.setBounds(0,0,account.getHeight()-30,account.getHeight()-30);
                    v.setElevation(10);
                    account.setCompoundDrawables(drawable,null,null,null);
                    Drawable drawable1 = getResources().getDrawable(R.drawable.password_n);
                    drawable1.setBounds(0,0,account.getHeight()-30,account.getHeight()-30);
                    password.setCompoundDrawables(drawable1,null,null,null);
                }
                else  {
                    Drawable drawable = getResources().getDrawable(R.drawable.account_n);
                    drawable.setBounds(0,0,account.getHeight()-30,account.getHeight()-30);
                    v.setElevation(0);
                    account.setCompoundDrawables(drawable,null,null,null);
                    Drawable drawable1 = getResources().getDrawable(R.drawable.password_f);
                    drawable1.setBounds(0,0,account.getHeight()-30,account.getHeight()-30);
                    password.setCompoundDrawables(drawable1,null,null,null);
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    v.setElevation(10);
                }
                else  {
                    v.setElevation(0);
                }
            }
        });
        //account.setFocusable(true);
    }
}
