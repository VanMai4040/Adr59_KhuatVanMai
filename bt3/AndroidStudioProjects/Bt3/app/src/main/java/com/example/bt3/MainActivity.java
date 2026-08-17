package com.example.bt3;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    CheckBox chkRemember;
    Button btnLogin;
    TextView txtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);

        btnLogin.setOnClickListener(v -> {

            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty()) {
                edtUsername.setError("Vui lòng nhập User Name");
                edtUsername.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Vui lòng nhập Password");
                edtPassword.requestFocus();
                return;
            }

            Toast.makeText(MainActivity.this, "Login thành công!", Toast.LENGTH_SHORT).show();
        });

        txtSignup.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Bạn đã chọn Sign up", Toast.LENGTH_SHORT).show();

        });
    }
}