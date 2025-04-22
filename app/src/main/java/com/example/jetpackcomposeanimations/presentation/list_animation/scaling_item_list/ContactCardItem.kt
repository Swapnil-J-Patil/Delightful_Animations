package com.example.jetpackcomposeanimations.presentation.list_animation.scaling_item_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.green

@Composable
fun ContactCardItem(
    name: String,
    modifier: Modifier = Modifier,
    onCardClicked:()->Unit,
) {
    Card(
        modifier = modifier.clickable {
            onCardClicked()
        }
            .padding(top=10.dp)
            ,
        colors = CardDefaults.cardColors(containerColor = Color.White), // Set the background color
        elevation = CardDefaults.cardElevation(4.dp), // Add elevation for shadow
        shape = RoundedCornerShape(8.dp) // Rounded corners
    ){
        Row(
            modifier = modifier.background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Profile Icon
            Box(
                modifier = Modifier
                    .size(40.dp) // Adjust size as needed
                    .clip(CircleShape)
                    .background(green.copy(alpha = 0.2f)), // Background color for the icon
                contentAlignment = Alignment.Center // Center the icon inside the box
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "FilterCoins",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text

            // Text Column
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
            }
        }
    }

}