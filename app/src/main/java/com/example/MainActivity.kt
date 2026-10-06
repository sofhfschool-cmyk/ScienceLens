package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.UserRole
import com.example.data.WebsitePage
import com.example.ui.ScienceLensViewModel
import com.example.ui.components.RoleBasedContextBanner
import com.example.ui.components.ScienceLensFooter
import com.example.ui.components.ScienceLensTopNavbar
import com.example.ui.screens.AboutPageScreen
import com.example.ui.screens.AssessmentFrameworkPageScreen
import com.example.ui.screens.ContactDemoPageScreen
import com.example.ui.screens.FaqPageScreen
import com.example.ui.screens.ForSchoolsPageScreen
import com.example.ui.screens.ForTeachersPageScreen
import com.example.ui.screens.HomePageScreen
import com.example.ui.screens.HowItWorksPageScreen
import com.example.ui.screens.PrivacyPolicyPageScreen
import com.example.ui.screens.SampleReportPageScreen
import com.example.ui.screens.StudentsAndParentsPageScreen
import com.example.ui.screens.TermsOfUsePageScreen
import com.example.ui.theme.DeepNavyDark
import com.example.ui.theme.OpticTealBright
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceLensDesign
import com.example.ui.theme.ScienceLensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScienceLensRoot()
        }
    }
}

@Composable
fun ScienceLensRoot(
    viewModel: ScienceLensViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val systemDark = isSystemInDarkTheme()
    val effectiveDarkTheme = uiState.isDarkThemeOverride ?: systemDark

    ScienceLensTheme(darkTheme = effectiveDarkTheme) {
        ScienceLensApp(
            viewModel = viewModel,
            isDarkTheme = effectiveDarkTheme,
            onToggleTheme = { viewModel.toggleThemeMode(effectiveDarkTheme) }
        )
    }
}

