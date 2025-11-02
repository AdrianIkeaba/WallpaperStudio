package com.ghostdev.wallpaperstudio.ui.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostdev.wallpaperstudio.R
import com.ghostdev.wallpaperstudio.data.models.Category
import com.ghostdev.wallpaperstudio.data.models.Favorite
import com.ghostdev.wallpaperstudio.data.prefs.ActiveWallpaperPrefs
import com.ghostdev.wallpaperstudio.ui.components.CategoriesCardGrid
import com.ghostdev.wallpaperstudio.ui.screens.homepage.components.ActiveWallpaperComponent
import com.ghostdev.wallpaperstudio.ui.theme.ClashDisplayFontFamily
import com.ghostdev.wallpaperstudio.ui.theme.PoppinsFontFamily
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navigateToCategories: () -> Unit
) {
    val prefs = koinInject<ActiveWallpaperPrefs>()
    val activeWallpaperId = prefs.activeWallpaperId.value

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

    val wallpapers = rememberSaveable {
        mutableStateListOf(
            Favorite("nature_1", "Nature 1", "Nature", R.drawable.nature_1),
            Favorite("nature_2", "Nature 2", "Nature", R.drawable.nature_2),
            Favorite("nature_3", "Nature 3", "Nature", R.drawable.nature_3),
            Favorite("nature_4", "Nature 4", "Nature", R.drawable.nature_4),
            Favorite("nature_5", "Nature 5", "Nature", R.drawable.nature_5),
            Favorite("nature_6", "Nature 6", "Nature", R.drawable.nature_6)
        )
    }

    HomeComponent(
        activeWallpaper = wallpapers.find { it.id == activeWallpaperId },
        categories = categories,
        onCategoryClicked = { navigateToCategories() }
    )
}

@Composable
private fun HomeComponent(
    activeWallpaper: Favorite?,
    categories: List<Category>,
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
            if (activeWallpaper == null) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Discover Beautiful Wallpapers",
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
                        text = "Discover curated collections of stunning wallpapers. Browse by category, preview in full-screen, and set your favorites.",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF575757)
                        )
                    )
                }
            } else {
                ActiveWallpaperComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    wallpaperImage = activeWallpaper.image,
                    category = activeWallpaper.category,
                    selection = activeWallpaper.title
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
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color(0xFFF8F8F8)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Categories",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )

                Text(
                    text = "See All",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF808080)
                    )
                )

            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .size(23.dp)
            )
        }

        items(categories.size, key = { index -> categories[index].title}) { index ->
            CategoriesCardGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                category = categories[index],
                onCategoryClicked = { onCategoryClicked() }
            )

            Spacer(
                modifier = Modifier
                    .size(23.dp)
            )
        }
    }
}