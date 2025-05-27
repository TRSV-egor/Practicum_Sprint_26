package com.example.practicum_sprint_26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactDetailsFull()
        }
    }
}

fun getInitials(fn: String, ln: String): String {
    return "${fn.take(1)}${ln.take(1)}"
}

@Composable
fun Placeholder(initials: String) {
    Box(
        modifier = Modifier.padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            tint = Color.Gray
        )
        Text(
            text = initials
        )
    }
}

@Composable
fun ContactImage(resId: Int) {
    Image(
        modifier = Modifier.fillMaxWidth(),
        alignment = Alignment.Center,
        painter = painterResource(id = resId),
        contentDescription = null
    )
}

@Composable
fun ContactName(
    fn: String,
    ln: String,
    sn: String,
    fav: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$fn $sn",
            style = MaterialTheme.typography.headlineSmall
        )
        Row {
            Text(
                text = ln,
                style = MaterialTheme.typography.headlineLarge
            )
            if (fav) {
                Image(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null,
                )
            }

        }

    }
}

@Composable
fun InfoRow(key: String, value: String) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            modifier = Modifier.weight(0.5f),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = "$key:",
                fontStyle = FontStyle.Italic,
            )

        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = value,
            )
        }
    }
}


@Composable
fun ContactDetails(contact: Contact) {

    Column(
        Modifier.fillMaxWidth()
    ) {

        with(contact) {

            Box(
                modifier = Modifier.padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (
                    imageRes == 0 || imageRes == null
                ) {
                    Placeholder(
                        getInitials(
                            name,
                            familyName
                        )
                    )
                } else {
                    ContactImage(imageRes)
                }
            }



            ContactName(
                fn = name,
                ln = familyName,
                sn = surname ?: "",
                fav = isFavorite
            )


            if (!email.isNullOrEmpty()) InfoRow(stringResource(id = R.string.email), email)
            InfoRow(stringResource(id = R.string.phone), phone)
            InfoRow(stringResource(id = R.string.address), address)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactDetailsFull() {
    ContactDetails(
        Contact(
            name = "Кот",
            surname = "Мурлыкович",
            familyName = "Блохастов",
            isFavorite = true,
            email = "i_hate@really.ru",
            phone = "+7 4812 55 22 86",
            imageRes = R.drawable.image_01,
            address = "Россия, Смоленская обл, г. Смоленск, ул. Рыленкова, д. 404"

        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactDetailsSmall() {
    ContactDetails(
        Contact(
            name = "Хомяк",
            familyName = "Редкоедов",
            isFavorite = false,
            phone = "+7 800 555 35 35",
            address = "Италия"
        )
    )
}