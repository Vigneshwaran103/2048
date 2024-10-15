package com.vignesh.atherassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.vignesh.atherassignment.ui.composables.GameBoardWithSwipe
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
                            GameIntent.Swipe(it)
                        )
                    },
                    onRestartClick = {
                        viewModel.handleIntent(GameIntent.Restart)
                    }
                )
            }
        }
    }
}

@Composable
fun GameScreen(
    state: MainGameState,
    modifier: Modifier = Modifier,
    onSwipe: (SwipeDirection) -> Unit,
    onRestartClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
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
                    text = stringResource(id = R.string.retry)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        GameBoardWithSwipe(
            board = state.board,
            onSwipe = {
                onSwipe.invoke(it)
            }
        )
    }
}



