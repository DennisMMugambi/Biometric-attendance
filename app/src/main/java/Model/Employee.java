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
    private int employee_id;
    @ColumnInfo(name = "rightThumb")
    private byte[] rightThumb;
    @ColumnInfo(name = "rightIndex")
    private byte[] rightIndex;
    @ColumnInfo(name = "leftThumb")
    private byte[] leftThumb;
    @ColumnInfo(name = "leftIndex")
    private byte[] leftIndex;
    @ColumnInfo(name = "employeeName")
    private String employeeName;
    @ColumnInfo(name = "Age")
    private int age;
    @ColumnInfo(name = "jobTitle")
    private String jobTitle;
    @ColumnInfo(name = "identification")
    private String identification;
    @ColumnInfo(name = "attendance_score")
    private Float attendanceScore;
    @ColumnInfo(name = "parent_contact")
    private String phone_number;

    public Employee(int id, byte[] rightThumb, byte[] rightIndex, byte[] leftThumb, byte[] leftIndex, String employeeName, int age, String jobTitle, String identification, float attendanceScore, String phone_number) {
        this.employee_id = id;
        this.rightThumb = rightThumb;
        this.rightIndex = rightIndex;
        this.leftThumb = leftThumb;
        this.leftIndex = leftIndex;
        this.employeeName = employeeName;
        this.age = age;
        this.jobTitle = jobTitle;
        this.identification = identification;
        this.attendanceScore = attendanceScore;
        this.phone_number = phone_number;
    }

    //@Ignore
    public Employee(byte[] rightThumb, byte[] rightIndex, byte[] leftThumb, byte[] leftIndex, String employeeName, int age, String jobTitle, String identification, float attendanceScore, String phone_number) {
        this.rightThumb = rightThumb;
        this.rightIndex = rightIndex;
        this.leftThumb = leftThumb;
        this.leftIndex = leftIndex;
        this.employeeName = employeeName;
        this.age = age;
        this.jobTitle = jobTitle;
        this.identification = identification;
        this.attendanceScore = attendanceScore;
        this.phone_number = phone_number;
    }

    public Float getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(Float attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public byte[] getRightThumb() {
        return rightThumb;
    }

    public void setRightThumb(byte[] rightThumb) {
        this.rightThumb = rightThumb;
    }

    public byte[] getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(byte[] rightIndex) {
        this.rightIndex = rightIndex;
    }

    public byte[] getLeftThumb() {
        return leftThumb;
    }

    public void setLeftThumb(byte[] leftThumb) {
        this.leftThumb = leftThumb;
    }

    public byte[] getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(byte[] leftIndex) {
        this.leftIndex = leftIndex;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
