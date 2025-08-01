package com.dmm.bootcamp.yatter2025.ui.login.bindingmodel

import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.domain.model.Username

data class LoginBindingModel(
    val username: String,
    val password: String,
)
