package com.example.kotlin_spring_security.common.dto

import com.example.kotlin_spring_security.common.status.ResultCode

data class BaseResponse<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String = ResultCode.SUCCESS.msg
) {
}