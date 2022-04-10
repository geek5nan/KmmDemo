package com.devwu.demo.kmmdemo

import com.devwu.demo.kmmdemo.dto.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val client = HttpClient {
        install(Logging) {
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }.apply {
        // 拦截器
        plugin(HttpSend).intercept { request ->
            request.parameter("key", "SSSLquKonsbcNF9Gm") // API 密钥
            request.parameter("language", "zh-Hans")     // API 国际化
            request.parameter("unit", "c")               // API 数据单位， c 表示摄氏度
            execute(request)                             // 执行请求
        }
    }

    fun greeting(): String {
        return "Hello1, ${Platform().platform}!"
    }

    suspend fun getHtml(): String {
        val response = client.get("http://baidu.com")
        return response.bodyAsText()
    }

    internal companion object {
        private const val BASE_URL = "https://api.seniverse.com"
        const val CURRENT_WEATHER = "$BASE_URL/v3/weather/now.json"
    }

    suspend fun getTodayWeather(location: String = "beijing"): WeatherResponse {
        return client.get(CURRENT_WEATHER) {
            parameter("location", location)
            contentType(ContentType.Application.Json)
        }.body()
    }
}