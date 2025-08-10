package com.example

import com.example.note.CreateNote
import com.example.note.CreateNoteServerImpl
import com.example.queries.CheckIfNoteExists
import com.example.queries.CheckIfNoteExistsPostgres
import com.example.queries.CheckIfNoteTitleExists
import com.example.queries.CheckIfNoteTitleExistsPostgres
import com.example.queries.CheckIfUserExistsByEmail
import com.example.queries.CheckIfUserExistsByEmailPostgres
import com.example.queries.DeleteNoteRecord
import com.example.queries.DeleteNoteRecordPostgres
import com.example.queries.FetchPrivilegesOfUser
import com.example.queries.FetchPrivilegesOfUserPostgres
import com.example.queries.FetchWTExpiresAtAndUserIdOrNull
import com.example.queries.FetchWTExpiresAtAndUserIdOrNullPostgres
import com.example.queries.GetPasswordAndSaltByUserId
import com.example.queries.GetPasswordAndSaltByUserIdPostgres
import com.example.queries.GetUserIdByEmail
import com.example.queries.GetUserIdByEmailPostgres
import com.example.queries.InsertIntoNote
import com.example.queries.InsertIntoNotePostgres
import com.example.queries.InsertIntoUser
import com.example.queries.InsertIntoUserPostgres
import com.example.queries.InsertIntoWT
import com.example.queries.InsertIntoWTPostgres
import com.example.queries.UpdateWTExpireTime
import com.example.queries.UpdateWTExpireTimePostgres
import com.example.user.DeleteNote
import com.example.user.DeleteNoteServerImpl
import com.example.user.DummyApi
import com.example.user.DummyApiServerImpl
import com.example.user.PasswordUtils
import com.example.user.SignIn
import com.example.user.SignInServerImpl
import com.example.user.SignUp
import com.example.user.SignUpServerImpl
import com.example.user.ValidateWT
import com.example.user.ValidateWTServerImpl
import io.ktor.server.application.ApplicationCall
import org.koin.dsl.module

val routesModules = module {
    single<SignUp> { SignUpServerImpl() }
    single<ValidateWT> { ValidateWTServerImpl() }
    factory<SignIn> { (call: ApplicationCall) -> SignInServerImpl(call = call) }
    factory<DummyApi> { (userId: java.util.UUID) -> DummyApiServerImpl(userId = userId) }
    factory<CreateNote> { (userId: java.util.UUID) -> CreateNoteServerImpl(userId = userId) }
    factory<DeleteNote> { (userId: java.util.UUID) -> DeleteNoteServerImpl(userId = userId) }
}

val databaseModules = module {
    single<InsertIntoUser> { InsertIntoUserPostgres() }
    single<CheckIfUserExistsByEmail> { CheckIfUserExistsByEmailPostgres() }
    single<FetchPrivilegesOfUser> { FetchPrivilegesOfUserPostgres() }
    single<FetchWTExpiresAtAndUserIdOrNull> { FetchWTExpiresAtAndUserIdOrNullPostgres() }
    single<UpdateWTExpireTime> { UpdateWTExpireTimePostgres() }
    single<InsertIntoWT> { InsertIntoWTPostgres() }
    single<GetPasswordAndSaltByUserId> { GetPasswordAndSaltByUserIdPostgres() }
    single<GetUserIdByEmail> { GetUserIdByEmailPostgres() }
    single<CheckIfNoteTitleExists> { CheckIfNoteTitleExistsPostgres() }
    single<InsertIntoNote> { InsertIntoNotePostgres() }
    single<DeleteNoteRecord> { DeleteNoteRecordPostgres() }
    single<CheckIfNoteExists> { CheckIfNoteExistsPostgres() }
}

val utilsModules = module {
    single<PasswordUtils> { PasswordUtils() }
}
