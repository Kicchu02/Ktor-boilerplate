package com.example

import com.example.user.GetUsers
import com.example.user.GetUsersServerImpl
import com.example.user.SignUp
import com.example.user.SignUpServerImpl
import com.example.user.queries.CheckIfUserExistsByEmail
import com.example.user.queries.CheckIfUserExistsByEmailPostgres
import com.example.user.queries.GetAllUsers
import com.example.user.queries.GetAllUsersPostgres
import com.example.user.queries.InsertIntoUser
import com.example.user.queries.InsertIntoUserPostgres
import org.koin.dsl.module

val routesModules = module {
    factory<GetUsers> { GetUsersServerImpl() }
    factory<SignUp> { SignUpServerImpl() }
}

val databaseModules = module {
    single<GetAllUsers> { GetAllUsersPostgres() }
    single<InsertIntoUser> { InsertIntoUserPostgres() }
    single<CheckIfUserExistsByEmail> { CheckIfUserExistsByEmailPostgres() }
}
