package edu.uga.cs.nationalparks;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DetailsActivity extends AppCompatActivity {
    TextView textView;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle extras = getIntent().getExtras();
        String type = "";
        if (extras != null) {
            type = extras.getString("type").toLowerCase();
        }
        String file = "raw/" + type + "_details";
        System.out.println("file = " + file);
        textView = findViewById(R.id.textView3);
        iv = findViewById(R.id.imageView);
        InputStream stream;
        String text = "";
        if (type.compareTo("yosemite") == 0) {
            iv.setImageResource(R.drawable.glacier_point);
        } else  if (type.compareTo("yellowstone") == 0) {
            iv.setImageResource(R.drawable.yellowstone2);
        } else  if (type.compareTo("zion") == 0) {
            iv.setImageResource(R.drawable.zion2);
        } else  if (type.compareTo("glacier") == 0) {
            iv.setImageResource(R.drawable.glacier2);
        } else  if (type.compareTo("arches") == 0) {
            iv.setImageResource(R.drawable.arches2);
        }

        try {
            //System.out.println((getBaseContext().getResources().getIdentifier(file,"id",getPackageName())));
            //stream = getBaseContext().getResources().openRawResource(R.raw.yosemite_details);

            stream = getBaseContext().getResources().openRawResource(getResources().getIdentifier(file,"id",getPackageName()));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            text = buffer.toString();
            //System.out.println("text: " + text);
        } catch (IOException e) {
            System.out.println(e);
        }
        textView.setText(text);

    }
}