package br.com.zup.hackatontimesheet.application.di.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by joaoh on 09/04/2018.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
