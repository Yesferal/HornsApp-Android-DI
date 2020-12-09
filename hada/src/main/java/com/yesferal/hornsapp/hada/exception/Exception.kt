package com.yesferal.hornsapp.hada.container

import java.lang.Exception

class DependencyRegisteredTwiceException(
    className: String
) : Exception("The Container already contains an instance of [${className}]")

class DependencyNotFoundException(
    className: String
) : Exception("The Container could not resolve instance of [${className}]")