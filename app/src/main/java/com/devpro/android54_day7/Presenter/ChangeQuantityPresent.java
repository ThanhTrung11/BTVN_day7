package com.devpro.android54_day7.Presenter;

import android.content.Context;

import com.devpro.android54_day7.Database;
import com.devpro.android54_day7.Interface.IOnClickQuantityModel;
import com.devpro.android54_day7.Interface.IOnClickQuantityView;

public class ChangeQuantityPresent implements IOnClickQuantityModel {
    private final Context context;
    private IOnClickQuantityView iOnClickQuantityView;
    private Database database;
    private Context mContext;
    public ChangeQuantityPresent(Context context, Database database, IOnClickQuantityView iOnClickQuantityView){

        this.context = mContext;
        this.database = new Database(context);
        this.database.setiOnClickQuantityModel(this);
        this.iOnClickQuantityView = iOnClickQuantityView;


    }
    public void onChangePLus(int id) {
        database.onClickPlus(id);
    }
    @Override
    public void onClickPlus(int id) {
        iOnClickQuantityView.onClickPlus(id);

    }

    @Override
    public void onClickMinus() {

    }
}
