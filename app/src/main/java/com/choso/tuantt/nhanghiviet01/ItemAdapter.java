package com.choso.tuantt.nhanghiviet01;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

/**
 * Created by admin on 1/23/2018.
 */

public class ItemAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<Item> items;
    ImageLoader imageLoader;
    public ItemAdapter(Activity activity, List<Item> items){
        this.activity=activity;
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater==null){
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view==null){
            view = inflater.inflate(R.layout.custom_layout, null);
        }
        if(imageLoader==null){
            imageLoader=AppController.getmInstance().getmImageLoader();
        }
        NetworkImageView imageView=(NetworkImageView)view.findViewById(R.id.thumbnail);
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView rate=(TextView)view.findViewById(R.id.rating);
        Item item = items.get(i);
        imageView.setImageUrl(item.getImage(), imageLoader);
        title.setText(item.getTitle());
        rate.setText(item.getRate());

        return view;
    }
}
