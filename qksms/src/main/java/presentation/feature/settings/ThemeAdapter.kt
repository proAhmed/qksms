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
package presentation.feature.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.moez.QKSMS.R
import common.util.extensions.setBackgroundTint
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.theme_list_item.view.*
import kotlinx.android.synthetic.main.theme_palette_list_item.view.*
import presentation.common.base.QkAdapter
import presentation.common.base.QkViewHolder
import javax.inject.Inject

class ThemeAdapter @Inject constructor(private val context: Context) : QkAdapter<List<Int>>() {

    val colorSelected: Subject<Int> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QkViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.theme_palette_list_item, parent, false)

        view.palette.run {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
        }

        return QkViewHolder(view)
    }

    override fun onBindViewHolder(holder: QkViewHolder, position: Int) {
        val palette = getItem(position)
        val view = holder.itemView

        view.palette.removeAllViews()

        // TODO this is hardcoded and bad but all palettes are hardcoded to have 10 colors, so it's fine for now
        (palette.subList(0, 5) + palette.subList(5, 10).reversed())
                .mapIndexed { index, color ->
                    LayoutInflater.from(context).inflate(R.layout.theme_list_item, view.palette, false).apply {
                        theme.setBackgroundTint(color)
                        setOnClickListener { colorSelected.onNext(color) }

                        (layoutParams as FlexboxLayout.LayoutParams).run {
                            isWrapBefore = index % 5 == 0
                            flexGrow = 5f
                        }
                    }
                }
                .forEach { theme -> view.palette.addView(theme) }
    }

}