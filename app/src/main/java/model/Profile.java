package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String name;
    public String detail;
    public String imageUrl;

    public Profile(String name, String detail, String imageUrl) {
        this.name = name;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }

    protected Profile(Parcel in) {
        name = in.readString();
        detail = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeString(imageUrl);
    }
}
