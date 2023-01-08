package com.example.noteapplication.feature_note.data.repository

import com.example.noteapplication.feature_note.data.data_source.NoteDao
import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun addNote(note: Note) {
       noteDao.addNote(note = note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(node = note)
    }
}