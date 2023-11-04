package com.example.royaal.api

import androidx.navigation.NamedNavArgument
import com.example.royaal.commoncompose.FeatureEntry

abstract class MainEntry : FeatureEntry {

    final override val featureRoute: String
        get() = "main_entry"

    final override val args: List<NamedNavArgument>
        get() = emptyList()

    fun destination() = featureRoute
}