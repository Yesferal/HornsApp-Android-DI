package com.yesferal.hornsapp.hada.exception

import java.lang.Exception

class DependencyRegisteredTwiceException(
    className: String
) : Exception("The Hada Container already contains an instance of [${className}]")

class DependencyNotFoundException(
    className: String
) : Exception("The Hada Container could not resolve instance of [${className}]")

class ParameterNotFoundException(
    index: Int,
    className: String
) : Exception(" Parameter #${index} [${className}] not found")