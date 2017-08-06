package ardnahcimor.orp.swiggyvariants.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by romichandra on 03-08-2017.
 */

@Generated("org.jsonschema2pojo")
public class Variation implements Parcelable{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("default")
    @Expose
    private Integer _default;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("inStock")
    @Expose
    private Integer inStock;
    @SerializedName("isVeg")
    @Expose
    private Integer isVeg;

    private boolean isExcluded = true;

    public Variation(boolean isExcluded, Integer inStock, String id, Integer price, String name) {
        this.isExcluded = isExcluded;
        this.inStock = inStock;
        this.id = id;
        this.price = price;
        this.name = name;
    }

    protected Variation(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public boolean isExcluded() {
        return isExcluded;
    }

    public void setExcluded(boolean excluded) {
        isExcluded = excluded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variation)) return false;

        Variation variation = (Variation) o;

        return getName() != null ? getName().equals(variation.getName()) : variation.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (isExcluded ? 1 : 0);
        return result;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Variation> CREATOR = new Creator<Variation>() {
        @Override
        public Variation createFromParcel(Parcel in) {
            return new Variation(in);
        }

        @Override
        public Variation[] newArray(int size) {
            return new Variation[size];
        }
    };

}