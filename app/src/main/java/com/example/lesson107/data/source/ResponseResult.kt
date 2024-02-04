package com.example.lesson107.data.source

import java.lang.Exception

abstract class ResponseResult {
    class Success<T>(val data: T): ResponseResult()
    class Error(exception: Exception): ResponseResult()
}