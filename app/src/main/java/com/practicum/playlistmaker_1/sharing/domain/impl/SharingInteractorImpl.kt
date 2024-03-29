package com.practicum.playlistmaker_1.sharing.domain.impl

import com.practicum.playlistmaker_1.sharing.SharingInteractor
import com.practicum.playlistmaker_1.sharing.data.ExternalNavigator
import com.practicum.playlistmaker_1.sharing.domain.models.EmailData

class SharingInteractorImpl(
    private val externalNavigator: ExternalNavigator,
) : SharingInteractor {

    override fun shareApp() {
        externalNavigator.shareLink(getShareAppLink())
    }

    override fun openTerms() {
        externalNavigator.openLink(getTermsLink())
    }

    override fun openSupport() {
        externalNavigator.openEmail(getSupportEmailData())
    }

    override fun sharePlaylist(playlist: String) {
        externalNavigator.sharePlaylist(playlist)
    }

    private fun getShareAppLink(): String {
        return APP_LINK
    }

    private fun getSupportEmailData(): EmailData {
        return EmailData(mail = SUPPORT_EMAIL)
    }

    private fun getTermsLink(): String {
        return TERM_LINK
    }

    companion object {
        const val APP_LINK = "https://practicum.yandex.ru/android-developer"
        const val SUPPORT_EMAIL = "tchervladislav@yandex.ru"
        const val TERM_LINK = "https://yandex.ru/legal/practicum_offer"
    }
}