package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AiDraftQuestionEntity
import com.example.data.CoreCompetency
import com.example.data.DemoRequestEntity
import com.example.data.InterventionActionEntity
import com.example.data.QuestionAttemptEntity
import com.example.data.ScienceLensDatabase
import com.example.data.ScienceLensRepository
import com.example.data.ScienceLensSeedData
import com.example.data.ScienceQuestion
import com.example.data.UserRole
import com.example.data.WebsitePage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class DashboardPreviewTab(val label: String) {
    TEACHER("Teacher (VIII-A)"),
    PRINCIPAL("Principal"),
    STUDENT("Student Report"),
    PARENT("Parent Report")
}

data class ScienceLensUiState(
    val currentPage: WebsitePage = WebsitePage.HOME,
    val pageHistory: List<WebsitePage> = emptyList(),
    val activeRole: UserRole = UserRole.PRINCIPAL,
    val activeDashboardTab: DashboardPreviewTab = DashboardPreviewTab.TEACHER,
    val activeLearningCycleStep: Int = 2, // Default highlight: 2 -> Diagnose
    val selectedQuestionIndex: Int = 0, // 0 is the featured Nail Force & Pressure question
    val selectedOptionByQuestionId: Map<String, String> = mapOf("SL-VIII-FP-01" to "C"), // Pre-show how a misconception or answer is analyzed, user can tap A/B/C/D
    val selectedMisconceptionCaseIndex: Int = 0,
    val selectedClassFilter: String = "All Classes (VI–X)",
    val selectedBoardFilter: String = "All Boards",
    val selectedCompetencyFilter: CoreCompetency? = null,
    val selectedPricingPlanForDemo: String = "School Plan (Classes VI–X)",
    val lastSubmittedSchoolName: String? = null,
    val isDarkThemeOverride: Boolean? = null
)

class ScienceLensViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ScienceLensRepository

    private val _uiState = MutableStateFlow(ScienceLensUiState())
    val uiState: StateFlow<ScienceLensUiState> = _uiState.asStateFlow()

    val demoRequests: StateFlow<List<DemoRequestEntity>>
    val questionAttempts: StateFlow<List<QuestionAttemptEntity>>
    val interventionActions: StateFlow<List<InterventionActionEntity>>
    val aiDraftQuestions: StateFlow<List<AiDraftQuestionEntity>>

    init {
        val db = ScienceLensDatabase.getInstance(application)
        repository = ScienceLensRepository(db.dao())

        demoRequests = repository.demoRequests.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        questionAttempts = repository.questionAttempts.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        interventionActions = repository.interventionActions.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        aiDraftQuestions = repository.aiDraftQuestions.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        viewModelScope.launch {
            repository.seedInitialAiDraftsIfNeeded()
        }
    }

    fun navigateToPage(page: WebsitePage, prefillPlan: String? = null) {
        _uiState.update { state ->
            val updatedHistory = if (state.currentPage != page) {
                (state.pageHistory + state.currentPage).takeLast(15)
            } else {
                state.pageHistory
            }
            state.copy(
                currentPage = page,
                pageHistory = updatedHistory,
                selectedPricingPlanForDemo = prefillPlan ?: state.selectedPricingPlanForDemo
            )
        }
    }

    fun navigateBack(): Boolean {
        val currentState = _uiState.value
        if (currentState.pageHistory.isNotEmpty()) {
            val previous = currentState.pageHistory.last()
            _uiState.update {
                it.copy(
                    currentPage = previous,
                    pageHistory = it.pageHistory.dropLast(1)
                )
            }
            return true
        } else if (currentState.currentPage != WebsitePage.HOME) {
            _uiState.update { it.copy(currentPage = WebsitePage.HOME) }
            return true
        }
        return false
    }

    fun selectRole(role: UserRole) {
        val matchingTab = when (role) {
            UserRole.PRINCIPAL, UserRole.SCHOOL_ADMIN, UserRole.SUPER_ADMIN -> DashboardPreviewTab.PRINCIPAL
            UserRole.TEACHER -> DashboardPreviewTab.TEACHER
            UserRole.STUDENT -> DashboardPreviewTab.STUDENT
            UserRole.PARENT -> DashboardPreviewTab.PARENT
        }
        _uiState.update {
            it.copy(
                activeRole = role,
                activeDashboardTab = matchingTab
            )
        }
    }

    fun selectDashboardTab(tab: DashboardPreviewTab) {
        _uiState.update { it.copy(activeDashboardTab = tab) }
    }

    fun selectLearningCycleStep(stepIndex: Int) {
        _uiState.update { it.copy(activeLearningCycleStep = stepIndex) }
    }

    fun selectSampleQuestion(index: Int) {
        _uiState.update { it.copy(selectedQuestionIndex = index) }
    }

    fun selectAnswerOption(question: ScienceQuestion, optionLetter: String) {
        _uiState.update { state ->
            state.copy(
                selectedOptionByQuestionId = state.selectedOptionByQuestionId + (question.id to optionLetter)
            )
        }
        val chosen = question.options.find { it.letter == optionLetter } ?: return
        viewModelScope.launch {
            repository.recordQuestionAttempt(
                questionId = question.id,
                selectedOptionLetter = optionLetter,
                isCorrect = chosen.isCorrect,
                diagnosticSummary = if (chosen.isCorrect) {
                    "Demonstrated ${question.competency.title} on ${question.topic}"
                } else {
                    chosen.linkedMisconceptionTag ?: question.likelyMisconception
                }
            )
        }
    }

    fun clearAttemptHistory() {
        viewModelScope.launch {
            repository.resetQuestionAttempts()
        }
    }

    fun selectMisconceptionCase(index: Int) {
        _uiState.update { it.copy(selectedMisconceptionCaseIndex = index) }
    }

    fun setClassFilter(classFilter: String) {
        _uiState.update { it.copy(selectedClassFilter = classFilter) }
    }

    fun setBoardFilter(boardFilter: String) {
        _uiState.update { it.copy(selectedBoardFilter = boardFilter) }
    }

    fun setCompetencyFilter(competency: CoreCompetency?) {
        _uiState.update { it.copy(selectedCompetencyFilter = competency) }
    }

    fun toggleInterventionAction(stepKey: String, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.toggleInterventionStep(stepKey, isCompleted)
        }
    }

    fun reviewAiDraft(id: Int, status: String, reviewerNote: String) {
        viewModelScope.launch {
            repository.reviewAiDraft(id, status, reviewerNote)
        }
    }

    fun generateAiAssistedQuestionDraft(
        className: String,
        board: String,
        chapter: String,
        competency: String
    ) {
        viewModelScope.launch {
            val draft = AiDraftQuestionEntity(
                className = className,
                board = board,
                chapter = chapter,
                topic = "$chapter – Applied Context",
                competency = competency,
                taxonomy = "Concept Application",
                estimatedDifficulty = "Medium (Est. p-value: 0.55)",
                questionStem = "During a classroom investigation on $chapter ($className, $board), two groups record different outcomes when one variable is doubled. Which explanation best accounts for this difference?",
                correctOption = "Doubling the active variable changes the rate of interaction proportionally while holding control parameters constant.",
                targetMisconception = "Conflates dependent and independent variables or assumes constant output regardless of parameter change.",
                reviewStatus = "PENDING_EDUCATOR_REVIEW",
                reviewerNote = "Generated by ScienceLens AI Support Layer. Mandatory Educator Review required before publication."
            )
            repository.createAiDraft(draft)
        }
    }

    fun submitSchoolDemoRequest(
        contactName: String,
        roleTitle: String,
        schoolName: String,
        board: String,
        classesSelected: String,
        preferredDelivery: String,
        planInterest: String,
        contactEmailOrPhone: String,
        notes: String
    ) {
        viewModelScope.launch {
            repository.submitDemoRequest(
                DemoRequestEntity(
                    contactName = contactName.trim(),
                    roleTitle = roleTitle.trim(),
                    schoolName = schoolName.trim(),
                    board = board,
                    classesSelected = classesSelected,
                    preferredDelivery = preferredDelivery,
                    planInterest = planInterest,
                    contactEmailOrPhone = contactEmailOrPhone.trim(),
                    notes = notes.trim()
                )
            )
            _uiState.update { it.copy(lastSubmittedSchoolName = schoolName.trim()) }
        }
    }

    fun dismissDemoConfirmation() {
        _uiState.update { it.copy(lastSubmittedSchoolName = null) }
    }

    fun toggleThemeMode(currentlyDark: Boolean) {
        _uiState.update { it.copy(isDarkThemeOverride = !currentlyDark) }
    }
}
