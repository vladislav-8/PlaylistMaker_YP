package com.practicum.playlistmaker_1.search.domain.api

import com.practicum.playlistmaker_1.search.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    fun searchTracks(expression: String): Flow<Pair<List<Track>?, String?>>
    fun getHistory(): List<Track>
    fun saveHistory(tracks: List<Track>)
}