/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
 *
 * This file is part of QKSMS.
 *
 * QKSMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QKSMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QKSMS.  If not, see <http://www.gnu.org/licenses/>.
 */
package presentation.feature.plus

import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import com.moez.QKSMS.R
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.kotlin.autoDisposable
import common.di.appComponent
import common.util.BillingManager
import common.util.extensions.setBackgroundTint
import kotlinx.android.synthetic.main.qksms_plus_activity.*
import presentation.common.base.QkActivity

class PlusActivity : QkActivity<PlusViewModel>(), PlusView {

    override val viewModelClass = PlusViewModel::class
    override val supporterSelectedIntent by lazy { supporter.clicks() }
    override val donorSelectedIntent by lazy { donor.clicks() }
    override val philanthropistSelectedIntent by lazy { philanthropist.clicks() }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qksms_plus_activity)
        setTitle(R.string.title_qksms_plus)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.bindView(this)

        colors.textPrimary
                .autoDisposable(scope())
                .subscribe { color ->
                    collapsingToolbar.setCollapsedTitleTextColor(color)
                    collapsingToolbar.setExpandedTitleColor(color)
                }

        colors.background
                .autoDisposable(scope())
                .subscribe { color -> window.decorView.setBackgroundColor(color) }

        colors.theme
                .autoDisposable(scope())
                .subscribe { color -> upgrade.setBackgroundTint(color) }
    }

    override fun render(state: PlusState) {
        description.text = getString(R.string.qksms_plus_description_summary, state.supporterPrice)

        supporter.isSelected = state.selectedPlan == BillingManager.SKU_3
        supporterPrice.text = state.supporterPrice

        donor.isSelected = state.selectedPlan == BillingManager.SKU_5
        donorPrice.text = state.donorPrice

        philanthropist.isSelected = state.selectedPlan == BillingManager.SKU_10
        philanthropistPrice.text = state.philanthropistPrice
    }

}