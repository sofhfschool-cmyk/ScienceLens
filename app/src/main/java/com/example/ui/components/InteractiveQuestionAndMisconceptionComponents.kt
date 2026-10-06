package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
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
import com.example.data.MisconceptionCase
import com.example.data.QuestionAttemptEntity
import com.example.data.ScienceQuestion
import com.example.ui.theme.CompetencyEmerald
import com.example.ui.theme.DeepNavyDark
import com.example.ui.theme.DiagnosticAmber
import com.example.ui.theme.OpticTeal
import com.example.ui.theme.OpticTealBright
import com.example.ui.theme.OpticTealLight
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceLensDesign
import com.example.ui.theme.SlateNavy

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InteractiveSampleQuestionSection(
    questions: List<ScienceQuestion>,
    selectedQuestionIndex: Int,
    selectedOptionByQuestionId: Map<String, String>,
    recentAttempts: List<QuestionAttemptEntity>,
    onSelectQuestion: (Int) -> Unit,
    onSelectOption: (ScienceQuestion, String) -> Unit,
    onClearAttempts: () -> Unit
) {
    val extColors = ScienceLensDesign.colors
    val question = questions.getOrElse(selectedQuestionIndex) { questions.first() }
    val selectedLetter = selectedOptionByQuestionId[question.id]
    var showFullMetadata by remember { mutableStateOf(true) }

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
            .testTag("interactive_sample_question_card")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = extColors.primaryAccentContainer,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "INTERACTIVE SAMPLE QUESTION",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onPrimaryAccentContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
                Text(
                    text = "Item ${selectedQuestionIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.labelMedium,
                    color = extColors.textMuted,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                questions.forEachIndexed { index, item ->
                    val active = index == selectedQuestionIndex
                    Surface(
                        color = if (active) ScienceBlue else extColors.subtleSurface,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (active) ScienceBlue else extColors.cardBorder,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelectQuestion(index) }
                            .testTag("sample_question_tab_$index")
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                            Text(
                                text = "Q${index + 1}: ${item.chapter}",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (active) PureWhite else extColors.textPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${item.className} • ${item.questionFormat}",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) Color(0xFFE2E8F0) else extColors.textMuted
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TagBadge(
                    text = question.className,
                    bgColor = extColors.primaryAccentContainer,
                    textColor = extColors.onPrimaryAccentContainer
                )
                TagBadge(
                    text = question.chapter,
                    bgColor = extColors.tealAccentContainer,
                    textColor = extColors.onTealAccentContainer
                )
                TagBadge(
                    text = question.competency.shortName,
                    bgColor = extColors.subtleSurface,
                    textColor = extColors.textPrimary
                )
                TagBadge(
                    text = question.difficulty,
                    bgColor = extColors.warningContainer,
                    textColor = extColors.onWarningContainer
                )
                TagBadge(
                    text = question.taxonomy.label,
                    bgColor = extColors.subtleSurface,
                    textColor = extColors.textSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "“${question.stem}”",
                style = MaterialTheme.typography.titleLarge,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("sample_question_stem")
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Tap any option (A–D) to see how ScienceLens diagnoses both correct reasoning and distractor-specific misconceptions:",
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                question.options.forEach { option ->
                    val isSelected = selectedLetter == option.letter
                    val bgColor = when {
                        isSelected && option.isCorrect -> extColors.successContainer
                        isSelected && !option.isCorrect -> extColors.warningContainer
                        else -> extColors.subtleSurface
                    }
                    val borderColor = when {
                        isSelected && option.isCorrect -> extColors.successAccent
                        isSelected && !option.isCorrect -> extColors.warningAccent
                        option.isCorrect && selectedLetter != null -> extColors.successAccent.copy(alpha = 0.65f)
                        else -> extColors.cardBorder
                    }
                    val optionTextColor = when {
                        isSelected && option.isCorrect -> extColors.onSuccessContainer
                        isSelected && !option.isCorrect -> extColors.onWarningContainer
                        else -> extColors.textPrimary
                    }

                    Surface(
                        color = bgColor,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onSelectOption(question, option.letter) }
                            .testTag("question_option_${option.letter}")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(CircleShape)
                                            .background(
                                                when {
                                                    isSelected && option.isCorrect -> CompetencyEmerald
                                                    isSelected && !option.isCorrect -> DiagnosticAmber
                                                    else -> ScienceBlue
                                                }
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = option.letter,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = PureWhite,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = "${option.letter}. ${option.text}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = optionTextColor,
                                        fontWeight = if (isSelected || option.isCorrect) FontWeight.SemiBold else FontWeight.Normal
                                    )
                                }

                                if (option.isCorrect) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Surface(
                                        color = CompetencyEmerald,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = "Correct",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = PureWhite,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }

                            AnimatedVisibility(visible = isSelected) {
                                Column(modifier = Modifier.padding(top = 10.dp)) {
                                    HorizontalDivider(color = borderColor.copy(alpha = 0.4f))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = if (option.isCorrect) {
                                            "Why this demonstrates ${question.competency.title}:"
                                        } else {
                                            "Misconception Engine Diagnostic (Beyond Right/Wrong):"
                                        },
                                        style = MaterialTheme.typography.labelMedium,
                                        color = optionTextColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = option.distractorRationale,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = optionTextColor
                                    )
                                    if (option.linkedMisconceptionTag != null) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "Diagnostic Tag: ${option.linkedMisconceptionTag} • Suggested Teacher Action: ${question.suggestedIntervention}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = optionTextColor,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = extColors.subtleSurface,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showFullMetadata = !showFullMetadata }
                            .testTag("toggle_question_metadata"),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Science,
                                contentDescription = "Question Metadata",
                                tint = extColors.primaryAccent,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Question Metadata & Psychometric Tags",
                                style = MaterialTheme.typography.titleSmall,
                                color = extColors.textPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (showFullMetadata) "Hide" else "Show",
                            style = MaterialTheme.typography.labelMedium,
                            color = extColors.primaryAccent,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    AnimatedVisibility(visible = showFullMetadata) {
                        Column(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetadataStackItem("Item ID & Format", "${question.id} • ${question.questionFormat}")
                            MetadataStackItem("Class & Board Alignment", "${question.className} • ${question.board}")
                            MetadataStackItem("Chapter & Topic", "${question.chapter} → ${question.topic}")
                            MetadataStackItem("Core Scientific Concept", question.concept)
                            MetadataStackItem("Competency & Taxonomy", "${question.competency.title} • ${question.taxonomy.label}")
                            MetadataStackItem("Cognitive Level & Difficulty", "${question.cognitiveLevel} • ${question.difficulty}")
                            MetadataStackItem("Real-World Context", question.contextDescription)
                            MetadataStackItem("Correct Answer", question.options.first { it.isCorrect }.let { "${it.letter}. ${it.text}" })
                            MetadataStackItem("Likely Misconception", question.likelyMisconception)
                            MetadataStackItem("Suggested Teacher Intervention", question.suggestedIntervention)
                        }
                    }
                }
            }

            if (recentAttempts.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = extColors.primaryAccentContainer.copy(alpha = 0.65f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Session Diagnostic Log (${recentAttempts.size} recorded)",
                                style = MaterialTheme.typography.labelMedium,
                                color = extColors.onPrimaryAccentContainer,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedButton(
                                onClick = onClearAttempts,
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.testTag("clear_attempts_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Reset log",
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Reset", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        val latest = recentAttempts.first()
                        Text(
                            text = "Latest: ${latest.questionId} (Option ${latest.selectedOptionLetter}) → ${latest.diagnosticSummary}",
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TagBadge(text: String, bgColor: Color, textColor: Color) {
    Surface(
        color = bgColor,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
private fun MetadataStackItem(label: String, value: String) {
    val extColors = ScienceLensDesign.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(extColors.cardBackground, RoundedCornerShape(8.dp))
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = extColors.primaryAccent,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = extColors.textPrimary
        )
    }
}

@Composable
fun MisconceptionEngineSection(
    cases: List<MisconceptionCase>,
    selectedIndex: Int,
    onSelectCase: (Int) -> Unit
) {
    val activeCase = cases.getOrElse(selectedIndex) { cases.first() }
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = DeepNavyDark),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF2B3D59), RoundedCornerShape(18.dp))
            .testTag("misconception_engine_section")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(OpticTeal.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = "Misconception Engine",
                        tint = OpticTealBright,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "MISCONCEPTION ENGINE • BEYOND RIGHT OR WRONG",
                        style = MaterialTheme.typography.labelSmall,
                        color = OpticTealBright,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "How ScienceLens Goes Beyond Marks",
                        style = MaterialTheme.typography.headlineSmall,
                        color = PureWhite,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Instead of simply marking an answer 'Wrong', ScienceLens analyzes response evidence across questions to surface the underlying conceptual confusion and suggest a targeted classroom intervention.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFCBD5E1)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                cases.forEachIndexed { index, item ->
                    val selected = index == selectedIndex
                    Surface(
                        color = if (selected) OpticTeal else SlateNavy,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (selected) OpticTealBright else Color(0xFF334155),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onSelectCase(index) }
                            .testTag("misconception_case_tab_$index")
                    ) {
                        Text(
                            text = "${item.classLevel}: ${item.chapter}",
                            style = MaterialTheme.typography.labelMedium,
                            color = PureWhite,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isCompact = maxWidth < 500.dp
                if (isCompact) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        ConventionalVsScienceLensComparisonCards(activeCase, isSideBySide = false)
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ConventionalVsScienceLensComparisonCards(activeCase, isSideBySide = true)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                color = extColors.cardBackground,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(14.dp))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DiagnosticDetailBlock(
                        badge = "1. CONCEPT INVOLVED",
                        content = "${activeCase.chapter} — ${activeCase.conceptInvolved}",
                        accentColor = extColors.primaryAccent
                    )
                    DiagnosticDetailBlock(
                        badge = "2. LIKELY MISCONCEPTION",
                        content = activeCase.likelyMisconception,
                        accentColor = extColors.warningAccent
                    )
                    DiagnosticDetailBlock(
                        badge = "3. EVIDENCE FROM RESPONSES",
                        content = activeCase.evidenceFromResponses,
                        accentColor = extColors.tealAccent
                    )
                    DiagnosticDetailBlock(
                        badge = "4. SUGGESTED TEACHER INTERVENTION",
                        content = activeCase.suggestedTeacherIntervention,
                        accentColor = extColors.successAccent
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(extColors.subtleSurface, RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "5. RECOMMENDED PRACTICE QUESTIONS",
                            style = MaterialTheme.typography.labelSmall,
                            color = extColors.primaryAccent,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        activeCase.recommendedPracticeQuestions.forEach { q ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "• ",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = extColors.tealAccent,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = q,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = extColors.textPrimary
                                )
                            }
                        }
                    }

                    Surface(
                        color = extColors.primaryAccentContainer,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Educator Review Disclaimer",
                                tint = extColors.onPrimaryAccentContainer,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Diagnostic Notice: Such insights are diagnostic indicators for teacher review and classroom inquiry, not definitive diagnoses.",
                                style = MaterialTheme.typography.labelSmall,
                                color = extColors.onPrimaryAccentContainer,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ConventionalVsScienceLensComparisonCards(
    activeCase: MisconceptionCase,
    isSideBySide: Boolean
) {
    Surface(
        color = SlateNavy,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(if (isSideBySide) 0.48f else 1f)
            .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "TRADITIONAL MARKSHEET",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFFDA4AF),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "✗ Marked 'Wrong' (0 / 1)",
                style = MaterialTheme.typography.titleSmall,
                color = PureWhite,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Treats a careless calculation slip and a deep conceptual confusion identically.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFCBD5E1)
            )
        }
    }

    Surface(
        color = Color(0xFF0F3D3E),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, OpticTealBright.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "SCIENCELENS DIAGNOSTIC ENGINE",
                style = MaterialTheme.typography.labelSmall,
                color = OpticTealBright,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gap Flagged (${activeCase.affectedStudentsPercent}% of Class Cohort)",
                style = MaterialTheme.typography.titleSmall,
                color = PureWhite,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = activeCase.studentSymptom,
                style = MaterialTheme.typography.bodySmall,
                color = OpticTealLight
            )
        }
    }
}

@Composable
private fun DiagnosticDetailBlock(
    badge: String,
    content: String,
    accentColor: Color
) {
    val extColors = ScienceLensDesign.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(extColors.subtleSurface, RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Text(
            text = badge,
            style = MaterialTheme.typography.labelSmall,
            color = accentColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = extColors.textPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}
