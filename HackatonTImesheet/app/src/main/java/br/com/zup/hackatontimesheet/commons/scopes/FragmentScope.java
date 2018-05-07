package br.com.zup.hackatontimesheet.commons.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by joaoh on 07/05/2018.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
