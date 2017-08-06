package ardnahcimor.orp.swiggyvariants.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by romichandra on 03-08-2017.
 */

@Generated("org.jsonschema2pojo")
public class ExcludeList {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("variation_id")
    @Expose
    private String variationId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

}