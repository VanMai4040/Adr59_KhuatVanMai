package vn.devpro.bt6;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtEmail;
    EditText edtPhone;
    EditText edtAddress;
    Spinner spinnerGender;
    Button btnSave;
    ImageButton btnBack;
    String avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        String[] genders = {
                "Male",
                "Female"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genders);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        Intent intent = getIntent();
        edtName.setText(intent.getStringExtra("name"));
        edtEmail.setText(intent.getStringExtra("email"));
        edtPhone.setText(intent.getStringExtra("phone"));
        edtAddress.setText(intent.getStringExtra("address"));
        String gender = intent.getStringExtra("gender");

        if ("Female".equals(gender)) {
            spinnerGender.setSelection(1);
        } else {
            spinnerGender.setSelection(0);
        }
        avatar = intent.getStringExtra("avatar");
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", edtName.getText().toString());
            resultIntent.putExtra("email", edtEmail.getText().toString());
            resultIntent.putExtra("gender", spinnerGender.getSelectedItem().toString());
            resultIntent.putExtra("phone", edtPhone.getText().toString());
            resultIntent.putExtra("address", edtAddress.getText().toString());

            if (avatar != null) {
                resultIntent.putExtra("avatar", avatar);
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}