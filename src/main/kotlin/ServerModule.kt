package com.example

import com.example.user.SignUp
import com.example.user.SignUpServerImpl
import com.example.user.queries.CheckIfUserExistsByEmail
import com.example.user.queries.CheckIfUserExistsByEmailPostgres
import com.example.user.queries.InsertIntoUser
import com.example.user.queries.InsertIntoUserPostgres
import org.koin.dsl.module

val routesModules = module {
    single<SignUp> { SignUpServerImpl() }
}

val databaseModules = module {
    single<InsertIntoUser> { InsertIntoUserPostgres() }
    single<CheckIfUserExistsByEmail> { CheckIfUserExistsByEmailPostgres() }
}
