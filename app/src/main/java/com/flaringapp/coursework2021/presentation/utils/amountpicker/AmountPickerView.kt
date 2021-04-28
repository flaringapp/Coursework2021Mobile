package com.flaringapp.coursework2021.presentation.utils.amountpicker

import android.content.Context
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import androidx.core.widget.TextViewCompat
import com.flaringapp.coursework2021.R
import com.flaringapp.coursework2021.databinding.ViewAmountPickerBinding
import com.flaringapp.coursework2021.presentation.utils.dp
import com.flaringapp.coursework2021.presentation.utils.dpi
import com.flaringapp.coursework2021.presentation.utils.sp
import kotlin.math.max

class AmountPickerView : ConstraintLayout {

    companion object {
        private const val DEFAULT_PADDING_HORIZONTAL = 12
        private const val DEFAULT_PADDING_VERTICAL = 4

        private const val DEFAULT_MIN_TEXT_WIDTH = 3

        private const val DEFAULT_MIN_AMOUNT = 1
        private const val DEFAULT_MAX_AMOUNT = Int.MAX_VALUE
        private const val DEFAULT_AMOUNT = 1

        private const val DEFAULT_AMOUNT_TEXT_MARGIN_HORIZONTAL = 16
        private const val DEFAULT_AMOUNT_TEXT_MARGIN_VERTICAL = 0

        private const val DEFAULT_AMOUNT_TEXT_COLOR = R.color.black
        private const val DEFAULT_AMOUNT_TEXT_SIZE = 24
        private val DEFAULT_AMOUNT_TEXT_FONT = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)

        private const val DEFAULT_BUTTON_SIZE = 32
        private const val DEFAULT_BUTTON_PADDING = 4
        private const val DEFAULT_BUTTON_MARGIN = 0
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    var minAmount: Int = DEFAULT_MIN_AMOUNT
        set(value) {
            field = value
            amountChangeSafeAction { it }
        }

    var maxAmount: Int = DEFAULT_MAX_AMOUNT
        set(value) {
            field = value
            amountChangeSafeAction { it }
        }

    var onAmountChange: ((Int) -> Unit)? = null

    private lateinit var binding: ViewAmountPickerBinding

    private var amount: Int = DEFAULT_AMOUNT
        private set(value) {
            if (field == value) return
            field = value

            binding.textAmount.text = value.toString()

            invalidateMaxTextSize()

            onAmountChange?.invoke(value)
        }


    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        initView()

        initAttributes(attrs, defStyleAttr, defStyleRes)

