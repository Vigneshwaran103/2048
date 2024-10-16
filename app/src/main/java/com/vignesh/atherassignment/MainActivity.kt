package com.vignesh.atherassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vignesh.atherassignment.core.Util
import com.vignesh.atherassignment.ui.composables.PuzzleBoardComposable
import com.vignesh.atherassignment.ui.theme.AtherAssignmentTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtherAssignmentTheme {
                GameScreen(
                    state = viewModel.state,
                    onSwipe = {
                        viewModel.handleIntent(
                            MainAction.Swipe(it)
                        )
                    },
                    onRestartClick = {
                        viewModel.handleIntent(MainAction.Restart)
                    }
                )
            }
        }
    }

    @Composable
    fun GameScreen(
        state: MainState,
        modifier: Modifier = Modifier,
        onSwipe: (Util.SwipeDirection) -> Unit,
        onRestartClick: () -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PuzzleBoardComposable(
                modifier = Modifier,
                board = state.board,
                onSwipe = {
                    onSwipe.invoke(it)
                }
            )
            Spacer(
                modifier = Modifier.weight(
                    0.5f
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.isGameOver) {
                    Text(
                        text = stringResource(id = R.string.game_over), style = TextStyle(
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp, textAlign = TextAlign.Center
                        )
                    )
                }
                if (state.puzzleSolved) {
                    Text(
                        text = stringResource(id = R.string.won), style = TextStyle(
                            color = Color(0Xff00b300),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier
                        .border(
                            color = Color.Black,
                            width = 1.dp,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                        .clickable {
                            onRestartClick.invoke()
                        },
                    text = stringResource(id = if (state.puzzleSolved) R.string.play_again else R.string.retry)
                )
            }
        }
    }
}





