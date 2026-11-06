package com.ejemplo.lonsamuel;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.Locale;

public class MainActivity extends Activity {

    private static final String TARGET_NAME = "lon";

    private EditText searchInput;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int pad = dp(16);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(pad, pad, pad, pad);

        searchInput = new EditText(this);
        searchInput.setHint(R.string.hint_search);
        searchInput.setSingleLine(true);
        root.addView(searchInput, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Button searchButton = new Button(this);
        searchButton.setText(R.string.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        root.addView(searchButton, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        resultView = new TextView(this);
        resultView.setGravity(Gravity.CENTER);
        resultView.setTextSize(20);
        resultView.setPadding(0, pad * 2, 0, 0);
        root.addView(resultView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        setContentView(root);
    }

    private void search() {
        String query = normalize(searchInput.getText().toString().trim());

        if (TextUtils.isEmpty(query)) {
            resultView.setText(R.string.result_empty);
        } else if (query.equals(TARGET_NAME)) {
            resultView.setText(R.string.result_found);
        } else {
            resultView.setText(R.string.result_not_found);
        }
    }

    /** Minúsculas y sin tildes, para que "LÓN" o "lon" también coincidan. */
    private static String normalize(String text) {
        String decomposed = Normalizer.normalize(text, Normalizer.Form.NFD);
        return decomposed.replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
