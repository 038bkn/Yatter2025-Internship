package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import androidx.navigation.compose.composable

class PublicTimelineDestination(
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) : Destination(ROUTE, builder) {
    companion object {
        private const val ROUTE = "publicTimeline"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(ROUTE) {
                PublicTimelinePage()
            }
        }
    }
}