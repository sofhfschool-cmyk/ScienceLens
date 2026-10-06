package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AutoAwesome
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
import com.example.data.AiDraftQuestionEntity
import com.example.data.CoreCompetency
import com.example.data.QuestionTaxonomy
import com.example.data.WebsitePage
import com.example.ui.theme.DeepNavyDark
import com.example.ui.theme.OpticTeal
import com.example.ui.theme.OpticTealBright
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceLensDesign

@Composable
fun HowItWorksPageScreen(
    activeStep: Int,
    onSelectStep: (Int) -> Unit,
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("how_it_works_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "HOW SCIENCELENS WORKS",
            title = "A Monthly Continuous Improvement Cycle for School Science",
            subtitle = "Designed to fit seamlessly into your existing school calendar across Classes VI–X (CBSE, ICSE & State Boards) without adding grading burden to teachers."
        )

        LearningCycleInteractiveSection(
            activeStep = activeStep,
            onSelectStep = onSelectStep
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Step-by-Step Monthly School Workflow",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                WorkflowDetailItem(
                    number = "01",
                    title = "Curriculum & Chapter Mapping",
                    detail = "At the start of the term, your Academic Coordinator confirms the monthly chapter schedule for Classes VI–X across CBSE, ICSE, or State Board syllabi."
                )
                WorkflowDetailItem(
                    number = "02",
                    title = "45-Minute Monthly Competency Assessment (30 Questions)",
                    detail = "Students complete a 30-question diagnostic assessment via Online mode, Computer Lab rotation, OMR/Paper sheets, or Teacher-Assisted projection."
                )
                WorkflowDetailItem(
                    number = "03",
                    title = "Automated Misconception & Competency Diagnosis",
                    detail = "Responses are analyzed across 6 core competencies and 8 question taxonomies. Distractor patterns flag likely conceptual misconceptions for teacher review."
                )
                WorkflowDetailItem(
                    number = "04",
                    title = "10-Minute Targeted Classroom Remediation",
                    detail = "Science teachers use the suggested 10-minute demonstration, 3 application prompts, discussion question, and exit ticket to clear the specific learning gap."
                )
                WorkflowDetailItem(
                    number = "05",
                    title = "Longitudinal Reassessment & Growth Tracking",
                    detail = "Next month's assessment includes anchor items on remediated concepts, updating Principal and Teacher growth charts month by month."
                )
            }
        }

        BoardCompatibilityAndDeliverySection()

        FinalCtaBanner(
            onRequestDemo = { onNavigateToPage(WebsitePage.CONTACT_DEMO, null) },
            onViewSampleReport = { onNavigateToPage(WebsitePage.SAMPLE_REPORT, null) }
        )
    }
}

