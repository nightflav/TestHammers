package com.example.royaal.presentation.ui

import android.content.Context
import android.text.Html
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import coil.compose.AsyncImage
import com.example.royaal.common.model.MenuItem
import com.example.royaal.common.model.MenuType
import com.example.royaal.common.model.PromotionItem
import com.example.royaal.presentation.MainScreenState
import com.example.royaal.presentation.MainViewModel
import com.example.royaal.presentation.R
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()
    when {
        state.isLoading -> {}
        state.error != null -> {}
        else -> MainScreenSuccessful(state = state)
    }
}

@Composable
private fun MainScreenSuccessful(
    state: MainScreenState
) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopRow(context = context)
        Promos(
            isPromosVisible = { scrollState.firstVisibleItemIndex },
            promotions = { state.promotions }
        )
        MenuTabs(
            scrollState = scrollState,
            items = {
                state.games.mapIndexed { index, menuItem ->
                    if (menuItem.type == MenuType.Separator)
                        Pair(menuItem.title, index)
                    else {
                        Pair("", -1)
                    }
                }.filter { it.second >= 0 }
            }
        )
        Games(scrollState = scrollState, games = { state.games })
    }
}

@Composable
private fun Games(
    scrollState: LazyListState, games: () -> List<MenuItem>
) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(games().size) {
            when (games()[it].type) {
                MenuType.Item -> GameItem(games()[it])
                MenuType.Separator -> {
                    Text(text = "Separator ${games()[it].title}")
                }
            }
        }
    }
}

@Composable
private fun Promos(
    isPromosVisible: () -> Int, promotions: () -> List<PromotionItem>
) {
    AnimatedVisibility(
        visible = isPromosVisible() <= 2
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = promotions().size,
            ) {
                promotions().forEach { promItem ->
                    PromotionItem(promotionItem = promItem)
                }
            }
        }
    }
}

@Composable
private fun GameItem(game: MenuItem) {
    Card(
        shape = RoundedCornerShape(15)
    ) {
        Row {
            AsyncImage(
                model = game.imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(15)),
            )
            Column {
                Text(
                    text = game.title, style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = Html.fromHtml(
                        game.description, Html.FROM_HTML_MODE_LEGACY
                    ).toString(),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun TopRow(context: Context) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        DropdownTowns(
            items = listOf(
                stringResource(id = R.string.moscow), stringResource(id = R.string.st_petersburg)
            )
        )
        Spacer(modifier = Modifier.fillMaxWidth(0.6f))
        IconButton(onClick = {
            Toast.makeText(context, "QR Icon pressed", Toast.LENGTH_SHORT).show()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.qr_icon), contentDescription = null
            )
        }
    }
}

@Composable
private fun MenuTabs(
    scrollState: LazyListState, items: () -> List<Pair<String, Int>>
) {
    val menuScope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    MenuTabSelector(contents = items().mapIndexed { i, it ->
        {
            MenuTab(isSelected = selectedTabIndex == i, text = it.first, onClick = {
                selectedTabIndex = i
                menuScope.launch {
                    scrollState.animateScrollToItem(it.second)
                }
            })
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuTab(
    modifier: Modifier = Modifier, isSelected: Boolean, onClick: () -> Unit, text: String
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(40),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer
            else MaterialTheme.colorScheme.onTertiaryContainer,
        ),
        onClick = {
            onClick()
        }
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
    }
}

@Composable
private fun PromotionItem(
    promotionItem: PromotionItem
) {
    Card(
        shape = RoundedCornerShape(10), modifier = Modifier.height(128.dp)
    ) {
        AsyncImage(
            model = promotionItem.imgUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.heightIn(96.dp)
        )
    }
}

@Composable
private fun RowScope.DropdownTowns(
    items: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
            .weight(1f)
            .align(CenterVertically)
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.tertiaryContainer
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }, text = {
                    Text(text = s)
                })
            }
        }
    }
}

@Composable
private fun MenuTabSelector(
    contents: List<@UiComposable @Composable () -> Unit>
) {
    LazyRow {
        contents.fastForEach {
            item {
                it()
            }
        }
    }
}