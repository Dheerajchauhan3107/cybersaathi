package com.dcdevelopers.cybersaathi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.ui.theme.*

enum class BottomTab {
    HOME, LEARN, PRACTICE, PROGRESS, PROFILE
}

@Composable
fun CyberBottomNavigation(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(BottomTab.HOME, "Home", Icons.Filled.Home, selectedTab, onTabSelected)
            NavItem(BottomTab.LEARN, "Learn", Icons.Filled.MenuBook, selectedTab, onTabSelected)
            NavItem(BottomTab.PRACTICE, "Practice", Icons.Filled.Science, selectedTab, onTabSelected)
            NavItem(BottomTab.PROGRESS, "Progress", Icons.Filled.EmojiEvents, selectedTab, onTabSelected)
            NavItem(BottomTab.PROFILE, "Profile", Icons.Filled.Person, selectedTab, onTabSelected)
        }
    }
}

@Composable
private fun NavItem(
    tab: BottomTab,
    label: String,
    icon: ImageVector,
    selectedTab: BottomTab,
    onSelect: (BottomTab) -> Unit
) {
    val isSelected = (tab == selectedTab)
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onSelect(tab) }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 38.dp, height = 24.dp)
                .clip(CircleShape)
                .background(if (isSelected) CyanCyber.copy(alpha = 0.2f) else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = if (isSelected) CyanCyber else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(Modifier.height(2.dp))

        Text(
            label,
            color = if (isSelected) CyanCyber else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
