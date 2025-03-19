package com.teeniv.codefobe_user_information;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView avatarImageView;
    private TextView idTextView, uidTextView, passwordTextView, firstNameTextView, lastNameTextView, usernameTextView, emailTextView;
    private Button previousButton, nextButton;
    private ProgressBar progressBar;

    private final List<JSONObject> usersList = new ArrayList<>();
    private int currentIndex = 0;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        avatarImageView = findViewById(R.id.avatar);
        idTextView = findViewById(R.id.id_field);
        uidTextView = findViewById(R.id.uid_field);
        passwordTextView = findViewById(R.id.password_field);
        firstNameTextView = findViewById(R.id.first_name_field);
        lastNameTextView = findViewById(R.id.last_name_field);
        usernameTextView = findViewById(R.id.username_field);
        emailTextView = findViewById(R.id.email_field);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        progressBar = findViewById(R.id.progress_bar); // Add a ProgressBar in XML layout

        // Initialize Volley RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Fetch data from API
        fetchUsers();

        // Button click listeners
        previousButton.setOnClickListener(v -> navigateUser(-1));
        nextButton.setOnClickListener(v -> navigateUser(1));
    }

    private void fetchUsers() {
        String url = "https://random-data-api.com/api/users/random_user?size=80";

        progressBar.setVisibility(View.VISIBLE); // Show loading indicator

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    usersList.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            usersList.add(response.getJSONObject(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    currentIndex = 0;
                    displayUser();
                    updateButtonStates();
                    progressBar.setVisibility(View.GONE); // Hide loading indicator
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Failed to fetch users. Check internet connection.", Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(request);
    }

    @SuppressLint("SetTextI18n")
    private void displayUser() {
        if (!usersList.isEmpty()) {
            try {
                JSONObject user = usersList.get(currentIndex);
                idTextView.setText("ID: " + getSafeString(user, "id"));
                uidTextView.setText("UID: " + getSafeString(user, "uid"));
                passwordTextView.setText("Password: " + getSafeString(user, "password"));
                firstNameTextView.setText("First Name: " + getSafeString(user, "first_name"));
                lastNameTextView.setText("Last Name: " + getSafeString(user, "last_name"));
                usernameTextView.setText("Username: " + getSafeString(user, "username"));
                emailTextView.setText("Email: " + getSafeString(user, "email"));

                // Load avatar using Glide
                Glide.with(this)
                        .load(getSafeString(user, "avatar"))
                        .placeholder(R.drawable.placeholder_avatar) // Add a default placeholder
                        .error(R.drawable.error_avatar) // Add an error image
                        .into(avatarImageView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void navigateUser(int direction) {
        if ((direction == -1 && currentIndex > 0) || (direction == 1 && currentIndex < usersList.size() - 1)) {
            currentIndex += direction;
            displayUser();
            updateButtonStates();
        }
    }

    private void updateButtonStates() {
        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < usersList.size() - 1);
    }

    private String getSafeString(JSONObject json, String key) {
        return json.has(key) ? json.optString(key, "N/A") : "N/A";
    }
}
