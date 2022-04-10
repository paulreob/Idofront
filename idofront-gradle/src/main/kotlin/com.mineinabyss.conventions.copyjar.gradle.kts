plugins {
    java
    id("com.github.johnrengelman.shadow")
}

val copyJar: String? by project
val pluginPath = project.findProperty("plugin_path")

if(copyJar != "false" && pluginPath != null) {
    tasks {
        register<Copy>("copyJar") {
            from(findByName("reobfJar") ?: findByName("shadowJar") ?: findByName("jar"))
            into(pluginPath)
            doLast {
                println("Copied to plugin directory $pluginPath")
            }
        }

        named<DefaultTask>("build") {
            dependsOn("copyJar")
        }
    }
}
