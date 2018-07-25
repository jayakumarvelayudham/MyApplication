package customer.mmpl.tcs.com.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import customer.mmpl.tcs.com.myapplication.R;

/**
 * Created by BeAsT on 23-Mar-18.
 */

public class Personal_Details extends AppCompatActivity{

    private static final String TAG = "Personal_Details";
    private TextView name;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        getSupportActionBar().setTitle("User Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String Image = getIntent().getStringExtra("IMAGE");
        String Name = getIntent().getStringExtra("NAME");
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.title);
        Picasso.with(this).load(Image).into(image);
        name.setText(Name);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
