import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    java
    com.github.johnrengelman.shadow
    id("me.tagavari.nmsremap")
}

val libs = the<LibrariesForLibs>()
val nmsDep = "io.papermc.paper:paper-server:${libs.versions.minecraft.get()}:mojang-mapped"

tasks {
    // Re-obfuscate jar to vanilla server
    val remapMojangObf by registering(me.tagavari.nmsremap.SSRemapTask::class) {
        inputFile.set(shadowJar.get().archiveFile)
        srgIn.set("org.spigotmc:minecraft-server:${libs.versions.minecraft.get()}:maps-mojang@txt")
        remappedDependencies.add(nmsDep)
        reverse.set(true)
        archiveClassifier.set("reobf-vanilla")
    }
    // De-obfuscate jar with spigot mappings
    val reobfJar by registering(me.tagavari.nmsremap.SSRemapTask::class) {
        inputFile.set(remapMojangObf.get().outputFile)
        srgIn.set("org.spigotmc:minecraft-server:${libs.versions.minecraft.get()}:maps-spigot@csrg")
        // TODO might have to change dep to the commented one?
        remappedDependencies.add(nmsDep)
//        remappedDependencies.add("org.spigotmc:spigot:$mcVersion:remapped-obf")
        println("Name of archive: ${shadowJar.get().archiveFileName.get()}")
        archiveName.set(shadowJar.get().archiveFile.map { it.asFile.nameWithoutExtension + "-reobf.jar"  })
    }

    assemble {
        dependsOn(reobfJar)
    }
}