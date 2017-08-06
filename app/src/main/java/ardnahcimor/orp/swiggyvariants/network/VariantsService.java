package ardnahcimor.orp.swiggyvariants.network;

import ardnahcimor.orp.swiggyvariants.model.ApiResponse;
import ardnahcimor.orp.swiggyvariants.model.Variants;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by romichandra on 02-08-2017.
 */


public class VariantsService {

    private VariantsNetworkService networkService;

    public VariantsService(VariantsNetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getVariants(final GetVariantsCallback callback){
        return networkService.getVariants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ApiResponse>>() {
                    @Override
                    public Observable<? extends ApiResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);

                    }

                    @Override
                    public void onNext(ApiResponse variantsResponse) {
                        callback.onSuccess(variantsResponse);

                    }
                });
    }



    public interface GetVariantsCallback{
        void onSuccess(ApiResponse variantsResponse);

        void onError(Throwable networkError);
    }
}
