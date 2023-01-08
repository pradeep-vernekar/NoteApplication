package com.example.noteapplication.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapplication.feature_note.domain.utils.NoteOrder
import com.example.noteapplication.feature_note.domain.utils.OrderBy

@Preview
@Composable
fun OrderSection(
    modifier:Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderBy.Ascending),
    onOrderChange : (NoteOrder) -> Unit = {}
){
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Title",
                selected = noteOrder is NoteOrder.Title,
                onSelected = {
                    onOrderChange(NoteOrder.Title(noteOrder.orderBy))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Color",
                selected = noteOrder is NoteOrder.Color,
                onSelected = {
                    onOrderChange(NoteOrder.Color(noteOrder.orderBy))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Date",
                selected = noteOrder is NoteOrder.Date,
                onSelected = {
                    onOrderChange(NoteOrder.Date(noteOrder.orderBy))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Ascending",
                selected = noteOrder.orderBy is OrderBy.Ascending,
                onSelected = {
                    onOrderChange(noteOrder.copy(OrderBy.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Descending",
                selected = noteOrder.orderBy is OrderBy.Descending,
                onSelected = {
                    onOrderChange(noteOrder.copy(OrderBy.Descending))
                }
            )
        }
    }

}