package com.example

import com.example.queries.CheckIfUserExistsByEmail
import com.example.queries.CheckIfUserExistsByEmailPostgres
import com.example.queries.FetchPrivilegesOfUser
import com.example.queries.FetchPrivilegesOfUserPostgres
import com.example.queries.FetchWTExpiresAtAndUserIdOrNull
import com.example.queries.FetchWTExpiresAtAndUserIdOrNullPostgres
import com.example.queries.InsertIntoUser
import com.example.queries.InsertIntoUserPostgres
import com.example.queries.UpdateWTExpireTime
import com.example.queries.UpdateWTExpireTimePostgres
import com.example.user.SignUp
import com.example.user.SignUpServerImpl
import com.example.user.ValidateWT
import com.example.user.ValidateWTServerImpl
import org.koin.dsl.module

val routesModules = module {
    single<SignUp> { SignUpServerImpl() }
    single<ValidateWT> { ValidateWTServerImpl() }
}

val databaseModules = module {
    single<InsertIntoUser> { InsertIntoUserPostgres() }
    single<CheckIfUserExistsByEmail> { CheckIfUserExistsByEmailPostgres() }
    single<FetchPrivilegesOfUser> { FetchPrivilegesOfUserPostgres() }
    single<FetchWTExpiresAtAndUserIdOrNull> { FetchWTExpiresAtAndUserIdOrNullPostgres() }
    single<UpdateWTExpireTime> { UpdateWTExpireTimePostgres() }
}
