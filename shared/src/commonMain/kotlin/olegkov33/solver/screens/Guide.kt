package olegkov33.solver.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class Guide {

    /**
     * Method used to create a window with a guide on how the app works
     * @param currentScreen variable used to switch to another screen via button
     */
    @Composable
    fun CreateGuideWindow(currentScreen: MutableState<WindowState>) {

        return Column(Modifier.padding(top = 16.dp)) {

            Button(onClick = {
                currentScreen.value = WindowState.Setup
            }) {
                Text("Back")
            }

            Column(Modifier.verticalScroll(rememberScrollState())) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "How does this app work?",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text("This is a guide tab, here you will get a quick guide\n")
                    Text(
                        "You can always come back to this page by pressing \"?\" button" +
                                "\nTo exit press \"Back\" button. ",
                        Modifier.padding(bottom = 8.dp)
                    )
                }
                Column(Modifier.padding(16.dp)) {

                    Text(
                        text = "General understanding",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )

                    Text(
                        "Once you exit this page, you will see a tetra-minx model or at least 4 hexagons with 6 slices in each." +
                                "\nThat is your tetra-minx. Their order is left to right -> \n" +
                                "1. Front side\n" +
                                "2. Right side\n" +
                                "3. Left side\n" +
                                "4. Bottom side\n"
                    )
                    Text(
                        text = "Pick one face as your Front face and keep it consistent.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Entering your tetra-minx into the program",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text(
                        "1. Pick which side will be your \"Front\" and enter its colors\n" +
                                "2. Turn your tetra-minx right and left and enter their colors\n" +
                                "3. To enter the bottom sides information; \n" +
                                "Tilt your tetra-minx on the left side. You know you done it correctly when" +
                                " your tetra-minx left side is on the bottom and your bottom is on the right.",
                        Modifier.padding(bottom = 8.dp)
                    )
                }

                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Final comments",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text(
                        "Once everything is ready, press \"Solve\", the time may vary, please be patient\n" +
                                "Once it is ready, you will be moved to a new page where you will get a list of instruction\n" +
                                "The instructions are written from the front side point of view.\n"
                    )

                    Text(
                        "Every color has 2 versions. If you find it difficult to use top or bottom color" +
                                " you are welcome to use the opposite color"
                    )

                    Text(
                        "\nIf you want, there is an option to scramble your tetra-minx, to do so;\n" +
                                "1. Press \"Scramble\" button and follow the instructions\n" +
                                "2. If the scramble is too easy or failed to work, just repeat the process",
                        Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }


    }
}