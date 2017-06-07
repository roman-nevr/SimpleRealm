package org.berendeev.roma.simplerealm.di;

import org.berendeev.roma.simplerealm.domain.Repository;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    Repository repository();
}
