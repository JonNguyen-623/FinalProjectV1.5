package edu.sjsu.android.finalprojectv1;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Item implements Parcelable{

    private final int imageId;
    private final String name;
    private final String desc;

    // We can add more if needed

    public Item(int imageId, String name, String desc) {
        this.imageId = imageId;
        this.name = name;
        this.desc = desc;
    }

    protected Item(Parcel in) {
        imageId = in.readInt();
        name = in.readString();
        desc = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeString(name);
        parcel.writeString(desc);
    }

}
