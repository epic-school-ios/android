package com.example.epicdatabaseexample.db.note

import android.provider.ContactsContract
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNote(note: NoteEntity): Completable

    @Query("SELECT * FROM note_table")
    abstract fun observeNoteList(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id = :noteId")
    abstract fun getNote(noteId: Int): Maybe<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id = :noteId")
    abstract fun getNoteWithPerson(noteId: Int): Maybe<NoteWithPerson>

    @Query("DELETE FROM note_table WHERE id = :noteId")
    abstract fun deleteNote(noteId: Int): Completable

    @Query("DELETE FROM note_table")
    abstract fun deleteAllNote(): Completable

    @Update
    abstract fun updateNote(note: NoteEntity) : Completable

    // TODO Добавить метод для обновления одного элемента. ✓
    //  Что можно указать в качестве возвращаемого типа?
    //  Напомню, что выше есть метод observeNoteList, который будет вызываться при любом
    //  изменении списка в Базе Данных.
}