package org.berendeev.roma.simplerealm.di;

import org.berendeev.roma.simplerealm.data.RepositoryCallbackImpl;
import org.berendeev.roma.simplerealm.domain.LivecycleObject;
import org.berendeev.roma.simplerealm.domain.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private LivecycleObject livecycleObject;

    public MainModule(LivecycleObject livecycleObject) {
        this.livecycleObject = livecycleObject;
    }

    @Provides
    public Repository provideRepository() {
        RepositoryCallbackImpl repositoryCallback = new RepositoryCallbackImpl();
        livecycleObject.addListener(repositoryCallback);
        return repositoryCallback;
    }
}
