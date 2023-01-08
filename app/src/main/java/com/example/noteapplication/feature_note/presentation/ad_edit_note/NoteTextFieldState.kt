package com.example.noteapplication.feature_note.presentation.ad_edit_note

data class NoteTextFieldState(
    val text:String = "",
    val hintText:String = "",
    val isHintVisible:Boolean = false
)