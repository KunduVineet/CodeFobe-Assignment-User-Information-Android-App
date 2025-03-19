package com.teeniv.codefobe_user_information;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView avatarImageView;
    private TextView idTextView;
    private TextView uidTextView;
    private TextView passwordTextView;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView usernameTextView;
    private TextView emailTextView;
    private Button previousButton;
    private Button nextButton;

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

        // Initialize Volley RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Fetch data from API
        fetchUsers();

        // Button click listeners
        previousButton.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                displayUser();
            }
            updateButtonStates();
        });

        nextButton.setOnClickListener(v -> {
            if (currentIndex < usersList.size() - 1) {
                currentIndex++;
                displayUser();
            }
            updateButtonStates();
        });
    }

    private void fetchUsers() {
        String url = "https://random-data-api.com/api/users/random_user?size=80";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error (e.g., log it or show a Toast)
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(request);
    }

    private void displayUser() {
        if (!usersList.isEmpty()) {
            try {
                JSONObject user = usersList.get(currentIndex);
                idTextView.setText("ID: " + user.getInt("id"));
                uidTextView.setText("UID: " + user.getString("uid"));
                passwordTextView.setText("Password: " + user.getString("password"));
                firstNameTextView.setText("First Name: " + user.getString("first_name"));
                lastNameTextView.setText("Last Name: " + user.getString("last_name"));
                usernameTextView.setText("Username: " + user.getString("username"));
                emailTextView.setText("Email: " + user.getString("email"));
                Glide.with(this)
                        .load(user.getString("avatar"))
                        .into(avatarImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateButtonStates() {
        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < usersList.size() - 1);
    }
}