plugins {
    java
    id("net.neoforged.moddev") version "2.0.134"
}

val modId: String by project
val modVersion: String by project
val mcVersion: String by project
val neoforgeVersion: String by project
val parchmentMcVersion: String by project
val parchmentMappingsVersion: String by project
val epicfightVersion: String by project

version = modVersion
group = "com.sorcery"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

repositories {
    // Epic Fight is distributed through Modrinth's Maven.
    exclusiveContent {
        forRepository { maven { name = "Modrinth"; url = uri("https://api.modrinth.com/maven") } }
        filter { includeGroup("maven.modrinth") }
    }
}

neoForge {
    version = neoforgeVersion

    parchment {
        mappingsVersion.set(parchmentMappingsVersion)
        minecraftVersion.set(parchmentMcVersion)
    }

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        create("data") {
            data()
            programArguments.addAll(
                "--mod", modId,
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
        }
    }

    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main {
    resources.srcDir(layout.projectDirectory.dir("src/generated/resources"))
}

dependencies {
    // Epic Fight is a hard, required dependency for this addon.
    implementation("maven.modrinth:epic-fight:$epicfightVersion")
}

tasks.processResources {
    val replaceProperties = mapOf(
        "modId" to modId,
        "modVersion" to modVersion,
        "minecraft" to mcVersion,
        "neoforgeVersion" to neoforgeVersion
    )
    inputs.properties(replaceProperties)
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replaceProperties)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
