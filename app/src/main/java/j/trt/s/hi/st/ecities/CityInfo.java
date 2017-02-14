package j.trt.s.hi.st.ecities;


import android.os.Parcel;
import android.os.Parcelable;

public class CityInfo implements Parcelable {
    public int id;
    public String name;
    public String establishment;
    public String url;
    public String arms;
    public String lastChar;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEstablishment() {
        return establishment;
    }

    public String getUrl() {
        return url;
    }

    public String getArms() {
        return arms;
    }

    public String getLastChar() {
        return lastChar;
    }

    public CityInfo() {
        this.id = 0;
        this.name = "";
        this.establishment = "";
        this.url = "";
        this.arms = "";
        this.lastChar = "";

    }

    public CityInfo(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.establishment = data[2];
        this.url = data[3];
        this.arms = data[4];
        this.lastChar = data[5];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[]{String.valueOf(this.id), this.name, this.establishment, this.url, this.arms, this.lastChar});
    }

    public static final Parcelable.Creator<CityInfo> CREATOR= new Parcelable.Creator<CityInfo>() {

        @Override
        public CityInfo createFromParcel(Parcel source) {
            return new CityInfo(source);
        }

        @Override
        public CityInfo[] newArray(int size) {
            return new CityInfo[size];
        }
    };
}
