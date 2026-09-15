package com.ajantha.quiz.presentation.home.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajantha.quiz.core.session.User
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.bio
import quiz.shared.generated.resources.country
import quiz.shared.generated.resources.email
import quiz.shared.generated.resources.phone

@Composable
fun ProfileContent(
    user: User
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileHeader(user = user)

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ProfileInfoRow(
                    icon = Icons.Default.Email,
                    title = stringResource(Res.string.email),
                    value = user.email
                )

                ProfileInfoRow(
                    icon = Icons.Default.Phone,
                    title = stringResource(Res.string.phone),
                    value = user.phone
                )

                ProfileInfoRow(
                    icon = Icons.Default.Language,
                    title = stringResource(Res.string.country),
                    value = user.country
                )

                ProfileInfoRow(
                    icon = Icons.Default.Info,
                    title = stringResource(Res.string.bio),
                    value = user.bio
                )
            }
        }
    }
}