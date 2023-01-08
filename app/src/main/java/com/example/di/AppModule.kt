package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.noteapplication.feature_note.data.data_source.NoteDatabase
import com.example.noteapplication.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteapplication.feature_note.domain.repository.NoteRepository
import com.example.noteapplication.feature_note.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,NoteDatabase::class.java,NoteDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository:NoteRepository):NoteUsaCases{
        return NoteUsaCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }

}