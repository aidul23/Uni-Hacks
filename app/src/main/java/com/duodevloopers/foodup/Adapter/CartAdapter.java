package com.duodevloopers.foodup.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.duodevloopers.foodup.Model.RestaurantItemPojo;
import com.duodevloopers.foodup.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<RestaurantItemPojo> myAppArrayList;
    private CartItemClickListener listener;


    public CartAdapter(List<RestaurantItemPojo> myAppArrayList) {
        this.myAppArrayList = myAppArrayList;
        Log.d(TAG, "CartAdapter: " + myAppArrayList.size());
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_cart_recycler_view, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        final RestaurantItemPojo currentItem = myAppArrayList.get(position);
        final RestaurantItemPojo abc = new RestaurantItemPojo(currentItem.getmImage(), currentItem.getmFoodName(), currentItem.getmFoodDes(), currentItem.getmFoodPrice(), currentItem.getQuantity());

        holder.mFoodImageView.setImageResource(currentItem.getmImage());
        holder.mFoodName.setText(currentItem.getmFoodName());
        holder.mFoodDes.setText(currentItem.getmFoodDes());
        holder.mFoodPrice.setText(String.format("%d Tk", (currentItem.getmFoodPrice() * currentItem.getQuantity())));
        holder.mElegantNumberButton.setNumber(String.valueOf(currentItem.getQuantity()));

        holder.mElegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                holder.mFoodPrice.setText(String.format("%d Tk", (currentItem.getmFoodPrice() * newValue)));

                if (oldValue > newValue) {
                    abc.setQuantity(newValue);
                    listener.onItemUpdated(abc);
                    listener.onDecreaseItem(currentItem.getmFoodPrice());
                } else if (oldValue < newValue) {
                    abc.setQuantity(newValue);
                    listener.onItemUpdated(abc);
                    listener.onIncreaseItem(currentItem.getmFoodPrice());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return myAppArrayList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public ImageView mFoodImageView;
        public TextView mFoodName, mFoodDes, mFoodPrice;
        public ElegantNumberButton mElegantNumberButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            mFoodImageView = itemView.findViewById(R.id.foodItemImage);
            mFoodName = itemView.findViewById(R.id.foodItemName);
            mFoodDes = itemView.findViewById(R.id.foodItemdes);
            mFoodPrice = itemView.findViewById(R.id.foodItemPrice);
            mElegantNumberButton = itemView.findViewById(R.id.elegantNumberButton_quantity);
        }
    }

    public interface CartItemClickListener {
        void onClick();

        void onIncreaseItem(int price);

        void onDecreaseItem(int price);

        void onItemUpdated(RestaurantItemPojo updatedItem);
    }

    public void setListener(CartAdapter.CartItemClickListener listener) {
        this.listener = listener;
    }

}
