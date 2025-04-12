package com.example

import com.typesafe.config.ConfigFactory.parseFile
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File

fun main(args: Array<String>) {
    val config = parseFile(File(args.first()))
    println("Token data: ${config.getInt("token.expireDurationInSeconds")}")
    println("Arguments: ${args.toList()}")
    embeddedServer(Netty, port = 8080) {
        DatabaseFactory.init()
        configureRouting()
    }.start(wait = true)
}
