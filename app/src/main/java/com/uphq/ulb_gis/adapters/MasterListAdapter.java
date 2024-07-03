package com.uphq.ulb_gis.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uphq.ulb_gis.Login;
import com.uphq.ulb_gis.MasterFormActivity;
import com.uphq.ulb_gis.ProjectMasterList;
import com.uphq.ulb_gis.R;
import com.uphq.ulb_gis.models.MasterListData;

import java.util.List;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MasterListViewHolder> {
    Context context;
    List<MasterListData> data;
    String oficeId,userid;
    public MasterListAdapter(Context context, List<MasterListData> data,String oficeId,String userid) {
        this.context = context;
        this.data = data;
        this.oficeId=oficeId;
        this.userid=userid;
    }

    @NonNull
    @Override
    public MasterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.master_list_item,parent,false);

        return new MasterListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasterListViewHolder holder, int position) {
        holder.tv_dob.setText(data.get(position).getDob());
        holder.tv_wardNumber.setText(String.valueOf(data.get(position).getWardNo()));
        holder.tv_PropertyNumber.setText(data.get(position).getPropertyNo());
        holder.tv_OwnerName.setText(data.get(position).getOwnerName());
        holder.tv_FatherName.setText(data.get(position).getFatherName());
        holder.tv_propertyName.setText(data.get(position).getPropertyName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MasterFormActivity.class);

                intent.putExtra("propertyId",String.valueOf(data.get(position).getPropertyId()));
                intent.putExtra("OfficeId",oficeId);
                intent.putExtra("UserId",userid);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MasterListViewHolder extends RecyclerView.ViewHolder{
        TextView tv_wardNumber,tv_PropertyNumber,tv_OwnerName,tv_FatherName,tv_dob,tv_propertyName;
        public MasterListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_dob=itemView.findViewById(R.id.tv_dob);
            tv_wardNumber=itemView.findViewById(R.id.tv_wardNumber);
            tv_PropertyNumber=itemView.findViewById(R.id.tv_PropertyNumber);
            tv_OwnerName=itemView.findViewById(R.id.tv_OwnerName);
            tv_FatherName=itemView.findViewById(R.id.tv_FatherName);
            tv_propertyName=itemView.findViewById(R.id.tv_propertyName);
        }
    }
}
