package com.jpr.myhrms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jpr.myhrms.db.DBHelper;
import com.jpr.myhrms.modal.Employee;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Realm.init(this);

        Employee employee=new Employee();
        employee.setEmpId("A5111");
        employee.setEmpName("Sandeep");
        employee.setEmpEmail("sandy.cool23@gmail.com");
        employee.setEmpDesignation("Executive");
        employee.setEmpGender("M");
        employee.setEmpMobile("9968791205");
        employee.setEmpAge(29);
        employee.setEmpLoginPin(1234);
        employee.setEmpPassword("san@1234");
        employee.setEmpAddress("Delhi Dwarka");



        final int isSaved= DBHelper.getInstance().createEmployee(employee);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isSaved > 0) {
                            Intent intent = new Intent(SplashActivity.this, AdminLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2 * 1000);

        }




    }

