package com.example.halalzone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayertimeActivity extends AppCompatActivity {

    private TextView fajrTime, sunriseTime, dhuhrTime, asrTime, maghribTime, ishaTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prayertime);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fajrTime = findViewById(R.id.fajrTime);
        sunriseTime = findViewById(R.id.sunriseTime);
        dhuhrTime = findViewById(R.id.dhuhrTime);
        asrTime = findViewById(R.id.asrTime);
        maghribTime = findViewById(R.id.maghribTime);
        ishaTime = findViewById(R.id.ishaTime);
        fetchPrayerTimes();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void fetchPrayerTimes() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<PrayerTimesResponse> call = apiService.getPrayerTimes("Riyadh", "Saudi Arabia", 3);

        call.enqueue(new Callback<PrayerTimesResponse>() {
            @Override
            public void onResponse(Call<PrayerTimesResponse> call, Response<PrayerTimesResponse> response) {
                if (response.isSuccessful()) {
                    PrayerTimesResponse data = response.body();
                    if (data != null && data.getData() != null && data.getData().getTimings() != null) {
                        PrayerTimesResponse.Data.Timings timings = data.getData().getTimings();

                        // Convert to 12-hour format
                        fajrTime.setText(convertTo12HourFormat(timings.getFajr()));
                        sunriseTime.setText(convertTo12HourFormat(timings.getSunrise()));
                        dhuhrTime.setText(convertTo12HourFormat(timings.getDhuhr()));
                        asrTime.setText(convertTo12HourFormat(timings.getAsr()));
                        maghribTime.setText(convertTo12HourFormat(timings.getMaghrib()));
                        ishaTime.setText(convertTo12HourFormat(timings.getIsha()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PrayerTimesResponse> call, Throwable t) {
                Toast.makeText(PrayertimeActivity.this, "Failed to fetch prayer times", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to convert 24-hour format to 12-hour format
    private String convertTo12HourFormat(String time24) {
        try {
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm a", Locale.getDefault());

            Date date = sdf24.parse(time24);
            return sdf12.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return time24; // Return original if parsing fails
        }
    }
}