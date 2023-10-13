package com.project.audiorecording.audiorecordingserver

import com.project.audiorecording.audiorecordingserver.controller.*
import com.project.audiorecording.audiorecordingserver.domain.dto.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.ResponseEntity
import java.time.Duration
import java.util.*

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.project.audiorecording.audiorecordingserver.repository"])
class AudioRecordingServerApplication(
    override val discController: DiscController,
    override val trackController: TrackController,
    override val rockCompositionController: RockCompositionController,
    override val popCompositionController: PopCompositionController,
    override val classicalCompositionController: ClassicalCompositionController

) : App {


    override fun start() {
        var running = true

        while (running) {
            println("1. Create disc")
            println("2. Add track to disc")
            println("3. List all discs")
            println("4. List tracks of disc")
            println("5. Calculate duration of disc")
            println("6. Sort tracks by style")
            println("7. Find tracks by length range")
            println("8. List all tracks")
            println("9. Remove track from disc")
            println("10. Remove disc")
            println("11. Exit")

            val input = readlnOrNull() ?: ""
            when (input) {
                "1" -> createDisc()
                "2" -> addTrackToDisc()
                "3" -> listAllDiscs()
                "4" -> listTracksOfDisc()
                "5" -> calculateDurationOfDisc()
                "6" -> sortTracksByStyle()
                "7" -> findTracksByLengthRange()
                "8" -> listAllTracks()
                "9" -> removeTrack()
                "10" -> removeDisc()
                "11" -> running = false
                else -> println("Invalid input")
            }
        }
    }

    override fun createDisc() {
        println("Enter disc name")
        val name = readlnOrNull() ?: ""
        println("Enter disc track number (you need to enter tracks later)")
        val trackNumber = readlnOrNull() ?.toInt() ?: 0

        var disc = DiscDto(
            name = name,
            trackNumber = trackNumber,
            totalDuration = Duration.ZERO,
        )

        val discResponse = discController.create(disc)
        printOperationResult(discResponse)
        disc = discResponse.body!!

        for (i in 1..trackNumber) {
            addTrackToDisc(disc.id!!)
        }
    }

    private fun <T> printOperationResult(result: ResponseEntity<T>) {
        if (result.statusCode.is2xxSuccessful) {
            println("Operation successful")
            println(result.toString())

        } else {
            println("Operation failed")
        }
    }

    override fun addTrackToDisc() {
        println("Enter disc id")
        val discId = readlnOrNull() ?: ""
        addTrackToDisc(UUID.fromString(discId))
    }

    private fun addTrackToDisc(discId : UUID) {
        println("Enter track type")
        println("1. Rock")
        println("2. Pop")
        println("3. Classical")

        try {
            val disc = discController.getOne(discId).body!!
            val input = readlnOrNull() ?: ""
            when (input) {
                "1" -> {
                    val rockComposition = getRockComposition(disc)
                    printOperationResult(rockCompositionController.create(rockComposition))
                }
                "2" -> {
                    val popComposition = getPopComposition(disc)
                    printOperationResult(popCompositionController.create(popComposition))
                }
                "3" -> {
                    val classicalComposition = getClassicalComposition(disc)
                    printOperationResult(classicalCompositionController.create(classicalComposition))
                }
                else -> println("Invalid input")
            }
        } catch (e: Exception) {
            println("Invalid disc id")
        }
    }

    private fun getTrack(disc: DiscDto): TrackDto {
        println("Enter track title")
        val title = readlnOrNull() ?: ""
        println("Enter track duration (in seconds)")
        val duration: Duration = Duration.ofSeconds(readlnOrNull()?.toLong() ?: 0)
        println("Enter track author")
        val author = readlnOrNull() ?: ""

        return TrackDto(
            title = title,
            duration = duration,
            author = author,
            disc = disc
        )
    }

    private fun getRockComposition(disc: DiscDto): RockCompositionDto {
        val track = getTrack(disc)
        println("Enter rock composition style")
        val style = readlnOrNull() ?: ""

        return RockCompositionDto(
            track = track,
            style = style
        )
    }

    private fun getPopComposition(disc: DiscDto): PopCompositionDto {
        val track = getTrack(disc)
        println("Enter pop composition genre")
        val genre = readlnOrNull() ?: ""
        println("Enter pop composition popularity")
        val popularity = readlnOrNull()?.toInt() ?: 0

        return PopCompositionDto(
            track = track,
            genre = genre,
            popularity = popularity
        )
    }

    private fun getClassicalComposition(disc: DiscDto): ClassicalCompositionDto {
        val track = getTrack(disc)
        println("Enter classical composition epoch")
        val epoch = readlnOrNull() ?: ""

        return ClassicalCompositionDto(
            track = track,
            epoch = epoch
        )
    }

    override fun listAllDiscs() {
        val discs = discController.getAll().body!!
        discs.forEach { disc ->
            println("Disc id: ${disc.id}")
            println("Disc name: ${disc.name}")
            println("Disc track number: ${disc.trackNumber}")
            println("Disc total duration: ${disc.totalDuration}")
        }
    }

    override fun listTracksOfDisc() {
        println("Enter disc id")
        val discId = readlnOrNull() ?: ""
        try {
            val disc = discController.getOne(UUID.fromString(discId)).body!!
            disc.tracks?.forEach { track ->
                println("Track id: ${track.id}")
                println("Track title: ${track.title}")
                println("Track duration: ${track.duration}")
                println("Track author: ${track.author}")
                println("Track disc: ${track.disc}")
            }
        } catch (e: Exception) {
            println("Invalid disc id")
        }
    }

    override fun calculateDurationOfDisc() {
        println("Enter disc id")
        val discId = readlnOrNull() ?: ""
        try {
            val totalDuration = discController.calculateDuration(UUID.fromString(discId))
            println("Disc total duration: $totalDuration")
        } catch (e: Exception) {
            println("Invalid disc id")
        }
    }

    override fun sortTracksByStyle() {
        println("Enter disc id")
        val discId = readlnOrNull() ?: ""
        try {
            discController.getSortedByStyle(UUID.fromString(discId)).forEach { track ->
                println("Track id: ${track.id}")
                println("Track title: ${track.title}")
                println("Track duration: ${track.duration}")
                println("Track author: ${track.author}")
                println("Track disc: ${track.disc}")
            }
        } catch (e: Exception) {
            println("Invalid disc id")
        }
    }

    override fun findTracksByLengthRange() {
        println("Enter disc id")
        val discId = readlnOrNull() ?: ""
        try {
            println("Enter min duration (in seconds)")
            val minDuration = Duration.ofSeconds(readlnOrNull()?.toLong() ?: 0)
            println("Enter max duration (in seconds)")
            val maxDuration = Duration.ofSeconds(readlnOrNull()?.toLong() ?: 0)
            discController.findSongsByLength(UUID.fromString(discId), minDuration, maxDuration).forEach { track ->
                println("Track id: ${track.id}")
                println("Track title: ${track.title}")
                println("Track duration: ${track.duration}")
                println("Track author: ${track.author}")
                println("Track disc: ${track.disc}")
            }
        } catch (e: Exception) {
            println("Invalid disc id")
        }
    }

    override fun listAllTracks() {
        val tracks = trackController.getAll().body!!
        tracks.forEach { track ->
            println("Track id: ${track.id}")
            println("Track title: ${track.title}")
            println("Track duration: ${track.duration}")
            println("Track author: ${track.author}")
            println("Track disc: ${track.disc}")
        }
    }

    override fun removeTrack() {
        println("Enter track id")
        val trackId = readlnOrNull() ?: ""
        try {
            trackController.delete(UUID.fromString(trackId))
        } catch (e: Exception) {
            println("Invalid track id")
        }
    }

    override fun removeDisc() {
        println("Enter disc id")
        val discId = readlnOrNull() ?: ""
        try {
            discController.delete(UUID.fromString(discId))
        } catch (e: Exception) {
            println("Invalid disc id")
        }
    }
}
fun main(args: Array<String>) {
    runApplication<AudioRecordingServerApplication>(*args)
}