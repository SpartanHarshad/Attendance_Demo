package com.harshad.attendanceapp.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ReportEntity::class], version = 1)
abstract class AttendanceDb : RoomDatabase() {

    abstract fun getAttendanceDao(): AttendanceReportDao

    companion object {
        private var INSTANCE: AttendanceDb? = null

        fun getDatabaseInstance(context: Context): AttendanceDb {
            return if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceDb::class.java,
                    "AttendanceRepo"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }
}