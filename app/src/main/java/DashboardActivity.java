import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rapidrescue.R;

public class DashboardActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button securityButton = findViewById(R.id.btnSecurity);
        Button clinicButton = findViewById(R.id.btnClinic);
        Button guidanceButton = findViewById(R.id.btnGuidance);
        Button serviceButton = findViewById(R.id.btnService);


        securityButton.setOnClickListener(v -> showEmergencyDialog("School Security"));

        clinicButton.setOnClickListener(v -> showEmergencyDialog("Clinic"));

        guidanceButton.setOnClickListener(v -> showEmergencyDialog("Guidance Counselor"));

        serviceButton.setOnClickListener(v -> showEmergencyDialog("Support Services"));
    }

    private void showEmergencyDialog(String serviceName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Emergency Call")
                .setMessage("Do you want to call " + serviceName + "?")
                .setPositiveButton("Yes, Call", (dialog, which) -> {
                    // Add call functionality here
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
