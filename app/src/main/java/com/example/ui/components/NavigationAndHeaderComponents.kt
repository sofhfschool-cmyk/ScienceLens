package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.UserRole
import com.example.data.WebsitePage
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.DeepNavyDark
import com.example.ui.theme.OpticTeal
import com.example.ui.theme.OpticTealBright
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ScienceBlue
import com.example.ui.theme.ScienceLensDesign
import com.example.ui.theme.SlateNavy

@Composable
fun ScienceLensTopNavbar(
    currentPage: WebsitePage,
    activeRole: UserRole,
    isDarkTheme: Boolean,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onToggleTheme: () -> Unit,
    onSelectPage: (WebsitePage) -> Unit,
    onOpenDirectoryDrawer: () -> Unit,
    onOpenRoleSelector: () -> Unit
) {
    val navbarLinks = listOf(
        WebsitePage.HOME,
        WebsitePage.HOW_IT_WORKS,
        WebsitePage.ASSESSMENT_FRAMEWORK,
        WebsitePage.FOR_SCHOOLS,
        WebsitePage.SAMPLE_REPORT,
        WebsitePage.FAQ,
        WebsitePage.CONTACT_DEMO,
        WebsitePage.FOR_TEACHERS,
        WebsitePage.STUDENTS_PARENTS,
        WebsitePage.ABOUT,
        WebsitePage.PRIVACY,
        WebsitePage.TERMS
    )

    Surface(
        color = DeepNavyDark,
        tonalElevation = 6.dp,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("top_navbar")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Primary Brand & Controls Row — mobile-optimized so nothing clips or wraps awkwardly
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left: Back Button + ScienceLens Brand Identity
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (canNavigateBack) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .size(38.dp)
                                .testTag("nav_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate back",
                                tint = PureWhite
                            )
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelectPage(WebsitePage.HOME) }
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                            .testTag("brand_logo_button")
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(9.dp))
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(ScienceBlue, OpticTeal)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = "ScienceLens Optical Emblem",
                                tint = PureWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Science",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = PureWhite,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                                Text(
                                    text = "Lens",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = OpticTealBright,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }
                            Text(
                                text = "See Beyond Marks",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF94A3B8),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                // Right: Theme Switcher + Role Pill + Request Demo + Menu Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Light / Dark Mode Switcher Pill
                    Surface(
                        color = SlateNavy,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .border(1.dp, Color(0xFF334155), RoundedCornerShape(18.dp))
                            .clip(RoundedCornerShape(18.dp))
                            .clickable { onToggleTheme() }
                            .testTag("theme_toggle_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                                tint = if (isDarkTheme) Color(0xFFFBBF24) else OpticTealBright,
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = if (isDarkTheme) "Light" else "Dark",
                                style = MaterialTheme.typography.labelSmall,
                                color = PureWhite,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                        }
                    }

                    // Role-Based Access Pill
                    Surface(
                        color = SlateNavy,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .border(1.dp, OpticTeal.copy(alpha = 0.65f), RoundedCornerShape(18.dp))
                            .clip(RoundedCornerShape(18.dp))
                            .clickable { onOpenRoleSelector() }
                            .testTag("role_switcher_pill")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(OpticTealBright)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = activeRole.label,
                                style = MaterialTheme.typography.labelSmall,
                                color = PureWhite,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                        }
                    }

                    // Request Demo CTA
                    Button(
                        onClick = { onSelectPage(WebsitePage.CONTACT_DEMO) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OpticTeal,
                            contentColor = PureWhite
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier
                            .heightIn(min = 34.dp)
                            .testTag("navbar_request_demo_cta")
                    ) {
                        Text(
                            text = "Demo",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }

                    // All 12 Pages Directory Menu Icon
                    IconButton(
                        onClick = onOpenDirectoryDrawer,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("open_all_pages_menu")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Open all 12 website pages menu",
                            tint = PureWhite
                        )
                    }
                }
            }

            // Secondary Horizontal Scrollable Navigation Strip (All 12 Pages)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DeepNavy)
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navbarLinks.forEach { page ->
                    val isSelected = currentPage == page
                    val isCta = page == WebsitePage.CONTACT_DEMO
                    Surface(
                        color = when {
                            isSelected -> ScienceBlue
                            isCta -> OpticTeal.copy(alpha = 0.25f)
                            else -> SlateNavy.copy(alpha = 0.65f)
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = when {
                                    isSelected -> ScienceBlue
                                    isCta -> OpticTealBright.copy(alpha = 0.6f)
                                    else -> Color(0xFF334155)
                                },
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onSelectPage(page) }
                            .testTag("nav_tab_${page.name.lowercase()}")
                    ) {
                        Text(
                            text = page.shortLabel,
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isSelected) PureWhite else Color(0xFFE2E8F0),
                            fontWeight = if (isSelected || isCta) FontWeight.Bold else FontWeight.Medium,
                            maxLines = 1,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RoleBasedContextBanner(
    activeRole: UserRole,
    onSelectRole: (UserRole) -> Unit,
    onJumpToRoleDestination: (UserRole) -> Unit
) {
    val extColors = ScienceLensDesign.colors

    Card(
        colors = CardDefaults.cardColors(containerColor = extColors.cardBackground),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, extColors.cardBorder, RoundedCornerShape(16.dp))
            .testTag("role_context_banner")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = "Role-Based Access",
                        tint = extColors.tealAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ROLE VIEW • ${activeRole.badgeTitle.uppercase()}",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.tealAccent,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                UserRole.entries.forEach { role ->
                    val selected = role == activeRole
                    Surface(
                        color = if (selected) ScienceBlue else extColors.subtleSurface,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = if (selected) ScienceBlue else extColors.cardBorder,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onSelectRole(role) }
                            .testTag("role_chip_${role.name.lowercase()}")
                    ) {
                        Text(
                            text = role.label,
                            style = MaterialTheme.typography.labelMedium,
                            color = if (selected) PureWhite else extColors.textPrimary,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                            maxLines = 1,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = activeRole.description,
                style = MaterialTheme.typography.bodySmall,
                color = extColors.textSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = extColors.cardBorder)
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "ACTIVE PERMISSIONS SCOPE",
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.textMuted,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = activeRole.permissionsSummary,
                        style = MaterialTheme.typography.labelSmall,
                        color = extColors.primaryAccent,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Surface(
                    color = extColors.primaryAccentContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onJumpToRoleDestination(activeRole) }
                        .testTag("open_role_dashboard_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${activeRole.label} View",
                            style = MaterialTheme.typography.labelSmall,
                            color = extColors.onPrimaryAccentContainer,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = extColors.onPrimaryAccentContainer,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScienceLensFooter(
    onSelectPage: (WebsitePage) -> Unit
) {
    Surface(
        color = DeepNavyDark,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("site_footer")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = SlateNavy),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "SCIENCELENS CORE PHILOSOPHY",
                        style = MaterialTheme.typography.labelSmall,
                        color = OpticTealBright,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "“Marks tell you what students got right or wrong. ScienceLens helps schools understand why.”",
                        style = MaterialTheme.typography.titleMedium,
                        color = PureWhite,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "COMPLETE PLATFORM DIRECTORY (12 PAGES)",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF94A3B8),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WebsitePage.entries.forEach { page ->
                    Surface(
                        color = DeepNavy,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onSelectPage(page) }
                            .testTag("footer_link_${page.name.lowercase()}")
                    ) {
                        Text(
                            text = page.title,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFFE2E8F0),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Security & Privacy",
                    tint = OpticTealBright,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "HTTPS Encrypted • Role-Based Access • Minimal Student Data • No Public Student Profiles • No Unconsented AI Training",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFCBD5E1)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Curriculum Compatibility Notice: ScienceLens maps assessment items to CBSE, ICSE, and selected State Board curricula for Classes VI–X using an independent competency framework. ScienceLens is an independent school assessment platform and does not claim official board endorsement or government affiliation. All analytics and student profiles shown are demonstration data for school evaluation.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8)
            )
        }
    }
}
