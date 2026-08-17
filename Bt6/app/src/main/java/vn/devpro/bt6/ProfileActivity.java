package vn.devpro.bt6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import vn.devpro.bt6.EditProfileActivity;
import vn.devpro.bt6.R;

public class ProfileActivity extends AppCompatActivity {
    ImageView imgAvatar;
    ImageButton btnEditAvatar;
    TextView txtName;
    TextView txtEmail;
    LinearLayout layoutEditProfile;
    Button btnSignOut;
    String name = "Albert Florest";
    String email = "albertflorest@email.com";
    String gender = "Male";
    String phone = "+44 1632 960860";
    String address = "314, St No 22 - Dwalington Street";
    Uri avatarUri;

    ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {

                        if (uri != null) {
                            avatarUri = uri;
                            imgAvatar.setImageURI(uri);
                        }
                    }
            );

    ActivityResultLauncher<Intent> editProfileLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            Intent data = result.getData();
                            name = data.getStringExtra("name");
                            email = data.getStringExtra("email");
                            gender = data.getStringExtra("gender");
                            phone = data.getStringExtra("phone");
                            address = data.getStringExtra("address");

                            String avatar = data.getStringExtra("avatar");

                            if (avatar != null) {
                                avatarUri = Uri.parse(avatar);
                                imgAvatar.setImageURI(avatarUri);
                            }
                            updateProfile();
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnEditAvatar = findViewById(R.id.btnEditAvatar);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        layoutEditProfile = findViewById(R.id.layoutEditProfile);
        btnSignOut = findViewById(R.id.btnSignOut);

        updateProfile();
        btnEditAvatar.setOnClickListener(v -> {

            galleryLauncher.launch("image/*");

        });
        layoutEditProfile.setOnClickListener(v -> {

            Intent intent =
                    new Intent(ProfileActivity.this, EditProfileActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("gender", gender);
            intent.putExtra("phone", phone);
            intent.putExtra("address", address);

            if (avatarUri != null) {
                intent.putExtra("avatar", avatarUri.toString());
            }

            editProfileLauncher.launch(intent);
        });
    }

    private void updateProfile() {
        txtName.setText(name);
        txtEmail.setText(email);
        if (avatarUri != null) {
            imgAvatar.setImageURI(avatarUri);
        }
    }
}