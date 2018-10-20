package com.github.kittinunf.fuel.gson

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Handler
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.core.response
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

// Created by ihor_kucherenko on 7/4/17.
// https://github.com/KucherenkoIhor

inline fun <reified T : Any> Request.responseObject(noinline handler: (Request, Response, Result<T, FuelError>) -> Unit) =
        response(gsonDeserializerOf(), handler)

inline fun <reified T : Any> Request.responseObject(handler: Handler<T>) = response(gsonDeserializerOf(), handler)

inline fun <reified T : Any> Request.responseObject() = response(gsonDeserializerOf<T>())

inline fun <reified T : Any> gsonDeserializerOf() = object : ResponseDeserializable<T> {
    override fun deserialize(reader: Reader): T = Gson().fromJson<T>(reader, object : TypeToken<T>() {}.type)
}