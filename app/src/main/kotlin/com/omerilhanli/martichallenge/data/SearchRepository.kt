package com.omerilhanli.martichallenge.data

import com.omerilhanli.api_ktx.service.SearchService
import javax.inject.Inject

class SearchRepository
@Inject
constructor(private val searchService: SearchService) {

    fun loadPredication(input: String) = searchService.loadPredictions(input)
}