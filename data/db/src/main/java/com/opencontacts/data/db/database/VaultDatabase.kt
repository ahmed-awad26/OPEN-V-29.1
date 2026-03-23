package com.opencontacts.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.opencontacts.data.db.dao.ContactsDao
import com.opencontacts.data.db.entity.BackupRecordEntity
import com.opencontacts.data.db.entity.ContactEntity
import com.opencontacts.data.db.entity.ContactTagCrossRef
import com.opencontacts.data.db.entity.FolderEntity
import com.opencontacts.data.db.entity.ImportExportHistoryEntity
import com.opencontacts.data.db.entity.NoteEntity
import com.opencontacts.data.db.entity.ReminderEntity
import com.opencontacts.data.db.entity.TagEntity
import com.opencontacts.data.db.entity.TimelineEntity

@Database(
    entities = [
        ContactEntity::class,
        NoteEntity::class,
        ReminderEntity::class,
        TimelineEntity::class,
        BackupRecordEntity::class,
        ImportExportHistoryEntity::class,
        TagEntity::class,
        FolderEntity::class,
        ContactTagCrossRef::class,
    ],
    version = 6,
    exportSchema = true,
)
abstract class VaultDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}

val VAULT_DB_MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE folders ADD COLUMN image_uri TEXT")
        database.execSQL("ALTER TABLE folders ADD COLUMN description TEXT")
        database.execSQL("ALTER TABLE folders ADD COLUMN sort_order INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE folders ADD COLUMN is_pinned INTEGER NOT NULL DEFAULT 0")
    }
}
