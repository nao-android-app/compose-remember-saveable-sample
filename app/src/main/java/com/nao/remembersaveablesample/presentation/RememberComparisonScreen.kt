package com.nao.remembersaveablesample.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nao.remembersaveablesample.R
import com.nao.remembersaveablesample.ui.theme.RememberSaveableSampleTheme

@Composable
fun RememberComparisonScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.remember_comparison_title),
            style = MaterialTheme.typography.headlineSmall
        )
        RememberSection()
        RememberSaveableSection()
    }
}

@Composable
private fun RememberSection() {
    var count by remember {
        mutableIntStateOf(0)
    }

    var text by remember {
        mutableStateOf("")
    }

    SampleCard(
        title = stringResource(R.string.remember_title),
        count = count,
        text = text,
        onCountClick = {
            count++
        },
        onTextChange = {
            text = it
        },
        onClearClick = {
            count = 0
            text = ""
        }
    )
}

@Composable
private fun RememberSaveableSection() {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    var text by rememberSaveable {
        mutableStateOf("")
    }

    SampleCard(
        title = stringResource(R.string.remember_saveable_title),
        count = count,
        text = text,
        onCountClick = {
            count++
        },
        onTextChange = {
            text = it
        },
        onClearClick = {
            count = 0
            text = ""
        }
    )
}

@Composable
private fun SampleCard(
    title: String,
    count: Int,
    text: String,
    onCountClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onClearClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = onTextChange,
                label = {
                    Text(stringResource(R.string.text_state_label))
                }
            )

            Text(
                text = stringResource(
                    R.string.count,
                    count
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onCountClick
                ) {
                    Text("+1")
                }

                Button(
                    onClick = onClearClick
                ) {
                    Text(stringResource(R.string.reset_state_label))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RememberComparisonScreenPreview() {
    RememberSaveableSampleTheme {
        RememberComparisonScreen()
    }
}
