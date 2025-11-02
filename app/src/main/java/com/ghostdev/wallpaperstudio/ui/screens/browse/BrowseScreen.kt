package com.ghostdev.wallpaperstudio.ui.screens.browse

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.data.models.Category
import com.ghostdev.wallpaperstudio.ui.components.CategoriesCardGrid
import com.ghostdev.wallpaperstudio.ui.components.CategoriesCardList
import com.ghostdev.wallpaperstudio.ui.theme.ClashDisplayFontFamily
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily

enum class Views {
    LIST_VIEW,
    GRID_VIEW
}

@Composable
fun BrowseScreen(
    navigateToCategories: () -> Unit
) {
    val categories = listOf(
        Category(
            number = 3,
            title = "Nature",
            subTitle = "Mountains, Forest and Landscapes",
            image = R.drawable.nature
        ),
        Category(
            number = 4,
            title = "Abstract",
            subTitle = "Modern Geomentric and artistic designs",
            image = R.drawable.abstract_image
        ),
        Category(
            number = 6,
            title = "Urban",
            subTitle = "Cities, architecture and street",
            image = R.drawable.urban
        ),
        Category(
            number = 6,
            title = "Minimalist",
            subTitle = "Clean, simple, and elegant",
            image = R.drawable.minimalist
        ),
        Category(
            number = 4,
            title = "Animals",
            subTitle = "Wildlife and nature photography",
            image = R.drawable.animals
        )
    )

    var view by rememberSaveable { mutableStateOf(Views.GRID_VIEW) }

    BrowseComponent(
        categories = categories,
        view = view,
        toggleView = { selectedView ->
            view = selectedView
        },
        onCategoryClicked = { navigateToCategories() }
    )
}

@Composable
private fun BrowseComponent(
    categories: List<Category>,
    view: Views,
    toggleView: (Views) -> Unit,
    onCategoryClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Browse Categories",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = ClashDisplayFontFamily,
                        fontWeight = FontWeight.Medium,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFBB03B),
                                Color(0xFFEC0C43)
                            )
                        )
                    )
                )

                Text(
                    text = "Explore our curated collections of stunning wallpapers",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF575757)
                    )
                )
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .size(50.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // Grid View Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8F0E1)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            if (view != Views.GRID_VIEW) {
                                toggleView(Views.GRID_VIEW)
                            } else {
                                toggleView(Views.LIST_VIEW)
                            }
                        },
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = if (view == Views.GRID_VIEW)
                                painterResource(R.drawable.grid_view)
                            else
                                painterResource(R.drawable.list_view),
                            tint = Color(0xFFFFA821),
                            contentDescription = "Grid View"
                        )
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .size(23.dp)
            )
        }

        item {
            AnimatedContent(
                targetState = view,
                transitionSpec = {
                    val scale = if (targetState == Views.GRID_VIEW) {
                        scaleIn(
                            initialScale = 0.8f,
                            animationSpec = tween(durationMillis = 400)
                        )
                    } else {
                        scaleIn(
                            initialScale = 1.2f,
                            animationSpec = tween(durationMillis = 400)
                        )
                    }

                    val fadeIn = fadeIn(animationSpec = tween(durationMillis = 400))
                    val fadeOut = fadeOut(animationSpec = tween(durationMillis = 400))

                    (scale + fadeIn).togetherWith(
                        scaleOut(
                            targetScale = if (initialState == Views.GRID_VIEW) 1.2f else 0.8f,
                            animationSpec = tween(durationMillis = 400)
                        ) + fadeOut
                    )
                },
                label = "view_transition"
            ) { currentView ->
                Column {
                    if (currentView == Views.GRID_VIEW) {
                        categories.forEachIndexed { index, category ->
                            CategoriesCardGrid(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                category = category,
                                onCategoryClicked = { onCategoryClicked() }
                            )

                            Spacer(
                                modifier = Modifier
                                    .size(23.dp)
                            )
                        }
                    } else {
                        categories.forEachIndexed { index, category ->
                            CategoriesCardList(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                category = category,
                                onCategoryClicked = { onCategoryClicked() }
                            )

                            if (index < categories.size - 1) {
                                Spacer(
                                    modifier = Modifier
                                        .size(20.dp)
                                )

                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    thickness = 1.dp,
                                    color = Color(0xFF0000001A).copy(alpha = 0.1f)
                                )

                                Spacer(
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .size(23.dp)
            )

        }
    }
}