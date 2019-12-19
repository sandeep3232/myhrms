package com.jpr.myhrms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jpr.myhrms.db.DBHelper;
import com.jpr.myhrms.modal.Employee;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdminLoginActivity extends AppCompatActivity {

    EditText adminpin_EDT;
    Button adminlogin_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_adminlogin);
        adminpin_EDT=findViewById(R.id.adminpin_EDT);
        adminlogin_btn=findViewById(R.id.adminlogin_btn);


    }

    public void AdminLogin(View view) {

        int loginPin=Integer.parseInt(adminpin_EDT.getText().toString());
        if (loginPin>0){
      Employee employee= DBHelper.getInstance().getEmpByempLoginPin(loginPin);
      if (employee!=null){
          Intent intent=new Intent(this,AdminDashboardMenuActivity.class);
          startActivity(intent);
          finish();
      }


        }
    }
}
