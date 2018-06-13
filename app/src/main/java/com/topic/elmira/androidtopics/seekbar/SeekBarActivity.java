package com.topic.elmira.androidtopics.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.NumberPicker;

import com.topic.elmira.androidtopics.R;

import java.text.NumberFormat;
import java.util.Locale;

public class SeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        AutoCompleteTextView tv = findViewById(R.id.autoCompleteTextView);
        String[] array = getResources().getStringArray(R.array.item_names_2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);

        tv.setAdapter(adapter);

        NumberPicker np = findViewById(R.id.np);
        np.setMinValue(0);
        np.setMaxValue(22);

        NumberPicker.Formatter npf = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return NumberFormat.getCurrencyInstance(Locale.US).format((long)value).toLowerCase();
            }
        };

        np.setFormatter(npf);
    }
}
