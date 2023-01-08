package com.example.noteapplication.feature_note.domain.usecase

import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()){
            throw Exception("Title of the note cannot be empty!")
        }
        if(note.content.isBlank()){
            throw Exception("Content of the note cannot be empty!")
        }
        repository.addNote(note)
    }
}