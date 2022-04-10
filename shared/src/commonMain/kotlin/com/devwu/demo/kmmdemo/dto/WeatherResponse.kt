package com.devwu.demo.kmmdemo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val results: List<WeatherResult>
)

@Serializable
data class WeatherResult(
    val location: Location,
    val now: Weather,
    @SerialName("last_update")
    val lastUpdate: String
)

@Serializable
data class Location(
    val id: String,
    val name: String,
    val country: String,
    val path: String,
    val timezone: String,
    @SerialName("timezone_offset")
    val timezoneOffset: String
)

@Serializable
data class Weather(
    val text: String,
    val code: String,
    val temperature: String,
    @SerialName("feels_like")
    val feelsLike: String,
    val pressure: String,
    val humidity: String,
    val visibility: String,
    @SerialName("wind_direction")
    val windDirection: String,
    @SerialName("wind_direction_degree")
    val windDirectionDegree: String,
    @SerialName("wind_speed")
    val windSpeed: String,
    @SerialName("wind_scale")
    val windScale: String,
    val clouds: String,
    @SerialName("dew_point")
    val dewPoint: String
)