package com.guang.drcomandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guang.drcomandroid.drcom.DrcomTask;
import com.guang.drcomandroid.drcom.HostInfo;

public class MainActivity extends Activity {
    private String mAccount;
    private String mPassword;
    private EditText edAccount;
    private EditText edPassword;
    private Button btnLogin;
    private Button btnLogout;
    private DrcomTask mDrcomTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edAccount = (EditText)this.findViewById(R.id.editText1);
        edPassword = (EditText)this.findViewById(R.id.editText2);
        btnLogin = (Button)this.findViewById(R.id.button1);
        btnLogout = (Button)this.findViewById(R.id.button2);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                mAccount = edAccount.getText().toString();
                mPassword = edPassword.getText().toString();
                HostInfo info = new HostInfo(mAccount,mPassword,"74-AC-5F-87-F3-ED");
                mDrcomTask = new DrcomTask();
                mDrcomTask.setOnMsgReceivedListener(new DrcomTask.MsgListener() {
                    @Override
                    public void onMsgReceived(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                mDrcomTask.execute(info);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mDrcomTask != null) {
                    mDrcomTask.notifyLogout();
                }
            }
        });

    }

}
