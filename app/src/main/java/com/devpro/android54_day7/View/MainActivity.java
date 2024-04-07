package com.devpro.android54_day7.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android54_day7.Adapter.CartAdapter;
import com.devpro.android54_day7.Presenter.ChangeQuantityPresent;
import com.devpro.android54_day7.Database;
import com.devpro.android54_day7.Model.CartItem;
import com.devpro.android54_day7.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        private RecyclerView rcv;
        private ArrayList<CartItem> getListFood;
        private ArrayList<CartItem> mListFood;
        private ChangeQuantityPresent present;

        private CartAdapter cartAdapter;
        private Database food_table;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initData();
            initView();
        }

        private void initData() {
////         khởi tạo dữ liệu r thêm vào database
            food_table = new Database(this);
            getListFood = new ArrayList<>();
            getListFood.add(new CartItem(1, R.drawable.beetles, "Beetles", 6.1));
            getListFood.add(new CartItem(2, R.drawable.lemon, "Lemon", 5.7));
            getListFood.add(new CartItem(3, R.drawable.potato, "Potato", 5.2));
            getListFood.add(new CartItem(4, R.drawable.cucumber, "Cucumber", 4.4));
            if(food_table == null){
                for (CartItem cartItem : getListFood) {
                    food_table.addFood(cartItem);
                }
            }

            //lấy all dữ liệu set vào array list để truyền vào adapter
            mListFood = food_table.getAll();

        }

        private void initView() {
            rcv = findViewById(R.id.rcvFood);
            cartAdapter = new CartAdapter(mListFood, MainActivity.this, present);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            rcv.setLayoutManager(layoutManager);

            rcv.setAdapter(cartAdapter);
        }

    }