package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.CoreCompetency
import com.example.data.InterventionActionEntity
import com.example.data.QuestionAttemptEntity
import com.example.data.QuestionTaxonomy
import com.example.data.ScienceLensSeedData
import com.example.data.ScienceQuestion
import com.example.data.WebsitePage
import com.example.ui.DashboardPreviewTab
import com.example.ui.ScienceLensUiState
import com.example.ui.components.InteractiveSampleQuestionSection
import com.example.ui.components.MisconceptionEngineSection
import com.example.ui.components.UnifiedDashboardsShowcaseSection
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.DeepNavyDark
import com.example.ui.theme.OpticTeal
import com.example.ui.theme.OpticTealBright
import com.example.ui.theme.OpticTealLight
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceBlueLight
import com.example.ui.theme.ScienceLensDesign
import com.example.ui.theme.SlateNavy

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomePageScreen(
    uiState: ScienceLensUiState,
    recentAttempts: List<QuestionAttemptEntity>,
    interventionActions: List<InterventionActionEntity>,
    onNavigateToPage: (WebsitePage, String?) -> Unit,
    onSelectLearningCycleStep: (Int) -> Unit,
    onSelectQuestion: (Int) -> Unit,
    onSelectOption: (ScienceQuestion, String) -> Unit,
    onClearAttempts: () -> Unit,
    onSelectMisconceptionCase: (Int) -> Unit,
    onSelectDashboardTab: (DashboardPreviewTab) -> Unit,
    onToggleInterventionStep: (String, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .testTag("home_page_screen"),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 1. HERO SECTION (Section 9)
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DeepNavyDark),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF2B3D59), RoundedCornerShape(20.dp))
                .testTag("hero_section_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(DeepNavyDark, DeepNavy, SlateNavy)
                        )
                    )
                    .padding(20.dp)
            ) {
                Surface(
                    color = OpticTeal.copy(alpha = 0.25f),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.border(1.dp, OpticTealBright, RoundedCornerShape(6.dp))
                ) {
                    Text(
                        text = "SCHOOL SCIENCE ASSESSMENT & ANALYTICS PLATFORM",
                        style = MaterialTheme.typography.labelSmall,
                        color = OpticTealLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "See Beyond Marks.\nMeasure Understanding.",
                    style = MaterialTheme.typography.displayMedium,
                    color = PureWhite,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.testTag("hero_headline")
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Monthly competency-based Science assessments that help schools identify learning gaps, misconceptions and the skills students need to develop.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFFE2E8F0)
                )

                Spacer(modifier = Modifier.height(14.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HeroScopeChip("Classes VI–X")
                    HeroScopeChip("CBSE • ICSE • Selected State Boards")
                    HeroScopeChip("30 Questions • 45 Minutes Monthly")
                    HeroScopeChip("Principals, Coordinators & Teachers")
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Responsive Primary & Secondary CTAs
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val isCompact = maxWidth < 460.dp
                    if (isCompact) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OpticTeal,
                                    contentColor = PureWhite
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 48.dp)
                                    .testTag("hero_request_demo_cta")
                            ) {
                                Text(
                                    text = "Request a School Demo",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            OutlinedButton(
                                onClick = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) },
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.5.dp, ScienceBlueLight),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = PureWhite),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 48.dp)
                                    .testTag("hero_view_sample_report_cta")
                            ) {
                                Text(
                                    text = "View Sample Report",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OpticTeal,
                                    contentColor = PureWhite
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = 48.dp)
                                    .testTag("hero_request_demo_cta")
                            ) {
                                Text(
                                    text = "Request a School Demo",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            OutlinedButton(
                                onClick = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) },
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.5.dp, ScienceBlueLight),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = PureWhite),
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = 48.dp)
                                    .testTag("hero_view_sample_report_cta")
                            ) {
                                Text(
                                    text = "View Sample Report",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Image(
                    painter = painterResource(id = R.drawable.img_hero_sciencelens_1791265403396),
                    contentDescription = "ScienceLens optical analytics prism transforming test sheets into competency insights",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(14.dp))
                )
            }
        }

        ProblemAndSolutionSection()

        LearningCycleInteractiveSection(
            activeStep = uiState.activeLearningCycleStep,
            onSelectStep = onSelectLearningCycleStep
        )

        CompetencyFrameworkOverviewCard(
            onExploreFullFramework = { onNavigateToPage(WebsitePage.ASSESSMENT_FRAMEWORK, null) }
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

        MisconceptionEngineSection(
            cases = ScienceLensSeedData.misconceptionCases,
            selectedIndex = uiState.selectedMisconceptionCaseIndex,
            onSelectCase = onSelectMisconceptionCase
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeaderBlock(
                eyebrow = "ROLE-BASED ANALYTICS & REPORTS",
                title = "Actionable Dashboards for Every School Stakeholder",
                subtitle = "Switch between Teacher (Class VIII-A), Principal, Student, and Parent views using realistic demonstration data."
            )
            Spacer(modifier = Modifier.height(12.dp))
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
        }

        BoardCompatibilityAndDeliverySection()

        SchoolBenefitsGridSection()

        PricingSectionCards(
            onRequestPlanPricing = { planTitle ->
                onNavigateToPage(WebsitePage.CONTACT_DEMO, planTitle)
            }
        )

        FaqPreviewSection(
            onViewAllFaqs = { onNavigateToPage(WebsitePage.FAQ, null) }
        )

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) }
        )
    }
}

