package olegkov33.tetramix_solver

import olegkov33.tetraminx_solver.Platform

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}
