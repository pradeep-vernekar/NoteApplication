package com.example.noteapplication.feature_note.domain.usecase

import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note:Note){
        repository.deleteNote(note)
    }
}