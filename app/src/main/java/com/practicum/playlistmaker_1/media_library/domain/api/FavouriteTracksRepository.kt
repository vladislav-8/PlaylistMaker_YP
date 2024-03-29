package com.practicum.playlistmaker_1.media_library.domain.api

import com.practicum.playlistmaker_1.search.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface FavouriteTracksRepository {

    suspend fun getFavoritesTracks(): Flow<List<Track>>

    suspend fun isFavoriteTrack(trackId: Long): Flow<Boolean>

    suspend fun addToFavorites(track: Track)

    suspend fun deleteFromFavorites(trackId: Long)
}