@Composable
private fun WorkflowDetailItem(number: String, title: String, detail: String) {
    val extColors = ScienceLensDesign.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(extColors.subtleSurface, RoundedCornerShape(12.dp))
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            color = DeepNavyDark,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.labelLarge,
                color = OpticTealBright,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textSecondary
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AssessmentFrameworkPageScreen(
    aiDrafts: List<AiDraftQuestionEntity>,
    onGenerateAiDraft: (String, String, String, String) -> Unit,
    onReviewAiDraft: (Int, String, String) -> Unit,
    onNavigateToPage: (WebsitePage, String?) -> Unit
) {
    val extColors = ScienceLensDesign.colors
    var selectedDraftClass by remember { mutableStateOf("Class VIII") }
    var selectedDraftBoard by remember { mutableStateOf("CBSE") }
    var selectedDraftChapter by remember { mutableStateOf("Force and Pressure") }
    var selectedDraftCompetency by remember { mutableStateOf("Application") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("assessment_framework_page"),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionHeaderBlock(
            eyebrow = "SCIENCELENS ASSESSMENT FRAMEWORK",
            title = "Engineered Around Scientific Competency, Not Rote Recall",
            subtitle = "Every monthly 30-question, 45-minute assessment balances question formats, cognitive levels, and diagnostic distractors."
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
                    text = "TYPICAL MONTHLY ASSESSMENT BLUEPRINT",
                    style = MaterialTheme.typography.labelSmall,
                    color = OpticTealBright,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "30 Questions • 45 Minutes • Multi-Format Diagnostic Design",
                    style = MaterialTheme.typography.headlineSmall,
                    color = PureWhite,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                val formats = listOf(
                    "Application-Based MCQs" to "Transferring laws to everyday phenomena",
                    "Caselets" to "Connected 2–3 item real-world or lab scenarios",
                    "Graph & Data Interpretation" to "Reading experimental plots, tables & trends",
                    "Diagram-Based Questions" to "Ray optics, circuits, force vectors & biological structures",
                    "Experimental Situations" to "Variable control, apparatus setup & observation prediction",
                    "Real-World Contexts" to "Engineering, household science, environment & health",
                    "Scientific Reasoning Items" to "Evaluating claims, evidence & cause-effect logic"
                )
                formats.forEach { (fmt, desc) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "• $fmt",
                            style = MaterialTheme.typography.bodySmall,
                            color = OpticTealBright,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFCBD5E1),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            }
        }

        // 6 Core Competencies Detail Card
        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "The 6 Core Science Competencies",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                CoreCompetency.entries.forEach { comp ->
                    Column(modifier = Modifier.padding(vertical = 6.dp)) {
                        Text(
                            text = comp.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = extColors.primaryAccent,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = comp.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Classroom Example: ${comp.exampleSkill}",
                            style = MaterialTheme.typography.labelSmall,
                            color = extColors.textSecondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = extColors.cardBorder)
                    }
                }
            }
        }

        // 8 Question Taxonomies Detail Card — Mobile-friendly stacked cards
        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "8-Part Question Taxonomy & Metadata Specification",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Every question in the ScienceLens bank carries 12 mandatory metadata attributes: Class, Board, Chapter, Topic, Concept, Competency, Cognitive Level, Difficulty, Context, Correct Answer, Distractors, and Likely Misconception.",
                    style = MaterialTheme.typography.bodySmall,
                    color = extColors.textSecondary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    QuestionTaxonomy.entries.forEach { tax ->
                        Surface(
                            color = extColors.subtleSurface,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, extColors.cardBorder, RoundedCornerShape(10.dp))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = tax.label,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = extColors.tealAccent
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = tax.cognitiveFocus,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = extColors.textPrimary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section 13: AI Features as an Intelligent Support Layer + Mandatory Educator Review Gate
        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, extColors.tealAccent, RoundedCornerShape(18.dp))
                .testTag("ai_support_layer_card")
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "AI Support Layer",
                        tint = extColors.tealAccent,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "INTELLIGENT SUPPORT LAYER • EDUCATOR REVIEW GATE",
                            style = MaterialTheme.typography.labelSmall,
                            color = extColors.tealAccent,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "AI-Assisted Workflow with Mandatory Educator Review",
                            style = MaterialTheme.typography.titleLarge,
                            color = extColors.textPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "ScienceLens uses AI strictly as an intelligent support layer for: (1) AI-assisted question drafting, (2) Automated taxonomy tagging, (3) Initial difficulty estimation, (4) Misconception pattern detection, (5) Automated report summaries, (6) Teacher intervention suggestions, and (7) Recommended practice questions.",
                    style = MaterialTheme.typography.bodySmall,
                    color = extColors.textSecondary
                )

                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = extColors.warningContainer,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Mandatory Quality Gate: AI-generated questions and diagnostic tags must undergo human educator review and approval before publication to any school assessment.",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onWarningContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Interactive Educator Review Queue (Simulate AI Draft + Academic Review):",
                    style = MaterialTheme.typography.titleSmall,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("Class VII", "Class VIII", "Class IX", "Class X").forEach { cls ->
                        val active = selectedDraftClass == cls
                        Surface(
                            color = if (active) ScienceBlue else extColors.subtleSurface,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { selectedDraftClass = cls }
                        ) {
                            Text(
                                text = cls,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                    listOf("Force and Pressure", "Light & Optics", "Chemical Effects", "Life Processes").forEach { ch ->
                        val active = selectedDraftChapter == ch
                        Surface(
                            color = if (active) OpticTeal else extColors.subtleSurface,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { selectedDraftChapter = ch }
                        ) {
                            Text(
                                text = ch,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        onGenerateAiDraft(
                            selectedDraftClass,
                            selectedDraftBoard,
                            selectedDraftChapter,
                            selectedDraftCompetency
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = OpticTeal, contentColor = PureWhite),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .testTag("generate_ai_draft_button")
                ) {
                    Text(
                        text = "+ Generate AI Draft for Review ($selectedDraftClass • $selectedDraftChapter)",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    aiDrafts.take(4).forEach { draft ->
                        val isApproved = draft.reviewStatus == "APPROVED_FOR_BANK"
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
                                        text = "${draft.className} • ${draft.board} • ${draft.chapter}",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = extColors.textPrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = if (isApproved) extColors.successContainer else extColors.warningContainer,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = if (isApproved) "APPROVED" else "PENDING REVIEW",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (isApproved) extColors.onSuccessContainer else extColors.onWarningContainer,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Competency: ${draft.competency} • Taxonomy: ${draft.taxonomy} • ${draft.estimatedDifficulty}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = extColors.primaryAccent
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = draft.questionStem,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = extColors.textPrimary,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Target Misconception: ${draft.targetMisconception}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = extColors.textSecondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                if (!isApproved) {
                                    OutlinedButton(
                                        onClick = {
                                            onReviewAiDraft(
                                                draft.id,
                                                "APPROVED_FOR_BANK",
                                                "Approved by Senior Science Coordinator for monthly assessment bank."
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.testTag("approve_draft_${draft.id}")
                                    ) {
                                        Text("Approve as Educator", style = MaterialTheme.typography.labelSmall)
                                    }
                                } else {
                                    Text(
                                        text = "✓ ${draft.reviewerNote}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = extColors.successAccent,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Section 14: Future Analytics & Psychometric Architecture Roadmap
        Card(
            colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
                .testTag("future_analytics_roadmap_card")
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "ARCHITECTURE ROADMAP • FUTURE PSYCHOMETRIC CAPABILITIES",
                    style = MaterialTheme.typography.labelSmall,
                    color = extColors.primaryAccent,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Designed for Advanced Psychometrics in Future Releases",
                    style = MaterialTheme.typography.titleLarge,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "The ScienceLens item-response data schema is architected from day one to support classical test theory (CTT) and modern psychometric modeling in upcoming platform versions (clearly distinguished from current core diagnostic features):",
                    style = MaterialTheme.typography.bodySmall,
                    color = extColors.textSecondary
                )
                Spacer(modifier = Modifier.height(10.dp))

                val futureMetrics = listOf(
                    "Item Difficulty (p-value) Calibration" to "Empirical cohort difficulty tracking across urban and regional school segments.",
                    "Item Discrimination Index (Point-Biserial)" to "Measuring how effectively each question differentiates high and developing competency mastery.",
                    "Distractor Functioning Analysis" to "Quantifying distractor attractiveness curves across cognitive subgroups.",
                    "Assessment Reliability (Cronbach's Alpha / KR-20)" to "Internal consistency auditing across monthly 30-item forms.",
                    "Longitudinal Multi-Year Growth Tracking" to "Cross-grade vertical progression from Class VI through Class X.",
                    "Rasch / IRT-Based Ability Scaling (Future Version)" to "Item Response Theory (1PL/2PL) invariant ability estimation across parallel test forms."
                )
                futureMetrics.forEach { (title, desc) ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("→ ", color = extColors.primaryAccent, fontWeight = FontWeight.Bold)
                        Column {
                            Text(
                                text = "$title (Roadmap Architecture)",
                                style = MaterialTheme.typography.labelLarge,
                                color = extColors.textPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = desc,
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
