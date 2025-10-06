package com.example.composeexamples.presentation.previewprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.composeexamples.presentation.viewmodel.UserInfo

class UserPreviewProvider : PreviewParameterProvider<UserInfo> {
    override val values = sequenceOf(
        UserInfo(
            id = 1,
            userName = "Alice Johnson",
            email = "alice.johnson@example.com"
        ),
        UserInfo(
            id = 2,
            userName = "Bob Smith",
            email = "bob.smith@example.com"
        ),
        UserInfo(
            id = 3,
            userName = "Catherine de la Cruz",
            email = "verylongemailaddress_catherine@example.com"
        )
    )
}
