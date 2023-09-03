package ru.microsrv.teamretroserviceliquibasejob

import org.springframework.boot.Banner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class TeamRetroServiceLiquibaseJobApplication

fun main(vararg args: String) {
    SpringApplicationBuilder()
        .sources(TeamRetroServiceLiquibaseJobApplication::class.java)
        .bannerMode(Banner.Mode.OFF)
        .web(WebApplicationType.NONE)
        .run(*args)
}
