package com.vignesh.atherassignment.di

import com.vignesh.atherassignment.data.MainRepositoryImpl
import com.vignesh.atherassignment.domain.MainRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    singleOf(::MainRepositoryImpl).bind<MainRepository>()
}