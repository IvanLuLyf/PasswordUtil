package cn.twimi.passwordutil;

import androidx.appcompat.app.AppCompatActivity;
import cn.twimi.util.PasswordDialogUtil;
import cn.twimi.widget.PasswordView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new PasswordDialogUtil.Builder(MainActivity.this)).setOnCompleted(new PasswordDialogUtil.OnPasswordCompleted() {
                    @Override
                    public void onCompleted(String password) {

                    }
                }).setMaxLength(8).setTitle("Input Password").build().show();
            }
        });

        PasswordView passwordView = findViewById(R.id.passwordView);
        passwordView.setOnCompleted(new PasswordView.OnCompleted() {
            @Override
            public void onCompleted(String password) {
                // Your logic to process password
            }
        });
    }
}
