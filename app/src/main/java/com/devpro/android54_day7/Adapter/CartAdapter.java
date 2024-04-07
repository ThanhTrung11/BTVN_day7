package com.devpro.android54_day7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android54_day7.Presenter.ChangeQuantityPresent;
import com.devpro.android54_day7.Model.CartItem;
import com.devpro.android54_day7.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements IOnChangeQuantityView {
    private ArrayList<CartItem> mListFood;
    private Context context;
    private ChangeQuantityPresent presenter;

    public CartAdapter(ArrayList<CartItem> mListFood, Context context, ChangeQuantityPresent presenter) {
        this.mListFood = mListFood;
        this.context = context;
        presenter = new ChangeQuantityPresent(context, this);
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = mListFood.get(position);
        holder.imgFood.setImageResource(cartItem.getImageFood());
        holder.tvFoodName.setText(cartItem.getFoodName());
        if (cartItem.getQuantity() >= 1) {
            holder.tvItemWeight.setText(cartItem.getQuantity() + " kg");
        } else {
            holder.tvItemWeight.setText((cartItem.getQuantity() * 1000) + " g");
        }
        holder.tvFoodPrice.setText("$" + cartItem.getPrice());

        holder.btnPlus.setOnClickListener(v -> {
            onClickPlus(position);
        });

    }

    @Override
    public int getItemCount() {
        return mListFood != null ? mListFood.size() : 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood, btnMinus, btnPlus;
        TextView tvFoodName, tvItemWeight, tvFoodPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvItemWeight = itemView.findViewById(R.id.tvItemWeight);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);

            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }

    @Override
    public void onClickPlus(int id) {
        presenter.onChangePLus(id);
        notifyItemChanged(id);
    }

    @Override
    public void onClickMinus() {

    }
}
