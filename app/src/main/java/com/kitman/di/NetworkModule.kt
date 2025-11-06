package com.kitman.di

import com.kitman.data.network.ApiService
import com.kitman.data.repository.impl.AthletesRepoImpl
import com.kitman.data.repository.impl.LoginRepoImpl
import com.kitman.data.repository.interfaces.AthletesRepo
import com.kitman.data.repository.interfaces.LoginRepository
import com.kitman.data.repository.validator.Validator
import com.kitman.domain.usecase.GetAthleteDetailsUseCase
import com.kitman.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<LoginRepository> {
        LoginRepoImpl(get(),get())
    }


    single<AthletesRepo> {
        AthletesRepoImpl(get())
    }

    single {
        GetAthleteDetailsUseCase(get())
    }

    single { Validator() }
}