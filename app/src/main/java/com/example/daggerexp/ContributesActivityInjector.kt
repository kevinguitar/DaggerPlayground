package com.example.daggerexp

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ContributesActivityInjector(
    val scope: KClass<*> = AppGraph::class,
)