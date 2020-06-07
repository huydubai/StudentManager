package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ActivitySignUp extends AppCompatActivity {
    private TextInputLayout layoutUser, layoutpsw;
    private TextInputEditText edUser, edPsw;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        event();
    }

    private void event() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTextUser() && getTextPsw()) {
                    Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                    String name =edUser.getText().toString();
                    intent.putExtra("username", edUser.getText().toString());
                    intent.putExtra("psw", edPsw.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    Toast.makeText(ActivitySignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    private boolean getTextUser() {
        if (layoutUser.getEditText().getText().toString().isEmpty()) {
            layoutUser.setError("Nhập tên tài khoản");
            return false;
        } else {
            layoutUser.setError(null);
            return true;
        }
    }

    private boolean getTextPsw() {
        if (layoutpsw.getEditText().getText().toString().isEmpty()) {
            layoutpsw.setError("Nhập mật khẩu");
            return false;
        } else {
            layoutpsw.setError(null);
            return true;
        }
    }


    private void init() {
        layoutUser = findViewById(R.id.layout_userName);
        layoutpsw = findViewById(R.id.layout_password);
        edUser = findViewById(R.id.ed_userName);
        edPsw = findViewById(R.id.edPsw);
        btnSignUp = findViewById(R.id.btn_SignUp);
    }
}
