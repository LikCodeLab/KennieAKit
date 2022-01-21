package com.liba_module_update.net;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * <b>Create Date:</b> 16/9/21<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class Model implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;

    public String msg;
    public String title;
    public String error;
    public String success ;

    public Model() {

    }

    /**
     * Protected constructer method workded with {@link Parcelable}.
     *
     * @param in {@link Parcel}
     */
    protected Model(Parcel in) {
        msg = in.readString();
        title = in.readString();
        error = in.readString();
        success = in.readString();
    }

    @Override
    public String toString() {
        return "Model{" +
                "msg='" + msg + '\'' +
                ", title='" + title + '\'' +
                ", error='" + error + '\'' +
                ", success='" + success + '\'' +
                '}';
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(title);
        dest.writeString(error);
        dest.writeString(success);
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }
}
