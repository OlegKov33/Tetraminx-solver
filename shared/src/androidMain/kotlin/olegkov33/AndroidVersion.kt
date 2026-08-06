package olegkov33

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import olegkov33.solver.screens.Window

class AndroidVersion {

    @Composable
    fun GeneratingAndroidVersion(){
        Column{
            Window().windowLogic()
        }
    }
}