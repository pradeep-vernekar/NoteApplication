package com.example.noteapplication.feature_note.domain.usecase

data class NoteUsaCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase : GetNoteUseCase
)
