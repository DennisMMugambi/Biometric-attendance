package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.Utils.DateTime;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "Attendance")
public class Attendance {
    @PrimaryKey(autoGenerate = true)
    private int attendance_Id;
    @ColumnInfo(name = "User_attendance_name")
    private String user_attendance_name;
    @ColumnInfo(name = "Date")
    private ArrayList<String> date = new ArrayList<>();
    @ColumnInfo(name = "time_in")
    private ArrayList<String> time_in = new ArrayList<>();
    @ColumnInfo(name = "time_out")
    private ArrayList<String> time_out = new ArrayList<>();

    public Attendance(){

    }

    public Attendance(int id, String user_attendance, String date, String time_in, String time_out) {
        attendance_Id = id;
        this.user_attendance_name = user_attendance;
        this.date.add(date);
        this.time_in.add(time_in);
        this.time_out.add(time_out);
    }

    @Ignore
    public Attendance(String user_attendance_name, String date, String time_in, String period) {
        this.user_attendance_name = user_attendance_name;
        this.date.add(date);
        this.time_in.add(time_in);
    }

    @Ignore
    public Attendance(String user_attendance_name, String date,  String time_out) {
        this.user_attendance_name = user_attendance_name;
        this.date.add(date);
        this.time_out.add(time_out);
    }

    public int getAttendance_Id() {
        return attendance_Id;
    }

    public void setAttendance_Id(int attendance_Id) {
        this.attendance_Id = attendance_Id;
    }

    public String getUser_attendance_name() {
        return user_attendance_name;
    }

    public void setUser_attendance_name(String user_attendance_name) {
        this.user_attendance_name = user_attendance_name;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<String> getTime_in() {
        return time_in;
    }

    public void setTime_in(ArrayList<String> time_in) {
        this.time_in = time_in;
    }

    public ArrayList<String> getTime_out() {
        return time_out;
    }

    public void setTime_out(ArrayList<String> time_out) {
        this.time_out = time_out;
    }
}
