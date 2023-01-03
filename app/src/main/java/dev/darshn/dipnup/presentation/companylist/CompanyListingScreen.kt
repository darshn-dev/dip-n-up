package dev.darshn.dipnup.presentation.companylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination(start = true)
fun CompanyListScreen(
    navigator: DestinationsNavigator,
    viewModel: CompanyListViewModel = hiltViewModel()
) {
    var swipeRefresh = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)

    val state = viewModel.state

    Column() {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(CompanyListingEvents.OnSearchQueryChange(it))
            },
            modifier = Modifier.padding(8.dp),
            placeholder = {
                Text(text = "Searching")
            }
        )


        SwipeRefresh(
            state = swipeRefresh,
            onRefresh = { viewModel.onEvent(CompanyListingEvents.Refresh) }) {

            LazyColumn(modifier = Modifier.padding(8.dp)){
                items(state.companyList.size){ i ->

                    val company = state.companyList[i]
                    CompanyItem(company = company, modifier = Modifier.clickable {
                        //TODO goto detail screen
                    })



                    if(i<state.companyList.size)
                        Divider(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

}