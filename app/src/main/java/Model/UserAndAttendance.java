package Model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndAttendance {
    @Embedded
    public Employee employee;
    @Relation(
            parentColumn = "employee_id",
            entityColumn = "attendance_Id"
    )
    public Attendance attendance;
}
