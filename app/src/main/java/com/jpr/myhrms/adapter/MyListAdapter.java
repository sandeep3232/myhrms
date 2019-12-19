package com.jpr.myhrms.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jpr.myhrms.R;
import com.jpr.myhrms.modal.Employee;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<Employee> employeeList;

    OnClickEmp onClickEmp;

    // RecyclerView recyclerView;
    public MyListAdapter(List<Employee> employeeList, OnClickEmp onClickEmp) {
        this.employeeList = employeeList;
        this.onClickEmp = onClickEmp;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.emp_rows, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Employee employee = employeeList.get(position);
        holder.nameTv.setText(""+employee.getEmpName());
        holder.emailTV.setText(""+employee.getEmpEmail());

        holder.empCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickEmp!=null){
                    onClickEmp.onClickOFEmp(position);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv,emailTV;

        CardView empCard;
        public ViewHolder(View itemView) {
            super(itemView);
            this.nameTv =  itemView.findViewById(R.id.nameTV);
            this.emailTV = itemView.findViewById(R.id.emailTV);
            this.empCard = itemView.findViewById(R.id.empCard);
        }
    }

    public interface OnClickEmp{
        void onClickOFEmp(int position);
    }


}