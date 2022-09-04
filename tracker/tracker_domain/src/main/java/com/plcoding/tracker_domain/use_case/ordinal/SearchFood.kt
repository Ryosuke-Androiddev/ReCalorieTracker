package com.plcoding.tracker_domain.use_case.ordinal

import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository
) {
    // 空文字を入れてないかとかの判断を全部ここで任せるんや
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            // これの使いどころを探る
            return Result.success(emptyList())
        }
        return repository.searchFood(
            query = query.trim(),
            page = page,
            pageSize = pageSize
        )
    }
}