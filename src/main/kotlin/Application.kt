package com.example

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory.parseFile
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

private lateinit var config: Config

fun main(args: Array<String>) {
    config = parseFile(File(args.first()))

    startKoin {
        modules(allModules)
    }

    embeddedServer(Netty, port = 8080) {
        install(Koin) {
            printLogger()
            modules(allModules)
        }
        DatabaseFactory.init()
        configureRouting()
    }.start(wait = true)
}

private val appModules = module {
    single<Config> { config }
}

private val allModules = appModules + routesModules + databaseModules
