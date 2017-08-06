package ardnahcimor.orp.swiggyvariants.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

import javax.annotation.Generated;

/**
 * Created by romichandra on 02-08-2017.
 */

@Generated("org.jsonschema2pojo")
public class Variants {

    @SerializedName("variant_groups")
    @Expose
    private List<VariantGroup> variantGroups = null;
    @SerializedName("exclude_list")
    @Expose
    private List<List<ExcludeList>> excludeList = null;

    public List<VariantGroup> getVariantGroups() {
        return variantGroups;
    }

    public void setVariantGroups(List<VariantGroup> variantGroups) {
        this.variantGroups = variantGroups;
    }

    public List<List<ExcludeList>> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<List<ExcludeList>> excludeList) {
        this.excludeList = excludeList;
    }

}