        invalidateMaxTextSize()
    }

    private fun initView() {
        View.inflate(context, R.layout.view_amount_picker, this)

        binding = ViewAmountPickerBinding.bind(this)

        if (background == null) {
            setBackgroundResource(R.drawable.selector_amount_picker)
        }

        binding.textAmount.text = amount.toString()

        binding.buttonMinus.setOnClickListener { onMinusClicked() }
        binding.buttonPlus.setOnClickListener { onPlusClicked() }

        val paddingHorizontal = context.dpi(DEFAULT_PADDING_HORIZONTAL)
        val paddingVertical = context.dpi(DEFAULT_PADDING_VERTICAL)
        setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)
    }

    private fun initAttributes(
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.AmountPickerView,
            defStyleAttr,
            defStyleRes
        )

        typedArray.getInt(R.styleable.AmountPickerView_amountMinValue, DEFAULT_MIN_AMOUNT).let {
            minAmount = it
        }

        typedArray.getInt(R.styleable.AmountPickerView_amountMaxValue, DEFAULT_MAX_AMOUNT).let {
            maxAmount = it
        }

        typedArray.getInt(R.styleable.AmountPickerView_amountDefaultValue, amount).let {
            amount = it
        }

        val marginHorizontal = typedArray.getDimension(
            R.styleable.AmountPickerView_amountTextMarginHorizontal,
            context.dp(DEFAULT_AMOUNT_TEXT_MARGIN_HORIZONTAL)
        ).toInt()

        val marginVertical = typedArray.getDimension(
            R.styleable.AmountPickerView_amountTextMarginVertical,
            context.dp(DEFAULT_AMOUNT_TEXT_MARGIN_VERTICAL)
        ).toInt()

        binding.textAmount.updateLayoutParams<LayoutParams> {
            leftMargin = marginHorizontal
            rightMargin = marginHorizontal
            topMargin = marginVertical
            bottomMargin = marginVertical
        }

        typedArray.getColor(
            R.styleable.AmountPickerView_amountTextColor,
            ContextCompat.getColor(context, DEFAULT_AMOUNT_TEXT_COLOR)
        ).let {
            binding.textAmount.setTextColor(it)
        }

        typedArray.getDimension(
            R.styleable.AmountPickerView_amountTextSize,
            context.sp(DEFAULT_AMOUNT_TEXT_SIZE)
        ).let {
            binding.textAmount.setTextSize(TypedValue.COMPLEX_UNIT_PX, it)
        }

        if (typedArray.hasValue(R.styleable.AmountPickerView_amountTextFontFamily)) {
            typedArray.getResourceId(
                R.styleable.AmountPickerView_amountTextFontFamily,
                0
            ).let { ResourcesCompat.getFont(context, it) }
        } else {
            DEFAULT_AMOUNT_TEXT_FONT
        }.let { binding.textAmount.typeface = it }

        typedArray.getResourceId(R.styleable.AmountPickerView_amountTextAppearance, -1)
            .takeIf { it != -1 }?.let {
                TextViewCompat.setTextAppearance(binding.textAmount, it)
            }

        typedArray.getDrawable(R.styleable.AmountPickerView_amountMinusButtonIcon)?.let {
            binding.buttonMinus.setImageDrawable(it)
        }

        typedArray.getDrawable(R.styleable.AmountPickerView_amountPlusButtonIcon)?.let {
            binding.buttonPlus.setImageDrawable(it)
        }

        typedArray.getDimension(
            R.styleable.AmountPickerView_amountButtonSize,
            context.dp(DEFAULT_BUTTON_SIZE)
        ).toInt().let { size ->
            buttonsForEach {
                it.layoutParams = it.layoutParams.apply {
                    width = size
                    height = size
                }
            }
        }

        typedArray.getDimension(
            R.styleable.AmountPickerView_amountButtonPadding,
            context.dp(DEFAULT_BUTTON_PADDING)
        ).toInt().let { padding ->
            buttonsForEach { it.setPadding(padding) }
        }

        typedArray.getDimension(
            R.styleable.AmountPickerView_amountButtonMargin,
            context.dp(DEFAULT_BUTTON_MARGIN)
        ).toInt().let { margin ->
            buttonsForEach {
                (it.layoutParams as MarginLayoutParams).setMargins(margin)
            }
        }

        typedArray.getColor(
            R.styleable.AmountPickerView_amountButtonTint,
            -1
        ).let { tint ->
            if (tint == -1) return@let
            buttonsForEach { it.setColorFilter(tint) }
        }

        typedArray.recycle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        binding.buttonPlus.isEnabled = enabled
        binding.buttonMinus.isEnabled = enabled

        val alpha = if (isEnabled) 1f else 0.25f
        binding.buttonPlus.alpha = alpha
        binding.buttonMinus.alpha = alpha
    }

    fun setSelectedAmount(amount: Int) {
        amountChangeSafeAction { amount }
    }

    private fun buttonsForEach(action: (ImageButton) -> Unit) {
        arrayOf(binding.buttonMinus, binding.buttonPlus).forEach { action(it) }
    }

    private fun onMinusClicked() {
        amountChangeSafeAction { it - 1 }
    }

    private fun onPlusClicked() {
        amountChangeSafeAction { it + 1 }
    }

    private fun numberSafeAction(action: (Int) -> Int): Int {
        action(amount).let {
            return when {
                it < minAmount -> minAmount
                it > maxAmount -> maxAmount
                else -> it
            }
        }
    }

    private fun amountChangeSafeAction(action: (Int) -> Int) {
        numberSafeAction(action).let {
            amount = it
        }
    }

    private var lastMeasuredLength = 0
    private fun invalidateMaxTextSize() {
        val currentMeasuredLength = max(amount.toString().length + 1, DEFAULT_MIN_TEXT_WIDTH)
        if (lastMeasuredLength == currentMeasuredLength) return
        lastMeasuredLength = currentMeasuredLength

        val textPaint = TextPaint()
        textPaint.textSize = binding.textAmount.textSize

        val sampleString = Array(currentMeasuredLength) { "8" }.joinToString(separator = "")

        binding.textAmount.minWidth = textPaint.measureText(sampleString).toInt()
    }
}