import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button securityButton = findViewById(R.id.securityButton);
        Button clinicButton = findViewById(R.id.clinicButton);
        Button guidanceButton = findViewById(R.id.guidanceButton);
        Button serviceButton = findViewById(R.id.serviceButton);


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
