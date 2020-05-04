package com.example.fuelstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    GridView gridView;

    String[] fuelNames = {"زيت","بنزين","سائل","شحم"};
    int[] fuelImages = {R.drawable.oil,R.drawable.fuel,R.drawable.gasoline,R.drawable.fuel};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gridView = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),TypesActivity.class);
                intent.putExtra("name",fuelNames[position]);
                intent.putExtra("image",fuelImages[position]);
                startActivity(intent);
            }
        });

    }
    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return fuelImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(fuelNames[i]);
            image.setImageResource(fuelImages[i]);
            return view1;



        }
    }
}

