package com.example.noteapplication.feature_note.presentation.notes

import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.utils.NoteOrder
import com.example.noteapplication.feature_note.domain.utils.OrderBy

data class NotesState(
    val notes:List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderBy.Descending),
    val isOrderSectionVisible:Boolean = false
)