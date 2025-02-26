package com.example.android1am;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView foodList;
    private Button nextButton;
    private ArrayList<FoodItem> foodItems;
    private FoodAdapter adapter;
    private TextView selectedFoodsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        foodList = findViewById(R.id.foodList);
        nextButton = findViewById(R.id.nextButton);
        selectedFoodsTextView = findViewById(R.id.selectedFoodsTextView);

        foodItems = new ArrayList<>();
        setInitialData();

        adapter = new FoodAdapter(this, foodItems);
        foodList.setAdapter(adapter);

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodItem item = foodItems.get(position);
                item.setSelected(!item.isSelected());

                adapter.notifyDataSetChanged();
                checkSelection(); // Apelăm checkSelection() după fiecare schimbare
            }
        });

        // Inițial, dezactivăm butonul
        nextButton.setEnabled(false);
    }

    private void setInitialData() {
        foodItems.add(new FoodItem("Masă pentru 2 persoane", "Preț: 500 lei", R.drawable.masa2));
        foodItems.add(new FoodItem("Masă pentru 3 persoane", "Preț: 1000 lei", R.drawable.masa3));
        foodItems.add(new FoodItem("Masă pentru 4 persoane", "Preț: 2000 lei", R.drawable.masa4));
        foodItems.add(new FoodItem("Masă pentru 10 persoane", "Preț: 5000 lei", R.drawable.masa10));
    }

    private void checkSelection() {
        boolean anyChecked = false;
        StringBuilder selectedFoods = new StringBuilder("Ați ales:\n ");

        for (FoodItem item : foodItems) {
            if (item.isSelected()) {
                anyChecked = true;
                selectedFoods.append(item.getName()).append(", ");
            }
        }

        if (anyChecked) {
            selectedFoods.delete(selectedFoods.length() - 2, selectedFoods.length());
        } else {
            selectedFoods.setLength(0);
        }

        selectedFoodsTextView.setText(selectedFoods.toString());
        nextButton.setEnabled(anyChecked); // Activăm butonul doar dacă ceva este selectat
    }

    public void onNextClick(View view) {
        if (nextButton.isEnabled()) {
            startActivity(new Intent(this, PaymentActivity.class));
        } else {
            Toast.makeText(this, "Selectează cel puțin un produs!", Toast.LENGTH_SHORT).show();
        }
    }

    public static class FoodItem {
        private String name;
        private String price;
        private int imageResId;
        private boolean isSelected;

        public FoodItem(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
            this.isSelected = false;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public int getImageResId() {
            return imageResId;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

    public static class FoodAdapter extends ArrayAdapter<FoodItem> {

        public FoodAdapter(Context context, List<FoodItem> items) {
            super(context, R.layout.list_item_layout, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FoodItem item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.item_image);
            TextView nameTextView = convertView.findViewById(R.id.item_name);
            TextView priceTextView = convertView.findViewById(R.id.item_price);
            CheckBox checkBox = convertView.findViewById(R.id.item_checkbox);

            imageView.setImageResource(item.getImageResId());
            nameTextView.setText(item.getName());
            priceTextView.setText(item.getPrice());
            checkBox.setChecked(item.isSelected());

            return convertView;
        }
    }

    public void onPayInRestaurantClick(View view) {
        startActivity(new Intent(this, FourthActivity.class));
    }

}