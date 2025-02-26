plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    id("org.flywaydb.flyway") version "9.22.0"
    id("nu.studer.jooq") version "8.2"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.zaxxer:HikariCP:5.0.1")
    jooqGenerator("org.postgresql:postgresql:42.7.2")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/demo_db"
    user = "admin"
    password = "Demo@123"
    schemas = arrayOf("public")
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}

jooq {
    version.set("3.18.6")
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(false)
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/demo_db"
                    user = "admin"
                    password = "Demo@123"
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        forcedTypes = listOf(
                            org.jooq.meta.jaxb.ForcedType().apply {
                                name = "Instant"
                                includeTypes = "TIMESTAMPTZ|TIMESTAMP WITH TIME ZONE"
                            }
                        )
                    }
                    generate.apply {
                        isPojos = true
                    }
                    target.apply {
                        packageName = "ktor-sample.jooq"
                        directory = "${projectDir}/jooq/src"
                    }
                }
            }
        }
    }
}
