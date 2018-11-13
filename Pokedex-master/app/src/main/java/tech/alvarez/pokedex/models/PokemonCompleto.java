package tech.alvarez.pokedex.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonCompleto implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("base_experience")
    @Expose
    private Integer baseExperience;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("abilities")
//    @Expose
//    private List<Ability> abilities = null;
//    @SerializedName("forms")
//    @Expose
//    private List<Form> forms = null;
//    @SerializedName("game_indices")
//    @Expose
//    private List<GameIndex> gameIndices = null;
//    @SerializedName("held_items")
//    @Expose
//    private List<HeldItem> heldItems = null;
//    @SerializedName("location_area_encounters")
//    @Expose
//    private List<LocationAreaEncounter> locationAreaEncounters = null;
//    @SerializedName("moves")
//    @Expose
//    private List<Move> moves = null;
//    @SerializedName("species")
//    @Expose
//    private Species species;
//    @SerializedName("sprites")
//    @Expose
//    private Sprites sprites;
//    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;
    @SerializedName("types")
    @Expose
    private List<type> types = null;
    public final static Parcelable.Creator<PokemonCompleto> CREATOR = new Creator<PokemonCompleto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PokemonCompleto createFromParcel(Parcel in) {
            return new PokemonCompleto(in);
        }

        public PokemonCompleto[] newArray(int size) {
            return (new PokemonCompleto[size]);
        }

    }
            ;

    protected PokemonCompleto(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.baseExperience = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isDefault = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.order = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.weight = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        in.readList(this.abilities, (com.example.Ability.class.getClassLoader()));
//        in.readList(this.forms, (com.example.Form.class.getClassLoader()));
//        in.readList(this.gameIndices, (com.example.GameIndex.class.getClassLoader()));
//        in.readList(this.heldItems, (com.example.HeldItem.class.getClassLoader()));
//        in.readList(this.locationAreaEncounters, (com.example.LocationAreaEncounter.class.getClassLoader()));
//        in.readList(this.moves, (com.example.Move.class.getClassLoader()));
//        this.species = ((Species) in.readValue((Species.class.getClassLoader())));
//        this.sprites = ((Sprites) in.readValue((Sprites.class.getClassLoader())));
//        in.readList(this.stats, (com.example.Stat.class.getClassLoader()));
//        in.readList(this.types, (com.example.Type.class.getClassLoader()));
    }

    public PokemonCompleto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

//    public List<Ability> getAbilities() {
//        return abilities;
//    }
//
//    public void setAbilities(List<Ability> abilities) {
//        this.abilities = abilities;
//    }
//
//    public List<Form> getForms() {
//        return forms;
//    }
//
//    public void setForms(List<Form> forms) {
//        this.forms = forms;
//    }
//
//    public List<GameIndex> getGameIndices() {
//        return gameIndices;
//    }
//
//    public void setGameIndices(List<GameIndex> gameIndices) {
//        this.gameIndices = gameIndices;
//    }
//
//    public List<HeldItem> getHeldItems() {
//        return heldItems;
//    }
//
//    public void setHeldItems(List<HeldItem> heldItems) {
//        this.heldItems = heldItems;
//    }
//
//    public List<LocationAreaEncounter> getLocationAreaEncounters() {
//        return locationAreaEncounters;
//    }
//
//    public void setLocationAreaEncounters(List<LocationAreaEncounter> locationAreaEncounters) {
//        this.locationAreaEncounters = locationAreaEncounters;
//    }
//
//    public List<Move> getMoves() {
//        return moves;
//    }
//
//    public void setMoves(List<Move> moves) {
//        this.moves = moves;
//    }
//
//    public Species getSpecies() {
//        return species;
//    }
//
//    public void setSpecies(Species species) {
//        this.species = species;
//    }
//
//    public Sprites getSprites() {
//        return sprites;
//    }
//
//    public void setSprites(Sprites sprites) {
//        this.sprites = sprites;
//    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<type> getTypes() {
        return types;
    }

    public void setTypes(List<type> types) {
        this.types = types;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(baseExperience);
        dest.writeValue(height);
        dest.writeValue(isDefault);
        dest.writeValue(order);
        dest.writeValue(weight);
//        dest.writeList(abilities);
//        dest.writeList(forms);
//        dest.writeList(gameIndices);
//        dest.writeList(heldItems);
//        dest.writeList(locationAreaEncounters);
//        dest.writeList(moves);
//        dest.writeValue(species);
//        dest.writeValue(sprites);
        dest.writeList(stats);
        dest.writeList(types);
    }

    public int describeContents() {
        return 0;
    }

}