package com.example.autoparamsdependencyissue2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AutoParamsDependencyIssue2Application

fun main(args: Array<String>) {
    runApplication<AutoParamsDependencyIssue2Application>(*args)
}
