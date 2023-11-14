rootProject.name = "b-java"

include("thread")
include("jvm", "jvm:asm", "jvm:classloader", "jvm:memory")
include("spring-demo", "spring-demo:event-demo", "spring-demo:kafka-demo", "spring-demo:poi-tl-demo")
