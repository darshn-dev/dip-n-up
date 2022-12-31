package dev.darshn.dipnup.presentation.companylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.darshn.dipnup.composeutil.AppColorPalette
import dev.darshn.dipnup.domain.model.CompanyListing

@Composable
fun CompanyItem(company: CompanyListing, modifier: Modifier){
    Row(modifier = modifier.padding(16.dp).fillMaxWidth().background(AppColorPalette.cardBackgroundColor)) {
        Column() {
            Text(text = company.name)
            Text(text =  company.symbol)
        }

        Text(text = company.exchange)
    }

}


//
//@Preview(showSystemUi = true)
//@Composable
//fun renderPreview(){
//    CompanyItem(CompanyListing("","",""))
//}