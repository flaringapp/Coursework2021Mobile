package com.flaringapp.coursework2021.presentation.features.room.list.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.ViewHolderRoomBinding
import com.flaringapp.coursework2021.presentation.features.common.adapter.MutableListItemViewHolder
import com.flaringapp.coursework2021.presentation.features.room.list.models.RoomViewData
import com.flaringapp.coursework2021.presentation.utils.inflater
import com.flaringapp.coursework2021.presentation.utils.textWithVisibility
import com.flaringapp.coursework2021.presentation.utils.updateCompoundDrawablesWithIntrinsicBounds

class RoomViewHolder(
    private val binding: ViewHolderRoomBinding
) : MutableListItemViewHolder(binding.root) {

    companion object {
        private var positiveDrawable: Drawable? = null
        private var negativeDrawable: Drawable? = null

        fun create(parent: ViewGroup) = RoomViewHolder(
            ViewHolderRoomBinding.inflate(parent.inflater, parent, false)
        ).also {
            init(parent.context)
        }

        private fun init(context: Context) {
            if (positiveDrawable == null) {
                positiveDrawable = getDrawable(context, R.drawable.ic_done, R.color.success)
            }
            if (negativeDrawable == null) {
                negativeDrawable = getDrawable(context, R.drawable.ic_fail, R.color.error)
            }
        }

        private fun getDrawable(
            context: Context,
            @DrawableRes drawableRes: Int,
            @ColorRes color: Int
        ): Drawable {
            return ContextCompat.getDrawable(context, drawableRes)!!.apply {
                setTint(ContextCompat.getColor(context, color))
            }
        }
    }

    fun bind(item: RoomViewData, onOptionsClicked: (String) -> Unit) = with(binding) {
        textName.text = item.name
        textDescription.textWithVisibility = item.description

        layoutBoardAndBalcony.isVisible = item.hasBoard != null || item.hasBalcony != null
        setHasBoard(item.hasBoard)
        setHasBalcony(item.hasBalcony)

        textWorkplacesCount.text = item.workplacesCount
        textWindowCount.textWithVisibility = item.windowsCount

        textPrice.textWithVisibility = item.price

        textArea.textWithVisibility = item.area

        textRoomType.textWithVisibility = item.roomType

        buttonOptions.setOnClickListener { onOptionsClicked(item.id) }
    }

    override fun setIsEditable(isEditable: Boolean) {
        binding.buttonOptions.isClickable = isEditable
    }

    private fun setHasBoard(hasBoard: Boolean?) {
        binding.textHasBoard.setHasItem(
            hasBoard,
            false,
            R.string.message_has_board,
            R.string.message_no_board
        )
    }

    private fun setHasBalcony(hasBalcony: Boolean?) {
        binding.textHasBalcony.setHasItem(
            hasBalcony,
            true,
            R.string.message_has_balcony,
            R.string.message_no_balcony
        )
    }

    private fun TextView.setHasItem(hasItem: Boolean?, isIconStart: Boolean, hasItemText: Int, noItemText: Int) {
        binding.textHasBalcony.isInvisible = hasItem == null
        if (hasItem == null) return

        val textRes: Int
        val drawable: Drawable?

        if (hasItem) {
            textRes = hasItemText
            drawable = positiveDrawable
        } else {
            textRes = noItemText
            drawable = negativeDrawable
        }
        setText(textRes)

        if (isIconStart) {
            updateCompoundDrawablesWithIntrinsicBounds(
                start = drawable
            )
        } else {
            updateCompoundDrawablesWithIntrinsicBounds(
                end = drawable
            )
        }
    }

}