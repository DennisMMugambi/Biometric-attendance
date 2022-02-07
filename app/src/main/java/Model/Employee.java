package Model;
;
import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fingerprints")
    private byte[] fingerprints;
    @ColumnInfo(name = "Name")
    private String employeeName;
    @ColumnInfo(name = "Age")
    private int age;
    @ColumnInfo(name = "jobTitle")
    private String jobTitle;

    public Employee(int id, byte[] fingerprints, String employeeName, int age, String jobTitle) {
        this.id = id;
        this.fingerprints = fingerprints;
        this.employeeName = employeeName;
        this.age = age;
        this.jobTitle = jobTitle;
    }

    @Ignore
    public Employee(byte[] fingerprints, String employeeName, int age, String jobTitle) {
        this.fingerprints = fingerprints;
        this.employeeName = employeeName;
        this.age = age;
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFingerprints() {
        return fingerprints;
    }

    public void setFingerprints(byte[] fingerprints) {
        this.fingerprints = fingerprints;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
