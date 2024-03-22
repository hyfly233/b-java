rootProject.name = "b-java"
include("jvm")
include("jvm:asm")
findProject(":jvm:asm")?.name = "asm"
include("jvm:classloader")
findProject(":jvm:classloader")?.name = "classloader"
include("jvm:memory")
findProject(":jvm:memory")?.name = "memory"
