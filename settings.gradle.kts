pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "healthy"
include(":app")
includeModulesFrom("feature")
includeBuild("build-logic")

fun includeModulesFrom(sourcesDirName: String, depth: Int = 3) {
    val sourcesDir = file(sourcesDirName)

    sourcesDir.walkTopDown()
        .maxDepth(depth)
        .forEach { subDir ->    // Проходимся по всем поддиректориям до указанной глубины depth.
            if (isModule(subDir)) {
                // Получаем полное название модуля через его относительный путь и замену слешей на дветочие
                val moduleName = subDir.relativeTo(sourcesDir.parentFile).toString()
                    .replace(File.separatorChar, ':')
                include(moduleName)
            }
        }
}

/**
 * Функция, определяющая, является ли директория модулем в контексте gradle
 */
fun isModule(dir: File): Boolean {
    // Просто проверяем наличие build.gradle.kts или build.gradle.kts.kts файла в директории
    return File(dir, "build.gradle.kts").exists() || File(dir, "build.gradle.kts.kts").exists()
}

