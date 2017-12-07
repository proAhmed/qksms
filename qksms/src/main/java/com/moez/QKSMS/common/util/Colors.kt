package com.moez.QKSMS.common.util

import android.content.Context
import com.moez.QKSMS.R
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Colors @Inject constructor(context: Context, prefs: Preferences) {

    val theme: Observable<Int> = prefs.theme.asObservable()
            .distinctUntilChanged()

    val statusBar: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.statusBarDark else R.color.statusBarLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val toolbarColor: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.toolbarDark else R.color.toolbarLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val background: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.backgroundDark else R.color.white }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val field: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.fieldDark else R.color.fieldLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val composeBackground: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.backgroundDark else R.color.backgroundLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val separator: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.separatorDark else R.color.separatorLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val textPrimary: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.textPrimaryDark else R.color.textPrimary }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val textSecondary: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.textSecondaryDark else R.color.textSecondary }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val textTertiary: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.textTertiaryDark else R.color.textTertiary }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    // TODO make this result depend on the theme color
    val textPrimaryOnTheme: Observable<Int> = prefs.theme.asObservable()
            .map { theme -> R.color.textPrimaryDark }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    // TODO make this result depend on the theme color
    val textSecondaryOnTheme: Observable<Int> = prefs.dark.asObservable()
            .map { theme -> R.color.textSecondaryDark }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    // TODO make this result depend on the theme color
    val textTertiaryOnTheme: Observable<Int> = prefs.dark.asObservable()
            .map { theme -> R.color.textTertiaryDark }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val bubble: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.bubbleDark else R.color.bubbleLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val switchThumbEnabled: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.switchThumbEnabledDark else R.color.switchThumbEnabledLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val switchThumbDisabled: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.switchThumbDisabledDark else R.color.switchThumbDisabledLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val switchTrackEnabled: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.switchTrackEnabledDark else R.color.switchTrackEnabledLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

    val switchTrackDisabled: Observable<Int> = prefs.dark.asObservable()
            .map { dark -> if (dark) R.color.switchTrackDisabledDark else R.color.switchTrackDisabledLight }
            .map { res -> context.resources.getColor(res) }
            .distinctUntilChanged()

}