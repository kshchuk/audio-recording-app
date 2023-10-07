package com.project.audiorecording.audiorecordingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AudioRecordingServerApplication

fun main(args: Array<String>) {
	runApplication<AudioRecordingServerApplication>(*args)
}
