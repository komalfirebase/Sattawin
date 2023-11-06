package satta.win.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import satta.win.R;
import satta.win.main.MainActivity;


public class LoginActivity extends AppCompatActivity {


    private EditText editText;
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = findViewById(R.id.editTextPhone);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = editText.getText().toString().trim();
               if (number.isEmpty() || number.length() < 10) {

                    editText.setError("Invalid Number Addresss");
                    Toast.makeText(LoginActivity.this,"Invalid Number Addresss", Toast.LENGTH_SHORT).show();

                }
                else {
                    String phoneNumber = "+91"  + number;
                    Intent intent = new Intent(LoginActivity.this, VerifyActivity.class);
                    intent.putExtra("phonenumber", phoneNumber);
                    startActivity(intent);
                }



            }
        });
    }
   /* boolean checkeFName() {
        if (eName.getText().toString().trim().isEmpty() == true) {
            eName.setError("Can not be empty");
            eName.requestFocus();
            return false;
        } else
            return true;
    }
    boolean checkEamil() {
        if (eEmail.getText().toString().trim().isEmpty() == true) {
            eEmail.setError("Can not be empty");
            eEmail.requestFocus();
            return false;
        } else
            return true;
    }*/
    private boolean checkloginEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
