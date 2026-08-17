package com.example.bt4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    TextView txtPasswordError;
    CheckBox chkRemember;
    Button btnLogin;
    TextView txtSignup;

    boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtPasswordError = findViewById(R.id.txtPasswordError);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);

        edtPassword.setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (event.getX() >= edtPassword.getWidth() - edtPassword.getCompoundDrawables()[2].getBounds().width() - edtPassword.getPaddingEnd()) {

                    if (passwordVisible) {

                        edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVisible = false;

                    } else {
                        edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                    edtPassword.setSelection(edtPassword.getText().length());
                    return true;
                }
            }
            return false;
        });


        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty()) {
                edtUsername.setError("Vui lòng nhập User Name");
                edtUsername.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                txtPasswordError.setText("Vui lòng nhập mật khẩu!");
                txtPasswordError.setVisibility(View.VISIBLE);
                edtPassword.requestFocus();
                return;
            }

            String correctPassword = "123456";

            if (!password.equals(correctPassword)) {
                txtPasswordError.setText("Mật khẩu không đúng!");
                txtPasswordError.setVisibility(View.VISIBLE);
                edtPassword.requestFocus();

            } else {

                txtPasswordError.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        txtPasswordError.setVisibility(View.GONE);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                }
        );

        txtSignup.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Bạn đã chọn Sign up", Toast.LENGTH_SHORT).show();
        });
    }
}