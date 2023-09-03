package ru.microsrv.teamretroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeamRetroServiceApplication

fun main(vararg args: String) {
    runApplication<TeamRetroServiceApplication>(*args)
}
