package com.jpr.myhrms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.jpr.myhrms.db.DBHelper;
import com.jpr.myhrms.modal.Employee;

import static com.jpr.myhrms.Constants.EMPID;

public class EmployeeDetailActivity extends AppCompatActivity {

    TextView nameTV, emailTV, mobileTV, addressTV, genderTV, designationTV;
    Button deleteBtn;
    Button editBtn;
    private String empId;
    Employee employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        nameTV = findViewById(R.id.empName);
        emailTV = findViewById(R.id.empEmail);
        mobileTV = findViewById(R.id.empMob);
        addressTV = findViewById(R.id.empAddress);
        genderTV = findViewById(R.id.empGender);
        designationTV = findViewById(R.id.empDesignation);

        deleteBtn = findViewById(R.id.deleteBtn);
        editBtn = findViewById(R.id.editBtn);
        final Intent intent = getIntent();
        empId = intent.getStringExtra(EMPID);

        final Employee employee = DBHelper.getInstance().getEmployee(empId);
       // Log.d(empId, "yes: ");
        if (employee != null) {
            nameTV.setText("" + employee.getEmpName());
            emailTV.setText("" + employee.getEmpAddress());
            mobileTV.setText("" + employee.getEmpMobile());
            addressTV.setText("" + employee.getEmpAddress());
            genderTV.setText("" + employee.getEmpGender());
            designationTV.setText("" + employee.getEmpDesignation());


        }
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employee!=null){
                    AskOption();

                }


            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EmployeeDetailActivity.this, AddEmplyoeeActivity.class);
                intent1.putExtra(EMPID, empId);
                startActivity(intent1);




            }
        });

    }


    private void  AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")


                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dialog.dismiss();
                        int isDeleted = DBHelper.getInstance().deleteEmplyoee(employee);
                        if (isDeleted > 0) {
                            finish();
                        }

                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        myQuittingDialogBox.show();
    }

}
