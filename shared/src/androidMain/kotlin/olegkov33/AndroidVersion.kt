package olegkov33

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import olegkov33.solver.screens.Window

class AndroidVersion {

    @Composable
    fun GeneratingAndroidVersion() {
        Column (Modifier.padding(top = 16.dp)){
            Window().WindowLogic()
        }
    }
}