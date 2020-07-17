package com.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.model.Views;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    public Publisher<Views> views(String title) {
        AtomicInteger count = new AtomicInteger();
        Observable<Views> observable = Observable.create(e -> {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(() -> {

                Views view = Views.builder()
                        .title(title)
                        .count(count.incrementAndGet())
                        .build();

                e.onNext(view);
            }, 0, 2, TimeUnit.SECONDS);
        });

        ConnectableObservable connectableObservable = observable.share().publish();
        connectableObservable.connect();
        return connectableObservable.toFlowable(BackpressureStrategy.BUFFER);

    }
}
