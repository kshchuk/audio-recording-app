package com.project.audiorecording.audiorecordingserver

import com.project.audiorecording.audiorecordingserver.controller.*

interface App {
    val discController: DiscController
    val trackController: TrackController
    val rockCompositionController: RockCompositionController
    val popCompositionController: PopCompositionController
    val classicalCompositionController: ClassicalCompositionController

    fun start()
    fun createDisc()
    fun addTrackToDisc()
    fun listAllDiscs()
    fun listTracksOfDisc()
    fun calculateDurationOfDisc()
    fun sortTracksByStyle()
    fun findTracksByLengthRange()
    fun listAllTracks()
    fun removeTrack()
    fun removeDisc()
}