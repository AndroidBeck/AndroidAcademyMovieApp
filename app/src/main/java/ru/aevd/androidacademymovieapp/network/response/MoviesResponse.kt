package ru.aevd.androidacademymovieapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
	@SerialName("page")
		val page: Int,
	@SerialName("total_pages")
		val totalPages: Int,
	@SerialName("results")
		val results: List<MoviesResponseResultItem>,
	@SerialName("total_results")
		val totalResults: Int
)

