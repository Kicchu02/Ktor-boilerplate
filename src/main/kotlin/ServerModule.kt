package com.example

import com.example.queries.CheckIfUserExistsByEmail
import com.example.queries.CheckIfUserExistsByEmailPostgres
import com.example.queries.InsertIntoUser
import com.example.queries.InsertIntoUserPostgres
import com.example.user.SignUp
import com.example.user.SignUpServerImpl
import org.koin.dsl.module

val routesModules = module {
    single<SignUp> { SignUpServerImpl() }
}

val databaseModules = module {
    single<InsertIntoUser> { InsertIntoUserPostgres() }
    single<CheckIfUserExistsByEmail> { CheckIfUserExistsByEmailPostgres() }
}
