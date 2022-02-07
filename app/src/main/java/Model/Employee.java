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
    @ColumnInfo(name = "rightThumb")
    private byte[] rightThumb;
    @ColumnInfo(name = "rightIndex")
    private byte[] rightIndex;
    @ColumnInfo(name = "leftThumb")
    private byte[] leftThumb;
    @ColumnInfo(name = "leftIndex")
    private byte[] leftIndex;
    @ColumnInfo(name = "Name")
    private String employeeName;
    @ColumnInfo(name = "Age")
    private int age;
    @ColumnInfo(name = "jobTitle")
    private String jobTitle;
    @ColumnInfo(name = "identification")
    private String identification;

    public Employee(int id, byte[] rightThumb, byte[] rightIndex, byte[] leftThumb, byte[] leftIndex, String employeeName, int age, String jobTitle, String identification) {
        this.id = id;
        this.rightThumb = rightThumb;
        this.rightIndex = rightIndex;
        this.leftThumb = leftThumb;
        this.leftIndex = leftIndex;
        this.employeeName = employeeName;
        this.age = age;
        this.jobTitle = jobTitle;
        this.identification = identification;
    }

    @Ignore
    public Employee(byte[] rightThumb, byte[] rightIndex, byte[] leftThumb, byte[] leftIndex, String employeeName, int age, String jobTitle, String identification) {
        this.rightThumb = rightThumb;
        this.rightIndex = rightIndex;
        this.leftThumb = leftThumb;
        this.leftIndex = leftIndex;
        this.employeeName = employeeName;
        this.age = age;
        this.jobTitle = jobTitle;
        this.identification = identification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
