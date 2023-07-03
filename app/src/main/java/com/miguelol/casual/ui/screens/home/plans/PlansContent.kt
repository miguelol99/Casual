package com.miguelol.casual.ui.screens.home.plans

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.R
import com.miguelol.casual.domain.model.Plan
import com.miguelol.casual.ui.theme.CasualTheme

@Composable
fun PlansContent(
    plans: List<Plan>,
    modifier: Modifier = Modifier,
    filterType: String = "all",

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        LazyColumn {
            items(plans) { plan ->
                PlanItem(plan = plan)
            }
        }
    }

}

@Composable
fun PlanItem(
    plan: Plan
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.paisaje),
                contentDescription = ""
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Column() {
                    Text(
                        text = plan.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(13.dp),
                            painter = painterResource(id = R.drawable.map_pin),
                            contentDescription = "",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = plan.location,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Gray
                        )
                    }

                }
                Column() {

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPlanItem() {
    CasualTheme {
        PlanItem(plan = Plan(name = "Unas cañitas con los panas", location = "Plaza de San Miguel"))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPlansContent() {
    CasualTheme {
        PlansContent(emptyList())
    }
}