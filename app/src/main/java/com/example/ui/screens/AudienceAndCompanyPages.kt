package com.example.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.DemoRequestEntity
import com.example.data.InterventionActionEntity
import com.example.data.QuestionAttemptEntity
import com.example.data.ScienceLensSeedData
import com.example.data.ScienceQuestion
import com.example.data.WebsitePage
import com.example.ui.DashboardPreviewTab
import com.example.ui.ScienceLensUiState
import com.example.ui.components.InteractiveSampleQuestionSection
import com.example.ui.components.MisconceptionEngineSection
import com.example.ui.components.ParentReportMockupCard
import com.example.ui.components.PrincipalDashboardMockupCard
import com.example.ui.components.StudentReportMockupCard
import com.example.ui.components.TeacherDashboardMockupCard
import com.example.ui.components.UnifiedDashboardsShowcaseSection
import com.example.ui.theme.DeepNavyDark
import com.example.ui.theme.OpticTeal
import com.example.ui.theme.OpticTealBright
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceLensDesign

@Composable
fun ForSchoolsPageScreen(
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("for_schools_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "FOR PRINCIPALS & ACADEMIC COORDINATORS",
            title = "School-Wide Science Competency Visibility Across Classes VI–X",
            subtitle = "A professional school assessment platform — not a coaching app or gamified children's portal — built for data-informed academic planning."
        )

        SchoolBenefitsGridSection()

        PrincipalDashboardMockupCard(data = ScienceLensSeedData.principalDashboard)

        BoardCompatibilityAndDeliverySection()

        PricingSectionCards(
            onRequestPlanPricing = { plan -> onNavigateToPage(WebsitePage.CONTACT_DEMO, plan) }
        )
    }
}

@Composable
fun ForTeachersPageScreen(
    uiState: ScienceLensUiState,
    interventionActions: List<InterventionActionEntity>,
    onSelectMisconceptionCase: (Int) -> Unit,
    onToggleInterventionStep: (String, Boolean) -> Unit,
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("for_teachers_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "FOR SCIENCE TEACHERS",
            title = "Spend Less Time Tabulating Marks and More Time Clearing Misconceptions",
            subtitle = "ScienceLens automatically diagnoses class-wide conceptual bottlenecks and provides a structured 10-minute classroom intervention after every monthly assessment."
        )

        TeacherDashboardMockupCard(
            data = ScienceLensSeedData.teacherDashboardClass8A,
            interventionActions = interventionActions,
            onToggleInterventionStep = onToggleInterventionStep
        )

        MisconceptionEngineSection(
            cases = ScienceLensSeedData.misconceptionCases,
            selectedIndex = uiState.selectedMisconceptionCaseIndex,
            onSelectCase = onSelectMisconceptionCase
        )

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) }
        )
    }
}

@Composable
fun StudentsAndParentsPageScreen(
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("students_parents_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "FOR STUDENTS & PARENTS",
            title = "Encouraging Curiosity & Understanding Without Exam Anxiety",
            subtitle = "Instead of stressful rank tables, ScienceLens gives students clear learning targets and provides parents with simple, jargon-free guidance for support at home."
        )

        StudentReportMockupCard(data = ScienceLensSeedData.studentReport)

        ParentReportMockupCard(data = ScienceLensSeedData.parentReport)

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) }
        )
    }
}

@Composable
fun SampleReportPageScreen(
    uiState: ScienceLensUiState,
    recentAttempts: List<QuestionAttemptEntity>,
    interventionActions: List<InterventionActionEntity>,
    onSelectDashboardTab: (DashboardPreviewTab) -> Unit,
    onSelectQuestion: (Int) -> Unit,
    onSelectOption: (ScienceQuestion, String) -> Unit,
    onClearAttempts: () -> Unit,
    onToggleInterventionStep: (String, Boolean) -> Unit,
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("sample_report_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "INTERACTIVE SAMPLE REPORTS & DIAGNOSTIC DEMO",
            title = "Explore Realistic Demonstration Reports Across All Four Perspectives",
            subtitle = "All reports below use realistic demonstration data for Class VIII-A and school-wide Classes VI–X cohorts."
        )

        UnifiedDashboardsShowcaseSection(
            activeTab = uiState.activeDashboardTab,
            onSelectTab = onSelectDashboardTab,
            teacherData = ScienceLensSeedData.teacherDashboardClass8A,
            principalData = ScienceLensSeedData.principalDashboard,
            studentData = ScienceLensSeedData.studentReport,
            parentData = ScienceLensSeedData.parentReport,
            interventionActions = interventionActions,
            onToggleInterventionStep = onToggleInterventionStep
        )

        InteractiveSampleQuestionSection(
            questions = ScienceLensSeedData.sampleQuestions,
            selectedQuestionIndex = uiState.selectedQuestionIndex,
            selectedOptionByQuestionId = uiState.selectedOptionByQuestionId,
            recentAttempts = recentAttempts,
            onSelectQuestion = onSelectQuestion,
            onSelectOption = onSelectOption,
            onClearAttempts = onClearAttempts
        )

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.ASSESSMENT_FRAMEWORK, null) }
        )
    }
}

