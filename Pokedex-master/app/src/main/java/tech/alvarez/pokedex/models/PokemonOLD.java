package tech.alvarez.pokedex.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonOLD implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("weight")
    @Expose
    private int weight;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location_area_encounters")
    @Expose
    private String location_area_encounters;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("stats")
    @Expose
    private stats[] stats;

    private type[] types;

    protected PokemonOLD(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PokemonOLD> CREATOR = new Creator<PokemonOLD>() {
        @Override
        public PokemonOLD createFromParcel(Parcel in) {
            return new PokemonOLD(in);
        }

        @Override
        public PokemonOLD[] newArray(int size) {
            return new PokemonOLD[size];
        }
    };

    public stats[] getStats() {
        return stats;
    }

    public void setStats(stats[] stats) {
        this.stats = stats;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public type[] getTypes() {
        return types;
    }

    public void setTypes(type[] types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setId(int id) {
        this.id = id;
    }
}