@Composable
private fun HeroScopeChip(label: String) {
    Surface(
        color = SlateNavy,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = PureWhite,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun SectionHeaderBlock(
    eyebrow: String,
    title: String,
    subtitle: String
) {
    val extColors = ScienceLensDesign.colors
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = eyebrow,
            style = MaterialTheme.typography.labelSmall,
            color = extColors.primaryAccent,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = extColors.textPrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = extColors.textSecondary
        )
    }
}

@Composable
fun ProblemAndSolutionSection() {
    val extColors = ScienceLensDesign.colors

    Column(modifier = Modifier.fillMaxWidth()) {
        SectionHeaderBlock(
            eyebrow = "WHY SCIENCELENS",
            title = "Marks Tell You What Students Got Right or Wrong. ScienceLens Helps Schools Understand Why.",
            subtitle = "Conventional school exams reward rote recall of textbook definitions, masking fragile conceptual foundations until board classes."
        )
        Spacer(modifier = Modifier.height(12.dp))

        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val isCompact = maxWidth < 560.dp
            if (isCompact) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    ProblemCard(extColors, Modifier.fillMaxWidth())
                    SolutionCard(Modifier.fillMaxWidth())
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ProblemCard(extColors, Modifier.weight(1f))
                    SolutionCard(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ProblemCard(
    extColors: com.example.ui.theme.ScienceLensExtendedColors,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.border(1.dp, extColors.cardBorder, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Surface(
                color = extColors.warningContainer,
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "THE SCHOOL ASSESSMENT PROBLEM",
                    style = MaterialTheme.typography.labelSmall,
                    color = extColors.onWarningContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ProblemBullet("High scores on definition recall hide weak application in unfamiliar scenarios.")
            ProblemBullet("A single percentage (e.g., 72%) never reveals which concept or reasoning skill is missing.")
            ProblemBullet("Teachers spend hours tabulating marks manually without diagnostic distractor insights.")
            ProblemBullet("Misconceptions in Classes VI–VIII compound silently into Classes IX–X.")
        }
    }
}

@Composable
private fun SolutionCard(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DeepNavyDark),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.border(1.dp, OpticTeal.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Surface(
                color = OpticTeal.copy(alpha = 0.25f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "THE SCIENCELENS SOLUTION",
                    style = MaterialTheme.typography.labelSmall,
                    color = OpticTealBright,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            SolutionBullet("Monthly 30-question, 45-minute competency assessments built on real-world contexts & diagrams.")
            SolutionBullet("Distractor-level Misconception Engine identifies the exact conceptual confusion behind errors.")
            SolutionBullet("Ready-to-use 10-minute classroom interventions and targeted follow-up questions for teachers.")
            SolutionBullet("Longitudinal competency tracking for Principals across Classes VI–X without punitive ranking.")
        }
    }
}

@Composable
private fun ProblemBullet(text: String) {
    val extColors = ScienceLensDesign.colors
    Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
        Text("• ", color = extColors.warningAccent, fontWeight = FontWeight.Bold)
        Text(text, style = MaterialTheme.typography.bodySmall, color = extColors.textPrimary)
    }
}

@Composable
private fun SolutionBullet(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
        Text("✓ ", color = OpticTealBright, fontWeight = FontWeight.Bold)
        Text(text, style = MaterialTheme.typography.bodySmall, color = PureWhite)
    }
}

@Composable
fun LearningCycleInteractiveSection(
    activeStep: Int,
    onSelectStep: (Int) -> Unit
) {
    val extColors = ScienceLensDesign.colors
    val steps = listOf(
        Triple(
            "1. Teach",
            "Classroom Instruction Along Your School Syllabus",
            "Teachers deliver regular chapter instruction aligned with CBSE, ICSE, or State Board lesson plans."
        ),
        Triple(
            "2. Assess",
            "Monthly 30-Question, 45-Minute Competency Assessment",
            "Students attempt application MCQs, caselets, graph/data interpretation, and experimental reasoning items via Online, Lab, or OMR/Paper mode."
        ),
        Triple(
            "3. Diagnose",
            "Misconception Engine & Competency Breakdown",
            "ScienceLens analyzes distractor choices to separate simple slips from recurring conceptual misconceptions across 6 core competencies."
        ),
        Triple(
            "4. Remediate",
            "Targeted 10-Minute Classroom Intervention",
            "Teachers receive a focused 10-minute demonstration plan, 3 application prompts, a peer discussion question, and a 2-minute exit ticket."
        ),
        Triple(
            "5. Reassess",
            "Continuous Monthly Growth Verification",
            "Subsequent monthly assessments include spiral anchor items to verify whether the misconception has been resolved."
        )
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
            .testTag("learning_cycle_section")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "CONTINUOUS IMPROVEMENT WORKFLOW",
                style = MaterialTheme.typography.labelSmall,
                color = extColors.tealAccent,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Teach → Assess → Diagnose → Remediate → Reassess",
                style = MaterialTheme.typography.titleLarge,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Monthly assessments create a closed-loop instructional cycle so learning gaps are addressed within days, not after annual exams.",
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                steps.forEachIndexed { idx, (label, _, _) ->
                    val selected = idx == activeStep
                    Surface(
                        color = if (selected) ScienceBlue else extColors.subtleSurface,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (selected) ScienceBlue else extColors.cardBorder,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelectStep(idx) }
                            .testTag("learning_cycle_step_$idx")
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (selected) PureWhite else extColors.textPrimary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                        )
                    }
                    if (idx < steps.lastIndex) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next step",
                            tint = extColors.textMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            val current = steps.getOrElse(activeStep) { steps[2] }
            Surface(
                color = extColors.primaryAccentContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "${current.first}: ${current.second}",
                        style = MaterialTheme.typography.titleSmall,
                        color = extColors.onPrimaryAccentContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = current.third,
                        style = MaterialTheme.typography.bodySmall,
                        color = extColors.textPrimary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CompetencyFrameworkOverviewCard(
    onExploreFullFramework: () -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CORE ASSESSMENT ARCHITECTURE",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.primaryAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "6 Core Competencies & 8 Question Taxonomies",
                        style = MaterialTheme.typography.titleLarge,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = onExploreFullFramework,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("explore_framework_button")
                ) {
                    Text("Details", style = MaterialTheme.typography.labelMedium)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Typical Monthly Assessment: 30 Questions • 45 Minutes • Spanning MCQs, Caselets, Graph/Data Interpretation, Diagram-Based Questions, Experimental Situations & Real-World Contexts.",
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CoreCompetency.entries.forEach { comp ->
                    Surface(
                        color = extColors.subtleSurface,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, extColors.cardBorder, RoundedCornerShape(10.dp))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = comp.title,
                                style = MaterialTheme.typography.titleSmall,
                                color = extColors.textPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = comp.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = extColors.textSecondary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "8-Part Question Taxonomy:",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuestionTaxonomy.entries.forEach { tax ->
                    Surface(
                        color = extColors.tealAccentContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = tax.label,
                            style = MaterialTheme.typography.labelMedium,
                            color = extColors.onTealAccentContainer,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BoardCompatibilityAndDeliverySection() {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "CURRICULUM & BOARD COMPATIBILITY",
                    style = MaterialTheme.typography.labelSmall,
                    color = extColors.primaryAccent,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Aligned with CBSE, ICSE & Selected State Boards (Classes VI–X)",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "ScienceLens maps monthly assessment items to your school's active curriculum and chapter sequence while maintaining a unified underlying Science competency framework.",
                    style = MaterialTheme.typography.bodySmall,
                    color = extColors.textSecondary
                )
                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    BoardBadgeRow(
                        badge = "CBSE",
                        detail = "Classes VI–X NCERT-mapped chapter sequences, competency indicators & caselets"
                    )
                    BoardBadgeRow(
                        badge = "ICSE",
                        detail = "Classes VI–X Physics, Chemistry & Biology discipline-specific topic mapping"
                    )
                    BoardBadgeRow(
                        badge = "State Boards",
                        detail = "Configurable chapter & medium alignment for selected State Board curricula"
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Transparency Note: Curriculum mapping is performed independently by ScienceLens specialists to support school instruction. ScienceLens does not claim official board endorsement or government partnership.",
                    style = MaterialTheme.typography.labelSmall,
                    color = extColors.textMuted
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
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "FLEXIBLE ASSESSMENT DELIVERY",
                    style = MaterialTheme.typography.labelSmall,
                    color = extColors.tealAccent,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Conduct Assessments Even with Limited School Technology",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    ScienceLensSeedData.deliveryModes.forEach { mode ->
                        Surface(
                            color = extColors.subtleSurface,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = mode.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = extColors.textPrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Surface(
                                        color = extColors.tealAccentContainer,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = mode.subtitle,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = extColors.onTealAccentContainer,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = mode.howItWorks,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = extColors.textSecondary
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
private fun BoardBadgeRow(badge: String, detail: String) {
    val extColors = ScienceLensDesign.colors
    Surface(
        color = extColors.subtleSurface,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = extColors.primaryAccentContainer,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(96.dp)
            ) {
                Text(
                    text = badge,
                    style = MaterialTheme.typography.labelLarge,
                    color = extColors.onPrimaryAccentContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textPrimary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SchoolBenefitsGridSection() {
    val benefits = listOf(
        "Identify learning gaps early" to "Detect fragile understanding in Classes VI–VIII before board-year pressure builds.",
        "Measure application, not just recall" to "Evaluate how students transfer concepts to unfamiliar situations and experiments.",
        "Understand misconceptions" to "Uncover the specific conceptual reasoning behind incorrect distractor choices.",
        "Track competency growth" to "Follow cohort progression across Conceptual, Application, Reasoning, and Interpretation skills.",
        "Give teachers actionable insights" to "Provide structured 10-minute classroom demonstrations and discussion prompts.",
        "Support data-informed academic planning" to "Equip Principals and Coordinators with topic heatmaps for department reviews.",
        "Monitor progress month by month" to "Create a steady, low-stakes monthly rhythm of diagnostic feedback and remediation.",
        "Reduce manual assessment analysis" to "Eliminate hours of manual spreadsheet mark entry and question-by-question tallying."
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = DeepNavyDark),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF2B3D59), RoundedCornerShape(18.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "BUILT FOR ACADEMIC LEADERSHIP",
                style = MaterialTheme.typography.labelSmall,
                color = OpticTealBright,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "8 Core Benefits for Participating Schools",
                style = MaterialTheme.typography.headlineSmall,
                color = PureWhite,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                benefits.forEach { (title, desc) ->
                    Surface(
                        color = SlateNavy,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = OpticTealBright,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = PureWhite,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = desc,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFFCBD5E1)
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
fun PricingSectionCards(
    onRequestPlanPricing: (String) -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("pricing_section")
    ) {
        SectionHeaderBlock(
            eyebrow = "TRANSPARENT SCHOOL PARTNERSHIP PLANS",
            title = "Flexible Plans for Single Schools & Multi-Branch Institutions",
            subtitle = "Pricing depends on school size, assessment frequency and services."
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            ScienceLensSeedData.pricingTiers.forEach { tier ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (tier.isFeatured) DeepNavyDark else extColors.cardBackground
                    ),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = if (tier.isFeatured) 2.dp else 1.dp,
                            color = if (tier.isFeatured) OpticTealBright else extColors.cardBorder,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .testTag("pricing_card_${tier.id.lowercase()}")
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = tier.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = if (tier.isFeatured) PureWhite else extColors.textPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = tier.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (tier.isFeatured) OpticTealLight else extColors.textSecondary
                                )
                            }
                            if (tier.isFeatured) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = OpticTeal,
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "RECOMMENDED",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = PureWhite,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ideal Scope: ${tier.targetScope}",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (tier.isFeatured) Color(0xFFE2E8F0) else extColors.primaryAccent,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        tier.highlights.forEach { feat ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "✓ ",
                                    color = if (tier.isFeatured) OpticTealBright else extColors.tealAccent,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = feat,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (tier.isFeatured) PureWhite else extColors.textPrimary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Pricing depends on school size, assessment frequency and services.",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (tier.isFeatured) Color(0xFF94A3B8) else extColors.textMuted
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { onRequestPlanPricing(tier.title) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tier.isFeatured) OpticTeal else ScienceBlue,
                                contentColor = PureWhite
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .testTag("pricing_cta_${tier.id.lowercase()}")
                        ) {
                            Text(
                                text = "${tier.ctaLabel} / Request Demo",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FaqPreviewSection(
    onViewAllFaqs: () -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "FREQUENTLY ASKED QUESTIONS",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.primaryAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Common Questions from School Leaders",
                        style = MaterialTheme.typography.titleLarge,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = onViewAllFaqs,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("view_all_faqs_button")
                ) {
                    Text("All FAQs", style = MaterialTheme.typography.labelMedium)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            ScienceLensSeedData.faqs.take(3).forEach { faq ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = faq.question,
                        style = MaterialTheme.typography.titleSmall,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = faq.answer,
                        style = MaterialTheme.typography.bodySmall,
                        color = extColors.textSecondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = extColors.cardBorder)
                }
            }
        }
    }
}

@Composable
fun FinalCtaBanner(
    onRequestDemo: () -> Unit,
    onViewSampleReport: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DeepNavyDark),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, OpticTeal.copy(alpha = 0.6f), RoundedCornerShape(18.dp))
            .testTag("final_cta_banner")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(DeepNavyDark, ScienceBlue, OpticTeal)
                    )
                )
                .padding(22.dp)
        ) {
            Text(
                text = "Ready to See What Your Students Really Understand?",
                style = MaterialTheme.typography.headlineSmall,
                color = PureWhite,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Schedule a 20-minute walkthrough of the ScienceLens competency framework, sample Class VI–X papers, and school diagnostic reports with our academic team.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFE2E8F0)
            )
            Spacer(modifier = Modifier.height(16.dp))

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isCompact = maxWidth < 460.dp
                if (isCompact) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onRequestDemo,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PureWhite,
                                contentColor = DeepNavyDark
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .testTag("final_cta_request_demo")
                        ) {
                            Text(
                                text = "Request a School Demo",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        OutlinedButton(
                            onClick = onViewSampleReport,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.5.dp, PureWhite),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = PureWhite),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .testTag("final_cta_sample_report")
                        ) {
                            Text(
                                text = "View Sample Report",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = onRequestDemo,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PureWhite,
                                contentColor = DeepNavyDark
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("final_cta_request_demo")
                        ) {
                            Text(
                                text = "Request a School Demo",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        OutlinedButton(
                            onClick = onViewSampleReport,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.5.dp, PureWhite),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = PureWhite),
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("final_cta_sample_report")
                        ) {
                            Text(
                                text = "View Sample Report",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
