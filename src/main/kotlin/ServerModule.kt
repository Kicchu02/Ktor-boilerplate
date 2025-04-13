package com.example

import com.example.user.GetUsers
import com.example.user.GetUsersServerImpl
import com.example.user.queries.GetAllUsers
import com.example.user.queries.GetAllUsersPostgres
import org.koin.dsl.module

val routesModules = module {
    factory<GetUsers> { GetUsersServerImpl() }
}

val databaseModules = module {
    single<GetAllUsers> { GetAllUsersPostgres() }
}
