package com.jpr.myhrms;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jpr.myhrms.db.DBHelper;
import com.jpr.myhrms.modal.Employee;

import io.realm.Realm;

import static com.jpr.myhrms.Constants.EMPID;

public class AddEmplyoeeActivity extends AppCompatActivity {

    EditText empid_edt, empname_edt, empemail_edt, empmob_edt, empaddress_edt, empage_edt, emppin_edt, emppwd_edt;
    RadioGroup sex_rg;
    RadioButton male_rb, female_rb;
    Spinner designation_spinner;
    private Button empsubmit_btn;
    String[] designationArray;
    String designation;
    int spinner_selected_item_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emplyoee);
        Realm.init(this);

        designationArray = getResources().getStringArray(R.array.designation);

        empemail_edt = findViewById(R.id.empmail_edt);
        empmob_edt = findViewById(R.id.empmob_edt);
        sex_rg = findViewById(R.id.sex_rg);
        empaddress_edt = findViewById(R.id.empaddress_edt);
        empage_edt = findViewById(R.id.empage_edt);
        emppin_edt = findViewById(R.id.emppin_edt);
        emppwd_edt = findViewById(R.id.emppwd_edt);
        male_rb = findViewById(R.id.male_rb);
        female_rb = findViewById(R.id.female_rb);
        empname_edt = findViewById(R.id.empname_edt);
        designation_spinner = findViewById(R.id.designation_spinner);
        empsubmit_btn = findViewById(R.id.empsubmit_btn);


        final Intent intent = getIntent();
        String empId = intent.getStringExtra(EMPID);

        final Employee employee = DBHelper.getInstance().getEmployee(empId);


        if (employee != null) {

            empemail_edt.setText("" + employee.getEmpEmail());
            empmob_edt.setText("" + employee.getEmpMobile());
            empaddress_edt.setText("" + employee.getEmpAddress());
            empage_edt.setText("" + employee.getEmpAge());
            emppin_edt.setText("" + employee.getEmpLoginPin());
            emppwd_edt.setText("" + employee.getEmpPassword());
            empname_edt.setText("" + employee.getEmpName());
            // designation_spinner.setText("" + employee.getEmpName());
            //empname_edt.setText("" + employee.getEmpName());
            Log.d("NAME ", employee.getEmpName());
            Log.d("DESIGNATION ", employee.getEmpDesignation());
            Log.d("GENDER ", employee.getEmpGender());
            if (employee.getEmpGender().equalsIgnoreCase("male")) {
                male_rb.setChecked(true);

            } else {
                female_rb.setChecked(true);
            }

            for (int i=0;i<designationArray.length;i++)
            {

                if(employee.getEmpDesignation().equalsIgnoreCase(designationArray[i])){
                    spinner_selected_item_position=i;
                }


                }
            designation_spinner.setSelection(spinner_selected_item_position);


        }


        designation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                designation = designationArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        empsubmit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int btnId = sex_rg.getCheckedRadioButtonId();
                RadioButton rbBtn = findViewById(btnId);
                String gender = rbBtn.getText().toString();

                String email = empemail_edt.getText().toString().trim();
                String name = empname_edt.getText().toString();
                String address = empaddress_edt.getText().toString();
                String password = emppwd_edt.getText().toString();
                int age = Integer.parseInt(empage_edt.getText().toString());
                int loginPin = Integer.parseInt(emppin_edt.getText().toString());
                String mobile = empmob_edt.getText().toString();


                boolean isValid = checkAllInput(designation, gender, email, mobile);


                if (isValid) {

                    Employee employee = new Employee();
                    employee.setEmpId("A5111" + System.currentTimeMillis());
                    employee.setEmpMobile(mobile);
                    employee.setEmpEmail(email);
                    employee.setEmpName(name);
                    employee.setEmpGender(gender);
                    employee.setEmpDesignation(designation);
                    employee.setEmpAddress(address);
                    employee.setEmpPassword(password);
                    employee.setEmpAge(age);
                    employee.setEmpLoginPin(loginPin);

                    int isSaved = DBHelper.getInstance().createEmployee(employee);

                    if (isSaved > 0) {
                        Toast.makeText(AddEmplyoeeActivity.this, "Employee added successfully ", Toast.LENGTH_LONG).show();
                        finish();
                    }


                }


            }
        });
    }


//designation, gender, email,name,address,password,age,loginPin,password


    private boolean checkAllInput(String designation, String gender, String email, String mobile) {

        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(AddEmplyoeeActivity.this, "Please select gender", Toast.LENGTH_LONG).show();
            return false;
        }


        if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
            Toast.makeText(AddEmplyoeeActivity.this, "Please enter valid  Email", Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(designation) || designation.equalsIgnoreCase("Select Designation")) {
            Toast.makeText(AddEmplyoeeActivity.this, "Please select designation", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
