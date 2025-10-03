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

/**
 * Overview class that displays broad information about a selected national park.
 */
public class OverviewActivity extends AppCompatActivity {

    TextView textView;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_overview);
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
        String file = "raw/" + type + "_overview";
        System.out.println("file = " + file);
        textView = findViewById(R.id.textView);
        iv = findViewById(R.id.imageView2);
        InputStream stream;
        String text = "";
        System.out.println("type = " + type);
        if (type.compareTo("yosemite") == 0) {
            iv.setImageResource(R.drawable.half_dome);
        } else  if (type.compareTo("yellowstone") == 0) {
            iv.setImageResource(R.drawable.yellowstone1);
        } else  if (type.compareTo("zion") == 0) {
            iv.setImageResource(R.drawable.zion1);
        } else  if (type.compareTo("glacier") == 0) {
            iv.setImageResource(R.drawable.glacier1);
        } else  if (type.compareTo("arches") == 0) {
            iv.setImageResource(R.drawable.arches1);
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