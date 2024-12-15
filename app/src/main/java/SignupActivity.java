import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
    private CheckBox checkboxTerms;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Link the UI elements
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        checkboxTerms = findViewById(R.id.checkboxTerms);

        // Setup listener for the form fields
        setupFieldListeners();
    }

    private void setupFieldListeners() {
        // Add listeners to auto-trigger actions when fields are edited
        etFullName.setOnFocusChangeListener((v, hasFocus) -> validateField("Full Name", etFullName));
        etEmail.setOnFocusChangeListener((v, hasFocus) -> validateField("Email", etEmail));
        etPhone.setOnFocusChangeListener((v, hasFocus) -> validateField("Phone Number", etPhone));
        etPassword.setOnFocusChangeListener((v, hasFocus) -> validateField("Password", etPassword));
        etConfirmPassword.setOnFocusChangeListener((v, hasFocus) -> validatePasswords());
        checkboxTerms.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                Toast.makeText(SignupActivity.this, "Please accept Terms & Conditions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateField(String fieldName, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, fieldName + " is required", Toast.LENGTH_SHORT).show();
        }
    }

    private void validatePasswords() {
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }
}
