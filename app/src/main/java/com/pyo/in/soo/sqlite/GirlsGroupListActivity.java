package com.pyo.in.soo.sqlite;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GirlsGroupListActivity extends AppCompatActivity {
    RecyclerView girlsListView;
    GirlsRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_group_list);

        girlsListView = (RecyclerView) findViewById(R.id.recyclerview);
        girlsListView.setLayoutManager(new LinearLayoutManager(GirlsGroupListActivity.this));

        ArrayList<GirlsGroupValueObject> list =
                GirlsGroupSQLiteOpenHelper.getInstance().findGirlsGroupListAll();
        adapter = new GirlsRecyclerAdapter(list);
        girlsListView.setAdapter(adapter);
    }

    public class GirlsRecyclerAdapter extends RecyclerView.Adapter<GirlsRecyclerAdapter.ViewHolder> {
        private ArrayList<GirlsGroupValueObject> girlsGroupList;

        public GirlsRecyclerAdapter(){}

        public GirlsRecyclerAdapter(ArrayList<GirlsGroupValueObject> list){
            this.girlsGroupList= list;
        }
        public  class ViewHolder extends RecyclerView.ViewHolder {
            public final View itemRootView;
            public final ImageView girlsImage;
            public final TextView memberName;
            public final TextView memberBirth;

            public ViewHolder(View view) {
                super(view);
                itemRootView = view;
                girlsImage = (ImageView) view.findViewById(R.id.girls_member_image);
                memberName = (TextView) view.findViewById(R.id.girls_member_name);
                memberBirth = (TextView) view.findViewById(R.id.girls_birthday);
            }
        }
        @Override
        public int getItemCount() {
            return girlsGroupList.size();
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
           GirlsGroupValueObject valueObject =  girlsGroupList.get(position);
            holder.girlsImage.setImageURI(Uri.parse(valueObject.memberImageURI));
            holder.memberName.setText(valueObject.memberName);
            holder.memberBirth.setText(valueObject.memberBirth);
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemRoot = LayoutInflater.from(parent.getContext()).inflate(
                              R.layout.girls_recyeler_item, parent, false);
            return new ViewHolder(itemRoot);
        }
    }
}
