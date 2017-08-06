package ardnahcimor.orp.swiggyvariants.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by romichandra on 02-08-2017.
 */

@Generated("org.jsonschema2pojo")
public class ApiResponse {

    @SerializedName("variants")
    @Expose
    private Variants variants;

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

}
