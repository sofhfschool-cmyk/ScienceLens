package com.example.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ScienceLens Core Brand Palette: Deep Navy + Blue + Teal + White + Light Grey
val DeepNavy = Color(0xFF0F172A)
val DeepNavyDark = Color(0xFF09111E)
val SlateNavy = Color(0xFF1E293B)
val SlateNavyElevated = Color(0xFF26354D)

val ScienceBlue = Color(0xFF2563EB)
val ScienceBlueDark = Color(0xFF1D4ED8)
val ScienceBlueBright = Color(0xFF60A5FA)
val ScienceBlueLight = Color(0xFFDBEAFE)

val OpticTeal = Color(0xFF0D9488)
val OpticTealDark = Color(0xFF0F766E)
val OpticTealBright = Color(0xFF2DD4BF)
val OpticTealLight = Color(0xFFCCFBF1)

// Neutral & Surface Tones (Light)
val PureWhite = Color(0xFFFFFFFF)
val OffWhiteSurface = Color(0xFFF8FAFC)
val LightGreySurface = Color(0xFFF1F5F9)
val BorderSlate = Color(0xFFE2E8F0)
val TextPrimaryNavy = Color(0xFF0F172A)
val TextSecondarySlate = Color(0xFF475569)
val TextMutedSlate = Color(0xFF64748B)

// Semantic Status / Diagnostic Colors
val DiagnosticAmber = Color(0xFFD97706)
val DiagnosticAmberBright = Color(0xFFFBBF24)
val DiagnosticAmberLight = Color(0xFFFEF3C7)

val DiagnosticCoral = Color(0xFFE11D48)
val DiagnosticCoralBright = Color(0xFFFB7185)
val DiagnosticCoralLight = Color(0xFFFFE4E6)

val CompetencyEmerald = Color(0xFF059669)
val CompetencyEmeraldBright = Color(0xFF34D399)
val CompetencyEmeraldLight = Color(0xFFD1FAE5)

@Immutable
data class ScienceLensExtendedColors(
    val isDark: Boolean,
    val cardBackground: Color,
    val subtleSurface: Color,
    val elevatedNavySurface: Color,
    val cardBorder: Color,
    val primaryAccent: Color,
    val primaryAccentContainer: Color,
    val onPrimaryAccentContainer: Color,
    val tealAccent: Color,
    val tealAccentContainer: Color,
    val onTealAccentContainer: Color,
    val successAccent: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warningAccent: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val dangerAccent: Color,
    val dangerContainer: Color,
    val onDangerContainer: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val chartGridLine: Color
)

val LightScienceLensColors = ScienceLensExtendedColors(
    isDark = false,
    cardBackground = PureWhite,
    subtleSurface = Color(0xFFF1F5F9),
    elevatedNavySurface = DeepNavy,
    cardBorder = Color(0xFFE2E8F0),
    primaryAccent = ScienceBlue,
    primaryAccentContainer = Color(0xFFEFF6FF),
    onPrimaryAccentContainer = Color(0xFF1E3A8A),
    tealAccent = OpticTeal,
    tealAccentContainer = Color(0xFFF0FDFA),
    onTealAccentContainer = Color(0xFF134E4A),
    successAccent = CompetencyEmerald,
    successContainer = Color(0xFFECFDF5),
    onSuccessContainer = Color(0xFF065F46),
    warningAccent = DiagnosticAmber,
    warningContainer = Color(0xFFFFFBEB),
    onWarningContainer = Color(0xFF92400E),
    dangerAccent = DiagnosticCoral,
    dangerContainer = Color(0xFFFFF1F2),
    onDangerContainer = Color(0xFF9F1239),
    textPrimary = TextPrimaryNavy,
    textSecondary = TextSecondarySlate,
    textMuted = TextMutedSlate,
    chartGridLine = Color(0xFFCBD5E1)
)

val DarkScienceLensColors = ScienceLensExtendedColors(
    isDark = true,
    cardBackground = Color(0xFF131F33),
    subtleSurface = Color(0xFF1E2D45),
    elevatedNavySurface = Color(0xFF17253B),
    cardBorder = Color(0xFF2B3D59),
    primaryAccent = ScienceBlueBright,
    primaryAccentContainer = Color(0xFF172B4D),
    onPrimaryAccentContainer = Color(0xFFDBEAFE),
    tealAccent = OpticTealBright,
    tealAccentContainer = Color(0xFF113837),
    onTealAccentContainer = Color(0xFFCCFBF1),
    successAccent = CompetencyEmeraldBright,
    successContainer = Color(0xFF0D3327),
    onSuccessContainer = Color(0xFFD1FAE5),
    warningAccent = DiagnosticAmberBright,
    warningContainer = Color(0xFF3B280C),
    onWarningContainer = Color(0xFFFEF3C7),
    dangerAccent = DiagnosticCoralBright,
    dangerContainer = Color(0xFF3D1422),
    onDangerContainer = Color(0xFFFFE4E6),
    textPrimary = Color(0xFFF8FAFC),
    textSecondary = Color(0xFFCBD5E1),
    textMuted = Color(0xFF94A3B8),
    chartGridLine = Color(0xFF334155)
)

val LocalScienceLensColors = staticCompositionLocalOf { LightScienceLensColors }

object ScienceLensDesign {
    val colors: ScienceLensExtendedColors
        @Composable
        get() = LocalScienceLensColors.current
}
