import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rapidrescue.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private CheckBox rememberMeCheckBox;
    private Button loginButton, signUpButton;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "RapidRescuePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        rememberMeCheckBox = findViewById(R.id.checkBoxRememberMe);
        loginButton = findViewById(R.id.buttonLogin);
        signUpButton = findViewById(R.id.buttonSignUp);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if credentials are already saved
        checkSavedCredentials();

        // Login button click listener
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInput(email, password)) {
                if (rememberMeCheckBox.isChecked()) {
                    saveCredentials(email, password);
                } else {
                    clearCredentials();
                }

                // Proceed to DashboardActivity
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Close LoginActivity
            }
        });

        // Sign up button click listener
        signUpButton.setOnClickListener(v -> {
            // Navigate to SignupActivity
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    // Validate input fields
    private boolean validateInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Check if credentials are saved
    private void checkSavedCredentials() {
        String savedEmail = sharedPreferences.getString("email", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (savedEmail != null && savedPassword != null) {
            emailEditText.setText(savedEmail);
            passwordEditText.setText(savedPassword);
            rememberMeCheckBox.setChecked(true);
        }
    }

    // Save credentials to SharedPreferences
    private void saveCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    // Clear saved credentials
    private void clearCredentials() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }
}
