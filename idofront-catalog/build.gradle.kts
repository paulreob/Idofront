import org.jetbrains.kotlin.util.prefixIfNot

plugins {
    `version-catalog`
    id("com.mineinabyss.conventions.publication")
}

catalog {
    versionCatalog {
        from(rootProject.files("gradle/libs.versions.toml"))
        // Add aliases for all our conventions plugins
        rootProject.file("idofront-gradle/src/main/kotlin").list()?.forEach { name ->
            val id = name.removeSuffix(".gradle.kts")
            plugin(id.removePrefix("com.mineinabyss.conventions").prefixIfNot("mia"), id).version(version.toString())
        }
        // Add all idofront projects to the catalogue
        rootProject.file(".").list()?.filter { it.startsWith("idofront") }?.forEach { name ->
            library(name, "com.mineinabyss:$name:${version.toString()}")
        }
    }
}