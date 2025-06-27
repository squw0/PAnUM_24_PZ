package com.example.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {
    private final Context context;
    private final List<CartItem> items;
    private final DatabaseHelper dbHelper;
    private final TotalPriceUpdateListener updateListener;

    public interface TotalPriceUpdateListener {
        void updateTotalPrice();
    }

    public CartAdapter(Context context, List<CartItem> items, DatabaseHelper dbHelper, TotalPriceUpdateListener listener) {
        super(context, R.layout.cart_item, items);
        this.context = context;
        this.items = items;
        this.dbHelper = dbHelper;
        this.updateListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cart_item, parent, false);

        CartItem item = items.get(position);

        ImageView imageView = rowView.findViewById(R.id.cartItemImage);
        TextView nameView = rowView.findViewById(R.id.cartItemName);
        TextView priceView = rowView.findViewById(R.id.cartItemPrice);
        TextView quantityView = rowView.findViewById(R.id.cartItemQuantity);
        Button removeButton = rowView.findViewById(R.id.removeButton);

        imageView.setImageResource(item.getImageResourceId());
        nameView.setText(item.getName());
        priceView.setText(item.getPrice() + " zł × " + item.getQuantity() + " = " + item.getTotalPrice() + " zł");
        quantityView.setText("Ilość: " + item.getQuantity());

        removeButton.setOnClickListener(v -> {
            dbHelper.removeFromCart(item.getName());
            items.remove(position);
            notifyDataSetChanged();
            updateListener.updateTotalPrice();
            Toast.makeText(context, "Usunięto: " + item.getName(), Toast.LENGTH_SHORT).show();
        });

        return rowView;
    }
}