@Composable
fun ScienceLensApp(
    viewModel: ScienceLensViewModel = viewModel(),
    isDarkTheme: Boolean = false,
    onToggleTheme: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val demoRequests by viewModel.demoRequests.collectAsStateWithLifecycle()
    val questionAttempts by viewModel.questionAttempts.collectAsStateWithLifecycle()
    val interventionActions by viewModel.interventionActions.collectAsStateWithLifecycle()
    val aiDraftQuestions by viewModel.aiDraftQuestions.collectAsStateWithLifecycle()

    var showDirectoryDialog by remember { mutableStateOf(false) }
    var showRoleDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val extColors = ScienceLensDesign.colors

    LaunchedEffect(uiState.currentPage) {
        scrollState.scrollTo(0)
    }

    BackHandler(enabled = uiState.currentPage != WebsitePage.HOME || uiState.pageHistory.isNotEmpty()) {
        viewModel.navigateBack()
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            ScienceLensTopNavbar(
                currentPage = uiState.currentPage,
                activeRole = uiState.activeRole,
                isDarkTheme = isDarkTheme,
                canNavigateBack = uiState.currentPage != WebsitePage.HOME || uiState.pageHistory.isNotEmpty(),
                onNavigateBack = { viewModel.navigateBack() },
                onToggleTheme = onToggleTheme,
                onSelectPage = { page -> viewModel.navigateToPage(page) },
                onOpenDirectoryDrawer = { showDirectoryDialog = true },
                onOpenRoleSelector = { showRoleDialog = true }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = DeepNavyDark,
                contentColor = PureWhite,
                modifier = Modifier.testTag("bottom_navigation_bar")
            ) {
                val primaryDestinations = listOf(
                    Triple(WebsitePage.HOME, "Home", Icons.Default.Home),
                    Triple(WebsitePage.HOW_IT_WORKS, "Workflow", Icons.Default.Layers),
                    Triple(WebsitePage.ASSESSMENT_FRAMEWORK, "Framework", Icons.Default.School),
                    Triple(WebsitePage.SAMPLE_REPORT, "Reports", Icons.Default.Analytics),
                    Triple(WebsitePage.CONTACT_DEMO, "Demo", Icons.Default.Send)
                )
                primaryDestinations.forEach { (page, label, icon) ->
                    val selected = uiState.currentPage == page
                    NavigationBarItem(
                        selected = selected,
                        onClick = { viewModel.navigateToPage(page) },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = label
                            )
                        },
                        label = {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                maxLines = 1
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PureWhite,
                            selectedTextColor = OpticTealBright,
                            indicatorColor = ScienceBlue,
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8)
                        ),
                        modifier = Modifier.testTag("bottom_nav_${page.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 900.dp)
                    .verticalScroll(scrollState)
            ) {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                    RoleBasedContextBanner(
                        activeRole = uiState.activeRole,
                        onSelectRole = { role -> viewModel.selectRole(role) },
                        onJumpToRoleDestination = { role ->
                            viewModel.selectRole(role)
                            when (role) {
                                UserRole.PRINCIPAL, UserRole.SCHOOL_ADMIN -> viewModel.navigateToPage(WebsitePage.FOR_SCHOOLS)
                                UserRole.TEACHER -> viewModel.navigateToPage(WebsitePage.FOR_TEACHERS)
                                UserRole.STUDENT, UserRole.PARENT -> viewModel.navigateToPage(WebsitePage.STUDENTS_PARENTS)
                                UserRole.SUPER_ADMIN -> viewModel.navigateToPage(WebsitePage.ASSESSMENT_FRAMEWORK)
                            }
                        }
                    )
                }

                when (uiState.currentPage) {
                    WebsitePage.HOME -> HomePageScreen(
                        uiState = uiState,
                        recentAttempts = questionAttempts,
                        interventionActions = interventionActions,
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) },
                        onSelectLearningCycleStep = { viewModel.selectLearningCycleStep(it) },
                        onSelectQuestion = { viewModel.selectSampleQuestion(it) },
                        onSelectOption = { q, opt -> viewModel.selectAnswerOption(q, opt) },
                        onClearAttempts = { viewModel.clearAttemptHistory() },
                        onSelectMisconceptionCase = { viewModel.selectMisconceptionCase(it) },
                        onSelectDashboardTab = { viewModel.selectDashboardTab(it) },
                        onToggleInterventionStep = { key, done -> viewModel.toggleInterventionAction(key, done) }
                    )

                    WebsitePage.HOW_IT_WORKS -> HowItWorksPageScreen(
                        activeStep = uiState.activeLearningCycleStep,
                        onSelectStep = { viewModel.selectLearningCycleStep(it) },
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.ASSESSMENT_FRAMEWORK -> AssessmentFrameworkPageScreen(
                        aiDrafts = aiDraftQuestions,
                        onGenerateAiDraft = { cls, brd, ch, comp ->
                            viewModel.generateAiAssistedQuestionDraft(cls, brd, ch, comp)
                        },
                        onReviewAiDraft = { id, status, note ->
                            viewModel.reviewAiDraft(id, status, note)
                        },
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.FOR_SCHOOLS -> ForSchoolsPageScreen(
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.FOR_TEACHERS -> ForTeachersPageScreen(
                        uiState = uiState,
                        interventionActions = interventionActions,
                        onSelectMisconceptionCase = { viewModel.selectMisconceptionCase(it) },
                        onToggleInterventionStep = { key, done -> viewModel.toggleInterventionAction(key, done) },
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.STUDENTS_PARENTS -> StudentsAndParentsPageScreen(
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.SAMPLE_REPORT -> SampleReportPageScreen(
                        uiState = uiState,
                        recentAttempts = questionAttempts,
                        interventionActions = interventionActions,
                        onSelectDashboardTab = { viewModel.selectDashboardTab(it) },
                        onSelectQuestion = { viewModel.selectSampleQuestion(it) },
                        onSelectOption = { q, opt -> viewModel.selectAnswerOption(q, opt) },
                        onClearAttempts = { viewModel.clearAttemptHistory() },
                        onToggleInterventionStep = { key, done -> viewModel.toggleInterventionAction(key, done) },
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.ABOUT -> AboutPageScreen(
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.CONTACT_DEMO -> ContactDemoPageScreen(
                        defaultPlan = uiState.selectedPricingPlanForDemo,
                        lastSubmittedSchool = uiState.lastSubmittedSchoolName,
                        savedRequests = demoRequests,
                        onSubmitDemoRequest = { name, role, school, board, classes, delivery, plan, contact, notes ->
                            viewModel.submitSchoolDemoRequest(
                                contactName = name,
                                roleTitle = role,
                                schoolName = school,
                                board = board,
                                classesSelected = classes,
                                preferredDelivery = delivery,
                                planInterest = plan,
                                contactEmailOrPhone = contact,
                                notes = notes
                            )
                        },
                        onDismissConfirmation = { viewModel.dismissDemoConfirmation() }
                    )

                    WebsitePage.FAQ -> FaqPageScreen(
                        onNavigateToPage = { page, plan -> viewModel.navigateToPage(page, plan) }
                    )

                    WebsitePage.PRIVACY -> PrivacyPolicyPageScreen()

                    WebsitePage.TERMS -> TermsOfUsePageScreen()
                }

                Spacer(modifier = Modifier.height(20.dp))

                ScienceLensFooter(
                    onSelectPage = { page -> viewModel.navigateToPage(page) }
                )
            }
        }
    }

    if (showDirectoryDialog) {
        AlertDialog(
            onDismissRequest = { showDirectoryDialog = false },
            containerColor = extColors.cardBackground,
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Website Pages (12)",
                        style = MaterialTheme.typography.titleLarge,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { showDirectoryDialog = false }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close menu",
                            tint = extColors.textPrimary
                        )
                    }
                }
            },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WebsitePage.entries.forEach { page ->
                        val active = uiState.currentPage == page
                        Surface(
                            color = if (active) ScienceBlue else extColors.subtleSurface,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, if (active) ScienceBlue else extColors.cardBorder, RoundedCornerShape(10.dp))
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    viewModel.navigateToPage(page)
                                    showDirectoryDialog = false
                                }
                                .testTag("directory_item_${page.name.lowercase()}")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = page.title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (active) PureWhite else extColors.textPrimary,
                                    fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = page.category,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (active) OpticTealBright else extColors.primaryAccent,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDirectoryDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    if (showRoleDialog) {
        AlertDialog(
            onDismissRequest = { showRoleDialog = false },
            containerColor = extColors.cardBackground,
            title = {
                Text(
                    text = "Select Role-Based Access View",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    UserRole.entries.forEach { role ->
                        val selected = role == uiState.activeRole
                        Surface(
                            color = if (selected) ScienceBlue else extColors.subtleSurface,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, if (selected) ScienceBlue else extColors.cardBorder, RoundedCornerShape(10.dp))
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    viewModel.selectRole(role)
                                    showRoleDialog = false
                                }
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "${role.label} — ${role.badgeTitle}",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (selected) PureWhite else extColors.textPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = role.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (selected) Color(0xFFE2E8F0) else extColors.textSecondary
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showRoleDialog = false }) {
                    Text("Done")
                }
            }
        )
    }
}
