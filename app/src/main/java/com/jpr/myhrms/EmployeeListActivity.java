package com.jpr.myhrms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.jpr.myhrms.adapter.MyListAdapter;
import com.jpr.myhrms.db.DBHelper;
import com.jpr.myhrms.modal.Employee;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity  implements MyListAdapter.OnClickEmp {

    List<Employee> employeeList;
    RecyclerView emplistRV;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);


        emplistRV= findViewById(R.id.emplistRV);








            }

    @Override
    protected void onResume() {
        super.onResume();

        employeeList = DBHelper.getInstance().getAllEmplyoee();
        if (employeeList!=null && employeeList.size()>0){
            MyListAdapter myListAdapter = new MyListAdapter(employeeList,this);

            emplistRV.setHasFixedSize(true);
            emplistRV.setLayoutManager(new LinearLayoutManager(this));
            emplistRV.setAdapter(myListAdapter);
        }



    }

    @Override
    public void onClickOFEmp(int position) {

        Employee employee= employeeList.get(position);
       // Toast.makeText(this,"empname= "+employee.getName()+" empmail= "+employee.getEmail(),Toast.LENGTH_LONG).show();
            String empid=employee.getEmpId();
        Intent intent=new Intent(EmployeeListActivity.this,EmployeeDetailActivity.class);
        intent.putExtra(Constants.EMPID,empid);
        startActivity(intent);





    }
}
