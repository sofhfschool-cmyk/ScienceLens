package com.example.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "demo_requests")
data class DemoRequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contactName: String,
    val roleTitle: String,
    val schoolName: String,
    val board: String,
    val classesSelected: String,
    val preferredDelivery: String,
    val planInterest: String,
    val contactEmailOrPhone: String,
    val notes: String,
    val submittedAtMillis: Long = System.currentTimeMillis()
)

@Entity(tableName = "question_attempts")
data class QuestionAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questionId: String,
    val selectedOptionLetter: String,
    val isCorrect: Boolean,
    val diagnosticSummary: String,
    val timestampMillis: Long = System.currentTimeMillis()
)

@Entity(tableName = "intervention_actions")
data class InterventionActionEntity(
    @PrimaryKey val stepKey: String,
    val isCompleted: Boolean,
    val updatedAtMillis: Long = System.currentTimeMillis()
)

@Entity(tableName = "ai_draft_questions")
data class AiDraftQuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val className: String,
    val board: String,
    val chapter: String,
    val topic: String,
    val competency: String,
    val taxonomy: String,
    val estimatedDifficulty: String,
    val questionStem: String,
    val correctOption: String,
    val targetMisconception: String,
    val reviewStatus: String, // "PENDING_EDUCATOR_REVIEW", "APPROVED_FOR_BANK", "REVISION_REQUESTED"
    val reviewerNote: String,
    val createdAtMillis: Long = System.currentTimeMillis()
)

@Dao
interface ScienceLensDao {
    @Query("SELECT * FROM demo_requests ORDER BY submittedAtMillis DESC")
    fun getAllDemoRequests(): Flow<List<DemoRequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDemoRequest(request: DemoRequestEntity)

    @Query("SELECT * FROM question_attempts ORDER BY timestampMillis DESC")
    fun getAllQuestionAttempts(): Flow<List<QuestionAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionAttempt(attempt: QuestionAttemptEntity)

    @Query("DELETE FROM question_attempts")
    suspend fun clearQuestionAttempts()

    @Query("SELECT * FROM intervention_actions")
    fun getInterventionActions(): Flow<List<InterventionActionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertInterventionAction(action: InterventionActionEntity)

    @Query("SELECT * FROM ai_draft_questions ORDER BY createdAtMillis DESC")
    fun getAllAiDraftQuestions(): Flow<List<AiDraftQuestionEntity>>

    @Query("SELECT COUNT(*) FROM ai_draft_questions")
    suspend fun getAiDraftCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAiDraftQuestion(draft: AiDraftQuestionEntity)

    @Query("UPDATE ai_draft_questions SET reviewStatus = :status, reviewerNote = :note WHERE id = :id")
    suspend fun updateAiDraftReviewStatus(id: Int, status: String, note: String)
}

@Database(
    entities = [
        DemoRequestEntity::class,
        QuestionAttemptEntity::class,
        InterventionActionEntity::class,
        AiDraftQuestionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ScienceLensDatabase : RoomDatabase() {
    abstract fun dao(): ScienceLensDao

    companion object {
        @Volatile
        private var INSTANCE: ScienceLensDatabase? = null

        fun getInstance(context: Context): ScienceLensDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScienceLensDatabase::class.java,
                    "sciencelens_platform.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class ScienceLensRepository(private val dao: ScienceLensDao) {
    val demoRequests: Flow<List<DemoRequestEntity>> = dao.getAllDemoRequests()
    val questionAttempts: Flow<List<QuestionAttemptEntity>> = dao.getAllQuestionAttempts()
    val interventionActions: Flow<List<InterventionActionEntity>> = dao.getInterventionActions()
    val aiDraftQuestions: Flow<List<AiDraftQuestionEntity>> = dao.getAllAiDraftQuestions()

    suspend fun submitDemoRequest(request: DemoRequestEntity) {
        dao.insertDemoRequest(request)
    }

    suspend fun recordQuestionAttempt(
        questionId: String,
        selectedOptionLetter: String,
        isCorrect: Boolean,
        diagnosticSummary: String
    ) {
        dao.insertQuestionAttempt(
            QuestionAttemptEntity(
                questionId = questionId,
                selectedOptionLetter = selectedOptionLetter,
                isCorrect = isCorrect,
                diagnosticSummary = diagnosticSummary
            )
        )
    }

    suspend fun resetQuestionAttempts() {
        dao.clearQuestionAttempts()
    }

    suspend fun toggleInterventionStep(stepKey: String, completed: Boolean) {
        dao.upsertInterventionAction(
            InterventionActionEntity(
                stepKey = stepKey,
                isCompleted = completed
            )
        )
    }

    suspend fun seedInitialAiDraftsIfNeeded() {
        if (dao.getAiDraftCount() == 0) {
            ScienceLensSeedData.initialAiDrafts.forEach { draft ->
                dao.insertAiDraftQuestion(draft)
            }
        }
    }

    suspend fun createAiDraft(draft: AiDraftQuestionEntity) {
        dao.insertAiDraftQuestion(draft)
    }

    suspend fun reviewAiDraft(id: Int, status: String, note: String) {
        dao.updateAiDraftReviewStatus(id, status, note)
    }
}
