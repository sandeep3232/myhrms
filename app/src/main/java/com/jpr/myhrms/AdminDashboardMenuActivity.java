package com.jpr.myhrms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jpr.myhrms.modal.Employee;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardMenuActivity extends AppCompatActivity {

    Button addEmp_btn;
    Button manageEmp_btn;
    List<Employee>employeeList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard_menu);
        addEmp_btn = findViewById(R.id.addEmp_btn);
        manageEmp_btn = findViewById(R.id.manageEmp_btn);


    }


    public void AddEmplyoee(View view) {
        Intent intent = new Intent(this, AddEmplyoeeActivity.class);
        startActivity(intent);
    }

    public void manageEmp(View view) {

        Intent intent=new Intent(AdminDashboardMenuActivity.this, EmployeeListActivity.class);
        startActivity(intent);

    }
}
