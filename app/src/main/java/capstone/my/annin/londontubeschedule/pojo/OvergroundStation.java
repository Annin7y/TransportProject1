package capstone.my.annin.londontubeschedule.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import static java.lang.String.valueOf;

public class OvergroundStation implements Parcelable
{
    /**
     * Station id
     */
    private String stationId;

    /**
     * Station name
     */
    private String stationName;

    /**
     * Station lat location
     */
    private double latLocation;

    /**
     * Station lon location
     */
    private double lonLocation;

    public OvergroundStation(String stationId, String stationName, double latLocation, double lonLocation)
    {
        this.stationId = stationId;
        this.stationName = stationName;
        this.latLocation = latLocation;
        this.lonLocation = lonLocation;
    }

    public void setStationId(String stationId)
    {
        this.stationId = stationId;
    }

    public String getStationId()
    {
        return stationId;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setLatLocation(double latLocation)
    {
        this.latLocation = latLocation;
    }

    public double getLatLocation()
    {
        return latLocation;
    }

    public void setLonLocation(double lonLocation)
    {
        this.lonLocation = lonLocation;
    }

    public double getLonLocation()
    {
        return lonLocation;
    }

    //Code based on the answer with 27 upvotes in the following stackoverflow post:
    //https://stackoverflow.com/questions/6680157/how-to-remove-duplicate-objects-in-a-listmyobject-without-equals-hashcode/36806739
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof OvergroundStation)
        {
            OvergroundStation temp = (OvergroundStation) o;
            return this.stationId.equals(temp.stationId) && this.stationName.equals(temp.stationName) &&
                    this.latLocation == temp.latLocation && this.lonLocation == temp.lonLocation;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return (this.stationId.hashCode() + this.stationName.hashCode() + (valueOf(latLocation).hashCode()) + (valueOf(lonLocation).hashCode()));

    }

    protected OvergroundStation(Parcel in)
    {
        stationId = in.readString();
        stationName = in.readString();
        latLocation = in.readDouble();
        lonLocation = in.readDouble();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(stationId);
        dest.writeString(stationName);
        dest.writeDouble(latLocation);
        dest.writeDouble(lonLocation);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OvergroundStation> CREATOR = new Parcelable.Creator<OvergroundStation>()
    {
        @Override
        public OvergroundStation createFromParcel(Parcel in)
        {
            return new OvergroundStation(in);
        }

        @Override
        public OvergroundStation[] newArray(int size)
        {
            return new OvergroundStation[size];
        }
    };
}



