package com.example

import com.typesafe.config.ConfigFactory
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    ConfigFactory.load(args.first())
    embeddedServer(Netty, port = 8080) {
        DatabaseFactory.init()
        configureRouting()
    }.start(wait = true)
}
