package com.jpr.myhrms.db;

import com.jpr.myhrms.modal.Employee;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class DBHelper implements IDbHelper {

    static DBHelper dbHelper = null;
    static Realm realm = null;

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
            realm = Realm.getDefaultInstance();
        }
        return dbHelper;
    }

    @Override
    public int createEmployee(final Employee employee) {

        try {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(employee);

                }
            });
        } catch (RealmException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Employee getEmployee(String empId) {
        Employee employee=null;
        RealmResults allEmps=realm.where(Employee.class).equalTo("empId",empId).findAll();
        if (allEmps!=null && allEmps.size()>0){
            employee= (Employee) allEmps.first();

        }
        return employee;
    }

    @Override
    public void updateEmplyoee(Employee employee) {




    }

    @Override
    public int deleteEmplyoee(final Employee employee) {
        try {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    employee.deleteFromRealm();
                }
            });

        }catch (Exception e){
            e.getStackTrace();
            return -1;
        }
        return 1;



    }

    @Override
    public List<Employee> getAllEmplyoee() {
        return realm.where(Employee.class).findAll();
    }

    @Override
    public Employee getEmpByIDANDPassword(String empId, String empPassword) {
        return null;
    }

    @Override
    public Employee getEmpByempLoginPin(int empLoginPin) {

        Employee employee = null;
        RealmResults<Employee> employees = realm.where(Employee.class).equalTo("empLoginPin", empLoginPin).findAll();
        if (employees != null && employees.size() > 0) {
            employee = employees.first();
        }
        return employee;
    }
}
