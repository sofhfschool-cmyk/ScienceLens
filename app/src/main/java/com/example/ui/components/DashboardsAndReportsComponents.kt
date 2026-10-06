package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.InterventionActionEntity
import com.example.data.ParentReportData
import com.example.data.PrincipalDashboardData
import com.example.data.StudentReportData
import com.example.data.TeacherDashboardData
import com.example.ui.DashboardPreviewTab
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.OpticTeal
import com.example.ui.theme.OpticTealLight
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceLensDesign

@Composable
fun UnifiedDashboardsShowcaseSection(
    activeTab: DashboardPreviewTab,
    onSelectTab: (DashboardPreviewTab) -> Unit,
    teacherData: TeacherDashboardData,
    principalData: PrincipalDashboardData,
    studentData: StudentReportData,
    parentData: ParentReportData,
    interventionActions: List<InterventionActionEntity>,
    onToggleInterventionStep: (String, Boolean) -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("dashboards_showcase_section")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DashboardPreviewTab.entries.forEach { tab ->
                val isSelected = tab == activeTab
                Surface(
                    color = if (isSelected) ScienceBlue else extColors.subtleSurface,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = if (isSelected) ScienceBlue else extColors.cardBorder,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onSelectTab(tab) }
                        .testTag("dashboard_tab_${tab.name.lowercase()}")
                ) {
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelLarge,
                        color = if (isSelected) PureWhite else extColors.textPrimary,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        when (activeTab) {
            DashboardPreviewTab.TEACHER -> TeacherDashboardMockupCard(
                data = teacherData,
                interventionActions = interventionActions,
                onToggleInterventionStep = onToggleInterventionStep
            )
            DashboardPreviewTab.PRINCIPAL -> PrincipalDashboardMockupCard(data = principalData)
            DashboardPreviewTab.STUDENT -> StudentReportMockupCard(data = studentData)
            DashboardPreviewTab.PARENT -> ParentReportMockupCard(data = parentData)
        }
    }
}

