package edu.uga.cs.nationalparks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity class that allows users to select a national park and navigate to overview or details activities.
 */
public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Button overviewButton;
    Button detailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.spinner);
        overviewButton = findViewById(R.id.button);
        detailsButton = findViewById(R.id.button2);
        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //System.out.println("overviewButton listener");
              Intent intent = new Intent(MainActivity.this, OverviewActivity.class);
              intent.putExtra("type", spinner.getSelectedItem().toString());
              startActivity(intent);
            }
        });
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("detailsButton listener");
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("type", spinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.topics_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

   
}