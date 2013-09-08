package com.example.clipvidva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vee on 7/9/2556.
 */
public class CategoryAdapter extends BaseAdapter{
    private ArrayList<Category> categories = new ArrayList<Category>();

    public CategoryAdapter() {
        categories.add(new Category(1, "Math", "math"));
        categories.add(new Category(2, "Ask", "ask"));
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return ((Category)getItem(i)).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.category_item, viewGroup, false);
        }
        Category category = categories.get(i);
        TextView categoryTextView = (TextView)view.findViewById(R.id.category_item_text);
        categoryTextView.setText(category.toString());
        return view;
    }
}
