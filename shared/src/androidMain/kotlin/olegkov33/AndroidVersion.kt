package olegkov33

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import olegkov33.solver.Window

class AndroidVersion {

    @Composable
    fun generatingAndroidVersion(){
        // load buttons, model and a way to switch to new tab

        Column{
            Window().windowLogic()
        }
    }
}