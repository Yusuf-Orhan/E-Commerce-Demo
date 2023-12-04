package com.yusuforhan.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.yusuforhan.composetest.ui.theme.ComposeTestTheme
import com.yusuforhan.composetest.ui.theme.DarkBlue
import com.yusuforhan.composetest.ui.theme.HalfGrey
import com.yusuforhan.composetest.ui.theme.TitleColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val categoryList = arrayListOf<CategoryModel>()
    categoryList.add(CategoryModel(Color(0XFFE4F3EA).toArgb(),R.drawable.ic_foods,"Foods"))
    categoryList.add(CategoryModel(Color(0XFFFFECE8).toArgb(),R.drawable.ic_gift,"Gift"))
    categoryList.add(CategoryModel(Color(0XFFFFF6E4).toArgb(),R.drawable.ic_fashion,"Fashion"))
    categoryList.add(CategoryModel(Color(0XFFF1EDFC).toArgb(),R.drawable.ic_gadget,"Gadget"))
    categoryList.add(CategoryModel(Color(0XFFE4F3EA).toArgb(),R.drawable.ic_compute,"Compute"))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
                title = {
                    Text(
                        text = "Mega Mall",
                        fontFamily = FontFamily(Font(R.font.dmsans)),
                        fontSize = 18.sp,
                        color = TitleColor
                    )
                },
                actions = {
                    Row(
                        modifier = modifier.padding(end = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "Notification",
                            tint = Color.Black,
                            modifier = modifier
                                .size(28.dp)
                                .padding(5.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shopping_cart),
                            contentDescription = "Shopping Cart",
                            tint = Color.Black,
                            modifier = modifier
                                .size(28.dp)
                                .padding(5.dp)
                        )
                    }

                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.White)
        ) {
            SearchBar()
            LazyRow(content = {
                item {
                    Image(painter = painterResource(id = R.drawable.ic_image1), contentDescription = null,modifier = modifier
                        .width(315.dp)
                        .height(164.dp)
                        .padding(5.dp))
                    Image(painter = painterResource(id = R.drawable.ic_iamge2), contentDescription = null,modifier = modifier
                        .width(315.dp)
                        .height(164.dp)
                        .padding(5.dp))
                }
            })
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Categories", fontFamily = FontFamily(Font(R.font.dmsans)),color = DarkBlue, fontSize = 16.sp, modifier = modifier.padding(15.dp))
                Text(text = "See all", fontFamily = FontFamily(Font(R.font.dmsans)),color = TitleColor, fontSize = 12.sp, modifier = modifier.padding(top = 20.dp, end = 15.dp))

            }
            LazyRow{

                items(categoryList.size) {
                    val model = categoryList[it]
                    Column {
                        Card(modifier = modifier
                            .size(88.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color(model.backgroundColor)),

                            ){
                            Box (
                                modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                Column {
                                    Icon(painter = painterResource(id = model.icon), contentDescription = null)
                                }

                            }
                        }
                        Text(text = model.title,modifier = modifier.width(88.dp), textAlign = TextAlign.Center, fontSize = 14.sp)
                    }

                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    val searchQuery = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = { s -> searchQuery.value = s },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = {Text(text = "Search Products",color = Color.White)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = HalfGrey,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    )
}