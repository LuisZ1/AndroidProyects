package tech.alvarez.pokedex.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon implements Parcelable {

        ArrayList<Object> abilities = new ArrayList<Object>();
        private float base_experience;
        ArrayList<Object> forms = new ArrayList<Object>();
        ArrayList<Object> game_indices = new ArrayList<Object>();
        private float height;
        ArrayList<Object> held_items = new ArrayList<Object>();
        private float id;
        private boolean is_default;
        private String location_area_encounters;
        ArrayList<Object> moves = new ArrayList<Object>();
        private String name;
        private float order;
        Species SpeciesObject;
        Sprites SpritesObject;
        ArrayList<Object> stats = new ArrayList<Object>();
        ArrayList<Object> types = new ArrayList<Object>();
        private float weight;


        // Getter Methods

        public float getBase_experience() {
            return base_experience;
        }

        public float getHeight() {
            return height;
        }

        public float getId() {
            return id;
        }

        public boolean getIs_default() {
            return is_default;
        }

        public String getLocation_area_encounters() {
            return location_area_encounters;
        }

        public String getName() {
            return name;
        }

        public float getOrder() {
            return order;
        }

        public Species getSpecies() {
            return SpeciesObject;
        }

        public Sprites getSprites() {
            return SpritesObject;
        }

        public float getWeight() {
            return weight;
        }

        // Setter Methods

        public void setBase_experience(float base_experience) {
            this.base_experience = base_experience;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public void setId(float id) {
            this.id = id;
        }

        public void setIs_default(boolean is_default) {
            this.is_default = is_default;
        }

        public void setLocation_area_encounters(String location_area_encounters) {
            this.location_area_encounters = location_area_encounters;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOrder(float order) {
            this.order = order;
        }

        public void setSpecies(Species speciesObject) {
            this.SpeciesObject = speciesObject;
        }

        public void setSprites(Sprites spritesObject) {
            this.SpritesObject = spritesObject;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }
    }

    public class Sprites {
        private String back_default;
        private String back_female = null;
        private String back_shiny;
        private String back_shiny_female = null;
        private String front_default;
        private String front_female = null;
        private String front_shiny;
        private String front_shiny_female = null;


        // Getter Methods

        public String getBack_default() {
            return back_default;
        }

        public String getBack_female() {
            return back_female;
        }

        public String getBack_shiny() {
            return back_shiny;
        }

        public String getBack_shiny_female() {
            return back_shiny_female;
        }

        public String getFront_default() {
            return front_default;
        }

        public String getFront_female() {
            return front_female;
        }

        public String getFront_shiny() {
            return front_shiny;
        }

        public String getFront_shiny_female() {
            return front_shiny_female;
        }

        // Setter Methods

        public void setBack_default(String back_default) {
            this.back_default = back_default;
        }

        public void setBack_female(String back_female) {
            this.back_female = back_female;
        }

        public void setBack_shiny(String back_shiny) {
            this.back_shiny = back_shiny;
        }

        public void setBack_shiny_female(String back_shiny_female) {
            this.back_shiny_female = back_shiny_female;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }

        public void setFront_female(String front_female) {
            this.front_female = front_female;
        }

        public void setFront_shiny(String front_shiny) {
            this.front_shiny = front_shiny;
        }

        public void setFront_shiny_female(String front_shiny_female) {
            this.front_shiny_female = front_shiny_female;
        }
    }

    public class Species {
        private String name;
        private String url;


        // Getter Methods

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        // Setter Methods

        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    protected Pokemon(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Pokemon> CREATOR = new Parcelable.Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };
}