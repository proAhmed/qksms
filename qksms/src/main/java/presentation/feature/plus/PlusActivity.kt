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
import com.moez.QKSMS.R
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.kotlin.autoDisposable
import common.di.appComponent
import common.util.extensions.setBackgroundTint
import kotlinx.android.synthetic.main.qksms_plus_activity.*
import presentation.common.base.QkActivity

class PlusActivity : QkActivity<PlusViewModel>(), PlusView {

    override val viewModelClass = PlusViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qksms_plus_activity)
        setTitle(R.string.title_qksms_plus)
        viewModel.bindView(this)

        colors.background
                .autoDisposable(scope())
                .subscribe { color -> window.decorView.setBackgroundColor(color) }

        colors.theme
                .autoDisposable(scope())
                .subscribe { color -> upgrade.setBackgroundTint(color) }
    }

    override fun render(state: PlusState) {
        description.text = getString(R.string.qksms_plus_description_summary, state.supporterPrice)

        supporterPrice.text = state.supporterPrice
        donorPrice.text = state.donorPrice
        philanthropistPrice.text = state.philanthropistPrice
    }

}