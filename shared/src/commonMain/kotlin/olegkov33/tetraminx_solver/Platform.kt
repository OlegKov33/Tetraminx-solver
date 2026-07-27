package olegkov33.tetraminx_solver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform