package ardnahcimor.orp.swiggyvariants.network;

import ardnahcimor.orp.swiggyvariants.model.ApiResponse;
import ardnahcimor.orp.swiggyvariants.model.Variants;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by romichandra on 02-08-2017.
 */

public interface VariantsNetworkService {
    @GET("https://api.myjson.com/bins/3b0u2")
    Observable<ApiResponse> getVariants();
}
