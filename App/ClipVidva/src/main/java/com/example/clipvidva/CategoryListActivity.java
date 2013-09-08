package com.example.clipvidva;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Vee on 7/9/2556.
 */
public class CategoryListActivity extends Activity {
    CategoryAdapter categoryAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_item_list);

        ListView listView = (ListView)findViewById(R.id.category_item_text);
        categoryAdapter = new CategoryAdapter();
        listView.setAdapter(categoryAdapter);
    }
}
