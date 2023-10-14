package com.project.audiorecording.audiorecordingserver.utils

import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

class ClassInspector {
    fun inspect(className: String) {
        val clazz = Class.forName(className).kotlin
        println("Class Name: ${clazz.qualifiedName}")
        println("Superclass: ${clazz.supertypes}")

        println("Properties:")
        clazz.memberProperties.forEach { prop ->
            println("  - ${prop.name}: ${prop.returnType}")
        }

        println("Functions:")
        clazz.functions.forEach { func ->
            println("  - ${func.name}: ${func.returnType}")
        }
    }

    fun printObjectProperties(obj: Any) {
        val clazz = obj::class
        println("Object Class Name: ${clazz.qualifiedName}")

        clazz.memberProperties.forEach { prop ->
            val value = prop.getter.call(obj)
            println("${clazz.simpleName} ${prop.name}: $value")
        }
    }
}