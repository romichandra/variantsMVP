package ardnahcimor.orp.swiggyvariants.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;

import java.util.List;

import javax.annotation.Generated;

import ardnahcimor.orp.swiggyvariants.R;

/**
 * Created by romichandra on 03-08-2017.
 */

@Generated("org.jsonschema2pojo")
public class VariantGroup extends CheckedExpandableGroup{

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("variations")
    @Expose
    private List<Variation> variations = null;

    private int iconResId;

    public VariantGroup(String title, List<Variation> items, String name, String groupId) {
        super(title, items);
        this.name = name;
        this.variations = items;
        this.groupId = groupId;
        this.iconResId = R.drawable.ic_star;
    }



    @Override
    public void onChildClicked(int childIndex, boolean checked) {
        if (checked) {
            for (int i = 0; i < getItemCount(); i++) {
                unCheckChild(i);
            }
            checkChild(childIndex);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariantGroup)) return false;

        VariantGroup group = (VariantGroup) o;

        return getName() == group.getName();
    }

    @Override
    public int hashCode() {
        return getIconResId();
    }

    protected VariantGroup(Parcel in) {
        super(in);
        iconResId = in.readInt();
    }

    public int getIconResId() {
        return iconResId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(iconResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VariantGroup> CREATOR = new Creator<VariantGroup>() {
        @Override
        public VariantGroup createFromParcel(Parcel in) {
            return new VariantGroup(in);
        }

        @Override
        public VariantGroup[] newArray(int size) {
            return new VariantGroup[size];
        }
    };

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Variation> getVariations() {
        return variations;
    }

    public void setVariations(List<Variation> variations) {
        this.variations = variations;
    }

}