@Composable
fun AboutPageScreen(
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("about_page"),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "ABOUT SCIENCELENS",
            title = "See Beyond Marks. Measure Understanding.",
            subtitle = "ScienceLens is an institutional Science assessment and learning analytics platform built specifically for schools."
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = DeepNavyDark),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF2B3D59), RoundedCornerShape(18.dp))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "OUR CENTRAL CONVICTION",
                    style = MaterialTheme.typography.labelSmall,
                    color = OpticTealBright,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "“Marks tell you what students got right or wrong. ScienceLens helps schools understand why.”",
                    style = MaterialTheme.typography.headlineSmall,
                    color = PureWhite,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "In classrooms across Classes VI–X, students often memorize definitions, formulas, and textbook diagrams to score marks while harboring persistent misconceptions about how scientific principles operate in the real world. ScienceLens bridges the gap between monthly testing and meaningful classroom instruction.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFCBD5E1)
                )
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "What Sets ScienceLens Apart",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "• School-Focused Institutional Platform: Built for Principals, Academic Coordinators, and Science Teachers — not a coaching centre test series or gamified consumer app.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = extColors.textPrimary
                )
                Text(
                    text = "• Competency & Taxonomy Rigor: Every 30-question monthly assessment maps to 6 core competencies and 8 cognitive question taxonomies.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = extColors.textPrimary
                )
                Text(
                    text = "• Teacher-First Diagnostics: All misconception indicators and AI-assisted drafts serve as decision-support tools for qualified teachers.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = extColors.textPrimary
                )
                Text(
                    text = "• Inclusive School Delivery: Supports Online, Computer Lab, OMR/Paper, and Teacher-Assisted modes so technology constraints never block diagnostic insight.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = extColors.textPrimary
                )
            }
        }

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactDemoPageScreen(
    defaultPlan: String,
    lastSubmittedSchool: String?,
    savedRequests: List<DemoRequestEntity>,
    onSubmitDemoRequest: (String, String, String, String, String, String, String, String, String) -> Unit,
    onDismissConfirmation: () -> Unit
) {
    val extColors = ScienceLensDesign.colors

    var contactName by remember { mutableStateOf("") }
    var roleTitle by remember { mutableStateOf("Principal / Academic Coordinator") }
    var schoolName by remember { mutableStateOf("") }
    var selectedBoard by remember { mutableStateOf("CBSE") }
    var classesSelected by remember { mutableStateOf("Classes VI–X") }
    var preferredDelivery by remember { mutableStateOf("Online Assessment") }
    var planInterest by remember(defaultPlan) { mutableStateOf(defaultPlan) }
    var contactInfo by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var validationError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("contact_demo_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "REQUEST A SCHOOL DEMO OR PRICING CONSULTATION",
            title = "Partner with ScienceLens for Your School",
            subtitle = "Schedule a walkthrough of sample Class VI–X question papers, the Misconception Engine, and Principal/Teacher dashboards. Pricing depends on school size, assessment frequency and services."
        )

        if (lastSubmittedSchool != null) {
            Surface(
                color = extColors.successContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.successAccent, RoundedCornerShape(12.dp))
                    .testTag("demo_submission_confirmation")
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Demo & Pricing Inquiry Saved!",
                            style = MaterialTheme.typography.titleSmall,
                            color = extColors.onSuccessContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Your request for $lastSubmittedSchool ($planInterest) has been recorded in the platform log.",
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(onClick = onDismissConfirmation) {
                        Text("OK", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "School Demo & Pricing Request Form",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = contactName,
                    onValueChange = {
                        contactName = it
                        validationError = null
                    },
                    label = { Text("Your Name (e.g., Dr. Meera Krishnan)") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_contact_name")
                )

                OutlinedTextField(
                    value = schoolName,
                    onValueChange = {
                        schoolName = it
                        validationError = null
                    },
                    label = { Text("School / Institution Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_school_name")
                )

                OutlinedTextField(
                    value = contactInfo,
                    onValueChange = {
                        contactInfo = it
                        validationError = null
                    },
                    label = { Text("Official Email or Phone Number") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_contact_info")
                )

                // Role Picker Chips
                Text(
                    text = "Your Role in School:",
                    style = MaterialTheme.typography.labelMedium,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        "Principal / Director",
                        "Academic Coordinator",
                        "HoD / Science Teacher",
                        "School Administrator"
                    ).forEach { r ->
                        val active = roleTitle == r
                        Surface(
                            color = if (active) ScienceBlue else extColors.subtleSurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .border(1.dp, if (active) ScienceBlue else extColors.cardBorder, RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { roleTitle = r }
                        ) {
                            Text(
                                text = r,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                            )
                        }
                    }
                }

                // Board Picker Chips (FlowRow so it never overflows on mobile)
                Text(
                    text = "School Curriculum Board:",
                    style = MaterialTheme.typography.labelMedium,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("CBSE", "ICSE", "State Board", "Multiple Boards").forEach { b ->
                        val active = selectedBoard == b
                        Surface(
                            color = if (active) ScienceBlue else extColors.subtleSurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .border(1.dp, if (active) ScienceBlue else extColors.cardBorder, RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { selectedBoard = b }
                        ) {
                            Text(
                                text = b,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                            )
                        }
                    }
                }

                // Plan Interest Picker Chips
                Text(
                    text = "Plan of Interest:",
                    style = MaterialTheme.typography.labelMedium,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Pilot Program", "School Plan", "Institutional Plan").forEach { p ->
                        val active = planInterest.contains(p, ignoreCase = true)
                        Surface(
                            color = if (active) OpticTeal else extColors.subtleSurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .border(1.dp, if (active) OpticTeal else extColors.cardBorder, RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { planInterest = p }
                        ) {
                            Text(
                                text = p,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                            )
                        }
                    }
                }

                // Delivery Mode Picker Chips
                Text(
                    text = "Preferred Assessment Delivery Mode:",
                    style = MaterialTheme.typography.labelMedium,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        "Online Assessment",
                        "OMR / Paper Assessment",
                        "Computer Lab Mode",
                        "Teacher-Assisted Mode"
                    ).forEach { mode ->
                        val active = preferredDelivery == mode
                        Surface(
                            color = if (active) ScienceBlue else extColors.subtleSurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .border(1.dp, if (active) ScienceBlue else extColors.cardBorder, RoundedCornerShape(8.dp))
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { preferredDelivery = mode }
                        ) {
                            Text(
                                text = mode,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Approximate Student Strength (Classes VI–X) & Questions (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_demo_notes")
                )

                if (validationError != null) {
                    Text(
                        text = validationError!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        if (contactName.isBlank() || schoolName.isBlank() || contactInfo.isBlank()) {
                            validationError = "Please enter your name, school name, and official email or phone number."
                        } else {
                            onSubmitDemoRequest(
                                contactName,
                                roleTitle,
                                schoolName,
                                selectedBoard,
                                classesSelected,
                                preferredDelivery,
                                planInterest,
                                contactInfo,
                                notes
                            )
                            contactName = ""
                            schoolName = ""
                            contactInfo = ""
                            notes = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ScienceBlue,
                        contentColor = PureWhite
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp)
                        .testTag("submit_demo_request_button")
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Submit School Demo & Pricing Request",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (savedRequests.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Submitted School Demo Inquiries (${savedRequests.size})",
                        style = MaterialTheme.typography.titleMedium,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    savedRequests.forEach { req ->
                        Surface(
                            color = extColors.subtleSurface,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(1.dp, extColors.cardBorder, RoundedCornerShape(10.dp))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "${req.schoolName} • ${req.planInterest}",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = extColors.textPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${req.contactName} (${req.roleTitle}) • Board: ${req.board} • Mode: ${req.preferredDelivery}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = extColors.textSecondary
                                )
                                Text(
                                    text = "Contact: ${req.contactEmailOrPhone}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = extColors.primaryAccent,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FaqPageScreen(
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("faq_page"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "FREQUENTLY ASKED QUESTIONS",
            title = "Answers for Principals, Coordinators, Teachers & Parents",
            subtitle = "Everything you need to know about ScienceLens pedagogy, board mapping, delivery modes, data privacy, and school onboarding."
        )

        ScienceLensSeedData.faqs.forEach { faq ->
            Card(
                colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = faq.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.tealAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = faq.question,
                        style = MaterialTheme.typography.titleMedium,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = faq.answer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = extColors.textSecondary
                    )
                }
            }
        }

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) }
        )
    }
}

@Composable
fun PrivacyPolicyPageScreen() {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("privacy_page"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "SECURITY, PRIVACY & STUDENT DATA GOVERNANCE",
            title = "ScienceLens Privacy Policy & School Data Protection",
            subtitle = "Engineered for institutional trust, minimal student data collection, and strict school-controlled governance."
        )

        val pillars = listOf(
            "1. Minimal Student Data Collection" to "ScienceLens collects only essential academic identifiers required to generate school-authorized competency reports (such as name/roll alias, class section, and assessment item responses). We do not collect unnecessary personal, biometric, or behavioral tracking data.",
            "2. Strict Role-Based Access Control (RBAC)" to "Access is strictly partitioned across Student, Parent, Teacher, Principal, School Admin, and Super Admin roles. Teachers view only their assigned classes; parents and students view only their own individual learning profiles.",
            "3. Zero Public Student Information" to "No student profile, assessment score, or report is ever indexed publicly or shared across schools. ScienceLens avoids public leaderboards and excessive student ranking.",
            "4. No Unconsented AI Model Training" to "School and student assessment data is strictly confidential. ScienceLens NEVER uses school or student data to train external or public AI models without explicit written institutional authorization.",
            "5. HTTPS Transport & Encryption at Rest" to "All platform communications are secured over TLS/HTTPS and sensitive records are encrypted at rest with comprehensive administrative audit logging.",
            "6. Institutional Data Retention & Deletion Controls" to "Participating schools retain ownership of their academic assessment records and may request archival export or permanent deletion upon completion of their institutional agreement."
        )

        pillars.forEach { (title, body) ->
            Card(
                colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = body,
                        style = MaterialTheme.typography.bodyMedium,
                        color = extColors.textSecondary
                    )
                }
            }
        }
    }
}

@Composable
fun TermsOfUsePageScreen() {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("terms_page"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "INSTITUTIONAL TERMS OF SERVICE",
            title = "ScienceLens Terms of Use for Schools & Educators",
            subtitle = "Clear, transparent operational terms governing school pilots, annual subscriptions, and diagnostic report usage."
        )

        val terms = listOf(
            "1. Institutional Authorization & Scope" to "ScienceLens is provided to schools, educational trusts, and authorized educators for academic assessment and diagnostic instruction across Classes VI–X.",
            "2. Nature of Diagnostic & Misconception Insights" to "All competency scores, distractor analyses, and misconception insights generated by ScienceLens are educational diagnostic indicators intended for qualified teacher review and classroom inquiry. They do not constitute clinical, psychological, or definitive academic diagnoses.",
            "3. Mandatory Educator Review for AI-Assisted Features" to "Where AI support tools assist in drafting questions, tagging taxonomy metadata, or summarizing class trends, human educator verification is mandatory prior to publishing assessments to students.",
            "4. Curriculum & Board Mapping Disclaimer" to "ScienceLens maps questions to CBSE, ICSE, and selected State Board curricula using an internal competency framework. ScienceLens operates independently and makes no claim of official government approval or board endorsement.",
            "5. Assessment Integrity & Intellectual Property" to "Question banks, caselets, diagrams, and diagnostic taxonomies remain the intellectual property of ScienceLens while schools retain full rights to their institutional performance reports."
        )

        terms.forEach { (title, body) ->
            Card(
                colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = body,
                        style = MaterialTheme.typography.bodyMedium,
                        color = extColors.textSecondary
                    )
                }
            }
        }
    }
}
