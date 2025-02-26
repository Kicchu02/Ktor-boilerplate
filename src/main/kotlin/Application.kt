package com.example

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) {
        DatabaseFactory.init()
        configureRouting()
    }.start(wait = true)
}
