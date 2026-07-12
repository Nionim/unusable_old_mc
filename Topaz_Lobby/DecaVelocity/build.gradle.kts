plugins {
	id("java")
}

group = "delta.cion"
version = "0.0.0-DEV"

repositories {
	mavenCentral()
	mavenLocal()
}

tasks {
	build {
		dependsOn(shadowJar)
	}

	shadowJar {
		mergeServiceFiles()
		archiveClassifier.set("")
	}
}
