package com.example.noteapplication.feature_note.domain.usecase

import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id:Int):Note?{
        return repository.getNoteById(id)
    }
}