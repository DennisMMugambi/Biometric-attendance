package Model;
;
import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "FingerPrint")
public class FingerPrint {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fingerprints")
    private byte[] refdata;

    public FingerPrint(int id, byte[] refdata) {
        this.id = id;
        this.refdata = refdata;
    }

    @Ignore
    public FingerPrint(byte[] refdata) {
        this.refdata = refdata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getRefdata() {
        return refdata;
    }

    public void setRefdata(byte[] refdata) {
        this.refdata = refdata;
    }
}