@Composable
fun TeacherDashboardMockupCard(
    data: TeacherDashboardData,
    interventionActions: List<InterventionActionEntity>,
    onToggleInterventionStep: (String, Boolean) -> Unit
) {
    val extColors = ScienceLensDesign.colors
    val completedKeys = interventionActions.filter { it.isCompleted }.map { it.stepKey }.toSet()

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
            .testTag("teacher_dashboard_card")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "TEACHER DIAGNOSTIC DASHBOARD",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.primaryAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = data.className,
                        style = MaterialTheme.typography.titleLarge,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = data.cycleLabel,
                        style = MaterialTheme.typography.bodySmall,
                        color = extColors.textMuted
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Surface(
                    color = DeepNavy,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.border(1.dp, OpticTeal, RoundedCornerShape(12.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.overallCompetency}%",
                            style = MaterialTheme.typography.headlineSmall,
                            color = OpticTealLight,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Overall",
                            style = MaterialTheme.typography.labelSmall,
                            color = PureWhite
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Competency Profile Breakdown (Class VIII-A)",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            CompetencyMetricRow("Conceptual Understanding", data.conceptualUnderstanding, extColors.successAccent)
            CompetencyMetricRow("Data Interpretation", data.dataInterpretation, extColors.primaryAccent)
            CompetencyMetricRow("Application", data.application, extColors.warningAccent)
            CompetencyMetricRow("Scientific Reasoning", data.scientificReasoning, extColors.warningAccent)

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = extColors.primaryAccentContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Diagnostic Insight",
                        tint = extColors.onPrimaryAccentContainer,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "CLASSROOM DIAGNOSTIC INSIGHT",
                            style = MaterialTheme.typography.labelSmall,
                            color = extColors.onPrimaryAccentContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "“Students show reasonable recall but struggle to apply concepts to unfamiliar situations.”",
                            style = MaterialTheme.typography.titleSmall,
                            color = extColors.onPrimaryAccentContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = data.coreInsight,
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Topics Needing Attention",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                data.topicsNeedingAttention.forEach { item ->
                    Surface(
                        color = extColors.subtleSurface,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = item.topic,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = extColors.textPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${item.chapter} • Gap: ${item.primaryGap}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = extColors.textMuted
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = extColors.warningContainer,
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "${item.competencyScore}% Mastery",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = extColors.onWarningContainer,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Observed Misconception: ${item.commonMisconception}",
                                style = MaterialTheme.typography.bodySmall,
                                color = extColors.textSecondary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = extColors.tealAccentContainer,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.tealAccent.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "SUGGESTED TEACHER INTERVENTION PLANNER",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onTealAccentContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = data.suggestedInterventionSummary,
                        style = MaterialTheme.typography.titleSmall,
                        color = extColors.onTealAccentContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    data.interventionComponents.forEach { (key, description) ->
                        val isChecked = completedKeys.contains(key)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onToggleInterventionStep(key, !isChecked) }
                                .padding(vertical = 6.dp, horizontal = 4.dp)
                                .testTag("intervention_step_$key"),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = if (isChecked) Icons.Default.CheckBox else Icons.Default.CheckBoxOutlineBlank,
                                contentDescription = if (isChecked) "Completed step" else "Mark step completed",
                                tint = if (isChecked) extColors.successAccent else extColors.onTealAccentContainer,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = description,
                                style = MaterialTheme.typography.bodySmall,
                                color = extColors.textPrimary,
                                fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompetencyMetricRow(
    label: String,
    percentage: Int,
    barColor: Color
) {
    val extColors = ScienceLensDesign.colors
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$percentage%",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = barColor
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        LinearProgressIndicator(
            progress = { percentage / 100f },
            color = barColor,
            trackColor = extColors.subtleSurface,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun PrincipalDashboardMockupCard(
    data: PrincipalDashboardData
) {
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
            .testTag("principal_dashboard_card")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "PRINCIPAL & ACADEMIC COORDINATOR DASHBOARD",
                style = MaterialTheme.typography.labelSmall,
                color = extColors.primaryAccent,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "School-Wide Science Learning Patterns (Classes VI–X)",
                style = MaterialTheme.typography.titleLarge,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Demonstration Data • Focused on competency growth & cohort patterns rather than individual student ranking",
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textMuted
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 2x2 Grid for KPI Summary Cards
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    KpiMetricBox(
                        title = "School Science Competency",
                        value = "${data.schoolCompetency}%",
                        subtitle = "Current Monthly Cycle",
                        accentColor = extColors.primaryAccent,
                        modifier = Modifier.weight(1f)
                    )
                    KpiMetricBox(
                        title = "Previous Month",
                        value = "${data.previousMonthCompetency}%",
                        subtitle = "September Cycle",
                        accentColor = extColors.textSecondary,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    KpiMetricBox(
                        title = "Monthly Improvement",
                        value = "+${data.changePoints} pp",
                        subtitle = "+5 percentage points",
                        accentColor = extColors.successAccent,
                        modifier = Modifier.weight(1f)
                    )
                    KpiMetricBox(
                        title = "Participation Rate",
                        value = "${data.participationRate}%",
                        subtitle = "Classes VI–X (${data.assessmentsCompletedCount} students)",
                        accentColor = extColors.tealAccent,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Monthly Competency Trend Chart (Jun – Oct)",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LegendDotItem("Conceptual", extColors.tealAccent)
                LegendDotItem("Overall School", extColors.primaryAccent)
                LegendDotItem("Application", extColors.warningAccent)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = extColors.subtleSurface,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    val gridColor = extColors.chartGridLine
                    val tealLine = extColors.tealAccent
                    val blueLine = extColors.primaryAccent
                    val amberLine = extColors.warningAccent

                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    ) {
                        val w = size.width
                        val h = size.height
                        val points = data.trendSeries
                        if (points.size >= 2) {
                            val stepX = w / (points.size - 1)
                            fun yForScore(score: Int): Float {
                                val normalized = ((score - 40).coerceIn(0, 60)) / 60f
                                return h - (normalized * (h - 24f)) - 12f
                            }

                            listOf(50, 60, 70, 80).forEach { gridScore ->
                                val gy = yForScore(gridScore)
                                drawLine(
                                    color = gridColor,
                                    start = Offset(0f, gy),
                                    end = Offset(w, gy),
                                    strokeWidth = 1.dp.toPx()
                                )
                            }

                            fun drawSeries(selector: (com.example.data.MonthlyTrendPoint) -> Int, color: Color) {
                                val path = Path()
                                points.forEachIndexed { idx, pt ->
                                    val px = idx * stepX
                                    val py = yForScore(selector(pt))
                                    if (idx == 0) path.moveTo(px, py) else path.lineTo(px, py)
                                }
                                drawPath(
                                    path = path,
                                    color = color,
                                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                                )
                                points.forEachIndexed { idx, pt ->
                                    val px = idx * stepX
                                    val py = yForScore(selector(pt))
                                    drawCircle(
                                        color = color,
                                        radius = 4.5.dp.toPx(),
                                        center = Offset(px, py)
                                    )
                                }
                            }

                            drawSeries({ it.conceptualScore }, tealLine)
                            drawSeries({ it.schoolCompetency }, blueLine)
                            drawSeries({ it.applicationScore }, amberLine)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        data.trendSeries.forEach { pt ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = pt.month,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = extColors.textSecondary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${pt.schoolCompetency}%",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = extColors.primaryAccent,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Topic & Competency Heatmap Across Classes VI–X",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(extColors.subtleSurface, RoundedCornerShape(12.dp))
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Topic Domain",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.textSecondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(2.1f)
                    )
                    listOf("VI", "VII", "VIII", "IX", "X").forEach { cls ->
                        Text(
                            text = cls,
                            style = MaterialTheme.typography.labelSmall,
                            color = extColors.textSecondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                HorizontalDivider(color = extColors.cardBorder)

                data.topicHeatmap.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = row.topicDomain,
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .weight(2.1f)
                                .padding(end = 6.dp)
                        )
                        listOf(row.classVI, row.classVII, row.classVIII, row.classIX, row.classX).forEach { score ->
                            val cellBg = when {
                                score >= 70 -> extColors.successContainer
                                score >= 63 -> extColors.primaryAccentContainer
                                else -> extColors.warningContainer
                            }
                            val cellText = when {
                                score >= 70 -> extColors.onSuccessContainer
                                score >= 63 -> extColors.onPrimaryAccentContainer
                                else -> extColors.onWarningContainer
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 2.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(cellBg)
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$score%",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = cellText,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Class-Wise Competency Comparison & Participation",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                data.classComparisons.forEach { item ->
                    Surface(
                        color = extColors.subtleSurface,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.sectionName,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = extColors.textPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Surface(
                                    color = extColors.tealAccentContainer,
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "${item.participationPercent}% Participation",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = extColors.onTealAccentContainer,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MetricPill("Overall Competency", "${item.overallScore}%", extColors.primaryAccent, Modifier.weight(1f))
                                MetricPill("Application", "${item.applicationScore}%", extColors.tealAccent, Modifier.weight(1f))
                                MetricPill("Reasoning", "${item.reasoningScore}%", extColors.warningAccent, Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LegendDotItem(label: String, color: Color) {
    val extColors = ScienceLensDesign.colors
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = extColors.textSecondary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun KpiMetricBox(
    title: String,
    value: String,
    subtitle: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    val extColors = ScienceLensDesign.colors
    Surface(
        color = extColors.subtleSurface,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = extColors.textSecondary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = accentColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = extColors.textMuted
            )
        }
    }
}

@Composable
private fun MetricPill(
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    val extColors = ScienceLensDesign.colors
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = extColors.textMuted
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StudentReportMockupCard(
    data: StudentReportData
) {
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
            .testTag("student_report_card")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(extColors.primaryAccentContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Student Report",
                        tint = extColors.onPrimaryAccentContainer
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "INDIVIDUAL STUDENT COMPETENCY REPORT",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.primaryAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${data.studentAlias} • ${data.className}",
                        style = MaterialTheme.typography.titleLarge,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = data.assessmentMonth,
                        style = MaterialTheme.typography.bodySmall,
                        color = extColors.textMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CompetencyMetricRow("Conceptual Understanding", data.conceptualUnderstanding, extColors.successAccent)
            CompetencyMetricRow("Data Interpretation", data.dataInterpretation, extColors.primaryAccent)
            CompetencyMetricRow("Application", data.application, extColors.tealAccent)
            CompetencyMetricRow("Scientific Reasoning", data.scientificReasoning, extColors.warningAccent)

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = extColors.successContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "STRENGTHS DEMONSTRATED",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onSuccessContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    data.strengths.forEach { item ->
                        Text(
                            text = "✓ $item",
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                color = extColors.warningContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "AREAS TO IMPROVE",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onWarningContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    data.areasToImprove.forEach { item ->
                        Text(
                            text = "• $item",
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                color = extColors.primaryAccentContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "NEXT LEARNING TARGET",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onPrimaryAccentContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = data.nextLearningTarget,
                        style = MaterialTheme.typography.bodyMedium,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun ParentReportMockupCard(
    data: ParentReportData
) {
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(18.dp))
            .testTag("parent_report_card")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(extColors.tealAccentContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FamilyRestroom,
                        contentDescription = "Parent Report",
                        tint = extColors.onTealAccentContainer
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "PARENT-FRIENDLY LEARNING SUMMARY",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.tealAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = data.childAlias,
                        style = MaterialTheme.typography.titleLarge,
                        color = extColors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = data.className,
                        style = MaterialTheme.typography.bodySmall,
                        color = extColors.textMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                color = extColors.subtleSurface,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, extColors.cardBorder, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = data.summaryHeadline,
                    style = MaterialTheme.typography.bodyMedium,
                    color = extColors.textPrimary,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Strengths (What Your Child Understands Well)",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.successAccent,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            data.whatYourChildDoesWell.forEach { point ->
                Text(
                    text = "✓ $point",
                    style = MaterialTheme.typography.bodySmall,
                    color = extColors.textPrimary,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Areas Requiring Attention",
                style = MaterialTheme.typography.titleSmall,
                color = extColors.warningAccent,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            data.areasRequiringAttention.forEach { point ->
                Text(
                    text = "• $point",
                    style = MaterialTheme.typography.bodySmall,
                    color = extColors.textPrimary,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                color = extColors.tealAccentContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "SUGGESTED SUPPORT AT HOME (SIMPLE CONVERSATIONS)",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.onTealAccentContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    data.suggestedSupportAtHome.forEachIndexed { idx, tip ->
                        Text(
                            text = "${idx + 1}. $tip",
                            style = MaterialTheme.typography.bodySmall,
                            color = extColors.textPrimary,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = data.teacherNote,
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.textSecondary
                    )
                }
            }
        }
    }
}
