package com.example.myfybroreport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfybroreport.ui.theme.MyFybroReportTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFybroReportTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    pickDuration()
                }
            }
        }
    }
}

@Composable
fun pickDuration() {

    val mExpanded =   remember { mutableStateOf(false) }

    // Create a string value to store the selected city
    val mSelectedText = remember { mutableStateOf("") }

    val mTextFieldSize = remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        OutlinedTextField(
            value = mSelectedText.value,
            onValueChange = { mSelectedText.value = it },
            modifier = Modifier
                .fillMaxWidth().wrapContentHeight().padding(12.dp)
                /*.onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize.value = coordinates.size.toSize()
                }*/,
            label = {Text(text = stringResource(id = R.string.txt_select_duration))},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded.value = !mExpanded.value })
            }
        )
        DropdownMenu(expanded = mExpanded.value, onDismissRequest = { mExpanded.value = false }, modifier =Modifier.wrapContentWidth()) {
            stringArrayResource(id = R.array.duration).forEach { label->
                DropdownMenuItem(onClick = { mSelectedText.value = label
                    mExpanded.value = false}) {
                    Text(text = label)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFybroReportTheme {
        pickDuration()
    }
}